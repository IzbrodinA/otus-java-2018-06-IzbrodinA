package ru.otus.l091.json;


import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;


public class MyJSON {
    private myType typeObj;
    private myType typeJSON;
    private PrintReflector printReflector = new PrintReflector();
    private String IF_FIRST_OBJECT_IS_NOT_OBJECT = "IF_FIRST_OBJECT_IS_NOT_OBJECT";

    public String toJson(Object obj) {
        defineType(obj);
        if (typeObj == myType.NULL) {
            return "null";
        }
        if (typeObj == myType.PRIMITIVE){
            if (String.class.isAssignableFrom(obj.getClass()) || Character.class.isAssignableFrom(obj.getClass())){
                obj = "\""+obj + "\"";
            }
            return obj.toString();
        }
        if (typeObj == myType.ARRAY) {
            return writeToString(buildTree(null, obj, null).build().getJsonArray(IF_FIRST_OBJECT_IS_NOT_OBJECT));
        }

        return writeToString(buildTree(null, obj, null).build());
    }

    private JsonObjectBuilder buildTree(JsonObjectBuilder tree, final Object obj, final String key) {
        defineType(obj);
        if (typeObj == myType.NULL || typeObj == myType.PRIMITIVE) {
                tree = printReflector.visit(obj, key, tree);
        } else if (typeObj == myType.ARRAY) {
            tree = joinJSONArray(tree, obj, key);
        } else if (typeObj == myType.OBJECT) {
            tree = joinJSONObject(tree, obj, key);
        }
        return tree;

    }

    private void defineType(final Object obj) {
        if (obj == null) {
            typeObj = myType.NULL;
        } else {
            Class<?> objClass = obj.getClass();
            if (isPrimitive(objClass)) {
                typeObj = myType.PRIMITIVE;
            } else if (objClass.isArray() || Collection.class.isAssignableFrom(objClass)) {
                typeObj = myType.ARRAY;
            } else {
                typeObj = myType.OBJECT;
            }
        }
    }

    private JsonObjectBuilder joinJSONArray(JsonObjectBuilder json, final Object obj, final String key) {
        JsonArrayBuilder array = Json.createArrayBuilder();
        boolean arrayIsGetPrimitive = false;
        //If Obj is Collection
        if (Collection.class.isAssignableFrom(obj.getClass())){

            if ( !((Collection) obj).isEmpty()){
                arrayIsGetPrimitive = isPrimitive( ((Collection) obj).iterator().next().getClass()) ;
            }

            for (Object elementCollection : (Collection) obj) {
                    if (arrayIsGetPrimitive) {
                        array = printReflector.visit(elementCollection, null, array);
                    } else {
                        array.add(buildTree(null, elementCollection, null));
                    }
            }
        }else {
            //If Obj is Array
            int length = Array.getLength(obj);
            if (length != 0) {
                Class<?> elementArrayClass = Array.get(obj, 0).getClass();
                arrayIsGetPrimitive = isPrimitive(elementArrayClass);
            }
            for (int i = 0; i < length; i++) {
                if (arrayIsGetPrimitive) {
                    array = printReflector.visit(Array.get(obj, i), null, array);
                } else {
                    array.add(buildTree(null, Array.get(obj, i), null));
                }
            }
        }
        if (json == null) {
            json = Json.createObjectBuilder().add(IF_FIRST_OBJECT_IS_NOT_OBJECT, array);
        } else {
            json.add(key, array);
        }
        return json;
    }


    private JsonObjectBuilder joinJSONObject(JsonObjectBuilder json, final Object obj, final String key) {
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        if (json == null) {
            json = jsonObject;
        }else {
            json.add(key,jsonObject);
        }
        Field fields[] = obj.getClass().getDeclaredFields();
        Field field;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            Object value;
            field.setAccessible(true);
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            jsonObject = buildTree(jsonObject, value, field.getName());
        }
        return json;
    }


    private static String writeToString(JsonStructure jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.write(jsonst);
        }

        return stWriter.toString();
    }

    private boolean isPrimitive(Class<?> fieldType) {
        return Arrays.stream(PRIMITIVES)
                .anyMatch(primitive -> primitive.isAssignableFrom(fieldType));
    }

    private static final Class<?> PRIMITIVES[] = {
            Boolean.class,
            String.class,
            Byte.class,
            Short.class,
            Integer.class,
            Character.class,
            Long.class,
            Float.class,
            Double.class
    };


}

