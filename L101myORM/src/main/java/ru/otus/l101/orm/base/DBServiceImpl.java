package ru.otus.l101.orm.base;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.executor.PrintQueryInsert;
import ru.otus.l101.orm.executor.PrintQuerySelect;
import ru.otus.l101.orm.executor.Executor;
import ru.otus.l101.orm.helpers.ReflectionHelper;

import static ru.otus.l101.orm.helpers.ConnectionHelper.closeConnection;
import static ru.otus.l101.orm.helpers.ConnectionHelper.getConnection;


/*

CREATE USER 'izbro'@'localhost' IDENTIFIED BY 'Izbrodin123/*';
GRANT ALL PRIVILEGES ON * . * TO 'izbro'@'localhost';
create database db_homeL101;
SET GLOBAL time_zone = '+3:00';
use db_homeL101;
CREATE TABLE homeworkL10 (id bigint(20) NOT NUll auto_increment,
 name VARCHAR(255), age int(3), PRIMARY KEY (id));

*/

public class DBServiceImpl implements DBService {

    private static final String CREATE_TABLE_USER = "create table if not exists  homeworkL10 (id bigint(20) NOT NUll" +
            " auto_increment, name VARCHAR(255), age int(3), PRIMARY KEY (id));";

    private static final String DELETE_USER = "drop table homeworkL10";


    private final Connection connection;
    private Executor<DataSet> exec;

    public DBServiceImpl() {

        connection = getConnection();
        exec = new Executor<>(getConnection());

        try {
            prepareTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + connection.getMetaData().getURL() + "\n" +
                    "DB name: " + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public void prepareTables() throws SQLException {
        exec.execUpdate(CREATE_TABLE_USER);

    }


    @Override
    public <T extends DataSet> void save(T user) throws SQLException {
        String insert = new PrintQueryInsert().visit(user);
        exec.execUpdate(insert);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        List<String> listNameFields = ReflectionHelper.getListFields(clazz);
        String select = PrintQuerySelect.getQuerySelect(listNameFields);
//        System.out.println(select);
        return (T) exec.execQuery(select, id, result -> {
            Object[] args = new Object[listNameFields.size()];
            result.next();
            for (int i = 0; i < listNameFields.size(); i++) {
                args[i] = result.getObject(listNameFields.get(i));
            }
            DataSet dataSet = ReflectionHelper.instantiate(clazz, args);
            Objects.requireNonNull(dataSet).setId(id);
            result.close();
            return dataSet;
        });


    }

    @Override
    public void deleteTables() throws SQLException {
        exec.execUpdate(DELETE_USER);
    }


    @Override
    public void close() {
     closeConnection(connection);
    }
}
