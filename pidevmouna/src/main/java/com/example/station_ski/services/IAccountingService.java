package com.example.station_ski.services;

import com.example.station_ski.entities.Accounting;


import java.util.List;

public interface IAccountingService {
    List<Accounting> retrieveAllAccountings();

    Accounting addAccounting(Accounting e);

    Accounting updateAccounting (Accounting e);

    Accounting retrieveAccounting (Integer accountingId);

    void deleteAccounting(Integer idAccounting);

    Accounting getAccountingById(Integer idAccounting);
    double calculerChiffreAffaires(double totalCharges, double totalRevenues);
}
