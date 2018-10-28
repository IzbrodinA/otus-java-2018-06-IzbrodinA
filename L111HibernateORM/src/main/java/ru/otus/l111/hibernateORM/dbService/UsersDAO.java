package ru.otus.l111.hibernateORM.dbService;

import org.hibernate.Session;
import ru.otus.l111.hibernateORM.dataSets.DataSet;
import ru.otus.l111.hibernateORM.dataSets.UsersDataSet;

public class UsersDAO {
    private Session session;

    public UsersDAO(final Session session) {
        this.session = session;
    }

    public <T extends DataSet> void save(T dataSet){
        session.save(dataSet);
    }
    public <T extends DataSet> T load (long id, Class<T> tClass){
        return session.load(tClass, id);
    }

}
