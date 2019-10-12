package ru.motrichkin.datastructures;

import java.util.Iterator;

public class MyLinkedQueueIterator<T> implements Iterator {

    private MyLinkedQueue queue;

    MyLinkedQueueIterator(MyLinkedQueue queue) {
        this.queue = queue;
    }

    @Override // to do
    public boolean hasNext() {
        return true;
    }

    @Override // to do
    public T next() {
        return null;
    }

    @Override // to do
    public void remove() {

    }

}
