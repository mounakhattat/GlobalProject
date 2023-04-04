package loantree.example.pidev.repository;

import loantree.example.pidev.Entities.Payment;
import loantree.example.pidev.Entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


    List<Payment> findByRefund(Refund refund);

}
