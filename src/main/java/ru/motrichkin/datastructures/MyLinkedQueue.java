package ru.motrichkin.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyLinkedQueue<T> implements Queue {

    class Link {
        private Link next = null;
        private Link prev = null;
        private final T object;

        Link(T object, Link next, Link prev) {
            this.object = object;
            this.next = next;
            this.prev = prev;
        }

        Link getNext() {
            return next;
        }

        void setNext(Link link) {
            this.prev = link;
        }

        Link getPrev() {
            return prev;
        }

        void setPrev(Link link) {
            this.prev = link;
        }

        T getObject() {
            return object;
        }
    }

    private Link head;
    private Link tail;
    private int size = 0;

    @Override
    public boolean add(Object object) {
        tail = new Link((T) object, tail, null);
        tail.getNext().setPrev(tail);
        size++;
        return true;
    }

    @Override
    public boolean offer(Object object) {
        return add(object);
    }

    @Override
    public T remove() {
        T result = head.getObject();
        head = head.getPrev();
        head.setNext(null);
        size--;
        return result;
    }

    @Override
    public T poll() {
        if (size > 0) {
            return remove();
        }
        return null;
    }

    @Override
    public T element() {
        return head.getObject();
    }

    public T peek() {
        if (size > 0) {
            return element();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 1;
    }

    @Override // to do
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedQueueIterator<T>(this);
    }

    @Override // to do
    public Object[] toArray() {
        return new Object[0];
    }

    @Override // to do
    public T[] toArray(Object[] array) {
        return (T[]) new Object[0];
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override // to do
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override // to do
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override // to do
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override // to do
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        tail = null;
        head = null;
    }

}
