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

    private static class TreeNode<T extends Comparable<T>> {
        public T data;

        public TreeNode(T data) {
            this.data = data;
        }

        public TreeNode<T> left = null ;

        public TreeNode<T> right = null ;

        public String toString() {
            return data.toString();
        }

        public String allToString() {
            String result = this.data.toString() + ": ";

            if (null != left) {
                result += "L: " + left.data.toString() + ", ";
            }

            if (null != right) {
                result += "R: " + right.data.toString() + ", ";
            }

            if (null != left) {
                result += "\n" + left.allToString();
            }

            if (null != right) {
                result += "\n" + right.allToString();
            }

            return result;

        }
    }

//    private static class Tree<T extends Comparable<T>> {
//        public T value ;
//        public Tree<T> parent = null ;
//        public Tree<T> leftChild = null ;
//        public Tree<T> rightChild = null ;
//
//        public Tree(T value) {
//            this.value = value;
//        }
//
//        public Tree() {
//            this(null);
//        }
//
//        public boolean addChildBalanced(Tree<T> tree) {
//            boolean result = false;
//
//            if (null == leftChild) {
//                leftChild = tree;
//                leftChild.parent = this;
//                result = true;
//            } else if (null == rightChild) {
//                rightChild = tree;
//                rightChild.parent = this;
//                result = true;
//            }
//
//            return result;
//        }
//
//        public boolean add(T item) {
//            boolean result = false;
//            if (null == this.value) {
//                this.value = item;
//                result = true;
//            } else {
//                Tree<T> nextTree = new Tree<>(item);  //uhhhh
//                result =  addChildBalanced(nextTree);
//                if (result) {
//                    nextTree.parent = this;
//                } else {
//                    result = leftChild.add(item);
//                    if (!result) {
//                        result = rightChild.add(item);
//                    }
//                }
//            }
//            return result;
//        }
//
//        public void swapWithParent() {
//            T temp = this.value;
//            this.value = parent.value;
//            parent.value = temp;
//        }
//
//        public void swapWithLeftChild() {
//            T temp = this.value;
//            this.value = leftChild.value;
//            leftChild.value = temp;
//        }
//
//        public void swapWithRightChild() {
//            T temp = this.value;
//            this.value = rightChild.value;
//            rightChild.value = temp;
//        }
//
//        @Override
//        public String toString() {
//            String result = this.value.toString() + ": " +
//
//            return result;
//        }
//    }

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

        ArrayList<Integer> arr = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10, 11, 12));
        System.out.println(arr);

        TreeNode<Integer> btree = createMinimalBST(arr);
        System.out.println(btree);

        ArrayList<LinkedList<TreeNode<Integer>>> depths = createLevelLinkedList(btree);


        System.out.println(depths);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));

        btree.right.right.right.right = new TreeNode<Integer>(69);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));
        btree.right.right.right.right.right = new TreeNode<Integer>(72);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));



    }

    private static ArrayList<LinkedList<Integer>> createDepthLists(TreeNode<Integer> btree) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        Queue<TreeNode<Integer>> processor = new ConcurrentLinkedQueue<>();
        HashMap<Integer, Integer> levelTracker = new HashMap<>();

        processor.add(btree) ;
        TreeNode<Integer> root ;

        int level = 0;
        levelTracker.put(0, 1);

        while (!processor.isEmpty()) {
            if (null == levelTracker.get(level+1)) {
                levelTracker.put(level+1, (int) Math.pow(2, level+1));
            }
            root = processor.remove();
            try {
                result.get(level).add(root.data);
            } catch (IndexOutOfBoundsException e) {
                result.add(level, new LinkedList<Integer>());
                result.get(level).add(root.data);
            }


            if (null != root.left) {
                processor.add(root.left);
            } else {
                levelTracker.put(level+1, levelTracker.get(level+1) - 1);
            }
            if (null != root.right) {
                processor.add(root.right);
            } else {
                levelTracker.put(level+1, levelTracker.get(level+1) - 1);
            }



            levelTracker.put(level, levelTracker.get(level) - 1) ;

            if (levelTracker.get(level).equals(0)) {
                level++;
            }
        }




        return result;
    }

    public static ArrayList<LinkedList<TreeNode<Integer>>> createLevelLinkedList(TreeNode<Integer> root) {
        ArrayList<LinkedList<TreeNode<Integer>>> result = new ArrayList<>();
        LinkedList<TreeNode<Integer>> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }

        while(current.size() > 0) {
            result.add(current);
            LinkedList<TreeNode<Integer>> parents = current;
            current = new LinkedList<TreeNode<Integer>>();

            for (TreeNode<Integer> parent : parents) {
                if (null != parent.left){
                    current.add(parent.left);
                }
                if (null != parent.right){
                    current.add(parent.right);
                }
            }

        }


        return result;
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

    public static TreeNode<Integer> createMinimalBST(ArrayList<Integer> array) {
        return createMinimalBST(array, 0, array.size() - 1);
    }

    public static TreeNode<Integer>  createMinimalBST(ArrayList<Integer> array, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode<Integer> n = new TreeNode<>(array.get(mid));
        n.left = createMinimalBST(array, start, mid-1);
        n.right = createMinimalBST(array, mid+1, end);
        return n;
    }

    public static boolean checkBalanced(TreeNode<Integer> root) {
        ArrayList<LinkedList<TreeNode<Integer>>> depths = createLevelLinkedList(root);


        Integer leftHeight;
        Integer rightHeight;

        for (LinkedList<TreeNode<Integer>> list : depths) {
            for (TreeNode<Integer> node : list) {
                leftHeight = getHeight(node.left);
                rightHeight = getHeight(node.right);
                if (Math.abs(leftHeight - rightHeight) >  1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static Integer getHeight(TreeNode<Integer> root) {
        if (null == root) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}