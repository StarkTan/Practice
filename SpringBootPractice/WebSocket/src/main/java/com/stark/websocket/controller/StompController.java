package com.stark.websocket.controller;

import com.stark.websocket.entity.Greeting;
import com.stark.websocket.entity.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class StompController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @RequestMapping("/sendall")
    @ResponseBody
    public void sendAll() {
        template.convertAndSend("/topic/greetings", new Greeting("Send All"));
    }

    @SubscribeMapping("/greetings")
    public Greeting sub() {
        return new Greeting("Hello ");
    }

    /*@MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public String sendDemo(String message) {
        System.out.println(message);
        template.convertAndSend("/topic/subscribeTest", "服务器主动推的数据");
        return "你发送的服务返回消息为:" + message;
    }

    @SubscribeMapping("/subscribeTest")
    public String sub() {
        return "感谢你订阅了我。。。";
    }*/
}
