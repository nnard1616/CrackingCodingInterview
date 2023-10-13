package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(0,8,5,6,7,5,4,3,6,2,2,9));


        System.out.println(returnKthToLast2(13, l.reversed().iterator()));
    }

    private static Integer returnKthToLast2(int k, Iterator<Integer> iter) {
        if (iter.hasNext() && k >= 1) {
            if (k == 1) {
                return iter.next();
            } else {
                iter.next();
                return returnKthToLast2(k - 1, iter);
            }
        }

        return null;

    }

    /**
     * ASsumption: Size known
     * @param k
     * @param list
     * @return
     */
    private static Integer returnKthToLast(int k, LinkedList<Integer> list) {
        Integer result = null;
        Iterator<Integer> iter = list.iterator();

        int i = 0;
        int n = list.size();
        int target = n - k;

        if (k <= 0 || k > n) {
            return result;
        }

        while (iter.hasNext()) {
            if (i == target) {
                result = iter.next();
                break;
            } else {
                iter.next();
                i++;
            }
        }

        return result;
    }


    private static void removeDuplicates(LinkedList<Integer> list) {

        Iterator<Integer> i1 = list.iterator();
        HashSet<Integer> buffer = new HashSet<>();
        Integer holder ;

        while (i1.hasNext()) {
            holder = i1.next();
            if (!buffer.add(holder)) {
                i1.remove();
            }
        }
    }
}