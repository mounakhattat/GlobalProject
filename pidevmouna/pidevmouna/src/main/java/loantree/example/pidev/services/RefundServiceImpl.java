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








}