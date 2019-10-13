package ru.motrichkin.recursion_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.recursion.Thing;

import java.math.BigDecimal;
import java.util.LinkedList;

import static ru.motrichkin.recursion.RecursionWork.*;

@RunWith(JUnit4.class)
public class RecursionWorkTests {
    @Test
    public void powerTest_01() {
        Assert.assertEquals(BigDecimal.ONE, power(BigDecimal.ZERO,0));
        Assert.assertEquals(BigDecimal.ZERO, power(BigDecimal.ZERO,1));
        Assert.assertEquals(BigDecimal.ONE, power(BigDecimal.ONE,1));
        Assert.assertEquals(BigDecimal.ONE, power(BigDecimal.ONE,3));
        Assert.assertEquals(BigDecimal.valueOf(27), power(BigDecimal.valueOf(3),3));
        Assert.assertEquals(BigDecimal.valueOf(-8), power(BigDecimal.valueOf(-2),3));
        Assert.assertEquals(BigDecimal.valueOf(1024), power(BigDecimal.valueOf(2),10));
        Assert.assertEquals(BigDecimal.ONE.divide(BigDecimal.valueOf(1024)), power(BigDecimal.valueOf(2),-10));
    }

    @Test
    public void KnapsackTaskTest_01() {
        LinkedList<Thing> things = new LinkedList<>();
        Assert.assertEquals(0, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_02() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(1, 1));
        Assert.assertEquals(1, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_03() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(100, 2));
        Assert.assertEquals(2, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_04() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(111, 3));
        Assert.assertEquals(0, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_05() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(1, 9));
        things.add(new Thing(2, 1));
        Assert.assertEquals(10, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_06() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(1, 9));
        things.add(new Thing(99, 2));
        Assert.assertEquals(11, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_07() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(51, 11));
        things.add(new Thing(51, 21));
        Assert.assertEquals(21, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_08() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(121, 11));
        things.add(new Thing(121, 11));
        things.add(new Thing(121, 21));
        Assert.assertEquals(0, valueOfThings(KnapsackTask(100, things)));
    }

    @Test
    public void KnapsackTaskTest_09() {
        LinkedList<Thing> things = new LinkedList<>();
        things.add(new Thing(1, 9));
        things.add(new Thing(2, 8));
        things.add(new Thing(3, 7));
        things.add(new Thing(4, 6));
        things.add(new Thing(5, 5));
        things.add(new Thing(6, 4));
        things.add(new Thing(7, 3));
        things.add(new Thing(8, 2));
        things.add(new Thing(9, 1));
        Assert.assertEquals(9, valueOfThings(KnapsackTask(1, things)));
        Assert.assertEquals(17, valueOfThings(KnapsackTask(3, things)));
        Assert.assertEquals(24, valueOfThings(KnapsackTask(6, things)));
    }

}
