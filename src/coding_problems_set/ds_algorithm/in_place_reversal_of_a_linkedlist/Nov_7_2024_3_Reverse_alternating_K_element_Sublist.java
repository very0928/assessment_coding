package coding_problems_set.ds_algorithm.in_place_reversal_of_a_linkedlist;

import coding_problems_set.common_bean.ListNode;

public class Nov_7_2024_3_Reverse_alternating_K_element_Sublist {
    public static void main(String[] args) {

    }

    public ListNode reverse(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode lastNodeOfPrevious = previous;
            ListNode lastNodeOfSublist = current;
            ListNode next;
            for (int i = 0; i < k && current != null; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            if (lastNodeOfPrevious == null) {
                head = previous;
            } else lastNodeOfPrevious.next = previous;
            lastNodeOfSublist.next = current;

            for (int i = 0; current != null && i < k; i++) {
                previous = current;
                current = current.next;
            }
        }

        return head;
    }
}
