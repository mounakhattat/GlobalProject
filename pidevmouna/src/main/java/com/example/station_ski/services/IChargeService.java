package com.example.station_ski.services;

import com.example.station_ski.entities.Charge;

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
