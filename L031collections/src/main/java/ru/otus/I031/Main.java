package ru.otus.I031;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = new MyArrayList<>();

        Collections.addAll(integerList, 2, 4, -1, 0, 13);

        List<Integer> copyList = new MyArrayList<>(integerList.size());

        Collections.copy(copyList, integerList);
        Collections.sort(copyList, Comparator.reverseOrder());

//        Test
//        for (int i=0; i < copyList.size();i++){
//            System.out.println(copyList.get(i));
//        }
    }
}
