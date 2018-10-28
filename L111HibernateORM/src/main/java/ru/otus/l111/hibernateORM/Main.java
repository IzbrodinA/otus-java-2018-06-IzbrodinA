package ru.otus.l111.hibernateORM;

import java.sql.SQLException;
import ru.otus.l111.hibernateORM.dataSets.AddressDataSet;
import ru.otus.l111.hibernateORM.dataSets.UsersDataSet;
import ru.otus.l111.hibernateORM.dbService.DBService;
import ru.otus.l111.hibernateORM.dbService.DBServiceHibernateImpl;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBService dbService = new DBServiceHibernateImpl();

        UsersDataSet user1 = new UsersDataSet("Kirill", 23, new AddressDataSet("Lenina"));
        UsersDataSet user2 = new UsersDataSet("Oleg", 40, new AddressDataSet("Putina"));

        dbService.save(user1);
        dbService.save(user2);
        UsersDataSet dataSet = dbService.load(2, UsersDataSet.class);
        System.out.println(dataSet);
    }
}
