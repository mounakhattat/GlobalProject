package com.example.station_ski.services;

import com.example.station_ski.entities.Accounting;
import com.example.station_ski.repositories.AccountingRepository;
import com.example.station_ski.repositories.ChargeRepository;
import com.example.station_ski.repositories.RevenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class AccountingService implements IAccountingService{



    @Autowired

    private AccountingRepository accountingRepository;
 @Autowired
private RevenueRepository revenueRepository;
 @Autowired
private ChargeRepository chargeRepository;


    @Override
    public List<Accounting> retrieveAllAccountings() {

        return accountingRepository.findAll();
    }

    @Override
    public Accounting addAccounting(Accounting e) {
        return accountingRepository.save(e);
    }

    @Override
    public Accounting updateAccounting(Accounting e) {
        return accountingRepository.save(e);
    }


    @Override
    public Accounting retrieveAccounting(Integer idAccounting) {
        return accountingRepository.findById(idAccounting).get();
    }
    @Override
    public void deleteAccounting(Integer idAccounting) {
        accountingRepository.deleteById(idAccounting);
    }
    @Override
    public double calculerChiffreAffaires(double totalCharges, double totalRevenues) {
        return totalRevenues - totalCharges;
    }
    @Override
    public Accounting getAccountingById(Integer idAccounting) {
        return accountingRepository.findById(idAccounting).orElse(null);
    }
    public Accounting getLatestAccounting() {
        return accountingRepository.findFirstByOrderByDateAccDesc();
    }
    public Accounting saveChiffreAffaires(double totalCharges, double totalRevenues) {
        double chiffreAffaires = calculerChiffreAffaires(totalCharges, totalRevenues);
        Accounting accounting = new Accounting(LocalDate.now(), "Calcul du chiffre d'affaires", totalCharges, totalRevenues);
        accounting.setChiffreAffaires(chiffreAffaires);
        return accountingRepository.save(accounting);
    }

}
