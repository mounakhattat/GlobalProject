package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Credit;

import java.util.List;

public interface CreditService {
    Credit AddCredit(Credit c) ;
    Credit UpdateCredit (Credit c);
    void DeleteCredit(Credit c);
    void DeleteCredit(Integer idCredit) ;
    List<Credit> getALLCredit();
}