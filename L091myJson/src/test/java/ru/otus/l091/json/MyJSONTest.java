package ru.otus.l091.json;

import com.google.gson.Gson;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
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
    public void checkNull() {

        String gsonString = gson.toJson(null);
        String myJSONString = myJSON.toJson(null);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkInteger() {
        Integer value = 1;

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }
    @Test
    public void checkString() {
        String value = "Dad";

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkChar() {
        Character value = 'a';

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkDouble() {
        Double value = 1.0;

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkBoolean() {
        Boolean value = true;

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkArrayInteger() {
        Integer[] value = {1,2};

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkArraySting() {
        String[] value = {"ab","ssf"};

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }


    @Test
    public void checkStringList() {
        List<String> value = new ArrayList<>();
        value.add("red");
        value.add("green");
        value.add("blue");
        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkIntegerList() {
        List<Integer> value = new ArrayList<>();
        value.add(1);
        value.add(888);
        value.add(109);
        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkSimpleObject() {
        SimpleObject value = new SimpleObject(1," st1");

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }


    @Test
    public void checkArraySimpleObject() {
        SimpleObject[] value = {new SimpleObject(1," st1"), new SimpleObject(10, "st10")};

        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }

    @Test
    public void checkBagObject() {
        BagObject value = new BagObject();

        double doubles[] = {1.0, 10.10, 40.40};
        String strings[] = {"test1", "test3", "test4"};

        List<SimpleObject> list = new ArrayList<>();
        SimpleObject simpleObject1 = new SimpleObject(99,"bal1");
        SimpleObject simpleObject2 = new SimpleObject(22,"fswe");
        list.add(new SimpleObject(1, "st1"));
        list.add(simpleObject1);
        list.add(simpleObject2);

        Set<String> set = new HashSet<>();
        set.add("set1");
        set.add("set2");
        set.add("set3");

        Queue<String> queue = new ArrayDeque<>();
        queue.add("str1");
        queue.add("str2");
        queue.add("str3");

        value.setDoubles(doubles);
        value.setStrings(strings);
        value.setList(list);
        value.setQueue(queue);
        value.setSet(set);


        String gsonString = gson.toJson(value);
        String myJSONString = myJSON.toJson(value);

        assertEquals(gsonString,myJSONString);
    }


}