package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Credit;





import java.util.List;


public interface CreditService {


    Credit AddCredit(Credit c);

    Credit updateCredit(Credit c);

    void deleteAllCredits();

    void DeleteCredit(Integer idCredit);

    List<Credit> getALLCredit();

    Credit getCreditById(Integer idCredit);

    int scoring(Integer idCredit, Integer id);


}
