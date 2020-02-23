package ru.motrichkin.datastructures_tests.hashtables_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.hashtables.HashTableWithAddresses;

@RunWith(JUnit4.class)
public class HashTableWithAddressesTests {

    @Test
    public void test_01() {
        HashTableWithAddresses<Integer> hashTable = new  HashTableWithAddresses<>();
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(1001);
        hashTable.add(2);
        hashTable.add(2001);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(2);
        hashTable.add(4);
        hashTable.add(5);
        hashTable.add(4);
        hashTable.add(6);
        hashTable.add(7);
        hashTable.add(1000);
        Assert.assertTrue(hashTable.contains(1));
        Assert.assertTrue(hashTable.contains(2));
        Assert.assertTrue(hashTable.contains(1001));
        Assert.assertTrue(hashTable.contains(2001));
        Assert.assertFalse(hashTable.contains(3001));
        Assert.assertFalse(hashTable.contains(1002));
        //System.out.println(hashTable.toStringDirectly());
        hashTable.remove(2);
        //System.out.println(hashTable.toStringDirectly());
        Assert.assertTrue(hashTable.contains(2));
        hashTable.removeEvery(2);
        //System.out.println(hashTable.toStringDirectly());
        Assert.assertFalse(hashTable.contains(2));
        hashTable.removeEvery(1);
        //System.out.println(hashTable.toStringDirectly());
        Assert.assertFalse(hashTable.contains(1));
        Assert.assertTrue(hashTable.contains(1001));
        Assert.assertTrue(hashTable.contains(2001));
        //System.out.println(hashTable.toStringDirectly());
    }

    @Test
    public void test_02() {
        HashTableWithAddresses<Integer> hashTable = new  HashTableWithAddresses<>();
        for (int i = 0; i < 2000; i++) { // не все влезут
            hashTable.add(i);
        }
        //System.out.println(hashTable.toString());
        hashTable.remove(501);
        for (int i = 1; i < 999; i++) {
            hashTable.remove(i);
        }
        //System.out.println(hashTable.toString());
        Assert.assertTrue(hashTable.contains(0));
        Assert.assertTrue(hashTable.contains(999));
    }

}