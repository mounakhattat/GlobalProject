package loantree.example.pidev.controllers;

import loantree.example.pidev.services.EmailServiceRR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EmailRayen")
    public class EmailControllerRR {

        @Autowired
        EmailServiceRR emailServiceRR;

        @GetMapping("/sendMail/{email}")
        public String sendEmail(@PathVariable(value = "email", required = true) String email)
        {
            System.out.println(email);
            return	emailServiceRR.sendEmail(email);

        }
    }