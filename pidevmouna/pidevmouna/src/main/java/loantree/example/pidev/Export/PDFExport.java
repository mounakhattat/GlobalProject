package loantree.example.pidev.Export;




        import com.itextpdf.text.*;
        import com.itextpdf.text.pdf.PdfPCell;
        import com.itextpdf.text.pdf.PdfPTable;
        import com.itextpdf.text.pdf.PdfWriter;

        import loantree.example.pidev.Entities.Charge;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Service;

        import java.io.ByteArrayInputStream;
        import java.io.ByteArrayOutputStream;
        import java.util.List;
        import java.util.stream.Stream;
@Service
public class PDFExport  {


    private static Logger logger = LoggerFactory.getLogger(PDFExport.class);

    public static ByteArrayInputStream chargePDFReport
            (List<Charge> charges) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 17,
                    BaseColor.BLACK);
            Paragraph para = new Paragraph("  la liste des charges ", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(8);
            // Add PDF Table Header ->
            Stream.of("Id", "TypeCharge","SommeCharge").forEach(headerTitle ->
            {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.COURIER);
                header.setBackgroundColor(BaseColor.PINK);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);

            });

            for (Charge p : charges) {
                PdfPCell idCell = new PdfPCell(new Phrase((String.valueOf(p.getIdCharge()))));
                idCell.setPaddingLeft(8);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell FirstName = new PdfPCell(new Phrase
                        (p.getTypeCharge()));
                FirstName.setPaddingLeft(8);
                FirstName.setVerticalAlignment(Element.ALIGN_MIDDLE);
                FirstName.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(FirstName);





            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }


        return new ByteArrayInputStream(out.toByteArray());


    }

}