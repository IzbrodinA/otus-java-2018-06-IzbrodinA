package ru.otus.l061;

/**
 *
 *  * <p>
 *  * VM options: -Xmx25m -Xms25m
 */
public class CacheMain {

    public static void main(String[] args) throws InterruptedException {
//        new CacheMain().eternalCacheExample();
        new CacheMain().lifeCacheExample();
    }

    private void eternalCacheExample() {
        int size = 175_000;
//        CacheEngine<Integer, String> cache = new CacheEngineImpl<>(size, 0, 0, true);
        CacheEngine<Integer, String> cache = new SoftCacheEngineImpl<>(size, 0, 0, true);

        for (int i = 0; i < size*2; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }

        for (int i = 0; i < size*2; i++) {
            MyElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }

    private void lifeCacheExample() throws InterruptedException {
        int size = 100_000;
//        CacheEngine<Integer, String> cache = new CacheEngineImpl<>(size, 500, 0, false);
        CacheEngine<Integer, String> cache = new SoftCacheEngineImpl<>(size, 500, 0, false);

        for (int i = 0; i < size; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
//            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        Thread.sleep(400);

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
//            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }

}
