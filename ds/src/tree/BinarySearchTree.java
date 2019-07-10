package tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree<E extends Comparable> {

    private Node<E> root;


    public void add(E e) {
        if (root == null) {
            root = new Node(e, null, null);
            return;
        }
        addNode(root, e);
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
	
	public void createBinaryTree(){
	}
	
	public void levelOrderTraversal(){
		if(root == null) {
			System.out.println("Tree is empty!");
		}
		
		Deque<Node<E>> q = new ArrayDeque();
		
		q.offer(root);
		Node<E> t;
		while(!q.isEmpty()) {
			t = q.poll();
			System.out.print(t.e + ", ");
			
			if(t.lChild != null){
				q.offer(t.lChild);
			}
			
			if(t.rChild != null){
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
                if(t.rChild != null){
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
			if(t.rChild == stack.peek()) {
				Node<E> temp = stack.pop();
				stack.push(t);
				t = temp;
			}else {
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

    private void addNode(Node<E> node, E e) {
        if (e.compareTo(node.e) < 0) {
            if (node.lChild != null) {
                addNode(node.lChild, e);
            } else {
                node.lChild = new Node(e, null, null);
                return;
            }
        } else {
            if (node.rChild != null) {
                addNode(node.rChild, e);
            } else {
                node.rChild = new Node(e, null, null);
                return;
            }
        }
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
                return "";
            }

            return node.e.toString();

        }
    }
}
