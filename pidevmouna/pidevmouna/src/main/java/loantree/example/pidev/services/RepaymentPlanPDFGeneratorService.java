package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;


@Service
public class RepaymentPlanPDFGeneratorService {

    private final CreditService creditService;

    public RepaymentPlanPDFGeneratorService(CreditService creditService) {
        this.creditService = creditService;
    }

    public ByteArrayInputStream generatePDF(Integer creditId) throws DocumentException {
        Credit credit = creditService.getCreditById(creditId);
        List<Refund> refunds = credit.getRefund();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();


        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraph = new Paragraph("", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
        fontBody.setSize(12);

        Paragraph paragraphIntro = new Paragraph("");
        paragraphIntro.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraphIntro);


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell = new PdfPCell(new Phrase("Due Date", fontBody));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Monthly Payment", fontBody));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Amortization", fontBody));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Interest", fontBody));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Remaining Balance", fontBody));
        table.addCell(cell);

        for (Refund refund : refunds) {
            cell = new PdfPCell(new Phrase(refund.getDue_Date().toString(), fontBody));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(refund.getMonthly_Payment().toString(), fontBody));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(refund.getAmortization().toString(), fontBody));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(refund.getInterest().toString(), fontBody));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(refund.getRemaining_Balence().toString(), fontBody));
            table.addCell(cell);
        }
        document.add(table);


        Paragraph paragraphClosing = new Paragraph("" , fontBody);  paragraphClosing.setSpacingAfter(10f);
        document.add(paragraphClosing);

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }


}
