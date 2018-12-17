package ru.otus.dbService;

import java.sql.SQLException;
import ru.otus.dataSets.DataSet;
import ru.otus.messageSystem.Addressee;


public interface DBService  extends Addressee{

    String getLocalStatus();

    public <T extends DataSet> void save(T user) throws SQLException;

    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

    public long countUser() throws SQLException;

    public long countUsersInCach();

    void shutdown();

}
