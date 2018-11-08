package ru.otus.l131.thread;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class MergeSortThreadTest {

   Random random;

    @Before
    public void setUp()  {
        random = new Random();


    }

    @Test
    public void checkNull() {
        int[] array = new int[0];
        int[] copyArray = Arrays.copyOf(array, array.length);
        MergeSortThread.sort(array);
        Arrays.sort(copyArray);
        assertArrayEquals(copyArray, array);
    }

    @Test
    public void checkArrayLength1() {
        int[] array = new int[1];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] copyArray = Arrays.copyOf(array, array.length);
        MergeSortThread.sort(array);
        Arrays.sort(copyArray);
        assertArrayEquals(copyArray, array);
    }

    @Test
    public void checkArrayLength4() {
        int[] array = new int[4];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        int[] copyArray = Arrays.copyOf(array, array.length);
        MergeSortThread.sort(array);
        Arrays.sort(copyArray);
        assertArrayEquals(copyArray, array);
    }


    @Test
    public void checkArrayLength20() {
        int[] array = new int[20];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] copyArray = Arrays.copyOf(array, array.length);

        MergeSortThread.sort(array);
        Arrays.sort(copyArray);

        assertArrayEquals(copyArray, array);
    }

    @Test
    public void checkArrayLength19() {
        int[] array = new int[19];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] copyArray = Arrays.copyOf(array, array.length);

        MergeSortThread.sort(array);
        Arrays.sort(copyArray);

        assertArrayEquals(copyArray, array);
    }

    @Test
    public void checkTwiceArrayLength19() {
        int[] array = new int[19];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] copyArray = Arrays.copyOf(array, array.length);

        MergeSortThread.sort(array);
        MergeSortThread.sort(array);
        Arrays.sort(copyArray);

        assertArrayEquals(copyArray, array);
    }

    @Test
    public void checkArrayLength1000() {
        int[] array = new int[1000];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }
        int[] copyArray = Arrays.copyOf(array, array.length);

        MergeSortThread.sort(array);
        Arrays.sort(copyArray);

        assertArrayEquals(copyArray, array);
    }
}