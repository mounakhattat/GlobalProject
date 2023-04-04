package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Credit;
import com.itextpdf.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class CreditExcelGeneratorService {



    public ByteArrayInputStream generateExcel(List<Credit> credits) throws IOException, java.io.IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Credits");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID de crédit");
        headerRow.createCell(1).setCellValue("Type de crédit");
        headerRow.createCell(2).setCellValue("Date de début");
        headerRow.createCell(3).setCellValue("Date de fin");
        headerRow.createCell(4).setCellValue("Taux d'intérêt");
        headerRow.createCell(5).setCellValue("Statut du crédit");
        headerRow.createCell(6).setCellValue("Durée");
        headerRow.createCell(7).setCellValue("Montant");
        headerRow.createCell(8).setCellValue("Score");

        int rowNum = 1;
        for(Credit credit : credits) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(credit.getIdCredit());
            row.createCell(1).setCellValue(credit.getType_credit().toString());
            row.createCell(2).setCellValue(credit.getStart_date().toString());
            row.createCell(3).setCellValue(credit.getEnd_date().toString());
            row.createCell(4).setCellValue(credit.getInterest_rate());
            row.createCell(5).setCellValue(credit.getStatus_credit().toString());
            row.createCell(6).setCellValue(credit.getDuration());
            row.createCell(7).setCellValue(credit.getAmount());
            row.createCell(8).setCellValue(credit.getScore());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream;
    }








}
