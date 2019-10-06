package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.MyDeque;

@RunWith(JUnit4.class)
public class MyDequeTests {

    private int AMOUNT_OF_ELEMENTS = 10;

    @Test
    public void expandingTest_01() {
        MyDeque<Integer> deque = new MyDeque<Integer>();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popLeft());
        }
    }

    @Test
    public void expandingTest_02() {
        MyDeque<Integer> deque = new MyDeque<Integer>();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (Integer i = AMOUNT_OF_ELEMENTS - 1; i >= 0; i--) {
            Assert.assertEquals(i, deque.popRight());
        }
    }

    @Test
    public void expandingTest_03() {
        MyDeque<Integer> deque = new MyDeque<Integer>();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendRight(i);
        }
        for (Integer i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            Assert.assertEquals(i, deque.popLeft());
        }
    }

    @Test
    public void expandingTest_04() {
        MyDeque<Integer> deque = new MyDeque<Integer>();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            deque.appendLeft(i);
        }
        for (Integer i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            Assert.assertEquals(i, deque.popRight());
        }
    }

    @Test
    public void bothSidesTest_01() {
        MyDeque<Integer> deque = new MyDeque<Integer>();
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

}
