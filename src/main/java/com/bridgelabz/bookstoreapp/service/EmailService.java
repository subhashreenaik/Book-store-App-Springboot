package com.bridgelabz.bookstoreapp.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Email;

@Service
public class EmailService {
	
	
	
	public ResponseEntity<ResponseDTO> sendMail(Email email) {
        final String fromEmail = "subhashree.naik@gmail.com";
        final String fromPwd = "bxxqyumikwqugvtu";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Authenticator authenticator = new Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication() {
              
                return new PasswordAuthentication(fromEmail, fromPwd);
            }
        };
        Session session = Session.getInstance(properties, authenticator);

        MimeMessage mail = new MimeMessage(session);

        try {
            mail.addHeader("Content-type", "text/HTML; charset=UTF-8");
            mail.addHeader("format", "flowed");
            mail.addHeader("Content-Transfer-Encoding", "8bit");

            mail.setFrom(new InternetAddress(fromEmail));
            mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            mail.setText(email.getBody(), "UTF-8");
            mail.setSubject(email.getSubject(), "UTF-8");

            Transport.send(mail);

            ResponseDTO responseDTO = new ResponseDTO(" Sent email ", mail, null);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ResponseDTO responseDTO = new ResponseDTO(" ERROR: Couldn't send email", null, null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    public String getLink(String token) {
        return "http://localhost:8080/user/verify/" + token;
    }
}