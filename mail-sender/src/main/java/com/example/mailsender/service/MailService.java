package com.example.mailsender.service;

import com.example.mailsender.entity.certification.Certification;
import com.example.mailsender.entity.certification.CertificationRepository;
import com.example.mailsender.entity.certification.Certified;
import com.example.mailsender.exception.EmailNotFoundException;
import com.example.mailsender.exception.SendMessageFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${code.exp}")
    private Integer CODE_EXP;

    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;

    @Transactional
    public String sendCode(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String code = createCode(createKey());
            message.setFrom("sdpthf@gmail.com");
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("[Test 이메일 인증]");
            message.setText(code);
            javaMailSender.send(message);
            return code;
        } catch (MessagingException e) {
            e.getStackTrace();
            throw new SendMessageFailedException();
        }

    }

    @Transactional
    public void resendEmail(String email) {
        certificationRepository.findByEmail(email)
                .map(certification -> certificationRepository.save(certification.updateCode(sendCode(email))))
                .orElseThrow(EmailNotFoundException::new);
    }

    @Transactional
    public void sendEmail(String email) {
        certificationRepository.save(Certification.builder()
                .code(sendCode(email))
                .email(email)
                .codeExp(CODE_EXP)
                .certified(Certified.NOT_CERTIFIED)
                .build());
    }

    public String createKey() {
        Random rnd = new Random();
        String key = Integer.toString(rnd.nextInt(111111) + 99999);
        System.out.println(key);
        return key;
    }

    public String createCode(String key) {
        return key.substring(0, 3) + "-" + key.substring(3, 6);
    }

}
