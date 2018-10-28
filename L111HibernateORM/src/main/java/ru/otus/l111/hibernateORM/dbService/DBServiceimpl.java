package ru.otus.l111.hibernateORM.dbService;

import java.sql.SQLException;
import ru.otus.l111.hibernateORM.dataSets.DataSet;

public class DBServiceimpl implements DBService {
    @Override
    public String getLocalStatus() {
        return null;
    }



    @Override
    public <T extends DataSet> void save(final T user) throws SQLException {

    }

    @Override
    public <T extends DataSet> T load(final long id, final Class<T> clazz) throws SQLException {
        return null;
    }

    @Override
    public void shutdown() {

    }

}
