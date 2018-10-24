package ru.otus.l101.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrintQueryVisitor <T> {

    public String visit(final Object object,  final Class<T> clazz) {
        if (clazz == null){
            return null;
        }
        Method method = getMethod( clazz);
        String query ="asd";
        try {
            method.invoke(this,  object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return query;
    }


    private Method getMethod(Class c) {

        Class newc = c;
        Method m = null;
        // Try the superclasses
        while (m == null && newc != Object.class) {
            String method = newc.getName();
            method = "visit" + method.substring(method.lastIndexOf('.') + 1);
            try {
                m = this.getClass().getMethod(method, newc);
            } catch (NoSuchMethodException e) {
                newc = newc.getSuperclass();
            }
        }
        if (m == null) {
            try {
                m = this.getClass().getMethod("visitObject", Object.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return m;
    }


    public void visitString(final String object) {

    }

    public void visitInteger( final Integer object {

    }

    public void visitCharacter(final Character object) {

    }

    public void visitLong(final Long object) {

    }

    public void visitDouble(final Double object) {

    }

    public void visitBoolean(final Boolean object) {

    }

    public void visitObject(final Object object) {

    }

}
