package ru.motrichkin.datastructures.hashtables;

import java.util.Arrays;
import java.util.LinkedList;

public class HashTableWithChains<Item> {

    private static int DIMENSION = 1000;

    private LinkedList<Item>[] array = new LinkedList[DIMENSION];

    private int myHashCode(Item item) {
        return item.hashCode() % DIMENSION;
    }

    private void checkCellAndMakeList(int cell) {
        if (array[cell] == null) {
            array[cell] = new LinkedList<>();
        }
    }

    public boolean add(Item item) {
        int cell = myHashCode(item);
        checkCellAndMakeList(cell);
        array[cell].add(item);
        return true;
    }

    public boolean contains(Item item) {
        int cell = myHashCode(item);
        if (array[cell] != null) {
            return array[cell].contains(item);
        }
        return false;
    }

    public boolean remove(Item item) {
        int cell = myHashCode(item);
        if (array[cell] != null) {
            return array[cell].remove(item);
        }
        return true;
    }

    public boolean removeEvery(Item item) {
        int cell = myHashCode(item);
        if (array[cell] != null) {
            while(array[cell].remove(item));
        }
        return true;
    }

    public Item[] toArray() {
        LinkedList<Item> list = new LinkedList<>();
        for (int i = 0; i < DIMENSION; i++) {
            if (array[i] != null) {
                list.addAll(array[i]);
            }
        }
        return (Item[]) list.toArray();
    }

    public String toString() {
        return Arrays.deepToString(this.toArray());
    }

    public String toStringByChains() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < DIMENSION; i++) {
            if (array[i] != null) {
                stringBuilder.append(Arrays.deepToString(array[i].toArray()));
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}
