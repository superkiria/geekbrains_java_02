package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.MyArrayListWithSorting;

@RunWith(JUnit4.class)
public class MyArrayListWithSortingTests {

    private static int AMOUNT_OF_ELEMENTS = 10000;

    private static Random randomForSeed = new Random();

    private static int seed = randomForSeed.nextInt();

    private static boolean isSorted(MyArrayListWithSorting list) {
        for (int i = 0; i < list.getSize() - 1; i++) {
            if (((Comparable) list.get(i)).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    private static MyArrayListWithSorting randomMyArrayListWithSorting(int size) {
        MyArrayListWithSorting<Integer> list = new MyArrayListWithSorting<Integer>();
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) {
            list.append(random.nextInt(100));
        }
        return list;
    }

    @Test
    public void isSortedTestOfTest_01() {
        MyArrayListWithSorting<Integer> list = new MyArrayListWithSorting<Integer>();
        list.append(-1);
        Assert.assertTrue(isSorted(list));
        list.append(1);
        Assert.assertTrue(isSorted(list));
        list.append(-1);
        Assert.assertFalse(isSorted(list));
    }

    @Test
    public void randomMyArrayListWithSortingTest() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        Assert.assertFalse(isSorted(list));
    }

    @Test
    public void bubbleSortTest() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        MyArrayListWithSorting listForCheck = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.bubbleSort();
        Assert.assertTrue(isSorted(list));
        listForCheck.qSort();
        Assert.assertEquals(listForCheck.toString(), list.toString());
    }

    @Test
    public void insertionSortTest() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        MyArrayListWithSorting listForCheck = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.insertionSort();
        Assert.assertTrue(isSorted(list));
        listForCheck.qSort();
        Assert.assertEquals(listForCheck.toString(), list.toString());
    }

    @Test
    public void selectionSortTest() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        MyArrayListWithSorting listForCheck = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.selectionSort();
        Assert.assertTrue(isSorted(list));
        listForCheck.qSort();
        Assert.assertEquals(listForCheck.toString(), list.toString());
    }

    @Test
    public void qSortTest() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.qSort();
        Assert.assertTrue(isSorted(list));
    }

    @Test
    public void bubbleSortSpeedCheck() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.bubbleSort();
    }

    @Test
    public void insertionSortSpeedCheck() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.insertionSort();
    }

    @Test
    public void selectionSortSpeedCheck() {
        MyArrayListWithSorting list = randomMyArrayListWithSorting(AMOUNT_OF_ELEMENTS);
        list.selectionSort();
    }

}
