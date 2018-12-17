package ru.otus.app.messages;


import ru.otus.frontendServise.FrontendService;
import ru.otus.app.MsgToFrontend;
import ru.otus.messageSystem.Address;

/**
 * Created by tully.
 */
public class MsgGetUserIdAnswer extends MsgToFrontend {
    private final String name;
    private final int id;

    public MsgGetUserIdAnswer(Address from, Address to, String name, int id) {
        super(from, to);
        this.name = name;
        this.id = id;
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.addUser(id, name);
    }
}
