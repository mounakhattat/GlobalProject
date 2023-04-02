package loantree.example.pidev.controllers;



import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.repository.RefundRepository;
import loantree.example.pidev.services.CreditService;
import loantree.example.pidev.services.RefundService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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


    @GetMapping("/MonthlyPaymentById/{id}")
    public ResponseEntity<Double> getMonthlyPaymentById(@PathVariable("id") Integer idCredit) {
        Optional<Credit> credit = creditRepository.findById(idCredit);
        if (credit.isPresent()) {
            Credit c = credit.get();
            List<Refund> refunds = c.getRefund();
            double g = 0;
            g = (c.getAmount() * (c.getInterest_rate() / 12)) / (1 - Math.pow(1 + (c.getInterest_rate() / 12), (-c.getDuration())));
            return new ResponseEntity<>(g, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }








}
