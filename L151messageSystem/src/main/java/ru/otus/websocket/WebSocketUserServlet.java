package ru.otus.websocket;

import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * This class represents a servlet starting a webSocket application
 */
public class WebSocketUserServlet extends WebSocketServlet {
    private final static long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10);

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new UserWebSocketCreator());
    }

}


