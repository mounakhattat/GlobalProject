package loantree.example.pidev.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Status_Credit;
import loantree.example.pidev.services.CreditExcelGeneratorService;
import loantree.example.pidev.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loantree.example.pidev.services.RepaymentPlanExcelGeneratorService;


@RestController
@RequestMapping("/credit/api/excel")
public class ExcelExportController {

    @Autowired
    private RepaymentPlanExcelGeneratorService repaymentPlanExcelGeneratorService;

    @Autowired
    private CreditService creditService;


    @GetMapping("/plan-de-remboursement/{creditId}")
    public ResponseEntity<InputStreamResource> generateExcel(@PathVariable Integer creditId, HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=plan-de-remboursement.xlsx");

        ByteArrayInputStream bis = repaymentPlanExcelGeneratorService.generateExcel(creditId);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(bis));
    }



    @GetMapping(value = "/credits/export/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> exportToExcel() throws IOException {
        List<Credit> credits = creditService.getALLCredit();

        List<Credit> confirmedCredits = credits.stream()
                .filter(credit -> credit.getStatus_credit() == Status_Credit.CONFIRME)
                .collect(Collectors.toList());

        CreditExcelGeneratorService creditExcelGeneratorService = new CreditExcelGeneratorService();
        ByteArrayInputStream excel = creditExcelGeneratorService.generateExcel(confirmedCredits);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=credits.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(excel));
    }


}


