package coding_problems_set.ds_algorithm.in_place_reversal_of_a_linkedlist;

import coding_problems_set.common_bean.ListNode;

public class Nov_7_2024_2_Reverse_every_K_element_Sublist {
    public static void main(String[] args) {
        Nov_7_2024_2_Reverse_every_K_element_Sublist sol = new Nov_7_2024_2_Reverse_every_K_element_Sublist();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = sol.reverse(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public ListNode reverse(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode lastNodeOfPrevious = previous;
            ListNode lastNodeOfSubset = current;
            ListNode next;
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            if (lastNodeOfPrevious == null) {
                head = previous;
            } else lastNodeOfPrevious.next = previous;
            lastNodeOfSubset.next = current;
            previous = lastNodeOfSubset;
        }
        return head;
    }
}
