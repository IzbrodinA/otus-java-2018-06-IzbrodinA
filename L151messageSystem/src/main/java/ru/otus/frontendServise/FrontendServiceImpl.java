package ru.otus.frontendServise;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.eclipse.jetty.websocket.api.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.MessageSystemContext;
import ru.otus.app.messages.MsgGetUserId;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Message;
import ru.otus.messageSystem.MessageSystem;


/**
 * Created by tully.
 */
public class FrontendServiceImpl implements FrontendService {
    private final Address address;
    private final MessageSystemContext context;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private final Map<String, Session> sessions = new HashMap<>();

    public FrontendServiceImpl(MessageSystemContext context) {
        this.context = context;
        this.address = new Address("Frontedservise");
        context.setFrontAddress(getAddress());
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void handleRequest(String login) {
        Message message = new MsgGetUserId(getAddress(), context.getDbAddress(), login);
        context.getMessageSystem().sendMessage(message);
    }

    public void addUser(int id, String name) {
//        sessions.put(id, name);
        System.out.println("User: " + name + " has id: " + id);
    }

    @Override
    public void closeSession(final Session session) {


    }

    @Override
    public String setSession(final Session session) {
        String uuid = UUID.randomUUID().toString();
        sessions.put(uuid, session);
        return uuid;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
