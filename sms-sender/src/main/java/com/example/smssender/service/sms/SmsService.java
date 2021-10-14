package com.example.smssender.service.sms;

import com.example.smssender.entity.certification.Certification;
import com.example.smssender.entity.certification.CertificationRepository;
import com.example.smssender.entity.certification.Certified;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsService {

    @Value("${code.exp}")
    private Integer codeExp;

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.phone-number}")
    private String fromNumber;

    private final CertificationRepository certificationRepository;


    @Transactional
    public void sendCode(String toNumber) {
        certificationRepository.findByPhoneNumber(toNumber)
                .map(certification -> certificationRepository.save(certification.updateCode(sendMessage(toNumber))))
                .orElse(
                        certificationRepository.save(Certification.builder()
                                .phoneNumber(toNumber)
                                .code(sendMessage(toNumber))
                                .codeExp(codeExp)
                                .certified(Certified.NOT_CERTIFIED)
                                .build())
                );
    }

    public String sendMessage(String toNumber) {

        Message coolsms = new Message(apiKey, apiSecret);
        String randomNumber = getRandomNumber();

        System.out.println(randomNumber);
        System.out.println(toNumber);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[TEST] 인증번호 " + randomNumber + "를 입력하세요.");
        params.put("app_version", "test app 1.0");

        System.out.println(params);

        try {
            coolsms.send(params);
            return randomNumber;
        } catch (CoolsmsException e) {
            e.getStackTrace();
            throw new IllegalArgumentException();
        }

    }

    public String getRandomNumber() {
        Random random = new Random();
        return Integer.toString(random.nextInt(99999) + 111111);
    }

}
