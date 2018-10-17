package ru.otus.l091.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class PrintReflector {
    private JsonObjectBuilder jsonObjectBuilder;
    private JsonArrayBuilder jsonArrayBuilder;


    public JsonObjectBuilder visit(final Object object,  final String name, JsonObjectBuilder jsonObjectBuilder) {
        if (object == null){
            return jsonObjectBuilder.addNull(name);
        }
        Method method = getMethod(object.getClass(), String.class);
        this.jsonObjectBuilder = jsonObjectBuilder;

        try {
             method.invoke(this,  object, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return jsonObjectBuilder;
    }

    public JsonArrayBuilder visit(final Object object,  final String name, JsonArrayBuilder jsonArrayBuilder) {

        Method method = getMethod(object.getClass(), String.class);
        this.jsonArrayBuilder = jsonArrayBuilder;

        try {
             method.invoke(this,  object, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return jsonArrayBuilder;
    }

    private Method getMethod(Class c, Class strClass) {
        Class newc = c;
        Method m = null;
        // Try the superclasses
        while (m == null && newc != Object.class) {
            String method = newc.getName();
            method = "visit" + method.substring(method.lastIndexOf('.') + 1);
            try {
                m = this.getClass().getMethod(method, newc, strClass);
            } catch (NoSuchMethodException e) {
                newc = newc.getSuperclass();
            }
        }
        if (m == null) {
            try {
                m = this.getClass().getMethod("visitObject", Object.class, strClass);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return m;
    }


    public void visitString(final String object, final String name ) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }
    }

    public void visitInteger( final Integer object, final  String name) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }
    }

    public void visitCharacter(final Character object, final  String name) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }
    }

    public void visitLong(final Long object, final  String name) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }
    }

    public void visitDouble(final Double object, final  String name) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }

    }

    public void visitBoolean(final Boolean object, final  String name) {
        if (name == null) {
            jsonArrayBuilder.add(object);
        } else {
            jsonObjectBuilder.add(name, object);
        }
    }

    public void visitObject(final Object object, final  String name) {


    }



}
