package com.msedonald.socket;

import com.msedonald.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 토픽을 구독할 수 있도록 설정합니다.
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메시지를 송신할 수 있는 경로를 설정합니다.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/socket")
                .addInterceptors(authInterceptor)
                .setAllowedOrigins("*")
                .withSockJS(); // SockJS를 사용하여 대체 옵션을 제공합니다.
    }
}