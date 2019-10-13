package ru.motrichkin.datastructures_tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.motrichkin.datastructures.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

@RunWith(JUnit4.class)
public class BinaryTreeTests {

    @Test
    public void test_01() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        System.out.println(tree.toString());
        Assert.assertEquals(0, tree.size());
        Assert.assertTrue(tree.isEmpty());
        tree.add(4);
        Assert.assertEquals(1, tree.size());
        Assert.assertFalse(tree.isEmpty());
        tree.add(2);
        Assert.assertEquals(2, tree.size());
        tree.add(10);
        Assert.assertEquals(3, tree.size());
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(3);
        linkedList.add(5);
        tree.addAll(linkedList);
        Assert.assertTrue(tree.containsAll(linkedList));
        Assert.assertEquals(5, tree.size());
        tree.add(1);
        Assert.assertEquals(6, tree.size());
        tree.add(6);
        Assert.assertEquals(7, tree.size());
        Assert.assertEquals(" 1  2  3  4  5  6  10 ", tree.toString());
        tree.remove(3);
        Assert.assertFalse(tree.containsAll(linkedList));
        Assert.assertTrue(tree.contains(4));
        Assert.assertFalse(tree.contains(3));
        Assert.assertFalse(tree.contains(300));
        Assert.assertEquals(6, tree.size());
        Assert.assertEquals(" 1  2  4  5  6  10 ", tree.toString());
        tree.remove(1);
        Assert.assertFalse(tree.isEmpty());
        Assert.assertEquals(5, tree.size());
        Assert.assertEquals(" 2  4  5  6  10 ", tree.toString());
        tree.remove(2);
        Assert.assertEquals(4, tree.size());
        Assert.assertEquals(" 4  5  6  10 ", tree.toString());
        tree.remove(10);
        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(" 4  5  6 ", tree.toString());
        tree.removeAll(linkedList);

        Assert.assertEquals(2, tree.size());
        Assert.assertEquals(" 4  6 ", tree.toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(6);
        arrayList.add(100);
        arrayList.add(5);
        tree.retainAll(arrayList);
        Assert.assertEquals(1, tree.size());
        Assert.assertEquals(" 6 ", tree.toString());
        Assert.assertFalse(tree.isEmpty());
        tree.remove(6);
        Assert.assertEquals(0, tree.size());
        Assert.assertEquals("  ", tree.toString());
        Assert.assertTrue(tree.isEmpty());
    }

    @Test
    public void balancingTest() {
        Random random = new Random();
        int balanced = 0;
        int unbalanced = 0;
        for (int i = 0; i < 200000; i++) {
            BinaryTree<Integer> tree = new BinaryTree<Integer>();
            while (tree.level() < 6) {
                tree.add(random.nextInt(201) - 100);
            }
            if (tree.isBalanced()) {
                balanced++;
            } else {
                unbalanced++;
            }
        }
        System.out.println("Balanced: " + balanced);
        System.out.println("Unbalanced: " + unbalanced);
        System.out.println("Percent of balanced: " + (100. * balanced / (balanced + unbalanced)));
    }

}
