
public class MergeList {


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(l1, l2);

        print(listNode);

    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null && l2 != null) {
            return l2;
        }

        if (l1 != null && l2 == null) {
            return l1;
        }

        mergeList(l1, l2);

        return l1;

    }


    private static void mergeList(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return;
        }

        if (l1.next == null) {
            l1.next = l2;
            return;
        }

        if (l2.next == null) {
            l2.next = l1;
            return;
        }

        if (l2.val <= l1.val) {
            ListNode t1 = l1.next;
            ListNode t2 = l2.next;
            l1.next = l2;
            l1.next.next = t1;
            l1 = t1;
            l2 = t2;
            mergeList(l1, l2);
        } else {

            mergeList(l1.next, l2.next);
        }
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("[");
            sb.append(val);
            sb.append("->");
            sb.append(next != null ? next.val : null);
            sb.append("]");
            return sb.toString();
        }
    }
}