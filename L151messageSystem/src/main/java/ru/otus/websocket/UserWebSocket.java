package ru.otus.websocket;

import java.util.Set;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.frontendServise.FrontendService;


@WebSocket
public class UserWebSocket {

    private Session session;
    private String id;
    private FrontendService fronted;

    private static Logger LOGGER = LoggerFactory.getLogger(UserWebSocket.class);

    public UserWebSocket(FrontendService fronted) {
        this.fronted = fronted;
    }

    @OnWebSocketMessage
    public void onMessage( String data) {
        LOGGER.info(data);
        fronted.handleRequest(data);
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        fronted.setSession(session);
        LOGGER.info("Open" + id);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
       fronted.closeSession (session);
       LOGGER.info("Close sesion" + id + " reason" + reason);
        System.out.println("onClose");
    }
}
