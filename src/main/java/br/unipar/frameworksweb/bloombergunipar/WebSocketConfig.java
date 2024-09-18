package br.unipar.frameworksweb.bloombergunipar;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Habilitando o broker simples para enviar mensagens aos clientes
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app"); // Prefixo para as mensagens enviadas pelos clientes
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registrando o endpoint para SockJS com suporte a fallback
        registry.addEndpoint("/stock-prices")
                .setAllowedOrigins("http://127.0.0.1:5500",
                        "http://localhost:3000/",
                        "http://localhost:63342/",
                        "null")
                .withSockJS();
    }


}

