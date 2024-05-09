package Tasks;

class ListNode {
    int value = 0;
    ListNode next = null;

    ListNode() {
    }

    ListNode(int val, ListNode next) {
        this.value = val;
        this.next = next;
    }
}

public class LinkedLists {

    static ListNode reverse(ListNode root) {
        ListNode head = null;

        while (root != null) {
            ListNode next = root.next;
            root.next = head;
            head = root;
            root = next;
        }

        return head.next;
    }

    public static void main(String a[]) {

        ListNode root = new ListNode(
                4,
                new ListNode(
                        9,
                        new ListNode(
                                1,
                                new ListNode(
                                        7,
                                        new ListNode(2, new ListNode())))));

        var reversed = reverse(root);
        if (reversed == null) {
            return;
        }

        while (reversed != null) {
            System.out.println(reversed.value);
            reversed = reversed.next;
        }
    }
}
