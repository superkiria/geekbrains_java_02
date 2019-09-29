package ru.motrichkin.datastructures;

public class MyArrayList<ItemClass> {

    static protected final int DEFAULT_CAPACITY = 1;
    static protected final int EXPAND_COEFFICIENT = 2;

    protected ItemClass[] array;
    protected int firstItemIndex = 0;
    protected int lastItemIndex = -1;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        array = (ItemClass[]) new Object[capacity];
    }

    protected boolean arrayHasNoSpace() {
        return !(lastItemIndex + 1 < array.length);
    }

    protected void expandArray() {
        ItemClass[] newArray = (ItemClass[]) new Object[array.length * EXPAND_COEFFICIENT];
        for(int i = 0; i < lastItemIndex - firstItemIndex + 1; i++) {
            newArray[i] = array[i + firstItemIndex];
        }
        lastItemIndex = lastItemIndex - firstItemIndex;
        firstItemIndex = 0;
        array = newArray;
    }

    protected void beforeAdding(){
        if (arrayHasNoSpace()) {
            expandArray();
        }
    }

    public int getSize() {
        return lastItemIndex - firstItemIndex + 1;
    }

    public ItemClass get(int index) {
        return array[index + firstItemIndex];
    }

    public int firstIndexOf(ItemClass item) {
        int i = firstItemIndex;
        while (i <= lastItemIndex && !array[i].equals(item)) {
            i++;
        }
        return i <= lastItemIndex ? i - firstItemIndex : -1;
    }

    public void append(ItemClass item) {
        beforeAdding();
        array[lastItemIndex + 1] = item;
        lastItemIndex++;
    }

    public void add(ItemClass item, int position) {
        beforeAdding();
        for (int i = lastItemIndex; i > position; i--) {
            array[i + 1] = array[i];
        }
        array[position] = item;
    }

    public boolean delete(int index) {
        if (index < 0 || index > lastItemIndex - firstItemIndex) {
            return false;
        }
        if (index > (lastItemIndex - firstItemIndex) / 2) {
            for(int i = index; i < lastItemIndex ; i++) {
                array[i] = array[i + 1];
            }
            lastItemIndex--;
        } else {
            for(int i = index; i > firstItemIndex; i--) {
                array[i] = array[i - 1];
            }
            firstItemIndex++;
        }
        return true;
    }

    public String toString() {
        String result = "";
        for (int i = firstItemIndex; i <= lastItemIndex; i++) {
            result = result + array[i].toString() + " ";
        }
        return result;
    }

}
