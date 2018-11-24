package ru.otus.dbService;

import java.sql.SQLException;
import ru.otus.dataSets.DataSet;


public interface DBService {

    String getLocalStatus();

    public <T extends DataSet> void save(T user) throws SQLException;

    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

    public long countUser() throws SQLException;

    void shutdown();

}
