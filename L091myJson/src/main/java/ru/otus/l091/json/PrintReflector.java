package ru.otus.l091.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class PrintReflector implements Visitor {
    private JsonObjectBuilder jsonObjectBuilder;


    public JsonObjectBuilder visit(final String name, final Object object,  JsonObjectBuilder jsonObjectBuilder) {
        Method method = getMethod(object.getClass(), name.getClass());
            this.jsonObjectBuilder = jsonObjectBuilder;
        try {
            jsonObjectBuilder = (JsonObjectBuilder) method.invoke(this,  object, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //        if (object instanceof Visitable) {
//            callAccept((Visitable) object);
//        }

        return jsonObjectBuilder;
    }


    //Visitable-controlled navigation:
//    private void callAccept(Visitable visitable) {
//        visitable.accept(this);
//    }

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
//        // Try the interfaces.  If necessary, you
//        // can sort them first to define 'visitable' interface wins
//        // in case an object implements more than one.
//        if (newc == Object.class) {
//            Class[] interfaces = c.getInterfaces();
//            for (final Class anInterface : interfaces) {
//                String method = anInterface.getName();
//                method = "visit" + method.substring(method.lastIndexOf('.') + 1);
//                try {
//                    m = getClass().getMethod(method, anInterface);
//                } catch (NoSuchMethodException ignored) {
//
//                }
//            }
//        }
        if (m == null) {
            try {
                m = this.getClass().getMethod("visitObject", Object.class, strClass);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return m;
    }


    public JsonObjectBuilder visitString(final String object, final String name ){

        return jsonObjectBuilder.add(name, object);
    }

    public JsonObjectBuilder visitInteger( final Integer object, final  String name){

        return jsonObjectBuilder.add(name, object);
    }

    public JsonObjectBuilder visitCharacter(final Character object, final  String name){
        jsonObjectBuilder.add(name, object.toString());
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitLong(final Long object, final  String name){
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitDouble(final Double object, final  String name){
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitBoolean(final Boolean object, final  String name){
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitObject(final Object object, final  String name) {

//        return this.visit(name, object, jsonObjectBuilder);
        System.out.println("visitObject");
        return jsonObjectBuilder;
    }



}
