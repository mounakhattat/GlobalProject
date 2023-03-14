package loantree.example.pidev.controllers;


import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.services.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping("/Credits")
    @ResponseBody
    public List<Credit> getAllCredits(){
        List<Credit> list= creditService.getALLCredit();
        return list;
    }

    @PostMapping("/AddCredit")
    @ResponseBody
    public Credit AddCredit(@RequestBody Credit c){
        return creditService.AddCredit(c);
    }

    @PutMapping("/UpdateCredit")
    @ResponseBody
    public Credit UpdateCredit(@RequestBody Credit c){
        return creditService.UpdateCredit(c);
    }

    @DeleteMapping("deleteCredit/{id}")
    @ResponseBody
    public void deleteCredit(@PathVariable("id") Integer idCredit){

        creditService.DeleteCredit(idCredit);
    }

}
