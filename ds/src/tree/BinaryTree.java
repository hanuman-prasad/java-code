package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class BinaryTree<E extends Comparable> {

    private static int methodCallCount;
    private Node<E> root;

    public void add(E e) {
        Node<E> newNode = new Node(e, null, null);
        if (root == null) {
            root = newNode;
            return;
        }


        Deque<Node<E>> q = new ArrayDeque();
        Node<E> t = root;
        q.offer(t);

        while (true) {
            t = q.poll();

            if (t.lChild == null) {
                t.lChild = newNode;
                break;
            } else {
                q.offer(t.lChild);
            }

            if (t.rChild == null) {
                t.rChild = newNode;
                break;
            } else {
                q.offer(t.rChild);
            }
        }

        maxHeapyfy(newNode);
    }

    public void createMirror() {
        if (root == null) {
            return;
        }

        createMirror(root);
    }
	
	
	public void createMaxHeap(){
		if(root == null){
			return;
		}
		
		maxHeapyfy(root);
        System.out.println(methodCallCount);
	}
	
	
	private void maxHeapyfy(Node<E> node){
        methodCallCount++;


		if(node.rChild != null) {
            maxHeapyfy(node.lChild);
        }
        if(node.rChild !=null) {
            maxHeapyfy(node.rChild);
        }

		if(node.lChild != null && node.lChild.e.compareTo(node.e) > 0){
			E t = node.lChild.e;
			node.lChild.e = node.e;
			node.e = t;
			maxHeapyfy(node.lChild);
		}
		
		if(node.rChild != null && node.rChild.e.compareTo(node.e) > 0){
			E t = node.rChild.e;
			node.rChild.e = node.e;
			node.e = t;
			maxHeapyfy(node.rChild);
		}
	}
			

    private void createMirror(Node<E> node) {
        if (node == null) {
            return;
        }

        createMirror(node.lChild);
        createMirror(node.rChild);
        Node<E> t = node.lChild;
        node.lChild = node.rChild;
        node.rChild = t;
    }


    public static void isMirror(BinaryTree b1, BinaryTree b2){
        if(b1.root == null || b2.root == null){
            System.out.println("one or both trees are empty.");
            return;
        }

        System.out.println("One is mirror of another : " + isMirror(b1.root, b2.root));

    }

    private static boolean isMirror(Node root1, Node root2) {
        if(root1 == null && root1 == null){
            return true;
        }

        if((root1 != null && root2 == null) || (root1 == null && root2 != null)){
            return false;
        }

        if(!root1.e.equals(root2.e)){
            return false;
        }

        return isMirror(root1.lChild, root2.rChild) && isMirror(root1.rChild, root2.lChild);
    }

    public int hight() {
        if (root == null) {
            return 0;
        }

        return hight(root);
    }

    public void printAllPathFromRoot() {
        if (root == null) {
            return;
        }


        printAllPathFromRoot(root, new ArrayList<>(), 0);

    }

    private void printAllPathFromRoot(Node<E> node, ArrayList<E> col, int len) {
        if (node == null) {
            return;
        }

        col.add(len++, node.e);

        if (node.lChild == null && node.rChild == null) {
            printList(col, len);
        }

        printAllPathFromRoot(node.lChild, col, len);
        printAllPathFromRoot(node.rChild, col, len);

    }

    private void printList(ArrayList<E> col, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(col.get(i) + ", ");
        }
        System.out.println();
    }

    public int leafNodeCout() {
        if (root == null) {
            return 0;
        }

        Deque<Node<E>> q = new ArrayDeque<>();
        q.offer(root);
        Node<E> t;

        int leafNodes = 0;
        while (!q.isEmpty()) {
            t = q.poll();

            if (t.lChild == null && t.rChild == null) {
                leafNodes++;
            }

            if (t.lChild != null) {
                q.offer(t.lChild);
            }

            if (t.rChild != null) {
                q.offer(t.rChild);
            }

        }

        return leafNodes;

    }

    public void leafNodes() {
        if (root == null) {
            return;
        }

        leafNodes(root);
        System.out.println();
    }

    private void leafNodes(Node<E> node) {
        if (node == null) {
            return;
        }
        if (node.lChild == null && node.rChild == null) {
            System.out.print(node.e + ", ");
        }

        leafNodes(node.lChild);
        leafNodes(node.rChild);
    }

    public int hightNonRecursive() {
        int h = 0;
        if (root == null) {
            return h;
        }

        Deque<Node<E>> q = new ArrayDeque();
        Deque temp = new ArrayDeque();
        q.offer(root);
        Node<E> t;
        while (!q.isEmpty()) {
            h++;
            while (!q.isEmpty()) {
                t = q.poll();
                if (t.lChild != null) {
                    temp.offer(t.lChild);
                }
                if (t.rChild != null) {
                    temp.offer(t.rChild);
                }
            }
            Deque tempQ = q;
            q = temp;
            temp = tempQ;
        }

        return h;

    }


    public void depestNode() {
        if (root == null) {
            return;
        }

        Deque<Node<E>> q = new ArrayDeque<>();
        q.offer(root);
        Node<E> t = null;
        while (!q.isEmpty()) {
            t = q.poll();

            if (t.lChild != null) {
                q.offer(t.lChild);
            }

            if (t.rChild != null) {
                q.offer(t.rChild);
            }
        }

        System.out.println("Depest element is : " + t.e);
    }


    private int hight(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int l = hight(node.lChild);
        int r = hight(node.rChild);

        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }


    public int max() {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        return max(root);
    }

    public int min() {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        return min(root);
    }

    private int max(Node<E> node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int max = (Integer) node.e;
        int lmax = max(node.lChild);
        int rmax = max(node.rChild);

        if (lmax > max) {
            max = lmax;
        }
        if (rmax > max) {
            max = rmax;
        }

        return max;
    }

    private int min(Node<E> node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int min = (Integer) node.e;
        int lmin = min(node.lChild);
        int rmin = min(node.rChild);

        if (lmin < min) {
            min = lmin;
        }
        if (rmin < min) {
            min = rmin;
        }

        return min;
    }

    public void preOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        preOrder(root);
        System.out.println();
    }

    public void inOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        inOrder(root);
        System.out.println();
    }

    public void postOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        postOrder(root);
        System.out.println();
    }


    public void levelOrder() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        levelOrder(root);
    }

    private void levelOrder(Node<E> root) {
        if (root == null) {
            return;
        }


    }

    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        Deque<Node<E>> q = new ArrayDeque();

        q.offer(root);
        Node<E> t;
        while (!q.isEmpty()) {
            t = q.poll();
            System.out.print(t.e + ", ");

            if (t.lChild != null) {
                q.offer(t.lChild);
            }

            if (t.rChild != null) {
                q.offer(t.rChild);
            }
        }

        System.out.println();
    }

    public void preOrderNonRecursive() {
        if (root == null) {
            System.out.println("Tree is empty!");
        }
        Deque<Node<E>> stack = new ArrayDeque();

        Node<E> t = root;

        while (true) {

            while (t != null) {
                System.out.print(t.e + ", ");
                stack.addFirst(t);
                t = t.lChild;

            }

            if (stack.isEmpty()) {
                break;
            }
            t = stack.removeFirst();

            t = t.rChild;
        }
        System.out.println();
    }

    public void inOrderNonRecursive() {
        if (root == null) {
            System.out.println("Tree is empty!");
        }
        Deque<Node<E>> stack = new ArrayDeque();

        Node<E> t = root;


        while (true) {

            while (t != null) {
                stack.addFirst(t);
                t = t.lChild;
            }

            if (stack.isEmpty()) {
                break;
            }

            t = stack.removeFirst();

            System.out.print(t.e + ", ");
            t = t.rChild;
        }
        System.out.println();
    }

    public void postOrderNonRecursive() {
        if (root == null) {
            System.out.println("Tree is empty!");
        }

        Deque<Node<E>> stack = new ArrayDeque();

        Node<E> t = root;

        while (true) {

            while (t != null) {
                if (t.rChild != null) {
                    stack.push(t.rChild);
                }
                stack.push(t);
                t = t.lChild;
            }

            if (stack.isEmpty()) {
                break;
            }

            t = stack.pop();
            /*if (t.rChild == null) {
                System.out.print(t.e + ", ");
				t = null;
				continue;
            }*/
            if (t.rChild == stack.peek()) {
                Node<E> temp = stack.pop();
                stack.push(t);
                t = temp;
            } else {
                System.out.print(t.e + ", ");
                t = null;
            }

        }


        System.out.println();
    }


    public int size() {
        if (root == null) {
            return 0;
        }

        return countNode(root);
    }

    private int countNode(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return 1 + countNode(node.lChild) + countNode(node.rChild);
    }

    private void preOrder(Node<E> node) {

        if (node == null) {
            return;
        }

        System.out.print(node.e + ", ");
        preOrder(node.lChild);
        preOrder(node.rChild);

    }

    private void inOrder(Node<E> node) {

        if (node == null) {
            return;
        }

        inOrder(node.lChild);
        System.out.print(node.e + ", ");
        inOrder(node.rChild);

    }

    private void postOrder(Node<E> node) {

        if (node == null) {
            return;
        }

        postOrder(node.lChild);
        postOrder(node.rChild);
        System.out.print(node.e + ", ");

    }

    private static class Node<E> {
        E e;
        Node<E> lChild;
        Node<E> rChild;

        Node(E e, Node<E> lChild, Node<E> rChild) {
            this.e = e;
            this.lChild = lChild;
            this.rChild = rChild;

        }

        @Override
        public String toString() {

            final StringBuilder sb = new StringBuilder("[");
            sb.append(getElement(this));
            sb.append(", L->").append(getElement(lChild));
            sb.append(", R->").append(getElement(rChild));
            sb.append(']');
            return sb.toString();
        }

        private String getElement(Node<E> node) {
            if (node == null || node.e == null) {
                return "<>";
            }

            return node.e.toString();

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(e, node.e);
        }

        @Override
        public int hashCode() {

            return Objects.hash(e);
        }
    }

}
