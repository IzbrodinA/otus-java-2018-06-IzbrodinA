package ru.otus.l111.hibernateORM.dbService;

import java.sql.SQLException;
import java.util.List;
import ru.otus.l111.hibernateORM.dataSets.DataSet;

public interface DBService {

    String getLocalStatus();

    public <T extends DataSet> void save (T user)throws SQLException;

    public <T extends DataSet> T load(long id , Class<T> clazz)throws SQLException;

    void shutdown();

}
