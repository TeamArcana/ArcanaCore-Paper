package com.arcana.utils;

/**
 * Simply util to pair two things together without having to use Map.Entry.
 * @param <K>
 * @param <T>
 */
public class ArcanaPair<K, T> {

    private K key;
    private T value;

    public ArcanaPair(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
