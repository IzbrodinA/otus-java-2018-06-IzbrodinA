package ru.otus.dbService;

import java.sql.SQLException;
import java.util.function.Function;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.app.MessageSystemContext;
import ru.otus.dataSets.DataSet;
import ru.otus.dataSets.UsersDataSet;
import ru.otus.dbService.cache.CacheEngine;
import ru.otus.dbService.cache.CacheEngineImpl;
import ru.otus.dbService.cache.MyElement;

import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.MessageSystem;


public class DBServiceHibernateImpl implements DBService {
    private final SessionFactory sessionFactory;
    private final  CacheEngine<Long, DataSet> cache;

    private final Address address;
    private final MessageSystemContext context;

    public DBServiceHibernateImpl(MessageSystemContext context) {

        address = new Address("DBService");

        this.context = context;
        context.setDbAddress(this.address);

        cache = new CacheEngineImpl<>(5, 30_000, 0, false);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hwl12");
        configuration.setProperty("hibernate.connection.username", "izbro");
        configuration.setProperty("hibernate.connection.password", "Izbrodin123/*");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public String getLocalStatus() {
        return runInSession(session -> {
            return session.getTransaction().getStatus().name();
        });
    }



    @Override
    public <T extends DataSet> void save(final T user) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            dao.save(user);
        }
        cache.put(new MyElement<>(user.getId(), user));


    }

    @Override
    public <T extends DataSet> T load(final long id, final Class<T> clazz) throws SQLException {
        if (cache.get(id) != null){
            return(T) cache.get(id).getValue();
        }

        return runInSession(session -> {
            UsersDAO dao = new UsersDAO(session);
            T object = dao.load(id, clazz);

            return object;
        });
    }

    @Override
    public  long countUser() throws SQLException {
            return runInSession(session -> {
                UsersDAO dao = new UsersDAO(session);
                return dao.countAllUsers();
            });
        }

    @Override
    public long countUsersInCach() {
        return cache.sizeCashe();
    }


    @Override
    public void shutdown() {
        sessionFactory.close();
    }



    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }


    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
