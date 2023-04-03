package com.example.station_ski.controller;

import com.example.station_ski.Export.ExcelExport;
import com.example.station_ski.Export.PDFExport;
import com.example.station_ski.entities.Charge;
import com.example.station_ski.services.ChargeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/charge")

public class ChargeController {
@Autowired
        ChargeService chargeService;




    //http://localhost:8089/stationSki/moniteur/retrieve-all-moniteurs
    @GetMapping("/retrieve-all-charges")
    public List<Charge> getCharges() {
        return chargeService.retrieveAllCharges();
    }
    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @GetMapping("/retrieve-charges/{charge-id}")
    public Charge retrieveAllCharges(@PathVariable("charge-id") Integer chargeId) {
        return chargeService.retrieveCharge(chargeId);
    }
    // http://localhost:8089/stationSki/charge/add-charge
    @PostMapping("/add-charge")
    public Charge addCharge(@RequestBody Charge c) {
        return chargeService.addCharge(c);
    }
    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-charge/{charge-id}")
    public void removeCharge(@PathVariable("charge-id") Integer chargeId) {
        chargeService.deleteCharge(chargeId);
    }
    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-charge")
    public Charge updateCharge(@RequestBody Charge c) {
        return chargeService.updateCharge(c);
    }
    @GetMapping(value = "/export/PDF")
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {
        List<Charge> p = (List<Charge>) chargeService.getAllCharges();

        ByteArrayInputStream bis = PDFExport.chargePDFReport(p);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "Online; filename=TableOfUsers.pdf");

        return ResponseEntity.ok().headers(headers)
                .body(new InputStreamResource(bis));

    }
    @GetMapping(value = "/PDF")
    public ResponseEntity<InputStreamResource> chargePDFReport() throws IOException {
        List<Charge> p = (List<Charge>) chargeService.getAllCharges();

        ByteArrayInputStream bis = PDFExport.chargePDFReport(p);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "Online; filename=TableOfCharges.pdf");

        return ResponseEntity.ok().headers(headers)
                .body(new InputStreamResource(bis));

    }
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Charges_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Charge> charges = chargeService.getAllCharges();

        ExcelExport excelExporter = new ExcelExport(charges);

        excelExporter.export(response);
    }

}
