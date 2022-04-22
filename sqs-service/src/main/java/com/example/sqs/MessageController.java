package com.example.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final AwsSQSSender awsSQSSender;

    @PostMapping("/message")
    public void send(@RequestBody String message) {
        awsSQSSender.sendMessage(message);
        log.info("send to sqs : " + message);
    }

}
