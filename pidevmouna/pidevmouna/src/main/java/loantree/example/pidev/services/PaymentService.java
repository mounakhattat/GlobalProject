package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Payment;
import loantree.example.pidev.Entities.Refund;

import java.util.List;


public interface PaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(Integer id);

    List<Payment> getPaymentsByRefund(Refund refund);


    List<Payment> addPayment(Integer refundId, Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(Integer id);


}