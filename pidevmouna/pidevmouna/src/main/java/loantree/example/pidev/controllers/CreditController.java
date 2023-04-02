package loantree.example.pidev.controllers;


import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.services.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/credit")
@AllArgsConstructor
public class CreditController {

    @Autowired
    CreditService creditService;
    CreditRepository RepCredit;



    @GetMapping("/Credits")
    @ResponseBody
    public List<Credit> getAllCredits(){
        return creditService.getALLCredit();
    }


    @GetMapping("/Credit/{id}")
    @ResponseBody
    public ResponseEntity<Credit> getCreditById(@PathVariable("id") Integer idCredit) {
        Credit credit = creditService.getCreditById(idCredit);
        if (credit != null) {
            return new ResponseEntity<>(credit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/AddCredit")
    @ResponseBody
    public Credit AddCredit(@RequestBody Credit c){
        return creditService.AddCredit(c);
    }



    @PutMapping("/updateCredit/{id}")
    @ResponseBody
    public ResponseEntity<Credit> updateCredit(@PathVariable("id") Integer idCredit, @RequestBody Credit credit) {
        Credit updatedCredit = creditService.getCreditById(idCredit);
        updatedCredit.setAmount(credit.getAmount());
        updatedCredit.setDuration(credit.getDuration());
        updatedCredit.setEnd_date(credit.getEnd_date());
        updatedCredit.setStart_date(credit.getStart_date());
        updatedCredit.setInterest_rate(credit.getInterest_rate());
        updatedCredit.setStatus_credit(credit.getStatus_credit());
        updatedCredit.setType_credit(credit.getType_credit());
        creditService.updateCredit(updatedCredit);
        return new ResponseEntity<>(updatedCredit, HttpStatus.OK);
    }



    @DeleteMapping("/delete-all")
    @ResponseBody
    public ResponseEntity<String> deleteAllCredits() {
        creditService.deleteAllCredits();
        return new ResponseEntity<>("All credits have been deleted", HttpStatus.OK);
    }




    @DeleteMapping("deleteCredit/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCredit(@PathVariable("id") Integer idCredit){
        creditService.DeleteCredit(idCredit);
        return new ResponseEntity<>("Credit with ID " + idCredit + " has been deleted successfully", HttpStatus.OK);
    }



    @GetMapping("/users/{idUser}/credits/{idCredit}/scoring")
    public ResponseEntity<Map<String, Object>> scoring(@PathVariable("idUser") Integer idUser, @PathVariable("idCredit") Integer idCredit) {
        int score = creditService.scoring(idCredit, idUser);
        Map<String, Object> response = new HashMap<>();
        response.put("", "Le score pour ce crédit est " + score);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
