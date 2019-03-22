package com.stark.websocket.config;

import com.stark.websocket.handler.HandlerContainer;
import com.stark.websocket.handler.SockJSTestHandler;
import com.stark.websocket.handler.TestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.validation.constraints.NotNull;


@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/sockjs/stomp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //注册节点
        webSocketHandlerRegistry.addHandler(testWebSocketHandler(), "/test")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
        webSocketHandlerRegistry.addHandler(sockJSTestWebSocketHandler(), "/sockjs/test")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler testWebSocketHandler() {
        TestHandler myHandler = new TestHandler();
        HandlerContainer.testHandler = myHandler;
        return myHandler;
    }

    @Bean
    public WebSocketHandler sockJSTestWebSocketHandler() {
        SockJSTestHandler myHandler = new SockJSTestHandler();
        HandlerContainer.sockJSTestHandler = myHandler;
        return myHandler;
    }
}
