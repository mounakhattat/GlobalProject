package loantree.example.pidev.controllers;

import loantree.example.pidev.services.RepaymentPlanPDFGeneratorService;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/credit")
public class PDFExportController {

    private final RepaymentPlanPDFGeneratorService pdfGeneratorService;

    public PDFExportController(RepaymentPlanPDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }


    @GetMapping("/pdf/generate/{creditId}")
    public void generatePDF(HttpServletResponse response, @PathVariable Integer creditId) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        ByteArrayInputStream bis = pdfGeneratorService.generatePDF(creditId);
        byte[] buf = new byte[1024];
        int len;
        while ((len = bis.read(buf)) > 0) {
            response.getOutputStream().write(buf, 0, len);
        }
        bis.close();
    }
}
