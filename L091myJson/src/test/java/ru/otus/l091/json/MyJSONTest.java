package ru.otus.l091.json;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyJSONTest {
    Gson gson;
    MyJSON myJSON;
    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        myJSON = new MyJSON();

    }

    @Test
    public void checkInteger() {
        Integer[] value = {1,2};

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }


    @Test
    public void checkArraySimpleObject() {
        SimpleObject[] value = {new SimpleObject(), new SimpleObject()};

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }
}