package com.example.station_ski.services;

import com.example.station_ski.entities.Charge;


import com.example.station_ski.entities.StatistiqueCharge;
import com.example.station_ski.repositories.AccountingRepository;
import com.example.station_ski.repositories.ChargeRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChargeService  implements IChargeService{
    @Autowired

    private ChargeRepository chargeRepository;




    @Override
    public List<Charge> retrieveAllCharges() {

        return chargeRepository.findAll();
    }
    @Override
    public List<Charge> getAllCharges() {
        return chargeRepository.findAll();
    }
    @Override
    public Charge addCharge(Charge e) {
        return chargeRepository.save(e);
    }

    @Override
    public Charge updateCharge(Charge e) {
        return chargeRepository.save(e);
    }


    @Override
    public Charge retrieveCharge(Integer idCharge) {
        return chargeRepository.findById(idCharge).get();
    }
    @Override
    public void deleteCharge(Integer idCharge) {
        chargeRepository.deleteById(idCharge);
    }

    public List<Charge> listAll() {
        return (List<Charge>) chargeRepository.findAll();
    }


 /*   public double calculateTotalCharge(LocalDate debutMois, LocalDate finMois) {
        return ;
    }*/
}
