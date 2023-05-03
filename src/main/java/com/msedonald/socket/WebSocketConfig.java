package com.msedonald.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // WebSocketHandler 에 관한 생성자 추가
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint 설정 : /api/socket
        // 이를 통해서 ws://localhost:8080/api/socket 으로 요청이 들어오면 websocket 통신을 진행한다.
        registry.addEndpoint("/api/socket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
