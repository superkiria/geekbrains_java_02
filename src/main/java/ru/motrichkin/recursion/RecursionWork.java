package ru.motrichkin.recursion;

import java.math.BigDecimal;
import java.util.*;

public class RecursionWork {

    public static BigDecimal power(BigDecimal number, int power){
        if (power < 0) {
            return BigDecimal.ONE.divide(power(number, -power));
        }
        if (power == 1) {
            return number;
        }
        if (power == 0) {
            return BigDecimal.ONE;
        }
        return number.multiply(power(number, power - 1));
    }

    public static int valueOfThings(List<Thing> things) {
        int value = 0;
        for (Thing thing : things) {
            value += thing.value;
        }
        return value;
    }

    public static List<Thing> KnapsackTask(int capacity, List<Thing> things) {
        if (things.size() == 1) {
            if (things.get(0).weight <= capacity) {
                return things;
            } else {
                return new LinkedList<Thing>();
            }
        }
        if (things.size() == 0) {
            return things;
        }
        LinkedList<Thing> result = new LinkedList<>();
        int resultValue = 0;
        for (Thing thing : things) {
            if (thing.weight > capacity) {
                continue;
            }
            LinkedList<Thing> newThings = new LinkedList<>(things);
            newThings.remove(thing);
            List<Thing> pack = KnapsackTask(capacity - thing.weight, newThings);
            if (thing.value + valueOfThings(pack) > resultValue) {
                resultValue = thing.value + valueOfThings(pack);
                result = (LinkedList<Thing>) pack;
                result.add(thing);
            }
        }
        return result;
    }

}

