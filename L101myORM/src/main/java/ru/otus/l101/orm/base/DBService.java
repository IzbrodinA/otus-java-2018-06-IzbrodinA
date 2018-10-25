package ru.otus.l101.orm.base;

import java.sql.SQLException;

import ru.otus.l101.orm.DataSet;


public interface DBService extends AutoCloseable {


    String getMetaData();

    void prepareTables() throws SQLException;

    public <T extends DataSet> void save (T user)throws SQLException;

    public <T extends DataSet> T load(long id , Class<T> clazz)throws SQLException;


    public void deleteTables() throws SQLException;


}
