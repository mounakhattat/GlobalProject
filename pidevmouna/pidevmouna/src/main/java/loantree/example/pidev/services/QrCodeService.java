package loantree.example.pidev.services;

import loantree.example.pidev.Entities.Credit;
import loantree.example.pidev.Entities.Refund;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.google.gson.Gson;


import org.springframework.beans.factory.annotation.Autowired;


@Service
public class QrCodeService {

    @Autowired
    private CreditService creditService;



    public void generateQr(Integer creditId, OutputStream outputStream) throws WriterException, IOException {
        Credit credit = creditService.getCreditById(creditId);
        List<Refund> refunds = credit.getRefund();

        BitMatrix bitMatrix = new QRCodeWriter().encode(refunds.toString(), BarcodeFormat.QR_CODE, 200, 200);
        MatrixToImageWriter.writeToStream(bitMatrix, "jpeg", outputStream );
    }



}