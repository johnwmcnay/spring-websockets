package com.tools.clipboard.controllers;

import com.tools.clipboard.models.MyClientEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage() throws URISyntaxException, IOException, DeploymentException {

//        javax.websocket.WebSocketContainer container =
//                javax.websocket.ContainerProvider.getWebSocketContainer();
//
////        Session session = container.connectToServer(MyClientEndpoint.class,
////                new URI("wss://vuybyli569.execute-api.us-east-1.amazonaws.com/test"));
////
////        System.out.println("session.getUserProperties() = " + session.getUserProperties());
////        System.out.println("session.getPathParameters() = " + session.getPathParameters());
////        System.out.println("session.getRequestParameterMap() = " + session.getRequestParameterMap());
////        System.out.println("session.getQueryString() = " + session.getQueryString());
////        System.out.println ("WebSocket opened: "+session.getId());
        

        return "index";
    }

    @GetMapping("/testing")
    @ResponseBody
     public String test() {

        return "test";
    }

    @PostMapping("/connect")
    @ResponseBody
    public String connectTest(String payload) {
        System.out.println("payload = " + payload);
        System.out.println("connectTest()");
        return "connectTest()";
    }

}
