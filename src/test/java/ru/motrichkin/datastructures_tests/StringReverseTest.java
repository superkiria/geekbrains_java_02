package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.MyDeque;

@RunWith(JUnit4.class)
public class StringReverseTest {

    @Test
    public void stringReverseTest() {
        MyDeque<Character> deque = new MyDeque<Character>();
        String testString = "Эту строку нужно перевернуть!";
        testString.chars().forEach(i -> deque.appendRight((char) i));
        char[] chars = new char[deque.size()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = deque.popRight();
        }
        String result = new String(chars);
        StringBuilder stringBuilder = new StringBuilder(testString);
        Assert.assertEquals(stringBuilder.reverse().toString(), result);
    }

}
