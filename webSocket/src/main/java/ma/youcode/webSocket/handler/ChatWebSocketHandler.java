package ma.youcode.webSocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//TextWebSocketHandler is a WebSocketHandler implementation which processes text messages.
//In the ChatWebSocketHandler, we react to the socket messages.
public class ChatWebSocketHandler extends TextWebSocketHandler {

    //we will save our webSocket session
    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();

    //Invoked after WebSocket negotiation has succeeded and the WebSocket connection is opened and ready for use.
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    //will be called each time we will receive the message from one of our clients
    //Invoked when a new WebSocket message arrives.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession webSocketSession : webSocketSessions){
            System.out.println(message.getPayload());
            webSocketSession.sendMessage(message);
        }
    }


    //Invoked after the WebSocket connection has been closed by either side, or after a transport error has occurred
    //sending messages at this point  will not succeed.
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
