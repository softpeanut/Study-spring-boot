package com.example.mailsender.service;

import com.example.mailsender.entity.certification.Certification;
import com.example.mailsender.entity.certification.CertificationRepository;
import com.example.mailsender.entity.certification.Certified;
import com.example.mailsender.exception.SendMessageFailedException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
            String code = getCode(createKey());
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
    public void sendEmail(String email) {
        certificationRepository.findByEmail(email)
                .map(certification -> certificationRepository.save(certification.updateCode(sendCode(email))))
                .orElseGet(() -> certificationRepository.save(Certification.builder()
                        .code(sendCode(email))
                        .email(email)
                        .codeExp(CODE_EXP)
                        .certified(Certified.NOT_CERTIFIED)
                        .build())
                );
    }

    public String createKey() {
        return RandomStringUtils.randomNumeric(6);
    }

    public String getCode(String key) {
        return key.substring(0, 3) + "-" + key.substring(3, 6);
    }

}
