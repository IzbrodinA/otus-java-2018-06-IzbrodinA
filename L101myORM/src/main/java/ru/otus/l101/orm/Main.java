package ru.otus.l101.orm;

import ru.otus.l101.orm.base.DBService;
import ru.otus.l101.orm.base.DBServiceImpl;

public class Main {
    public static void main(String[] args) {
        try (DBService dbService = new DBServiceImpl() ){
//            System.out.println(dbService.getMetaData());
            DataSet set1 = new UserDataSet("kirll", 22);
            DataSet set2 = new UserDataSet("oleg", 10);
            dbService.save(set1);
            dbService.save(set2);
            DataSet set3 = dbService.load(1,UserDataSet.class);
            System.out.println(set3);
            dbService.deleteTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
