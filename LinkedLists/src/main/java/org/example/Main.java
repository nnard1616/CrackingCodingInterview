package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static class Node {
        Node next = null;
        Integer data;

        public Node(int d) {
            data = d;
        }

        void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }

        @Override
        public String toString() {
            if (null != next) {
                return this.data.toString() + ", " + next.toString();
            } else {
                return this.data.toString();
            }
        }
    }
    public static void main(String[] args) {
//        LinkedList<Integer> l = new LinkedList<>(Arrays.asList(0,8,5,6,7,5,4,3,6,2,2,9));

        Node l = new Node(0);
        l.appendToTail(5);
        l.appendToTail(6);
        l.appendToTail(7);
        l.appendToTail(5);
        l.appendToTail(4);
        l.appendToTail(3);
        l.appendToTail(6);
        l.appendToTail(2);
        l.appendToTail(6);
        l.appendToTail(2);
        l.appendToTail(9);
        l.appendToTail(1);

        Node a = new Node(7);
        a.appendToTail(1);
        a.appendToTail(6);
        a.appendToTail(1);
        a.appendToTail(1);
        a.appendToTail(1);

        Node b = new Node(5);
        b.appendToTail(9);
        b.appendToTail(9);

        Node a2 = new Node(1);
        a2.appendToTail(1);
        a2.appendToTail(1);
        a2.appendToTail(6);
        a2.appendToTail(1);
        a2.appendToTail(7);

        Node b2 = new Node(9);
        b2.appendToTail(9);
        b2.appendToTail(5);

        System.out.println(sumListsRecursive(a, b));
    }

    private static Node sumListsRecursive(Node a, Node b) {
        if (null == a.next && null == b.next) {
            return new Node(a.data + b.data);
        }
//        if (null == a.next && null != b.next) {
//            return sumListsRecursive(a, b.next);
//        }
//        if (null != a.next && null == b.next) {
//            return sumListsRecursive(a.next, b);
//        }

        Node result = sumListsRecursive(a.next, b.next);
        if (result.data >= 10) {
            result.data -= 10;
            Node newHead = new Node(1);
            newHead.next = result;
            result = newHead;
        }

        return result;
    }

    private static Node sumLists(Node a, Node b) {
        Node result = new Node (0);
        Node resultHead = result;

        while (null != a && null != b) {
            result.data += (a.data + b.data);
            if (result.data >= 10) {
                result.data -= 10;
                result.appendToTail(1);
            }
            a = a.next;
            b = b.next;
            if (null != result.next) {
                result = result.next;
            } else if (null != a && null != b) {
                result.appendToTail(0);
            }
        }

        if (null == a && null != b) {
            result.data += b.data;
            result.next = b.next;
        }
        if (null == b && null != a) {
            result.data += a.data;
            result.next = a.next;
        }


        return resultHead;
    }

    private static Node partitionSort(Node l, Integer p) {
        Node head = l;
        Node previous = null ;
        while (null != l ) {

            if (null != previous && l.data < p) {
                previous.next = l.next;
                l.next = head;
                head = l;
                l = previous.next;
            } else {
                previous = l;
                l = l.next;
            }
        }
        return head;
    }

    private static void removeMiddle(Node l) {
        Integer length = getLength(l);

        for (int i = 0; i < (length/2) - 1 ; i++) {
            l = l.next;
        }

        l.next = l.next.next;


    }

    private static Integer returnKthToLastHard(int k, Node l) {
        Integer length = getLength(l);
        Integer result = null;

        for (int i = 0; i < length ; i++) {
            if (length - k == i) {
                result = l.data;
                break;
            }
            l = l.next;
        }
        return result;
    }

    private static Integer getLength(Node l) {
        if (null == l.next) {
            return 1;
        }

        return getLength(l.next) + 1;
    }

    private static void removeDuplicatesHard(Node l) {
        Node runner = l.next;
        Node previous = l;

        while (null != l) {
            while (null != runner) {
                if (null != runner && l.data == runner.data) {
                    previous.next = runner.next;
                }
                previous = null == previous ? null : previous.next;
                runner = null == runner ? null : runner.next;

            }
            l = null == l ? null : l.next;
            previous = l;
            runner = null == l ? null : l.next;
        }
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