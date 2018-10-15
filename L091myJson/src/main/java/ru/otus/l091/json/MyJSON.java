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
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;


public class MyJSON {
    private myType typeObj;
    private myType typeJSON;
    private PrintReflector printReflector = new PrintReflector();

    public String toJson(Object obj) {
        if (obj == null) {
            return writeToString(Json.createObjectBuilder().addNull("null").build());
        }
        return writeToString((JsonStructure) buildTree(null, obj, null));
    }

    private JsonValue buildTree(JsonValue tree, final Object obj, final String key) {
        defineType(obj);
        defineTypeJSON(tree);
        if (typeObj == myType.NULL || typeObj == myType.PRIMITIVE) {
            if (typeJSON == myType.NULL) {
                tree = printReflector.visit(obj, key);
            } else
            tree = joinJSONValue(tree, obj, key);
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

    private void defineTypeJSON(final JsonValue tree) {
        if (tree == null) {
            typeJSON = myType.NULL;
        } else {
           JsonValue.ValueType treeType = tree.getValueType();
            if (treeType == JsonValue.ValueType.ARRAY) {
                typeJSON = myType.ARRAY;
            } else if (treeType == JsonValue.ValueType.OBJECT) {
                typeJSON = myType.OBJECT;
            }
        }
    }

    private JsonValue joinJSONValue(JsonValue json, final Object obj, final String key) {
        if (typeJSON == myType.NULL) {
                json = (JsonValue) obj;
        } else if (typeJSON == myType.ARRAY) {
            JsonArrayBuilder jab = Json.createArrayBuilder().add(json);
            json = jab.add(printReflector.visit(obj, null)).build();
//          ((JsonArray) json).add(printReflector.visit(obj, null));
        } else if (typeJSON == myType.OBJECT) {
             ((JsonObject) json).put(key, printReflector.visit(obj,null));
        }
        return json;
    }

    private JsonValue joinJSONArray(JsonValue json, final Object obj, final String key) {
        JsonValue array = Json.createArrayBuilder().build();
        json = joinJSONValue(json, array, key);
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            buildTree(array, Array.get(obj, i), null);
        }
        return json;
    }


    private JsonValue joinJSONObject(JsonValue json, final Object obj, final String key) {
        JsonValue jsonObject = Json.createObjectBuilder().build();
        json = joinJSONValue(json, jsonObject, key);
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
            buildTree(jsonObject, value, field.getName());
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
            Long.class,
            Float.class,
            Double.class
    };


}

