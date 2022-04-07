package a03_linked_list;

import java.util.List;

public class Leetcode_2_AddTwoNums
{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null)
        {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            // System.out.println(curr.next.val);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0)
        {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    public static void main(String[] args)
    {
        // Specify l1
        ListNode l1 = new ListNode(9, null);
        l1.next = new ListNode(9, null);
        l1.next.next = new ListNode(9, null);

        // Speicfy l2
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4, null);

        // Add l1 and l2
        Leetcode_2_AddTwoNums sol = new Leetcode_2_AddTwoNums();
        ListNode res = sol.addTwoNumbers(l1, l2);

        // Show the result
        while (res!=null)
        {
            System.out.println(res.val);
            res = res.next;
        }

    }
}
