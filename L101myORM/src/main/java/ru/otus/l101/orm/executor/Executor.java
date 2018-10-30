package ru.otus.l101.orm.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ru.otus.l101.orm.DataSet;

public class Executor<T extends DataSet> {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }

    public <T> T execQuery(String query, long id, TResultHandler<T> handler) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            return handler.handle(result);

        }
    }


    public void execUpdate(String update) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(update)) {
            stmt.execute();
        }
    }


}
