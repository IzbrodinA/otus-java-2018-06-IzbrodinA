package ru.otus.frontendServise;


import org.eclipse.jetty.websocket.api.Session;
import ru.otus.messageSystem.Addressee;

/**
 * Created by tully.
 */
public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);

    void closeSession(Session session);

    String setSession (Session session);
}

