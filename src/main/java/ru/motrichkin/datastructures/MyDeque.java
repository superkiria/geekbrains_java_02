package ru.motrichkin.datastructures;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDeque<ItemClass> implements Deque {

    static private int DEFAULT_CAPACITY = 3;

    protected int right;
    protected int left;
    protected ItemClass[] array;

    public MyDeque() {
        this(DEFAULT_CAPACITY);
    }

    public MyDeque(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        array = (ItemClass[]) new Object[capacity];
        left = 0;
        right = left + 1;
    }

    protected void expand() {
        int newCapacity = array.length * 2;
        ItemClass[] newArray = (ItemClass[]) new Object[newCapacity];
        int newLeft = newCapacity / 4;
        if (realLeft() < realRight()) {
            System.arraycopy(array, realLeft(), newArray, newLeft, size());
        } else {
            System.arraycopy(array, realLeft(), newArray, newLeft, array.length - realLeft());
            System.arraycopy(array, 0, newArray, newLeft + (array.length - realLeft()), realRight());
        }
        array = newArray;
        int size = size();
        left = newLeft;
        right = newLeft + size + 1;
    }

    protected void checkCapacity() {
        if (! (array.length - (right - left) > 0)) {
            expand();
        }
    }

    protected int realRight() {
        return right >= 0 ? right % array.length: array.length + right;
    }

    protected int realLeft() {
        return left >= 0 ? left % array.length : array.length + left;
    }

    public void appendRight(ItemClass item) {
        array[realRight()] = item;
        right++;
        checkCapacity();
    }

    public void appendLeft(ItemClass item) {
        array[realLeft()] = item;
        left--;
        checkCapacity();
    }

    public ItemClass popRight() {
        if (size() > 0) {
            right--;
        }
        ItemClass result = array[realRight()];
        array[realRight()] = null;
        return result;
    }

    public ItemClass popLeft() {
        if (size() > 0) {
            left++;
        }
        ItemClass result = array[realLeft()];
        array[realLeft()] = null;
        return result;
    }

    @Override
    public void addFirst(Object o) {
        appendLeft((ItemClass) o);
    }

    @Override
    public void addLast(Object o) {
        appendRight((ItemClass) o);
    }

    @Override
    public boolean offerFirst(Object o) {
        appendLeft((ItemClass) o);
    }

    @Override
    public boolean offerLast(Object o) {
        appendRight((ItemClass) o);
    }

    @Override
    public Object removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        return popLeft();
    }

    @Override
    public Object removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        return popRight();
    }

    @Override
    public Object pollFirst() {
        return popLeft();
    }

    @Override
    public Object pollLast() {
        return popRight();
    }

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public Object getLast() {
        return null;
    }

    @Override
    public Object peekFirst() {
        return null;
    }

    @Override
    public Object peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {
        return null;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    @Override
    public void push(Object o) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return right - left - 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    @Override
    public Iterator descendingIterator() {
        return null;
    }

}
