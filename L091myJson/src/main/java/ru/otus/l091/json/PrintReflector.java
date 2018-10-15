package ru.otus.l091.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class PrintReflector implements Visitor {
    private JsonValue jsonValue;


    public JsonValue visit(final Object object,  final String name) {
        Method method = getMethod(object.getClass(), String.class);

        try {
            jsonValue = (JsonValue) method.invoke(this,  object, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return jsonValue;
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


    public JsonValue visitString(final String object, final String name ){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitInteger( final Integer object, final  String name){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitCharacter(final Character object, final  String name){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitLong(final Long object, final  String name){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitDouble(final Double object, final  String name){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitBoolean(final Boolean object, final  String name){
        if (name == null){
            return Json.createArrayBuilder().add(object).build();
        }
        return Json.createObjectBuilder().add(name, object).build();
    }

    public JsonValue visitObject(final Object object, final  String name) {


        return null;
    }



}
