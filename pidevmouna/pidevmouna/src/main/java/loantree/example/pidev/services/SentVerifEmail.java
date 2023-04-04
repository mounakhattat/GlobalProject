package loantree.example.pidev.services;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SentVerifEmail {
	    @Autowired
	    private JavaMailSenderImpl emailSender;


	    public String sendHtmlEmail(String email) throws MessagingException {

	        MimeMessage message = emailSender.createMimeMessage();

            emailSender.setUsername("guermazikarim4@gmail.com"); //replace with your email address
            emailSender.setPassword("etrdtgyapzuzwepe"); //replace with your email password


	        boolean multipart = true;

	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");




	        String htmlMsg="\r\n" +
	                "<html>\n" +
	                "                      <body> \n" +
	                "                         <div align='center'>\n" +
	                "                            <div style='text-align:center;'>\n" +
	                "                             \n" +
	                "                           </div>\n" +
	                "   \n" +
	                "                            <br/><br/>\n" +
	                "                          	transaction done  \n" +
	                "                      </body>\n" +
	                "                   </html>\n";

	        message.setContent(htmlMsg, "text/html");
	        helper.setTo(email);

	        helper.setSubject("transaction Verification");


	        this.emailSender.send(message);

	        return "Email Sent!";
	    }
}
