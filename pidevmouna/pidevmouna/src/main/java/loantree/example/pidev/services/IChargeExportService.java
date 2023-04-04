package loantree.example.pidev.services;


import loantree.example.pidev.Entities.Charge;

import java.io.OutputStream;
import java.util.List;

public interface IChargeExportService {
    public void exportChargesToExcel(List<Charge> charges, OutputStream outputStream);
}
