package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.repository.RefundRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RefundServiceImpl implements RefundService {

    @Autowired
    RefundRepository RepRefund ;
    CreditRepository creditRepository;


    @Override
    public Refund AddRefund(Refund r,Integer id) {

        RepRefund.save(r);
        return r;
    }

    @Override
    public Refund UpdateRefund(Refund r) {
        return null;
    }

//    @Override
//    public Refund UpdateRefund(Refund r) {
//        return null;
//    }

    @Override
    public Refund UpdateRefund(Refund r, Integer id) {

        return r;
    }

    @Override
    public void DeleteRefund(Refund r) {
        RepRefund.delete(r);
    }

    @Override
    public void DeleteRefund(Integer idRefund) {
        RepRefund.deleteById(idRefund);
    }

    @Override
    public List<Refund> getALLRefund() {
        return (List<Refund>) RepRefund.findAll();
    }



}
