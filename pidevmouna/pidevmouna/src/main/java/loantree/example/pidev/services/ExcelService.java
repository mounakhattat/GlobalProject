package loantree.example.pidev.services;

import com.lowagie.text.*;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import loantree.example.pidev.Entities.Charge;
import loantree.example.pidev.repository.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import com.lowagie.text.*;
import com.lowagie.text.Font;

import static java.awt.Color.PINK;

public class ExcelService {


    @Autowired
    ChargeRepository chargeRepository;
    Charge acc ;
    public ExcelService(Charge acc) {
        this.acc = acc;
    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PINK);
        cell.setPadding(5);
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
        cell.setPhrase(new Phrase("Type charge", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Somme charge", font));
        table.addCell(cell);
      ;
        // cell.setPhrase(new Phrase("openDate", font));
        //  table.addCell(cell);
    }
    private void writeTableData(PdfPTable table) {

        table.addCell(acc.getTypeCharge());
        table.addCell("total");
        //    String pattern = "yyyy-MM-dd";
        //     SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLACK);
        Paragraph p = new Paragraph("Extrait charges ", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2f, 2f, 2f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
