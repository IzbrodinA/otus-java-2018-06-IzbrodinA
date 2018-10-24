package ru.otus.l101.orm.base;

import java.sql.SQLException;
import java.util.List;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.UserDataSet;

public interface DBService extends AutoCloseable {


    String getMetaData();

    void prepareTables() throws SQLException;

    void addUser(UserDataSet user);

    UserDataSet getUserById(long id);


}
