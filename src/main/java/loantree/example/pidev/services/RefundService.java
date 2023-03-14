package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Refund;

import java.util.Date;
import java.util.List;

public interface RefundService {


    Refund AddRefund(Refund r,Integer id ) ;
    Refund UpdateRefund (Refund r);

    Refund UpdateRefund(Refund r, Integer id);

    void DeleteRefund(Refund r);
    void DeleteRefund(Integer idRefund) ;
    List<Refund> getALLRefund();

}
