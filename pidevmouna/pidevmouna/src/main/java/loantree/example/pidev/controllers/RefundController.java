package loantree.example.pidev.controllers;




import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.repository.RefundRepository;
import loantree.example.pidev.services.CreditService;
import loantree.example.pidev.services.RefundService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/credit")
@AllArgsConstructor
public class RefundController {

    @Autowired
    RefundService refundService;
    CreditService creditService;
    CreditRepository creditRepository;

    RefundRepository RepRefund;





    @GetMapping("/Refunds")
    @ResponseBody
    public List<Refund> getAllRefunds(){
        return refundService.getALLRefund();

    }



    @GetMapping("/Refund/{id}")
    public ResponseEntity<Refund> findById(@PathVariable Integer id) {
        Refund refund = refundService.findById(id);
        return ResponseEntity.ok(refund);
    }


    @DeleteMapping("deleteRefund/{id}")
    @ResponseBody
    public void deleteRefund(@PathVariable("id") Integer idRefund){
        refundService.DeleteRefund(idRefund);
    }


    @DeleteMapping("/allRefund")
    public void deleteAllRefunds() {
        refundService.deleteAllRefunds();
    }










}
