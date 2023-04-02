package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;
import loantree.example.pidev.repository.CreditRepository;
import loantree.example.pidev.repository.RefundRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
public class RefundServiceImpl implements RefundService {

    @Autowired
    RefundRepository RepRefund ;
    CreditRepository creditRepository;


    @Override
    public Refund findById(Integer id) {
        return RepRefund.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found with id " + id));
    }

    @Override
    public void DeleteRefund(Integer idRefund) {
        RepRefund.deleteById(idRefund);
    }

    @Override
    public void deleteAllRefunds() {
        RepRefund.deleteAll();
    }

    @Override
    public List<Refund> getALLRefund() {
        return (List<Refund>) RepRefund.findAll();
    }


    @Override
    public double getMonthly_Payment(Refund r) {
        List<Refund> refunds=RepRefund.findAll();
        double g=0;
        Credit l =r.getCredit();
        g= (l.getAmount()*(l.getInterest_rate()/12)) / (1 - Math.pow(1+ (l.getInterest_rate()/12),(-l.getDuration())));
        return g;

    }
}