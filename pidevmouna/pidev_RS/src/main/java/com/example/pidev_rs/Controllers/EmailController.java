package com.example.pidev_rs.Controllers;

import com.example.pidev_rs.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
    public class EmailController {

        @Autowired
        EmailService emailService;

        @GetMapping("/sendMail/{email}")
        public String sendEmail(@PathVariable(value = "email", required = true) String email)
        {
            System.out.println(email);
            return	emailService.sendEmail(email);

        }
    }