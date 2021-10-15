package com.example.smssender.service.sms;

import com.example.smssender.entity.certification.Certification;
import com.example.smssender.entity.certification.CertificationRepository;
import com.example.smssender.entity.certification.Certified;
import com.example.smssender.exception.CoolsmsConnectFailedException;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

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
                .orElseGet(() -> certificationRepository.save(Certification.builder()
                                .phoneNumber(toNumber)
                                .code(sendMessage(toNumber))
                                .codeExp(codeExp)
                                .certified(Certified.NOT_CERTIFIED)
                                .build())
                );
    }

    public String sendMessage(String toNumber) {

        Message coolsms = new Message(apiKey, apiSecret);
        String randomCode = getCode(getRandomKey());

        HashMap<String, String> params = new HashMap<>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[TEST] 인증번호 " + randomCode + "를 입력하세요.");
        params.put("app_version", "test app 1.0");

        try {
            coolsms.send(params);
            return randomCode;
        } catch (CoolsmsException e) {
            e.getStackTrace();
            throw new CoolsmsConnectFailedException();
        }

    }

    public String getRandomKey() {
        return RandomStringUtils.randomAlphabetic(3) + RandomStringUtils.randomNumeric(3);
    }

    public String getCode(String key) {
        StringBuffer code = new StringBuffer();
        String code2 = key.substring(0, 3);
        String code3 = key.substring(3);

        for (int i = 0; i < 3; i++) {
            code.append(code2.substring(i, i + 1));
            code.append(code3.substring(i, i + 1));
        }

        return code.toString();
    }

}
