package com.msedonald.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class AuthInterceptor implements HandshakeInterceptor {

//    private final TokenValidator tokenValidator;
//
//    public AuthInterceptor(TokenValidator tokenValidator) {
//        this.tokenValidator = tokenValidator;
//    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpHeaders headers = request.getHeaders();
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        log.info("header : {}", authorizationHeader);
        // Validate the token and obtain the UserAuth object
//      UserAuth userAuth = tokenValidator.validateToken(token);
//
//        // Store the UserAuth object in the attributes map for later use
//        attributes.put("userAuth", userAuth);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // No action needed
    }
//
//    private String extractTokenFromHeader(String authorizationHeader) {
//        // Extract the token from the Authorization header
//        // ...
//        return token;
//    }
}
