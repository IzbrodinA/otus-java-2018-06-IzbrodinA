package ru.otus.l101.orm.executor;


import java.util.List;

public class PrintQuerySelect {
    private PrintQuerySelect(){}

    static public  String getQuerySelect(List<String> nameFields) {
        StringBuilder select = new StringBuilder("SELECT ");
        for (String field: nameFields) {
            select.append(field).append(", ");
        }
        select.replace(select.length() -2, select.length(), " FROM homeworkL10 WHERE id = ? ;");
        return select.toString();
    }






}
