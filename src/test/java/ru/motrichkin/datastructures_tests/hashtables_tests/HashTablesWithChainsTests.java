package ru.motrichkin.datastructures_tests.hashtables_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.hashtables.HashTableWithChains;

@RunWith(JUnit4.class)
public class HashTablesWithChainsTests {

    @Test
    public void test_01() {
        HashTableWithChains<Integer> hashTable = new  HashTableWithChains<>();
        hashTable.add(1);
        hashTable.add(1001);
        hashTable.add(2001);
        hashTable.add(2);
        hashTable.add(2);
        hashTable.add(2);
        Assert.assertTrue(hashTable.contains(1));
        Assert.assertTrue(hashTable.contains(2));
        Assert.assertTrue(hashTable.contains(1001));
        Assert.assertTrue(hashTable.contains(2001));
        Assert.assertFalse(hashTable.contains(3001));
        Assert.assertFalse(hashTable.contains(1002));
        hashTable.remove(2);
        Assert.assertTrue(hashTable.contains(2));
        hashTable.removeEvery(2);
        Assert.assertFalse(hashTable.contains(2));
        hashTable.removeEvery(1);
        Assert.assertFalse(hashTable.contains(1));
        Assert.assertTrue(hashTable.contains(1001));
        Assert.assertTrue(hashTable.contains(2001));
    }

    @Test
    public void test_02() {
        HashTableWithChains<Integer> hashTable = new  HashTableWithChains<>();
        for (int i = 0; i < 2000; i++) { // все влезут
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
