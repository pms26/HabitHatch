package com.habitHatch.Kafka;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessagingService {
    @Value("${twilio.from.number}")
    private String fromNumber;
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }
    public void sendEmail(String to, String subject, String body) {
        try {
            log.info("Sending email to: {}, Subject: {}, Body: {}", to, subject, body);
            JavaMailSender javaMailSender = new JavaMailSenderImpl();
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Exception while sendEmail ", e);
        }
    }
//    public void sendSMS(String phoneNumber,String message){
//        Message messageToSend = Message.creator(
//                new PhoneNumber(phoneNumber),
//                new PhoneNumber(fromNumber),
//                message
//        ).create();
//
//    }



}