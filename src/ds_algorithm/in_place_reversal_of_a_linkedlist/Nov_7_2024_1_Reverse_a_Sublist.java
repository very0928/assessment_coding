package ds_algorithm.in_place_reversal_of_a_linkedlist;

import common_bean.ListNode;

/**
 * Given the head of a LinkedList and two positions 'p' and 'q',
 * reverse the LinkedList from position 'p' to 'q'
 */
public class Nov_7_2024_1_Reverse_a_Sublist {
    public static void main(String[] args) {
        Nov_7_2024_1_Reverse_a_Sublist sol = new Nov_7_2024_1_Reverse_a_Sublist();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = sol.reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public ListNode reverse(ListNode head, int p, int q) {
        if (p == q) {
            return head;
        }
        ListNode previous = null;
        ListNode current = head;
        for (int i = 0; current != null && i < p - 1; i++) {
            previous = current;
            current = current.next;
        }

        ListNode lastNodeOfPrevious = previous;
        ListNode lastNodeOfSubset = current;
        ListNode next = null;
        for (int i = p; current != null && i <= q; i++) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        if (lastNodeOfPrevious != null) {
            lastNodeOfPrevious.next = previous;
        } else {
            head = previous;
        }
        lastNodeOfSubset.next = current;

        return head;
    }
}
