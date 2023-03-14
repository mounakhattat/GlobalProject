package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.repository.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService{

    @Autowired
    CreditRepository RepCredit ;


    @Override
    public Credit AddCredit(Credit c) {
        RepCredit.save(c);
        return c;
    }

    @Override
    public Credit UpdateCredit(Credit c) {
        RepCredit.save(c);
        return c;
    }

    @Override
    public void DeleteCredit(Credit c) {
        RepCredit.delete(c);

    }

    @Override
    public void DeleteCredit(Integer idCredit) {
        RepCredit.deleteById(idCredit);

    }

    @Override
    public List<Credit> getALLCredit() {
        return (List<Credit>) RepCredit.findAll();
    }
}
