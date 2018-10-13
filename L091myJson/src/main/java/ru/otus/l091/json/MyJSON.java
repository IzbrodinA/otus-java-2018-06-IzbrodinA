package ru.otus.l091.json;


import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.json.JsonWriter;


public class MyJSON {
    PrintReflector printReflector = new PrintReflector();
    PrintArrayReflector printArrayReflector = new PrintArrayReflector();

    public String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        return writeToString((JsonObject) create(obj));
    }

    private JsonStructure create(final Object obj) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (!Modifier.isStatic(fields[i].getModifiers())) {
                String name = fields[i].getName();
                Object object = ReflectionHelper.getFieldValue(obj, name);
                if (object.getClass().isArray()) {
                    jsonObjectBuilder = printArrayReflector.visit(name, object, jsonObjectBuilder);
                } else {
                    jsonObjectBuilder =printReflector.visit(name, object, jsonObjectBuilder);
                }
            }
        }
        return jsonObjectBuilder.build();
    }


    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }

        return stWriter.toString();
    }

//    private <T> JsonObjectBuilder addArray(final String name, T[] array, final  JsonObjectBuilder jsonObjectBuilder){
//        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
//        for (int i = 0; i < array.length; i++) {
////            jsonArrayBuilder.add( array[i]);
//        }
//        return jsonObjectBuilder.add(name, jsonArrayBuilder);
//    }



    }

