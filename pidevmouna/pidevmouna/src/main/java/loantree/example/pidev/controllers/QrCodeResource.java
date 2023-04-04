package loantree.example.pidev.controllers;

import loantree.example.pidev.services.QrCodeService;
import com.google.zxing.NotFoundException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/credit")
public class QrCodeResource {

    @Autowired
    private QrCodeService qrCodeService;



    @GetMapping(value = "/api/qr/generate/{creditId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> generateQrCode(@PathVariable Integer creditId) throws IOException, WriterException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            qrCodeService.generateQr(creditId, outputStream);
        } catch (com.google.zxing.WriterException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=qr-code.jpg");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray())));
    }


    @GetMapping("/decode")
    public ResponseEntity<String> decodeQrCode(@RequestParam("image") MultipartFile file) {
        try {
            String result = qrCodeService.decodeQr(file.getBytes());
            return ResponseEntity.ok(result);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to decode QR code.");
        }
    }

}



