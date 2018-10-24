package ru.otus.l101.orm.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.base.TResultHandler;

public class Executor<T extends DataSet>  {

    private final Connection connection;

    public Executor(Connection connection) {

        this.connection = connection;
    }



    Connection getConnection() {
        return connection;
    }

    public <T> T execQuery(String query, TResultHandler<T> handler) throws SQLException {
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            return handler.handle(result);
        }
    }


    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }
}
