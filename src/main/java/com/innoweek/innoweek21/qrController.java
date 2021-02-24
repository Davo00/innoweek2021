package com.innoweek.innoweek21;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@CrossOrigin(origins = "*")
@RestController("/qr")
public class qrController {

    private double co2 = Math.random()*10;
    private String message = co2 + " Tonnen CO2e für eine Tafel";

    @RequestMapping(value = "/get",  produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<Object> sendQR() throws Exception {
        return new ResponseEntity<>(generateQRCodeImage(message), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/number")
    ResponseEntity<Object> sendCO2(){
        CO2Response co2Response = new CO2Response();
        co2Response.setCo2(co2);
        return new ResponseEntity<>(co2Response, HttpStatus.OK);
    }

    private static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
