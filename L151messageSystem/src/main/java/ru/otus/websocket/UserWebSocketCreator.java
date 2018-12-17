package ru.otus.websocket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.frontendServise.FrontendService;

/**
 * @author v.chibrikov
 */
public class UserWebSocketCreator implements WebSocketCreator {
    private final static Logger log = Logger.getLogger(UserWebSocketCreator.class.getName());

    private FrontendService frontendService;

    public UserWebSocketCreator(FrontendService frontend) {
        this.frontendService = frontend;
        log.info("UserWebSocketCreator created");
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        UserWebSocket socket = new UserWebSocket(frontendService);
        log.info("Socket created");
        return socket;
    }
}
