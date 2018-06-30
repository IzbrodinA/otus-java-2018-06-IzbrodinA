package ru.otus.l011;


import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;


public class testMaven {

    public static void main(String[] args) {
        OrderedMap<String,String> map = new LinkedMap<>();
        map.put("Hello ", " World");
        System.out.println(map.toString());

    }
}
