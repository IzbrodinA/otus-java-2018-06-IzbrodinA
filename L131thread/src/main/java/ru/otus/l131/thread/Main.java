package ru.otus.l131.thread;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private  static int SIZE_ARRAY = 20;

    public static void main(String[] args) {
        int[] array = new int[SIZE_ARRAY];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        System.out.println(Arrays.toString(array));


    }
}
