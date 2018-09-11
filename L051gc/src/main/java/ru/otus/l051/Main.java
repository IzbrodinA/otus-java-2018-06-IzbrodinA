package ru.otus.l051;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by tully.
 * <p>
 * Java 9 changes in logs:https://dzone.com/articles/disruptive-changes-to-gc-logging-in-java-9
 * C
 */
/*
 -agentlib:jdwp=transport=dt_socket,address=14000,server=y,suspend=n
 -Xms512m
 -Xmx512m
 -XX:MaxMetaspaceSize=256m
 -verbose:gc
 -Xlog:gc:file=./L051gc/logs/gc_pid_%p.log
 -Dcom.sun.management.jmxremote.port=15000
 -Dcom.sun.management.jmxremote.authenticate=false
 -Dcom.sun.management.jmxremote.ssl=false
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:HeapDumpPath=./L051gc/dumps/

 -XX:+UseG1GC
 -XX:ParallelGCThreads=2

 -XX:+UseParallelGC -XX:+UseParallelOldGC

 -XX:+UseConcMarkSweepGC


 -XX:+UseSerialGC

-XX:+PrintGC
-Xloggc:./L051gc/logs/gc_pid_%p.log
 -XX:+UseShenandoahGC
 -XX:+UnlockExperimentalVMOptions
 -XX:ShenandoahUncommitDelay=1000
 -XX:ShenandoahGuaranteedGCInterval=10000

 jps -- list vms or ps -e | grep java
 jstack <pid> >> threaddumps.log -- get dump from pid
 jinfo -- list VM parameters
 jhat /  jvisualvm-- analyze heap dump
 */

public class Main {

    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 5 * 1000 * 1000;
//        int size = 100 * 1000;
//        int size = 50 * 1000 * 1000;//for OOM with -Xms512m
//        int size = 50 * 1000 * 100; //for small dump

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setSize(size);
        mbean.run();
    }
}
