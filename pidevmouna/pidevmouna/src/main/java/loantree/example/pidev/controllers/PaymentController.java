package loantree.example.pidev.controllers;

import loantree.example.pidev.Entities.Payment;
import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }


    @GetMapping("/refund/{id}")
    public ResponseEntity<List<Payment>> getPaymentsByRefund(@PathVariable("id") Integer id) {
        Refund refund = new Refund();
        refund.setIdRefund(id);
        List<Payment> payments = paymentService.getPaymentsByRefund(refund);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }


    @PostMapping("/refund/{id}")
    public ResponseEntity<?> addPayment(@PathVariable("id") Integer refundId, @RequestBody Payment payment) {
        try {
            List<Payment> payments = paymentService.addPayment(refundId, payment);
            return new ResponseEntity<>(payments, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") Integer id, @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.getPaymentById(id);
        if (updatedPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedPayment.setDate_payment(payment.getDate_payment());
        updatedPayment.setStatus_ref(payment.getStatus_ref());
        updatedPayment.setPenalty(payment.getPenalty());
        updatedPayment.setAmount_pay(payment.getAmount_pay());
        Payment savedPayment = paymentService.updatePayment(updatedPayment);
        return new ResponseEntity<>(savedPayment, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
