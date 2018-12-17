package ru.otus.app;


import ru.otus.messageSystem.Addressee;

/**
 * Created by tully.
 */
public interface DBService extends Addressee {
    void init();

    int getUserId(String name);
}
