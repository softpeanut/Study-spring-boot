package com.example.fcmserver.service;

import com.example.fcmserver.domain.entity.User;
import com.example.fcmserver.domain.repository.TokenRepository;
import com.example.fcmserver.dto.MessageForm;
import com.example.fcmserver.firebase.FirebaseCloudMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final TokenRepository tokenRepository;
    private final FirebaseCloudMessageService fcmService;

    public void sendMessage(User receiver, MessageForm messageForm) throws Exception {

        String token = tokenRepository.findByUser(receiver).getToken();
        String title = "[" + messageForm.getSender() + "]";
        String body = messageForm.getMessage();
        String path = "http://localhost:8080/";

        fcmService.sendMessageTo(token, title, body, path);
    }

}
