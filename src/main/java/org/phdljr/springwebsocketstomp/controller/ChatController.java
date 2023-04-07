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

    // "/topic/chat"을 구독한 사람들한테 데이터 전송
    @MessageMapping("/chat")
    public void handleChat(String textMessage) {
        log.info("{}", textMessage);
        simpMessagingTemplate.convertAndSend("/topic/chat", textMessage);
    }
}
