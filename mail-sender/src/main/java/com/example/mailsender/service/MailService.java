package com.example.mailsender.service;

import com.example.mailsender.entity.certification.Certification;
import com.example.mailsender.entity.certification.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final CertificationRepository certificationRepository;
    private static String certificationCode = createKey();

    public String sendEmail(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String code = createCode(certificationCode);
            message.setFrom("sdpthf@gmail.com");
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSubject("[Test 이메일 인증]");
            message.setText(code);
            javaMailSender.send(message);
            certificationRepository.save(Certification.builder()
                    .code(code)
                    .build());
            return code;
        } catch (MessagingException e) {
            throw new IllegalArgumentException();
        }

    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public String createCode(String key) {
        return key.substring(0, 3) + "-" + key.substring(3, 6);
    }

}
