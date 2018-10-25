package ru.otus.l101.orm.base;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.PrintQueryVisitor;
import ru.otus.l101.orm.UserDataSet;
import ru.otus.l101.orm.executor.Executor;

import static ru.otus.l101.orm.base.ConnectionHelper.getConnection;

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
    private Executor exec;

    public DBServiceImpl() {
        connection = getConnection();
        exec = new Executor(getConnection());

        try {
            prepareTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }



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


    @Override
    public <T extends DataSet> void save (T user) throws SQLException {
        PrintQueryVisitor pqv = new PrintQueryVisitor();
        String insert = pqv.visit(user);
        exec.execUpdate(insert);
    }
    @Override
    public <T extends DataSet> T load(long id , Class<T> clazz) throws SQLException {
//        String select= "kk";
//        return (T) exec.execQuery(String.format(select, id), result -> {
//            result.next();
//            return result.getString("name");
//        });
        return null;
    }

    @Override
    public void deleteTables() throws SQLException {
            exec.execUpdate(DELETE_USER);
        }



    @Override
    public void close() throws Exception {
        connection.close();

    }
}
