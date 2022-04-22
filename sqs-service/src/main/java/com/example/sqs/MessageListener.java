package com.example.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @SqsListener(value = "${cloud.aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receive(@Payload String message, Acknowledgment acknowledgment) {
        acknowledgment.acknowledge();
        log.info("message from sqs : " + message + ", " + System.currentTimeMillis());
    }

}
