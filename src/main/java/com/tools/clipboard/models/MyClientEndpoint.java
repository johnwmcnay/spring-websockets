package com.tools.clipboard.models;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class MyClientEndpoint {

    @OnOpen
    public void myOnOpen (Session session) {
        System.out.println ("WebSocket opened: "+session.getId());
    }

}
