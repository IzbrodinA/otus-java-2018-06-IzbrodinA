package ru.otus.l131.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//@SuppressWarnings("ALL")
public class MergeSortThread {
    private static int SIZE_ARRAY = 19;
    private static int SIZE_THREADS = 4;
    private static List<Thread> pool_Threads = new ArrayList<>();
    private static Integer countThread = 0;
    static int[] array = new int[SIZE_ARRAY];
    private static int SIZE_PART_ARRAY = Math.floorDiv(SIZE_ARRAY , SIZE_THREADS);

    public static void main(String[] args) {

        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(1000);
        }

        System.out.println(Arrays.toString(array));

        for (int i = 0; i < SIZE_THREADS; i++) {
            pool_Threads.add(new Thread(() -> {
                sort();
            }));
        }

        for (int i = 0; i < SIZE_THREADS; i++) {
            pool_Threads.get(i).start();
        }

        for (int i = 0; i < SIZE_THREADS; i++) {
            try {
                pool_Threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(SIZE_PART_ARRAY);

        merge(0, (SIZE_ARRAY / 2 - 1) / 2, SIZE_ARRAY / 2 - 1);
        merge(SIZE_ARRAY / 2, SIZE_ARRAY / 2 + (SIZE_ARRAY - 1 - SIZE_ARRAY / 2) / 2, SIZE_ARRAY - 1);
        merge(0, (SIZE_ARRAY - 1) / 2, SIZE_ARRAY - 1);
        System.out.println(Arrays.toString(array));

    }

    static void merge(int low, int mid, int high) {
        int[] left = new int[mid - low + 1];
        int[] right = new int[high - mid];

        // n1 is size of left part and n2 is size
        // of right part
        int n1 = mid - low + 1, n2 = high - mid, i, j;

        // storing values in left part
        for (i = 0; i < n1; i++)
            left[i] = array[i + low];

        // storing values in right part
        for (i = 0; i < n2; i++)
            right[i] = array[i + mid + 1];

        int k = low;
        i = j = 0;

        // merge left and right in ascending order
        while (i < n1 && j < n2) {
            if (left[i] <= right[j])
                array[k++] = left[i++];
            else
                array[k++] = right[j++];
        }

        // insert remaining values from left
        while (i < n1) {
            array[k++] = left[i++];
        }

        // insert remaining values from right
        while (j < n2) {
            array[k++] = right[j++];
        }
    }

    private static void sort() {
        int sizePart;

        synchronized (countThread) {
            sizePart = countThread++;
        }

        int low = sizePart * (SIZE_ARRAY / SIZE_THREADS);
        int high = (sizePart + 1) * (SIZE_ARRAY / SIZE_THREADS) ;
        System.out.println(low + " " + high);

        int[] sortPartArrays = Arrays.copyOfRange(array, low, high );
        Arrays.sort(sortPartArrays);
        for (int i = low; i < high; i++) {
            array[i] = sortPartArrays[i - low];
        }

    }

}
