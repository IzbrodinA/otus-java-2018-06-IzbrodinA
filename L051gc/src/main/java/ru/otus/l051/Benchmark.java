package ru.otus.l051;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;

    @SuppressWarnings("InfiniteLoopStatement")
    void run() throws InterruptedException {

        List<String> list = new ArrayList<>();
        System.out.println("List of size: " + list.size() + " created");
        System.out.println("Starting the loop");
        while (true) {
            int local = size;

            System.out.println("List of size: " + list.size() + " next loop");
            GC.printGCMetrics();
            for (int i = 0; i < local; i++) {
                list.add(new String(new char[0]));
            }
            System.out.println("Created " + local + " objects.");
            GC.printGCMetrics();
            if (local / 2 > 0) {
                list.subList(0, local / 2).clear();
            }


            System.out.println("Delete " + local / 2 + " objects.");
            GC.printGCMetrics();
         //   Thread.sleep(46000);    // for G1 java10
//            Thread.sleep(70000);    // for ParallelGC java10
            Thread.sleep(42000);    // for CMS and UseSerialGC java10
        }


    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}