package com.msedonald.socket;

import com.msedonald.auth.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;
    private final AuthInterceptor authInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // end point : /api/socket
        // ex)ws://localhost:8080/api/socket for WebSocket Request
        registry.addHandler(webSocketHandler, "/api/socket")
                .addInterceptors(authInterceptor)
                .setAllowedOrigins("*");
    }
}