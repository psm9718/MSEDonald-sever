package com.msedonald.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msedonald.exception.MessageSendingException;
import com.msedonald.socket.data.PlayerInfo;
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

    private final ObjectMapper objectMapper;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        sessions.put(sessionId, session);
        log.info("session start : {}", sessionId);

        sendMessage(sessionId, PlayerInfo.builder()
                .move_x(0)
                .move_z(0)
                .build());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionId = session.getId();
        String payload = message.getPayload();
        log.info("> payload {}", payload);

        PlayerInfo playerInfo = objectMapper.readValue(payload, PlayerInfo.class);

        sendMessage(sessionId, playerInfo);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessions.remove(sessionId);

        publishMessage(objectMapper.writeValueAsString(PlayerInfo.builder()
                .move_x(0)
                .move_z(0)
                .build()));
        log.info("session {} successfully removed", sessionId);
    }

    private void publishMessage(String string) {
        sessions.values().forEach(s -> {
                    try {
                        s.sendMessage(new TextMessage(string));
                        log.info(">> message broadcast to user {} [ {} ]", s.getId(), s.getUri());
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new MessageSendingException();
                    }
                }
        );
    }

    private void sendMessage(String sessionId, PlayerInfo playerInfo) {
        sessions.values().forEach(s -> {
                    try {
                        if (!s.getId().equals(sessionId)) {
                            s.sendMessage(new TextMessage(objectMapper.writeValueAsString(playerInfo)));
                            log.info(">> message sent to user {} [ {} ]", s.getId(), s.getUri());
                        }
                    } catch (IOException e) {
                        throw new MessageSendingException();
                    }
                }
        );
    }
}
