package ma.youcode.webSocket.config;



import ma.youcode.webSocket.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
//WebSocketConfig configures WebSocket in a Spring web application with @EnableWebSocket.
@EnableWebSocket
public class WebSocketMessageConfig implements WebSocketConfigurer {

    //will be in endpoint were our webSocket will abulable
    private final String CHAT_ENDPOINT = "/chat";

    //We inject our getChatWebSocketHandler. It is registered with this methode
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
                .setAllowedOrigins("*");
    }


    @Bean
    public WebSocketHandler getChatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
}
