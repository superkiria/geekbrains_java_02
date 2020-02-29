package ru.motrichkin.datastructures;

import java.util.*;

public class MyArrayDeque<ItemClass> implements Deque {

    static private int DEFAULT_CAPACITY = 3;

    private int initial_capacity;
    private int right;
    private int left;
    private ItemClass[] array;

    public MyArrayDeque() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayDeque(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        initial_capacity = capacity;
        array = (ItemClass[]) new Object[capacity];
        left = 0;
        right = left + 1;
    }

    private void expand() {
        int newCapacity = array.length * 2;
        ItemClass[] newArray = (ItemClass[]) new Object[newCapacity];
        int newLeft = newCapacity / 4;
        if (realPosition(left + 1) <= realPosition(right - 1)) {
//            System.out.println("A");
            System.arraycopy(array, realPosition(left + 1), newArray, newLeft + 1, size());
        } else {
//            System.out.println("B " + left + " " + realPosition(left + 1));
            System.arraycopy(array, realPosition(left + 1), newArray, newLeft + 1, array.length - realLeft() - 1);
            System.arraycopy(array, 0, newArray, newLeft + array.length - realLeft(), realRight());
        }
        array = newArray;
        int size = size();
        left = newLeft;
        right = newLeft + size + 1;
    }

    private void checkCapacity() {
        if (array.length <= size() + 2) {
            expand();
        }
    }

    private int realRight() {
        return realPosition(right);
    }

    private int realLeft() {
        return realPosition(left);
    }

    private int realPosition(int position) {
        return position >= 0 ? position % array.length : array.length + position;
    }

    private void remove(int position) {
        if (position - left < right - position) {
            // вариант А
            if (realPosition(left + 1) <= realPosition(position)) {
                // вариант А.1
                System.out.println("A1");
                System.arraycopy(array, realPosition(left + 1), array, realPosition(left + 1) + 1, realPosition(position) - realPosition(left + 1));
                left++;
                array[realPosition(left)] = null;
            } else {
                // вариант А.5
                System.out.println("A5");
                System.arraycopy(array, 0, array, 1, realPosition(position));
                array[0] = array[array.length - 1];
                System.arraycopy(array, realPosition(right + 1), array, realPosition(right + 1) + 1, array.length - realPosition(right) - 2);
                left++;
                array[realPosition(left)] = null;
            }
        } else {
            // вариант B
            if (realPosition(position) <= realPosition(right - 1)) {
                System.out.println("B2");
                System.arraycopy(array, realPosition(position + 1), array, realPosition(position), realPosition(right - 1) - realPosition(position));
                right--;
                array[realPosition(right)] = null;
            } else {
                System.out.println("B6");
                System.arraycopy(array, realPosition(position + 1), array, realPosition(position), array.length - realPosition(position) - 1);
                array[array.length - 1] = array[0];
                System.arraycopy(array, 1, array, 0, realPosition(right) - 1);
                right--;
                array[realPosition(right)] = null;
            }

        }
    }

    public void appendRight(ItemClass item) {
        checkCapacity();
        array[realRight()] = item;
        right++;
    }

    public void appendLeft(ItemClass item) {
        checkCapacity();
        array[realLeft()] = item;
        left--;
    }

    public ItemClass popRight() {
        throwExceptionIfEmpty();
        right--;
        ItemClass result = array[realRight()];
        array[realRight()] = null;
        return result;
    }

    public ItemClass popLeft() {
        throwExceptionIfEmpty();
        left++;
        ItemClass result = array[realLeft()];
        array[realLeft()] = null;
        return result;
    }

    private void throwExceptionIfEmpty() {
        if (size() == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
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
        return true;
    }

    @Override
    public boolean offerLast(Object o) {
        appendRight((ItemClass) o);
        return true;
    }

    @Override
    public Object removeFirst() {
        throwExceptionIfEmpty();
        return popLeft();
    }

    @Override
    public Object removeLast() {
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
        throwExceptionIfEmpty();
        return array[realPosition(left + 1)];
    }

    @Override
    public Object getLast() {
        throwExceptionIfEmpty();
        return array[realPosition(left + 1)];
    }

    @Override
    public Object peekFirst() {
        return array[realPosition(left + 1)];
    }

    @Override
    public Object peekLast() {
        return array[realPosition(right - 1)];
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        int i = left;
        do {
            i++;
        } while (i < right && ! array[realPosition(i)].equals(o));
        if (i < right) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        int i = right;
        do {
            i--;
        } while (i > left && ! array[realPosition(i)].equals(o));
        if (i > left) {
            remove(i);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Object o) {
        appendRight((ItemClass) o);
        return true;
    }

    @Override
    public boolean offer(Object o) {
        appendRight((ItemClass) o);
        return false;
    }

    @Override
    public Object remove() {
        throwExceptionIfEmpty();
        return poll();
    }

    @Override
    public Object poll() {
        if (size() == 0) {
            return null;
        } else {
            return popLeft();
        }
    }

    @Override
    public Object element() {
        return getFirst();
    }

    @Override
    public Object peek() {
        if (size() == 0) {
            return null;
        }
        return getFirst();
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object o : collection) {
            appendRight((ItemClass) o);
        }
        return true;
    }

    @Override
    public void clear() {
        array = (ItemClass[]) new Object[initial_capacity];
        left = 0;
        right = left + 1;
    }

    @Override
    public boolean retainAll(Collection collection) {
        while (size() > 0 && !collection.contains(peekFirst())) {
            removeFirst();
        }
        while (size() > 0 && !collection.contains(peekLast())) {
            removeLast();
        }
        for (int i = left + 2; i < right - 1 && size() > 0; i++) {
            if (!collection.contains(array[realPosition(i)])) {
                remove(i);
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection collection) {
        while (size() > 0 && collection.contains(peekFirst())) {
            removeFirst();
        }
        while (size() > 0 && collection.contains(peekLast())) {
            removeLast();
        }
        for (int i = left + 2; i < right - left / 2; i++) {
            if (collection.contains(array[realPosition(i)])) {
                remove(i);
            }
        }
        for (int i = right - 2; i >= right - left / 2; i--) {
            if (collection.contains(array[realPosition(i)])) {
                remove(i);
            }
        }
        return true;
    }

    @Override
    public void push(Object o) {
        appendLeft((ItemClass) o);
    }

    @Override
    public Object pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        int i = left;
        do {
            i++;
        } while (i < right && ! array[realPosition(i)].equals(o));
        return i < right;
    }

    @Override
    public int size() {
        return right - left - 1;
    }

    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size()];
        if (realLeft() < realRight()) {
            System.arraycopy(array, realPosition(left + 1), newArray, 0, size());
        } else {
            System.arraycopy(array, realPosition(left + 1), newArray, 0, array.length - realLeft() - 1);
            System.arraycopy(array, 0, newArray, array.length - realLeft() - 1, realRight());
        }
        return newArray;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (size() > objects.length) {
            return toArray();
        } else {
            for (int i = left + 1; i < right; i++) {
                objects[i - left - 1] = array[realPosition(i)];
            }
            for (int i = size(); i < objects.length; i++) {
                objects[i] = null;
            }
            return objects;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Iterator descendingIterator() {
        return null;
    }

    public Object[] getUnderlyingArray() {
        return array;
    }

    public String underlyingToString() {
        return "left: " + left + " right: " + right + " realLeft: " + realPosition(left)
                + " realRight: " + realPosition(right) + " " + Arrays.toString(getUnderlyingArray());
    }

}
