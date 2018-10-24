package ru.otus.l101.orm.base;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.UserDataSet;
import ru.otus.l101.orm.executor.Executor;

import static ru.otus.l101.orm.base.ConnectionHelper.getConnection;

public class DBServiceImpl implements DBService {

    private static final String CREATE_TABLE_USER = "create table if not exists  homeworkL10 (id bigint(20) NOT NUll" +
                                                    " auto_increment, name VARCHAR(255), age int(3), PRIMARY KEY (id));";


    private final Connection connection;
    private Executor exec;

    public DBServiceImpl() {
        connection = getConnection();
        exec = new Executor(getConnection());
        prepareTables();


    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                    "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + getConnection().getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void prepareTables() throws SQLException {
        exec.execUpdate(CREATE_TABLE_USER);

    }

    public <T extends DataSet> void save (T user) throws SQLException {
        String insert = null;
        exec.execUpdate(insert);
    }

    public <T extends DataSet> T load(long id , Class<T> clazz) throws SQLException {
        String select= "kk";
        return (T) exec.execQuery(String.format(select, id), result -> {
            result.next();
            return result.getString("name");
        });
    }


    @Override
    public void close() throws Exception {
        connection.close();

    }
}
