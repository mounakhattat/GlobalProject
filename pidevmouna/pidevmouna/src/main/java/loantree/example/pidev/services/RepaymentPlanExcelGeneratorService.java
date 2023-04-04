package loantree.example.pidev.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;

@Service
public class RepaymentPlanExcelGeneratorService {

    private final CreditService creditService;

    public RepaymentPlanExcelGeneratorService(CreditService creditService) {
        this.creditService = creditService;
    }

    public ByteArrayInputStream generateExcel(Integer creditId) throws IOException {
        Credit credit = creditService.getCreditById(creditId);
        List<Refund> refunds = credit.getRefund();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Plan de remboursement");
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Date d'échéance");
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Paiement mensuel");
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Amortissement");
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Intérêts");
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Solde restant");
        int rowNum = 1;
        for (Refund refund : refunds) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(refund.getDue_Date().toString());
            row.createCell(1).setCellValue(refund.getMonthly_Payment());
            row.createCell(2).setCellValue(refund.getAmortization());
            row.createCell(3).setCellValue(refund.getInterest());
            row.createCell(4).setCellValue(refund.getRemaining_Balence());
        }
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
        workbook.write(out);
        workbook.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
