package com.example.chatservice.controller;

import com.example.chatservice.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/enter")
    public void enter(ChatMessageDto message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId());
    }

    @MessageMapping("/send/message")
    public void message(@DestinationVariable ChatMessageDto message) {
        messagingTemplate.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
    }

}
