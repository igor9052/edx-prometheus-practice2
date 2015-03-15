package ua.com.igorka.algorithms101.task7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by igor9_000 on 14.03.2015.
 *
 */
public class BinaryTree<T> {

    public int rightCounter = 0;
    private Node<T> root;
    private int[] tree;
    private int[] searchTree;
    private ArrayList<Integer> tempArray = new ArrayList<Integer>();
    private int i = 0;
    private List<Node<T>> nodeLeafList = new ArrayList<Node<T>>();
    private List<Node<T>> nodeSum = new ArrayList<Node<T>>();


    public BinaryTree(int[] tree) {
        this.tree = tree;
    }

    public Node<T> getRoot() {
        return root;
    }

    public int size() {
        return tree.length;
    }

    public int[] getSearchTree() {
        return searchTree;
    }

    public void makeSearchTree() {
        inOrderTreeWalk(0);
        rightCounter = 0;
        searchTree = tree;
        Collections.sort(tempArray);
        //make searchTree as search binary tree
        inOrderTreeWalkAndCopyToSearchTree(0);
        //make Binary Search tree based on Node
        createSearchTree(0);
    }

    public void inOrderTreeWalk(int x) {
        if (tree[x] != 0) {
            inOrderTreeWalk(left(x));
            //System.out.println(tree[x]);
            tempArray.add(tree[x]);
            inOrderTreeWalk(right(x));
        }
    }

    private void inOrderTreeWalkAndCopyToSearchTree(int x) {
        if (tree[x] != 0) {
            inOrderTreeWalkAndCopyToSearchTree(left(x));
            searchTree[x] = tempArray.get(i);
            i++;
            inOrderTreeWalkAndCopyToSearchTree(right(x));
        }
    }


    public void inOrderSearchTreeWalk(int x) {
        if (searchTree[x] != 0) {
            inOrderSearchTreeWalk(left(x));
            System.out.print(searchTree[x] + " ");
            inOrderSearchTreeWalk(right(x));
        }
    }


    public List<Node<T>> getSearchTreeLeafs() {
        nodeLeafList.clear();
        preOrderSearchTreeNodeWalk(root, new GetLeafs());
        return nodeLeafList;
    }

    private void preOrderTreeWalk(int x) {
        if (tree[x] != 0) {
            System.out.println(tree[x]);
            preOrderTreeWalk(left(x));
            preOrderTreeWalk(right(x));
        }
    }

    public void preOrderSearchTreeNodeWalk(Node<T> node, Command command) {
        if (node != null) {
            //System.out.print(node.getValue() + " ");
            if (command != null) {
                command.execute((Node<Integer>) node);
            }
            preOrderSearchTreeNodeWalk(node.getLeft(), command);
            preOrderSearchTreeNodeWalk(node.getRight(), command);
        }
    }

    private void createListLeafs(Node<T> node) {
        if (node.getLeft() == null && node.getRight() == null) {
            nodeLeafList.add(node);
        }
    }

    private void createSearchTree(int x) {
        rightCounter = x;
        tempArray.clear();
        root = new Node<T>();
        preOrderSearchTreeWalkPrivate(x, root, null);
    }

    public void searchTreeSum(Node<T> node) {
        preOrderSearchTreeNodeWalk(node, new SearchSum());
    }

    private Node<T> preOrderSearchTreeWalkPrivate(int x, Node<T> node, Node<T> parentNode) {
        if (searchTree[x] != 0) {
            node.setKey(x);
            node.setValue((T) new Integer(searchTree[x]));
            node.setParent(parentNode);
            //System.out.print(searchTree[x] + " ");
            Node<T> leftNode = preOrderSearchTreeWalkPrivate(left(x), new Node<T>(), node);
            node.setLeft(leftNode);
            Node<T> rightNode = preOrderSearchTreeWalkPrivate(right(x), new Node<T>(), node);
            node.setRight(rightNode);
            return node;
        }
        return null;
    }

    private int calcSum(Node<T> node) {
        int sum = 0;
        nodeSum.clear();
        while (node != null) {
            sum += (Integer) node.getValue();
            nodeSum.add(node);
            node = node.getParent();
            if (sum == 1059 || sum == 1546 || sum == 1940){
                return sum;
            }
        }
        return sum;
    }

    private int left(int x) {
        rightCounter++;
        return x + 1;
    }

    private int right(int x) {
        rightCounter++;
        return rightCounter;
    }

    public interface Command {
        void execute(Node<Integer> node);
    }

    public static class Node<T> {
        private int key;
        private T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }


        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }

    public class GetLeafs implements Command {

        @Override
        public void execute(Node<Integer> node) {
            createListLeafs((Node<T>) node);
        }
    }

    public class SearchSum implements Command {

        @Override
        public void execute(Node<Integer> node) {
            int sum = calcSum((Node<T>) node);
            if (sum == 1059 || sum == 1546 || sum == 1940){
                Collections.reverse(nodeSum);
                System.out.println("*** " + sum + " ***");
                for (Node<T> item : nodeSum) {
                    System.out.println(item.getKey() + " " + item.getValue());
                }
            }
        }
    }


}
