package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.MyArrayDeque;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@RunWith(JUnit4.class)
public class MyArrayDequeTests {

    private int AMOUNT_OF_ELEMENTS = 100000;

    @Test
    public void sizeTest() {
        MyArrayDeque<Object> deque = new MyArrayDeque<Object>(4);
        Assert.assertEquals(0, deque.size());
        for(int i = 1; i < 10000; i++) {
            deque.appendRight(new Object());
            Assert.assertEquals(i, deque.size());
        }
        for(int i = 9998; i >= 0; i--) {
            deque.popRight();
            Assert.assertEquals(i, deque.size());
        }
        deque = new MyArrayDeque<Object>();
        Assert.assertEquals(0, deque.size());
        for(int i = 1; i < 10000; i++) {
            deque.appendLeft(new Object());
            Assert.assertEquals(i, deque.size());
        }
        for(int i = 9998; i >= 0; i--) {
            deque.popLeft();
            Assert.assertEquals(i, deque.size());
        }
    }

    @Test
    public void expandingTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(5);
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popLeft());
        }
    }

    @Test
    public void expandingTest_02() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(6);
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popRight());
        }
    }

    @Test
    public void expandingTest_03() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(7);
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (Integer i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            Assert.assertEquals(i, deque.popLeft());
        }
    }

    @Test
    public void expandingTest_04() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(8);
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (Integer i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            Assert.assertEquals(i, deque.popRight());
        }
    }

    @Test
    public void bothSidesTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(9);
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popRight());
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popRight());
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popLeft());
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popLeft());
        }
    }

    @Test
    public void removeFirstOccurrenceTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(3);
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(3);
        deque.appendRight(3);
        deque.appendRight(4);
        deque.appendRight(5);
        deque.appendRight(6);
        deque.appendRight(6);
        deque.appendRight(7);
        deque.removeFirstOccurrence(2);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 3, 4, 5, 6, 6, 7})));
        deque.removeFirstOccurrence(3);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 4, 5, 6, 6, 7})));
        deque.removeFirstOccurrence(4);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 5, 6, 6, 7})));
        deque.removeFirstOccurrence(5);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 6, 6, 7})));
        deque.removeFirstOccurrence(7);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 6, 6})));
        deque.removeFirstOccurrence(6);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 3, 6})));

    }

    @Test
    public void removeFirstOccurrenceTest_02() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(3);
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(3);
        deque.appendRight(4);
        deque.appendLeft(5);
        deque.appendLeft(6);
        deque.appendLeft(7);
        deque.appendLeft(8);
        deque.appendLeft(9);
        deque.appendLeft(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 9, 8, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(8);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 9, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(9);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{7, 6, 5, 1, 2, 3, 4})));
        deque.appendLeft(8);
        deque.appendLeft(9);
        deque.appendLeft(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 9, 8, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(9);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 8, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(8);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{10, 7, 6, 5, 1, 2, 3, 4})));
        deque.removeFirstOccurrence(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{7, 6, 5, 1, 2, 3, 4})));

    }

    @Test
    public void removeFirstOccurrenceTest_03() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(3);
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(3);
        deque.appendRight(4);
        deque.appendRight(5);
        deque.appendRight(6);
        deque.appendRight(7);
        deque.appendRight(8);
        deque.appendRight(9);
        deque.appendRight(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
        deque.removeFirstOccurrence(8);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 9, 10})));
        deque.removeFirstOccurrence(9);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 10})));
        deque.removeFirstOccurrence(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7})));
        deque.appendRight(8);
        deque.appendRight(9);
        deque.appendRight(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})));
        deque.removeFirstOccurrence(8);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 9, 10})));
        deque.removeFirstOccurrence(10);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7, 9})));
        deque.removeFirstOccurrence(9);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5, 6, 7})));
    }

    @Test
    public void toString_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(2);
        deque.appendRight(1);
        Assert.assertTrue("[1]".equals(deque.toString()));
        deque.appendRight(2);
        Assert.assertTrue("[1, 2]".equals(deque.toString()));
        deque.appendLeft(3);
        Assert.assertTrue("[3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(4);
        Assert.assertTrue("[4, 3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(5);
        Assert.assertTrue("[5, 4, 3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(6);
        Assert.assertTrue("[6, 5, 4, 3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(7);
        Assert.assertTrue("[7, 6, 5, 4, 3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(8);
        Assert.assertTrue("[8, 7, 6, 5, 4, 3, 1, 2]".equals(deque.toString()));
        deque.appendLeft(9);
        Assert.assertTrue("[9, 8, 7, 6, 5, 4, 3, 1, 2]".equals(deque.toString()));
    }

    @Test
    public void removeLastOccurrenceTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>(2);
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(1);
        deque.appendRight(3);
        deque.appendRight(1);
        deque.appendRight(4);
        deque.appendRight(1);
        deque.appendRight(5);
        deque.appendRight(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 1, 4, 1, 5, 1})));
        deque.removeLastOccurrence(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 1, 4, 1, 5})));
        deque.removeLastOccurrence(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 1, 4, 5})));
        deque.removeLastOccurrence(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 4, 5})));
        deque.removeLastOccurrence(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 3, 4, 5})));
        deque.removeLastOccurrence(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{2, 3, 4, 5})));
    }

    @Test
    public void retainAllTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>();
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(1);
        deque.appendRight(3);
        deque.appendRight(1);
        deque.appendRight(4);
        deque.appendRight(1);
        deque.appendRight(5);
        deque.appendRight(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 1, 4, 1, 5, 1})));
        HashSet<Integer> set = new HashSet<>();
        set.add(2);
        set.add(4);
        set.add(5);
        deque.retainAll(set);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{2, 4, 5})));
    }

    @Test
    public void removeAllTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>();
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(1);
        deque.appendRight(3);
        deque.appendRight(1);
        deque.appendRight(4);
        deque.appendRight(1);
        deque.appendRight(5);
        deque.appendRight(1);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{1, 2, 1, 3, 1, 4, 1, 5, 1})));
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        deque.removeAll(set);
        Assert.assertTrue(Arrays.equals(deque.toArray(), (new Integer[]{2, 4, 5})));
    }

    @Test
    public void toArrayTest_01() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>();
        deque.appendRight(1);
        deque.appendRight(2);
        deque.appendRight(3);
        deque.appendRight(4);
        deque.appendRight(5);
        Object[] array = new Object[7];
        Assert.assertTrue(Arrays.equals(deque.toArray(array), new Integer[]{1, 2, 3, 4, 5, null, null}));
    }

//    @Test
//    public void simplestTest_01() {
//        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>();
//        System.out.println(deque.arrayToString());
//        deque.appendRight(1);
//        System.out.println(deque.arrayToString());
//        deque.appendRight(2);
//        System.out.println(deque.arrayToString());
//        deque.appendRight(3);
//        System.out.println(deque.arrayToString());
//        deque.appendRight(4);
//        System.out.println(deque.arrayToString());
//        deque.appendRight(5);
//        System.out.println(deque.arrayToString());
//    }
//
//    @Test
//    public void simplestTest_02() {
//        MyArrayDeque<Integer> deque = new MyArrayDeque<Integer>();
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(1);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(2);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(3);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(4);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(5);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(6);
//        deque.appendLeft(7);
//        deque.appendLeft(8);
//        deque.appendLeft(9);
//        deque.appendLeft(10);
//        System.out.println(deque.arrayToString());
//        deque.appendLeft(11);
//        System.out.println(deque.arrayToString());
//    }

}
