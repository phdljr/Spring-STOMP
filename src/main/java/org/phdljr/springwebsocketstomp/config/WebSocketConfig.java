package org.phdljr.springwebsocketstomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메시지를 보낼 경로 맨 앞에 "/queue"또는 "/topic"가 붙어있으면 그 경로를 구독한 클라이언트에게 메시지를 보냄
        // ex) 4번 채팅방에 들어간 모든 클라이언트에게 메시지 보내기 "/topic/chat/room/4"
        // queue는 1:1로, topic은 1:N으로 메시지를 보낼 때 많이 쓰이는 url 관습
        registry.enableSimpleBroker("/queue", "/topic");

        // 클라이언트가 메시지를 보낼 때 경로 맨 앞에 "/broker"이 붙어있으면 broker로 보내짐
        // broker로 보내지면 @Controller 내부의 @MessageMapping 메소드로 라우팅됨
        // ex) 클라이언트가 4번 채팅방을 구독할 때 url "/broker/chat/room/4" => "/chat/room/4" 채널을 구독한다는 의미
        registry.setApplicationDestinationPrefixes("/broker");
    }
}
