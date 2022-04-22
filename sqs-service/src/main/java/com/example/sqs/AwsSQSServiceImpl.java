package com.example.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AwsSQSServiceImpl implements AwsSQSSender {

    private final AmazonSQS amazonSQS;

    @Value("${cloud.aws.sqs.queue.url}")
    private String url;

    @Override
    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(url, message)
                .withMessageGroupId("live-commerce")
                .withMessageDeduplicationId(UUID.randomUUID().toString());
        amazonSQS.sendMessage(sendMessageRequest);
    }
}
