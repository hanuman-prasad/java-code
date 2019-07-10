package list;

public class LinkList<E> {

    private Node<E> head;

    public void add(E e) {
        addAtEnd(e);
    }

    public void addAt(int index, E e) {
        addAtIndex(index, e);
    }

    public void reverse() {
        reverseLinkList();
    }

    public void reverseRecursively() {
        reverseLinkListRecursively(head);
    }


    public boolean findLoop(){
        if(head == null || head.next == null) {
            return false;
        }

        Node<E> t = head;
        Node<E> fast = head;
        Node<E> fastPrev = head;

        while (fast != null && fast.next !=null) {
            t = t.next;
            fastPrev = fast;
            fast = fast.next.next;
        }

        return true;
    }

    public void addLoop() {
        if(head == null || head.next == null) {
            return;
        }
        Node<E> t = head;
        Node<E> fast = head;
        Node<E> fastPrev = head;

        while (fast != null && fast.next !=null) {
            t = t.next;
            fastPrev = fast;
            fast = fast.next.next;
        }

        if(fastPrev.next == null) {
            fastPrev.next = t;
        }else {
            fastPrev.next.next = t;
        }

    }

    private Node<E> reverseLinkListRecursively(Node<E> node) {
        if(node ==null || node.next == null) {
			head = node;
			return head;
		}


        Node<E> nextNode = reverseLinkListRecursively(node.next);
        node.next = null;
        nextNode.next = node;

        return node;

    }

    private Node<E> reverseLinkListPairRecursively(Node<E> node) {
        if(node ==null || node.next == null) {
            head = node;
            return head;
        }

//        if(node != null && node.next != null) {
            Node<E> nextNode = reverseLinkListPairRecursively(node.next.next);
            nextNode.next.next = nextNode.next;
            nextNode.next = node;
            node.next = null;
            return node;
//        }


//        return reverseLinkListPairRecursively(node.next);
//        Node<E> nextNode = reverseLinkListPairRecursively(node.next);
//        node.next = null;
//        nextNode.next = node;
//
//        return node;

    }

    private void reverseLinkList() {

        if (head == null || head.next == null) {
            return;
        }

        Node<E> current = head;
        Node<E> next;
        Node<E> prev = null;


        while (current.next != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;

    }


    private void addAtEnd(E e) {
        if (head == null) {
            head = new Node(e, null);
        } else {
            Node<E> t = head;
            while (t.next != null) {
                t = t.next;
            }
            t.next = new Node(e, null);
        }
    }

    private void addAtIndex(int index, E e) {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<E> t = head;

        while (index > 1 && t.next != null) {
            t = t.next;
            index--;
        }

        if (index >= 1 && t.next == null) {
            throw new IndexOutOfBoundsException("Invalid index");
        }


        t.next = new Node(e, t.next);
    }


    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        Node<E> t = head;
        StringBuilder sb = new StringBuilder();
        sb.append("head : ");
        while (t != null) {
            if (t.e != null) {
                sb.append(t.e.toString());
                if (t.next != null) {
                    sb.append(" -> ");
                }
            }
            t = t.next;
        }
        return sb.toString();
    }

    public void printLinkListNode() throws InterruptedException {
        Node<E> t = head;

        while(t!=null){
            System.out.print(t.e + ", ");
            Thread.sleep(1000*1);
            t=t.next;
        }
        System.out.println();
    }

    private static class Node<E> {

        private E e;
        private Node<E> next;

        Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            String currentElement = e != null ? e.toString() : null;
            String nextElement = next != null ? next.e != null ? next.e.toString() : null : null;
            return "[" + currentElement + ", next->" + nextElement + "]";
        }
    }

}
