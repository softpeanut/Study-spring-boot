package com.example.sqs;

public interface AwsSQSSender {
    void sendMessage(String message);
}
