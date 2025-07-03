//package com.habitHatch.Kafka;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class MessagingService {
//
//    public void sendEmail(String to, String subject, String body) {
//        try {
//            log.info("Sending email to: {}, Subject: {}, Body: {}", to, subject, body);
//            JavaMailSender javaMailSender = new JavaMailSenderImpl();
//            SimpleMailMessage mail = new SimpleMailMessage();
//            mail.setTo(to);
//            mail.setSubject(subject);
//            mail.setText(body);
//            javaMailSender.send(mail);
//            log.info("Email sent successfully to: {}", to);
//        } catch (Exception e) {
//            log.error("Exception while sendEmail ", e);
//        }
//    }
//
//
//
//
//}