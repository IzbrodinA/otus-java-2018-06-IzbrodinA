package ru.otus.dbService.cache;

/**
 * Created by tully.
 */
public interface CacheEngine<K, V> {

    void put(MyElement<K, V> element);

    MyElement<K, V> get(K key);
    public int sizeCashe();

    void dispose();
}
