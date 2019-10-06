package ru.motrichkin.datastructures;

public class MyDeque<ItemClass> {

    static private int DEFAULT_CAPACITY = 3;

    protected int capacity;
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
        this.capacity = capacity;
        array = (ItemClass[]) new Object[capacity];
        left = 0;
        right = left + 1;
    }

    protected void expand() {
        int newCapacity = capacity * 2;
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
        right = newLeft + size;
        capacity = newCapacity;
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

    public int size() {
        return right - left;
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
        right--;
        ItemClass result = array[realRight()];
        array[realRight()] = null;
        return result;
    }

    public ItemClass popLeft() {
        left++;
        ItemClass result = array[realLeft()];
        array[realLeft()] = null;
        return result;
    }

}
