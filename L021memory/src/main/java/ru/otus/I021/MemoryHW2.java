package ru.otus.I021;

import java.util.*;
import java.util.function.Supplier;

public class MemoryHW2 {
    public static void main(String[] args) throws InterruptedException {
        // 23
        System.out.println("Element size stringEmpty: " + getElementsize(() -> new String("")));

        System.out.println("Element size stringEmpty2: " + getElementsize(() -> new String("")));
        // 23
        System.out.println("Element size stringWithValue_1: " + getElementsize(() -> new String(""+1)));
    //23
        System.out.println("Element size stringEmptyChar: " + getElementsize(() -> new String(new char[0])));

    //39 or 40
        System.out.println("Element size stringEmptyByte: " + getElementsize(() -> new String(new byte[0])));

    //39 or 40
        System.out.println("Element size arrayListEmpty: " + getElementsize(() -> new ArrayList<>(0)));

    //24
        System.out.println("Element size arrayList(10): " + getElementsize(() -> new ArrayList<>()));

    //440
        System.out.println("Element size arrayList(100 Integer) : " + getElementsize(() -> {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        ((ArrayList<Integer>) arrayList).trimToSize();
        return arrayList;
    }));

    //480
        System.out.println("Element size arrayList(100) without Trim: " + getElementsize(() -> {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }));
    //560
        System.out.println("Element size arrayList(10 String) : " + getElementsize(() -> {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("" + i);
        }
        ((ArrayList<String>) arrayList).trimToSize();
        return arrayList;
    }));

    //32
        System.out.println("Element size linkedList(10): " + getElementsize(LinkedList::new));
    //48
        System.out.println("Element size HashMap(10): " + getElementsize(HashMap::new));
    //64
        System.out.println("Element size HashSet(10): " + getElementsize(HashSet::new));
//
    }

    private static long getElementsize(Supplier<?> supplier) throws InterruptedException {
        int size = 1_000_000;

        long mem = getMem();
      //  System.out.println("Mem: " + mem);
        Object[] array = new Object[size];

        long mem2 = getMem();
       // System.out.println("Ref size: " + (mem2 - mem) / array.length);

        for (int i = 0; i < array.length; i++) {
            array[i] = supplier.get();
        }

        long mem3 = getMem();
         long result = (mem3 - mem2) / array.length;
        array = null;
        return result;
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

