package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Charge;

import java.util.List;

public interface IChargeService {
    List<Charge> retrieveAllCharges();

    Charge addCharge(Charge e);

    Charge updateCharge (Charge e);

    Charge retrieveCharge (Integer chargeId);

    void deleteCharge(Integer idCharge);

    public List<Charge> getAllCharges();


    public List<Charge> listAll();
}
