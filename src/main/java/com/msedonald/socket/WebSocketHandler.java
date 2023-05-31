package com.msedonald.socket;

import com.msedonald.exception.MessageSendingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String sessionId = session.getId();
        sessions.put(sessionId, session);
        log.info("session start : {}", sessionId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String sessionId = session.getId();
        log.info("> payload {}", message.getPayload());

        sendMessage(sessionId, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("transport error {} : {}", session.getId(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        String sessionId = session.getId();
        log.info("> session {} try to remove [{}]", sessionId, status);
        sessions.remove(sessionId);
        log.info("> session {} successfully removed", sessionId);
    }

    private void sendMessage(String sessionId, TextMessage message) {
        sessions.values().forEach(s -> {
                    try {
                        if (!s.getId().equals(sessionId)) {
                            s.sendMessage(message);
                            log.info(">> message {} sent to user {}", message, s.getId());
                        }
                    } catch (IOException e) {
                        throw new MessageSendingException();
                    }
                }
        );
    }
}
