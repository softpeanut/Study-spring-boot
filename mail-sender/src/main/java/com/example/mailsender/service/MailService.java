package com.example.mailsender.service;

import com.example.mailsender.payload.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static Integer certificationNumber = 123456;

    @Async
    public void sendEmail(SignupRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("[Test 이메일 인증]");
        message.setText("귀하의 이메일 인증번호는 " + certificationNumber + "입니다.");
        javaMailSender.send(message);
    }

}
