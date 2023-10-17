import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    private static class Graph<T extends Comparable<T>> {
        private ArrayList<Node<T>> nodes = new ArrayList<>();

        public String  toString() {
            String result = "";

            for (Node<T> node : nodes ) {
                result += node.toString() + "\n";
            }

            return result;
        }
    }

    private static class Tree<T extends Comparable<T>> {
        public T value ;
        public Tree<T> parent = null ;
        public Tree<T> leftChild = null ;
        public Tree<T> rightChild = null ;

        public Tree(T value) {
            this.value = value;
        }

        public Tree() {
            this(null);
        }

        public boolean addChildBalanced(Tree<T> tree) {
            boolean result = false;

            if (null == leftChild) {
                leftChild = tree;
                leftChild.parent = this;
                result = true;
            } else if (null == rightChild) {
                rightChild = tree;
                rightChild.parent = this;
                result = true;
            }

            return result;
        }

        public boolean add(T item) {
            boolean result = false;
            if (null == this.value) {
                this.value = item;
                result = true;
            } else {
                Tree<T> nextTree = new Tree<>(item);  //uhhhh
                result =  addChildBalanced(nextTree);
                if (result) {
                    nextTree.parent = this;
                } else {
                    result = leftChild.add(item);
                    if (!result) {
                        result = rightChild.add(item);
                    }
                }
            }
            return result;
        }

        public void swapWithParent() {
            T temp = this.value;
            this.value = parent.value;
            parent.value = temp;
        }

        public void swapWithLeftChild() {
            T temp = this.value;
            this.value = leftChild.value;
            leftChild.value = temp;
        }

        public void swapWithRightChild() {
            T temp = this.value;
            this.value = rightChild.value;
            rightChild.value = temp;
        }

        @Override
        public String toString() {
            String result = this.value.toString() + ": " +

            return result;
        }
    }

    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
        public T data;

        public Node(T item) {
            this.data = item;
        }

        public boolean marked = false;
        private ArrayList<Node<T>> children = new ArrayList<>();



        public void addChild(Node<T> child) {
            children.add(child);
        }
        public void addChild(T item) {
            children.add(new Node(item));
        }

        @Override
        public int compareTo(Node<T> tNode) {
            return this.data.compareTo(tNode.data);
        }

        @Override
        public String toString() {
            String result = data.toString() + ": ";

            for (Node<T> child : children) {
                result += child.data.toString() + ", ";
            }

            return result;
        }
    }


    public static void main(String[] args) {
        Node<Integer> a = new Node<>(0);
        Node<Integer> b = new Node<>(1);
        Node<Integer> c = new Node<>(2);
        Node<Integer> d = new Node<>(3);
        Node<Integer> e = new Node<>(4);
        Node<Integer> f = new Node<>(5);

        a.addChild(b);
        a.addChild(e);
        a.addChild(f);

        b.addChild(d);
        b.addChild(e);

        c.addChild(b);

        d.addChild(c);
        d.addChild(e);

        Graph<Integer> g = new Graph<>();
        g.nodes.add(a);
        g.nodes.add(b);
        g.nodes.add(c);
        g.nodes.add(d);
        g.nodes.add(e);
        g.nodes.add(f);


        System.out.println(g);
        System.out.println(pathExists(a, d));
        System.out.println(pathExists(c, a));
        System.out.println(pathExists(e, f));

        System.out.println(new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9,10, 11, 12)));

    }

    /**
     * Trivial Case: S = E, done; return true
     *
     * add S to Queue
     *
     * Iterate through children of S
     *      If  E is a child of current parent, return true
     *      else add child's children to Queue
     *
     * @param source
     * @param destination
     * @return
     */
    public static boolean pathExists(Node<Integer> source, Node<Integer> destination) {
        Queue<Node<Integer>> q = new ConcurrentLinkedQueue<>() ;

        q.add(source);
        Node<Integer> currParent;

        while (!q.isEmpty()) {
            currParent = q.remove();
            currParent.marked = true;
            if (currParent.compareTo(destination) == 0) {
                return true;
            } else {
                for (Node<Integer> child : currParent.children) {
                    if (child.compareTo(destination) == 0) {
                        return true;
                    } else if (!child.marked){
                        q.add(child);
                    }
                }
            }
        }

        return false;
    }



    public Graph<Integer> createBinarySearchTree(ArrayList<Integer> sortedArray) {
        Tree<Integer> result = new Tree<>();

        for (Integer i : sortedArray) {
            Tree<Integer> tree = new Tree<>(i);
        }



        return result;
    }
}