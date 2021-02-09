package com.tools.clipboard.models;

import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class MyClientEndpoint {

    @OnOpen
    public void myOnOpen (Session session) {
        System.out.println("session.getUserProperties() = " + session.getUserProperties());
        System.out.println("session.getPathParameters() = " + session.getPathParameters());
        System.out.println("session.getRequestParameterMap() = " + session.getRequestParameterMap());
        System.out.println("session.getQueryString() = " + session.getQueryString());
        System.out.println ("WebSocket opened: "+session.getId());

    }

    @OnMessage
    public String myOnMessage (@RequestBody String txt)
    {
        System.out.println("txt = " + txt);
        return txt;
    }

}
