package com.revspeed.backend_p1.service;


import com.revspeed.backend_p1.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Registration Confirmation");
        message.setText("Dear " + user.getName() + ",\n\nThank you for registering with RevSpeed!\n\nBest regards,\nRevSpeed Team");
        mailSender.send(message);
    }
}
