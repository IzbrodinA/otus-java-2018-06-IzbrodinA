package ru.otus.dbService;


import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import ru.otus.dataSets.DataSet;
import ru.otus.dataSets.UsersDataSet;

public class UsersDAO {
    private Session session;

    public UsersDAO(final Session session) {
        this.session = session;
    }

    public <T extends DataSet> void save(T dataSet) {
        session.save(dataSet);
    }

    public <T extends DataSet> T load(long id, Class<T> tClass) {
        return session.get(tClass, id);
    }

    public long countAllUsers() {

        return (long) session.createCriteria(UsersDataSet.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();


    }

}
