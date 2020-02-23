package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.*;
import org.junit.Test;

@RunWith(JUnit4.class)
public class MyArrayListTests {

    @Test
    public void creatingMyArrayListTest_01() {
        MyArrayList<String> myArrayList01 = new MyArrayList<String>();
        MyArrayList<String> myArrayList02 = new MyArrayList<String>(-100);
        MyArrayList<String> myArrayList03 = new MyArrayList<String>(0);
        MyArrayList<String> myArrayList04 = new MyArrayList<String>(1000000);
    }

    @Test
    public void appendigItemsTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            myArrayList.append(Integer.valueOf(i).toString());
            Assert.assertEquals("myArrayList.getSize(), i + 1", i + 1, myArrayList.getSize());
        }
    }

    @Test
    public void getTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        Assert.assertNull(myArrayList.get(0));
        myArrayList.append("!");
        Assert.assertNotNull(myArrayList.get(0));
    }

    @Test
    public void getTest_02() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            myArrayList.append("" + i);
        }
        Assert.assertEquals("0", myArrayList.get(0));
        Assert.assertEquals("1", myArrayList.get(1));
        Assert.assertEquals("12345", myArrayList.get(12345));
        Assert.assertEquals("999999", myArrayList.get(999999));
    }

    @Test
    public void getSizeTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            myArrayList.append(Integer.valueOf(i).toString());
            Assert.assertEquals(i + 1, myArrayList.getSize());
        }
    }

    @Test
    public void deleteTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        myArrayList.append("!");
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertFalse(myArrayList.delete(0));
        myArrayList.append("!");
        myArrayList.append("!");
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertFalse(myArrayList.delete(0));
        myArrayList.append("!");
        myArrayList.append("!");
        myArrayList.append("!");
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertFalse(myArrayList.delete(2));
        myArrayList.append("!");
        myArrayList.append("!");
        Assert.assertTrue(myArrayList.delete(1));
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertFalse(myArrayList.delete(1));
        myArrayList.append("!");
        myArrayList.append("!");
        myArrayList.append("!");
        Assert.assertTrue(myArrayList.delete(2));
        Assert.assertTrue(myArrayList.delete(1));
        Assert.assertTrue(myArrayList.delete(0));
        Assert.assertFalse(myArrayList.delete(-1));
    }

    @Test
    public void deleteTest_02() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        myArrayList.append("0");
        myArrayList.append("1");
        myArrayList.append("2");
        myArrayList.append("3");
        myArrayList.append("4");
        myArrayList.append("5");
        myArrayList.append("6");
        myArrayList.append("7");
        Assert.assertEquals("6", myArrayList.get(6));
        myArrayList.delete(6);
        Assert.assertEquals("7", myArrayList.get(6));
        Assert.assertEquals("1", myArrayList.get(1));
        myArrayList.delete(1);
        Assert.assertEquals("2", myArrayList.get(1));
    }

    @Test
    public void firstIndexForTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        myArrayList.append("a");
        myArrayList.append("s");
        myArrayList.append("d");
        myArrayList.append("f");
        myArrayList.append("g");
        myArrayList.append("h");
        myArrayList.append("j");
        myArrayList.append("k");
        Assert.assertEquals(0, myArrayList.firstIndexOf("a"));
        Assert.assertEquals(3, myArrayList.firstIndexOf("f"));
        Assert.assertEquals(7, myArrayList.firstIndexOf("k"));
        Assert.assertEquals(-1, myArrayList.firstIndexOf("!"));
        myArrayList.delete(6);
        Assert.assertEquals(0, myArrayList.firstIndexOf("a"));
        Assert.assertEquals(3, myArrayList.firstIndexOf("f"));
        Assert.assertEquals(6, myArrayList.firstIndexOf("k"));
        Assert.assertEquals(-1, myArrayList.firstIndexOf("!"));
        myArrayList.delete(3);
        Assert.assertEquals(0, myArrayList.firstIndexOf("a"));
        Assert.assertEquals(3, myArrayList.firstIndexOf("g"));
        Assert.assertEquals(5, myArrayList.firstIndexOf("k"));
        Assert.assertEquals(-1, myArrayList.firstIndexOf("!"));
        myArrayList.delete(0);
        Assert.assertEquals(0, myArrayList.firstIndexOf("s"));
        Assert.assertEquals(2, myArrayList.firstIndexOf("g"));
        Assert.assertEquals(4, myArrayList.firstIndexOf("k"));
        Assert.assertEquals(-1, myArrayList.firstIndexOf("!"));
    }

    @Test
    public void expandWithDeletionTest_01() {
        MyArrayList<String> myArrayList = new MyArrayList<String>();
        myArrayList.append("0");
        myArrayList.delete(0);
        myArrayList.append("1");
        Assert.assertEquals(0,myArrayList.firstIndexOf("1"));
        myArrayList.append("2");
        myArrayList.delete(0);
        Assert.assertEquals(0,myArrayList.firstIndexOf("2"));
        myArrayList.append("3");
        myArrayList.append("4");
        myArrayList.delete(0);
        myArrayList.append("5");
        myArrayList.append("6");
        myArrayList.append("7");
        myArrayList.append("8");
        myArrayList.append("9");
        myArrayList.append("10");
        myArrayList.append("11");
        myArrayList.append("12");
        myArrayList.delete(0);
        Assert.assertEquals(-1,myArrayList.firstIndexOf("2"));
        Assert.assertEquals(-1,myArrayList.firstIndexOf("3"));
        Assert.assertEquals(0,myArrayList.firstIndexOf("4"));
    }
}
