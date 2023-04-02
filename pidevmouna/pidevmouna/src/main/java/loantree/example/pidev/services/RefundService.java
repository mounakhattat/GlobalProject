package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Refund;


import java.util.List;

public interface RefundService {


    Refund findById(Integer id);

    void DeleteRefund(Integer idRefund);

    void deleteAllRefunds();

    List<Refund> getALLRefund();


    double getMonthly_Payment(Refund r);


}