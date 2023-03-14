package loantree.example.pidev.controllers;



import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.services.RefundService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RefundController {

    @Autowired
    RefundService refundService;

    @GetMapping("/Refunds")
    @ResponseBody
    public List<Refund> getAllRefunds(){
        List<Refund> list= refundService.getALLRefund();
        return list;
    }
    /*GetMapping("/getm")
    @ResponseBody
    public double getMensionalite(){
        return refundService.getMensionalite();
    }*/

    @PostMapping("/AddRefund/{id}")
    @ResponseBody
    public Refund AddRefund(@RequestBody Refund r,@PathVariable("id")Integer id){
        return refundService.AddRefund(r, id);
    }

    @PutMapping("/UpdateRefund/{id}")
    @ResponseBody
    public Refund UpdateRefund(@RequestBody Refund r,@PathVariable("id")Integer id){
        return refundService.UpdateRefund(r, id);
    }

    @DeleteMapping("deleteRefund/{id}")
    @ResponseBody
    public void deleteRefund(@PathVariable("id") Integer idRefund){
        refundService.DeleteRefund(idRefund);
    }

}
