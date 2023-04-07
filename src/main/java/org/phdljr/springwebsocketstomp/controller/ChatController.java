package org.phdljr.springwebsocketstomp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(String textMessage) {
        log.info("{}", textMessage);
        simpMessagingTemplate.convertAndSend("/topic/chat", textMessage + "서버에서 보냄");
    }
}
