import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    private static class Graph<T extends Comparable<T>> {
        private ArrayList<Node<T>> nodes = new ArrayList<>();

        public void removeFalseNodes() {
            Iterator<Node<T>> it = nodes.iterator();

            while (it.hasNext()) {
                if (!it.next().marked) {
                    it.remove();
                }
            }
        }

        public void resetNodes() {
            Iterator<Node<T>> it = nodes.iterator();

            while (it.hasNext()) {
                it.next().marked = false;
            }
        }

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

        public TreeNode<T> parent = null;

        public TreeNode(T data) {
            this.data = data;
        }

        public TreeNode<T> left = null ;

        public TreeNode<T> right = null ;

        public boolean  marked = false;

        public void setParents() {
            if (null != left) {
                left.parent = this;
                left.setParents();
            }

            if (null != right) {
                right.parent = this;
                right.setParents();
            }

        }

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


    public static void main(String[] args) throws Exception{
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
        System.out.println(isValidBST(btree));

        btree.setParents();

        System.out.println(getNext(btree.left.right.right));

        ArrayList<LinkedList<TreeNode<Integer>>> depths = createLevelLinkedList(btree);


        System.out.println(depths);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));

        System.out.println("Get next: " + getNext(btree.left.right.right));
        System.out.println("Get next: " + getNext(btree.left.right));
        System.out.println("Get next: " + getNext(btree.left.left.right));
        System.out.println("Get next: " + getNext(btree.right));


        System.out.println("Common Ancestor: " + getCommonAncestor(btree.right.left.right, btree.right.right.right));
        System.out.println("Common Ancestor: " + getCommonAncestor(btree.left.left.right, btree.left.right.right));
        System.out.println("Common Ancestor: " + getCommonAncestor(btree.left.left, btree.left));

        btree.right.right.right.right = new TreeNode<Integer>(69);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));
        System.out.println(isValidBST(btree));
        btree.right.right.right.right.right = new TreeNode<Integer>(72);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));
        System.out.println(isValidBST(btree));
        btree.right.right.right.right.right.right = new TreeNode<Integer>(720);
        System.out.println(checkBalanced(btree));
        System.out.println(getHeight(btree));
        System.out.println(isValidBST(btree));



        Node<Character> a2 = new Node<>('a');
        Node<Character> b2 = new Node<>('b');
        Node<Character> c2 = new Node<>('c');
        Node<Character> d2 = new Node<>('d');
        Node<Character> e2 = new Node<>('e');
        Node<Character> f2 = new Node<>('f');

        a2.addChild(d2);

        b2.addChild(d2);

        d2.addChild(c2);
//        d2.addChild(f2);

        f2.addChild(a2);
        f2.addChild(b2);

        Graph<Character> g2 = new Graph<>();
        g2.nodes.add(a2);
        g2.nodes.add(b2);
        g2.nodes.add(c2);
        g2.nodes.add(d2);
        g2.nodes.add(e2);
        g2.nodes.add(f2);

        System.out.println(g2);
        System.out.println(a2);

        printBuildOrder(g2);

        TreeNode<Integer> simpleBtree = new TreeNode<Integer>(2);
        simpleBtree.left = new TreeNode<Integer>(1);
        simpleBtree.right = new TreeNode<>(3);

//        System.out.println(getPossibleOrderings(btree));


        System.out.println(allSequences(btree));

    }

    private static ArrayList<LinkedList<Integer>> allSequences(TreeNode<Integer> node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<Integer>();
        prefix.add(node.data);

        /* Recurse on left and right subtrees. */
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        /* Weave together each list from the left and right sides. */
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<LinkedList<Integer>>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    /**
     * Weave lists together in all possible ways. This algorithm works by removing the head
     * from one list, recursing, and then doing the same thing with the other list.
     * @param prefix
     */
    private static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        /* One list is empty.  add remainder to [a cloned] prefix and store result. */
        if (first.size() == 0 || second.size() == 0 ) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        /* recurse with head of first added to the prefix. Removing the head will damage first, so we'll need
        to put it back where we found it afterwards.
         */
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        /* Do the same thing with second, damaging and then restoring th elist. */
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    private static ArrayList<ArrayList<Integer>> getPossibleOrderings(TreeNode<Integer> input) {

        if (input.left == null && input.right == null) {
            return new ArrayList<>(List.of(new ArrayList<>(List.of(input.data))));
        }
        if (input.left == null && input.right != null) {
            ArrayList<ArrayList<Integer>> rightSubs = getPossibleOrderings(input.right);
            for (ArrayList<Integer> rightSub : rightSubs) {
                rightSub.addFirst(input.data);
            }
            return rightSubs;
        }
        if (input.left != null && input.right == null) {
            ArrayList<ArrayList<Integer>> leftSubs = getPossibleOrderings(input.left);
            for (ArrayList<Integer> leftSub : leftSubs) {
                leftSub.addFirst(input.data);
            }
            return leftSubs;
        }
        if (input.left != null && input.right != null) {
            ArrayList<ArrayList<Integer>> rightSubs = getPossibleOrderings(input.right);
            ArrayList<ArrayList<Integer>> leftSubs = getPossibleOrderings(input.left);
            ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> result;
            for (ArrayList<Integer> rightSub : rightSubs) {
                for (ArrayList<Integer> leftSub : leftSubs) {
                    result = new ArrayList<>(List.of(input.data));
                    result.addAll(rightSub);
                    result.addAll(leftSub);
                    results.add(result);

                    result = new ArrayList<>(List.of(input.data));
                    result.addAll(leftSub);
                    result.addAll(rightSub);
                    results.add(result);

                }

            }
            return results;
        }
        return null;
    }

    private static TreeNode<Integer> getCommonAncestor(TreeNode<Integer> first, TreeNode<Integer> second) {

        while (null != first.parent ) {
            first.marked = true;
            first = first.parent;
        }

        while (null != second.parent ) {
            if (second.marked) {
                return second;
            } else {
                second.marked = true;
                second = second.parent;
            }
        }

        return null;
    }

    private static void printBuildOrder(Graph<Character> dependencies) throws Exception{
        StringBuilder sb = new StringBuilder();
        Integer nodeCount = dependencies.nodes.size();

        while (!dependencies.nodes.isEmpty()) {

            for (Node<Character> project : dependencies.nodes) {
                for (Node<Character> dependency : project.children) {
                    dependency.marked = true;
                }
            }

            for (Node<Character> project : dependencies.nodes) {
                if (!project.marked) {
                    sb.append(project.data + ", ");
                }
            }
            dependencies.removeFalseNodes();
            dependencies.resetNodes();
            if (nodeCount == dependencies.nodes.size()) {
                throw new Exception("cyclical dependency exists");
            }

            nodeCount = dependencies.nodes.size();
        }

        System.out.println(sb.toString());

    }

    private static TreeNode<Integer> getNext(TreeNode<Integer> root) {
        TreeNode<Integer> result;
        if (null != root.right) {
            result = root.right;
            while (null != result.left) {
                result = result.left;
            }
        } else {
            result = root.parent;
            while (result.parent.data < result.data) {
                result = result.parent;
            }
            result = result.parent;
        }

        return result;


    }

    private static boolean isValidBST(TreeNode<Integer> btree) {
        boolean result = true;

        if (null == btree) {
            return result;
        }

        result = isValidBST(btree.left) && isValidBST(btree.right);

        if (null != btree.left){
            result = result && btree.left.data <= btree.data;
        }

        if (null != btree.right){
            result = result && btree.right.data > btree.data;
        }


        return result;
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