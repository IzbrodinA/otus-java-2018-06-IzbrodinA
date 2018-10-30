package ru.otus.l101.orm.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PrintQueryInsert {
    Map<String, String> baseStringValue = new HashMap<>();
    Map<String, Number> baseNumberValue = new HashMap<>();


    public String visit(final Object object) {
        if (object == null) {
            return null;
        }

        Method method;
        Field fields[] = object.getClass().getDeclaredFields();
        Field field;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            Object value;
            field.setAccessible(true);
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            method = getMethod(value.getClass(), String.class);
            try {
                method.invoke(this, value, field.getName());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return printQuery();
    }



    private String printQuery() {
        StringBuilder insert = new StringBuilder("INSERT INTO homeworkL10 SET ");

        for (Map.Entry<String, String> stringEntry : baseStringValue.entrySet()) {
            insert.append(stringEntry.getKey()).append("='").append(stringEntry.getValue()).append("', ");
        }
        for (Map.Entry<String, Number> numberEntry : baseNumberValue.entrySet()) {
            insert.append(numberEntry.getKey()).append("=").append(numberEntry.getValue()).append(", ");
        }
        int length = insert.length();
        insert.replace(length - 2, length, ";");
        return insert.toString();
    }


    private Method getMethod(Class c, Class stringClass) {
        Method m = null;
        // Try the superclasses
        while (m == null && c != Object.class) {
            String method = c.getName();
            method = "visit" + method.substring(method.lastIndexOf('.') + 1);
            try {
                m = this.getClass().getMethod(method, c, stringClass);
            } catch (NoSuchMethodException e) {
                c = c.getSuperclass();
            }
        }
        if (m == null) {
            try {
                m = this.getClass().getMethod("visitObject", Object.class, stringClass);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return m;
    }


    public void visitString(final String object, String name) {
        baseStringValue.put(name, object);
    }

    public void visitInteger(final Integer object, String name) {
        baseNumberValue.put(name, object);
    }

    public void visitCharacter(final Character object, String name) {
        baseStringValue.put(name, object.toString());
    }

    public void visitLong(final Long object, String name) {
        baseNumberValue.put(name, object);
    }

    public void visitDouble(final Double object, String name) {
        baseNumberValue.put(name, object);
    }

    public void visitBoolean(final Boolean object, String name) {
        baseStringValue.put(name, object.toString());
    }

    public void visitObject(final Object object, String name) {

    }

}
