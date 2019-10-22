package ru.motrichkin.datastructures.hashtables;

import java.util.Arrays;
import java.util.LinkedList;

public class HashTableWithAddresses<Item> {

    private static int DIMENSION = 1000;

    private Item[] array = (Item[]) new Object[DIMENSION];

    private int myHashCode(Item item) {
        return item.hashCode() % DIMENSION;
    }

    private static int cellPlusPlus(int cell) {
        return (cell + 1) % DIMENSION;
    }

    public boolean add(Item item) {
        int initCell = myHashCode(item);
        int cell = initCell;
        while(array[cell] != null && cellPlusPlus(cell) != initCell) {
            cell = cellPlusPlus(cell);
        }
        if (array[cell] == null) {
            array[cell] = item;
            return true;
        }
        return false;
    }

    public boolean contains(Item item) {
        int initCell = myHashCode(item);
        int cell = initCell;
        while(array[cell] != null && cellPlusPlus(cell) != initCell) {
            if (array[cell].equals(item)) {
                return true;
            }
            cell = cellPlusPlus(cell);
        }
        return false;
    }

    public boolean remove(Item item) {
        int initCell = myHashCode(item);
        int cell = initCell;
        while(array[cell] != null && !array[cell].equals(item) && cellPlusPlus(cell) != initCell) {
            cell = cellPlusPlus(cell);
        }
        if (array[cell] == null) {
            return false;
        }
        array[cell] = null;
        while(array[cellPlusPlus(cell)] != null && cellPlusPlus(cell) != initCell) {
            cell = cellPlusPlus(cell);
            if (myHashCode(array[cell]) != cell) {
                Item temp = array[cell];
                array[cell] = null;
                this.add(temp);
            }
        }
        return true;
    }

    public boolean removeEvery(Item item) {
        while(this.remove(item));
        return true;
    }

    public Item[] toArray() {
        LinkedList<Item> list = new LinkedList<>();
        for (int i = 0; i < DIMENSION; i++) {
            if (array[i] != null) {
                list.add(array[i]);
            }
        }
        return (Item[]) list.toArray();
    }

    public Item[] toArrayDirectly() {
        return array;
    }

    public String toString() {
        return Arrays.deepToString(this.toArray());
    }

    public String toStringDirectly() {
        return Arrays.deepToString(this.toArrayDirectly());
    }

}
