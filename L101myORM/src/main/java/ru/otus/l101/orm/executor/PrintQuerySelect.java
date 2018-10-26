package ru.otus.l101.orm.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PrintQuerySelect {
    private PrintQuerySelect(){}

    static public  String getQuerySelect(List<String> nameFields) {
        StringBuilder select = new StringBuilder("SELECT ");
        for (String field: nameFields) {
            select.append(field).append(", ");
        }
        select.replace(select.length() -2, select.length(), " from homeworkL10 where ");
        return select.toString();
    }




}
