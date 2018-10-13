package ru.otus.l091.json;

import com.google.gson.Gson;

import ru.otus.l091.json.Elements.PrimitiveObject;
import ru.otus.l091.json.Elements.SimpleObject;

public class Demo {
    public static void main(String[] args) {
        SimpleObject obj = new SimpleObject();
//        PrimitiveObject obj = new PrimitiveObject(999);
//        Object obj = null;
        Gson gson = new Gson();
//        String obj = "aa";
        String jsonGson = gson.toJson(obj);
//        System.out.println("gson:");
        System.out.println(jsonGson);
        MyJSON myJSON = new MyJSON();
        String jsonMyJson = myJSON.toJson(obj);
//        System.out.println("myJSON");
        System.out.println(jsonMyJson);
    }
}
