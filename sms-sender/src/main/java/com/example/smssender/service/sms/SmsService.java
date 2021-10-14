package com.example.smssender.service.sms;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Random;

@Service
public class SmsService {

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.phone-number}")
    private String fromNumber;

    public void sendMessage(String toNumber) {

        Message coolsms = new Message(apiKey, apiSecret);
        String randomNumber = getRandomNumber();

        HashMap<String, String> params = new HashMap<>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[TEST] 인증번호 "+randomNumber+" 를 입력하세요.");
        params.put("app_version", "test app 1.0");

        try {
            coolsms.send(params);
        } catch (CoolsmsException e) {
            e.getStackTrace();
        }

    }

    public String getRandomNumber() {
        Random random = new Random();
        return random.toString();
    }

}
