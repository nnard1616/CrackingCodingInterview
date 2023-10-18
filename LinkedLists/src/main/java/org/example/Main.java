package org.example;

import java.util.*;

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
        b2.appendToTail(0);
        b2.appendToTail(0);
        b2.appendToTail(0);
        b2.appendToTail(9);
        b2.appendToTail(5);


        Node a3 = new Node(7);
        a3.appendToTail(1);
        a3.appendToTail(6);
        a3.appendToTail(1);
        a3.appendToTail(7);

        Node b3 = new Node(9);
        b3.appendToTail(0);
        b3.appendToTail(0);
        b3.appendToTail(0);
        b3.appendToTail(9);
        b3.appendToTail(5);

        Node c3 = new Node(7);
        c3.appendToTail(1);
        c3.appendToTail(1);
        c3.appendToTail(7);

        Node d3 = new Node(7);

        Node e3 = new Node(7);
        e3.appendToTail(7);

        Node f3 = new Node(7);
        f3.appendToTail(1);


        Node a4 = new Node(0);
        a4.appendToTail(1);
        a4.next.next = f3;

        Node b4 = new Node(7);
        b4.appendToTail(3);
        b4.appendToTail(4);
        b4.next.next.next = f3;

        Node a5 = a4;
        a5.next.next.next.next = b4;

        System.out.println(getCycleStart(c3));
        System.out.println(getCycleStart(a5));

        System.out.println(Math.round(Math.pow(2,3)));

    }

    private static Integer getCycleStart(Node head) {
        Integer result = null ;


        // O(n) time, O(n) additional space
//        HashSet<Node> memo = new HashSet<>();
//        while (!memo.contains(head) && null != head) {
//            System.out.println(head.data);
//            memo.add(head);
//            head = head.next;
//        }
//
//        if (null != head){
//            result = head.data;
//
//        }

        Node slow = head;
        Node fast = head;

        do {
            if (null != slow && null != fast && null != fast.next) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                break;
            }
        } while (null != slow && null != fast && slow != fast);

        if (null == fast || null == slow) {
            return null;
        }
        if (slow == fast) {
            slow = head;
        }

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        result = slow.data;

        return result;
    }

    private static Node findIntersectionNode(Node first, Node second) {
        Node result = null;


        //O(ab) solution

//        Node secondHead = second;
//        while (null != first) {
//            while (null != second) {
//                if (first == second) {
//                    result = first;
//                    return result;
//                }
//                second = second.next;
//            }
//            second = secondHead;
//            first = first.next;
//        }


        // O(a+b) time, O(a) additional space solution
        HashSet<Node> memo = new HashSet<>();
        while (null != first) {
            memo.add(first);
            first = first.next;
        }
        while (null != second) {
            if (memo.contains(second)) {
                result = second;
                break;
            }
            second = second.next;
        }

        return result;
    }


    /**
     * Determines if singly linked list, given by its head node, is a palindrome
     *
     * base cases:
     * List of one elment is palindrome
     * List of two identical elements is palindrome
     *
     * general cases:
     * List with even number of elements, n, is a palindrome when indices 0..(n-1)/2 == reversed((n-1)/2 + 1 .. n-1)
     * List with odd number of elements, n, is a palindrome when indices 0..(n-1)/2 - 1 == reversed((n-1)/2 + 1 .. n-1)
     *
     * @param head
     * @return
     */
    private static boolean isPalindrome(Node head) {
        boolean result = true;
        Integer length = getLength(head);
        Stack<Integer> buffer = new Stack<>();
        Integer popped;

        for (int i = 0; i < length; i++) {

            if (isEven(length)) {
                if (i <= (length -1) / 2 ) {
                    buffer.push(head.data);
                } else {
                    popped = buffer.pop();
                    if (popped != head.data) {
                        result = false;
                        break;
                    }
                }
            } else { // odd length
                if (i <= (length -1) / 2 - 1) {
                    buffer.push(head.data);
                } else if (i == (length -1) / 2 ){ //middle value
                    ;
                } else {
                    popped = buffer.pop();
                    if (popped != head.data) {
                        result = false;
                        break;
                    }
                }
            }

            head = head.next;
        }


        return result;
    }

    private static boolean isEven(Integer value) {
        return value % 2 == 0;
    }

    private static Node sumListsForward(Node a, Node b) {
        Integer aLength = getLength(a);
        Integer bLength = getLength(b);

        while (aLength < bLength ) {
            Node aNewHead = new Node(0);
            aNewHead.next = a;
            a = aNewHead;
            aLength++;
        }

        while (aLength > bLength) {
            Node bNewHead = new Node(0);
            bNewHead.next = b;
            b = bNewHead;
            bLength++;
        }
        System.out.println(a);
        System.out.println(b);



        Node result = sumListsRecursiveHelper(a, b);

        //final carry the 1
        if (result.data >= 10) {
            Node newHead = new Node(1);
            result.data -= 10;
            newHead.next = result;
            result = newHead;
        }

        return result;
    }

    /**
     * Assume a and b are of thh same length
     * @param a
     * @param b
     * @return
     */
    private static Node sumListsRecursiveHelper(Node a, Node b) {
        if (null == a.next && null == b.next) {
            return new Node(a.data + b.data);
        }

        Node tail = sumListsRecursiveHelper(a.next, b.next);
        Node head = new Node(a.data + b.data);
        head.next = tail;

        // carry the 1
        if (tail.data >= 10) {
            tail.data -= 10;
            head.data++;
        }
        return head;
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