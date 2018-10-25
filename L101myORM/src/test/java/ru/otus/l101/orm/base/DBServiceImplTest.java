package ru.otus.l101.orm.base;


import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.otus.l101.orm.DataSet;
import ru.otus.l101.orm.UserDataSet;

import static org.junit.Assert.*;


public class DBServiceImplTest {
    DataSet set1;
    DataSet set2;
    DBService dbService;

    @Before
    public void setUp() throws Exception {
        set1 = new UserDataSet("Oleg", 33);
        set2 = new UserDataSet("Kirill", 11);
       dbService = new DBServiceImpl();

    }

    @Test
    public void save() throws SQLException {

        dbService.save(set1);
        dbService.save(set2);

    }

    @Test
    public void load() {
    }

    @After
    public void after() throws Exception {
        dbService.deleteTables();

    }

}
