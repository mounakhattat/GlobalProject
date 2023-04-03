package com.example.station_ski.services;

import com.example.station_ski.entities.Charge;

import java.io.OutputStream;
import java.util.List;

public interface IChargeExportService {
    public void exportChargesToExcel(List<Charge> charges, OutputStream outputStream);
}
