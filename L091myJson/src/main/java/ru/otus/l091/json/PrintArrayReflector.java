package ru.otus.l091.json;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class PrintArrayReflector {
    private JsonObjectBuilder jsonObjectBuilder;


    public JsonObjectBuilder visit(final String name, final Object object, JsonObjectBuilder jsonObjectBuilder) {
        Method method = getMethod(object.getClass(), name.getClass());
        this.jsonObjectBuilder = jsonObjectBuilder;
        try {
            jsonObjectBuilder = (JsonObjectBuilder) method.invoke(this, object, name);
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
            String method = newc.getTypeName();
            method = method.substring(0, method.indexOf('['));
            method = "get" + method.substring(0, 1).toUpperCase() + method.substring(1);
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


    public JsonObjectBuilder visitString(final String object, final String name) {

        return jsonObjectBuilder.add(name, object);
    }

    public JsonObjectBuilder visitInteger(final Integer object, final String name) {

        return jsonObjectBuilder.add(name, object);
    }

    public JsonObjectBuilder visitCharacter(final Character object, final String name) {
        jsonObjectBuilder.add(name, object.toString());
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitLong(final Long object, final String name) {
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitDouble(final Double object, final String name) {
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitBoolean(final Boolean object, final String name) {
        jsonObjectBuilder.add(name, object);
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder visitObject(final Object object, final String name) {


        return jsonObjectBuilder =  new PrintReflector().visit(name, object, jsonObjectBuilder);
    }

    private final Class<?>[] ARRAY_PRIMITIVE_TYPES = {
            int[].class, float[].class, double[].class, boolean[].class,
            byte[].class, short[].class, long[].class, char[].class};

    private JsonObjectBuilder addJsonArray(String name, Object val, JsonObjectBuilder jsonObjectBuilder) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        Class<?> valKlass = val.getClass();
        Object[] outputArray = null;
        for (Class<?> arrKlass : ARRAY_PRIMITIVE_TYPES) {
            if (valKlass.isAssignableFrom(arrKlass)) {
                Method method = getMethod(arrKlass, int.class);
                int arrlength = Array.getLength(val);
                outputArray = new Object[arrlength];
                for (int i = 0; i < arrlength; ++i) {
                    try {
                        jsonArrayBuilder.add(method.invoke(val, i));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
        if (outputArray == null) { // not primitive type array
            outputArray = (Object[]) val;

        }
        return jsonObjectBuilder;
    }


}


