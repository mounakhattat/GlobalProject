package loantree.example.pidev.services;

import loantree.example.pidev.Entities.EmailMessage;

import javax.mail.MessagingException;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);



}
