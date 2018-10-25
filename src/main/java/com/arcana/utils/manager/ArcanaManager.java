package com.arcana.utils.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An extensible Manager class to easily create Managers of any sort. UserManager, ChatChannelManager, etc.
 * @param <T>
 */
public abstract class ArcanaManager<T> {

    protected List<T> objects;

    public ArcanaManager() {
        objects = new CopyOnWriteArrayList<>();
    }

    public abstract void addNoDuplicate(T t);

    public void add(T t){
        objects.add(t);
    }

    public void remove(T t){
        objects.remove(t);
    }

    public List<T> getObjects() {
        return objects;
    }
}
