package ru.motrichkin.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BinaryTree<Item extends Comparable> implements Set {

    private Item item = null;
    private BinaryTree left = null;
    private BinaryTree right = null;

    private BinaryTree<Item> smartAdd(BinaryTree tree, Object o) {
        BinaryTree<Item> result;
        if (tree == null) {
            result = new BinaryTree<>();
        } else {
            result = tree;
        }
        result.add(o);
        return result;
    }

    private BinaryTree smartRemove(BinaryTree tree, Object o) {
        if (tree == null) {
            return null;
        }
        if (tree.item.compareTo(o) == 0 && tree.size() == 1) {
            tree = null;
            return tree;
        }
        tree.remove(o);
        return tree;
    }

    private boolean hasLeft() {
        return left != null;
    }

    private boolean hasRight() {
        return right != null;
    }

    private Item min() {
        if (hasLeft()) {
            return (Item) left.min();
        }
        return item;
    }

    private Item max() {
        if (hasRight()) {
            return (Item) right.max();
        }
        return item;
    }

    @Override
    public int size() {
        return (hasRight() ? right.size() : 0)
                + (hasLeft() ? left.size() : 0)
                + (item != null ? 1 : 0);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (item.compareTo(o) == 0) {
            return true;
        }
        if (item.compareTo(o) < 0) {
            return hasRight() && right.contains(o);
        }
        if (item.compareTo(o) > 0) {
            return hasLeft() && left.contains(o);
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] leftArray = hasLeft() ? left.toArray() : new Object[0];
        Object[] centerArray = new Object[1];
        centerArray[0] = item;
        Object[] rightArray = hasRight() ? right.toArray() : new Object[0];
        Object[] result = new Object[leftArray.length + centerArray.length + rightArray.length];
        System.arraycopy(leftArray, 0, result, 0, leftArray.length);
        System.arraycopy(centerArray, 0, result, leftArray.length, centerArray.length);
        System.arraycopy(rightArray, 0, result, leftArray.length + centerArray.length, rightArray.length);
        return result;
    }

    @Override
    public boolean add(Object o) {
        if (this.item  == null) {
            this.item = (Item) o;
            return true;
        }
        if (this.item.compareTo(o) < 0) {
            right = smartAdd(right, o);
            return true;
        }
        if (this.item.compareTo(o) > 0) {
            left = smartAdd(left, o);
            return true;
        }
        if (this.item.compareTo(o) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (item.compareTo(o) > 0) {
            BinaryTree wasLeft = left;
            left = smartRemove(left, o);
            return wasLeft == null || left == null || wasLeft.size() > left.size();
        }
        if (item.compareTo(o) < 0) {
            BinaryTree wasRight = right;
            right = smartRemove(right, o);
            return wasRight == null || right == null || wasRight.size() > right.size();
        }
        if (item.compareTo(o) == 0) {
            if (hasLeft()) {
                Item temp = (Item) left.max();
                //left.remove(temp);
                left = smartRemove(left, temp);
                this.item = temp;
                return true;
            }
            if (hasRight()) {
                Item temp = (Item) right.min();
                right = smartRemove(right, temp);
                this.item = temp;
                return true;
            }
            item = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object object : collection) {
            this.add(object);
        }
        return true;
    }

    @Override
    public void clear() {
        left = null;
        right = null;
        item = null;
    }

    @Override
    public boolean removeAll(Collection collection) {
        for (Object object : collection) {
            this.remove(object);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection collection) {
        for (Object o : this.toArray()) {
            if (!collection.contains(o)) {
                this.remove(o);
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object object : collection) {
            if (!this.contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        return new Object[0];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hasLeft() ? left.toString() : "");
        stringBuilder.append(" ");
        stringBuilder.append(item != null ? item.toString() : "");
        stringBuilder.append(" ");
        stringBuilder.append(hasRight() ? right.toString() : "");
        return stringBuilder.toString();
    }

    public String toStrangeString() {
        return toStringWithLevel(0);
    }

    private String toStringWithLevel(int level) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(level);
        stringBuilder.append(":");
        String itemString = item != null ? item.toString() : "#";
        stringBuilder.append(itemString);
        stringBuilder.append(" ");
        if (hasLeft()) {
            stringBuilder.append(level);
            stringBuilder.append("L:[");
            stringBuilder.append(left.toStringWithLevel(level + 1));
            stringBuilder.append("]");
        }
        if (hasRight()) {
            stringBuilder.append(level);
            stringBuilder.append("R:[");
            stringBuilder.append(hasRight() ? right.toStringWithLevel(level + 1) : "");
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }

    public boolean isBalancedBySize() {
        if (!hasLeft() && !hasRight()) {
            return true;
        }
        if (!hasRight()) {
            return left.size() < 2;
        }
        if (!hasLeft()) {
            return right.size() < 2;
        }
        return Math.abs(left.size() - right.size()) < 2 && left.isBalancedBySize() && right.isBalancedBySize();
    }

    public boolean isBalancedByLevel() {
        if (!hasLeft() && !hasRight()) {
            return true;
        }
        if (!hasRight()) {
            return left.level() < 2;
        }
        if (!hasLeft()) {
            return right.level() < 2;
        }
        return Math.abs(left.level() - right.level()) < 2 && left.isBalancedByLevel() && right.isBalancedByLevel();
    }

    public int level() {
        return (item != null ? 1 : 0) + Math.max(left != null ? left.level() : 0, right != null ? right.level() : 0);

    }

}
