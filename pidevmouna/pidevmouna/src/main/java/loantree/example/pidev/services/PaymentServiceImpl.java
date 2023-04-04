package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Payment;
import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.repository.PaymentRepository;
import loantree.example.pidev.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RefundRepository RepRefund;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPaymentsByRefund(Refund refund) {
        return paymentRepository.findByRefund(refund);
    }


    @Override
    public List<Payment> addPayment(Integer refundId, Payment payement) {
        Refund refund = RepRefund.findById(refundId).orElseThrow(() -> new RuntimeException("Refund not found with id " + refundId));
        payement.setRefund(refund);
        paymentRepository.save(payement);
        return getPaymentsByRefund(refund);
    }


    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }



}