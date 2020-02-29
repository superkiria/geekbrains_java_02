package ru.motrichkin.datastructures_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArrayCopyTests {

    Integer[] array = new Integer[100000000];

    {
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    @Test
    public void arrayCopySpeedTest_01() {
        for(int i = 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }
        array[array.length - 1] = null;
    }

    @Test
    public void arrayCopySpeedTest_02() {
        System.arraycopy(array, 1, array, 0, array.length - 2);
        array[array.length - 1] = null;
    }

    @Test
    public void arrayCopySpeedTest_03() {
        for(int i = 0; i < 10; i ++) {
            System.arraycopy(array, 1, array, 0, array.length / 10);
        }
        array[array.length - 1] = null;
    }

    @Test
    public void arrayCopySpeedTest_04() {
        for(int i = 0; i < 100; i ++) {
            System.arraycopy(array, 1, array, 0, array.length / 100);
        }
        array[array.length - 1] = null;
    }

}
