package com.maslke.spring.aop.advice;

class ListNode {
    ListNode next;
    int val;
    public ListNode(int v) { this.val = v; }
}

public class ReverseKGroups {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        ListNode next2 = new ListNode(3);
        ListNode next3 = new ListNode(4);
        ListNode next4 = new ListNode(5);
        head.next = next;
        next.next = next2;
        next2.next = next3;
        next3.next = next4;
        ListNode node = reverseKGroup(head, 2);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        int i = 0;
        while (true) {
            boolean flag = reverse(newHead, k * i + 1, k * i + k);
            i++;
            if (!flag) break;
        }

        return newHead.next;



    }

    private static boolean reverse(ListNode head, int m, int n) {
        ListNode prev = head;
        ListNode temp = head.next;
        for (int i = 1; i < m; i++) {
            prev = temp;
            temp = temp.next;
            if (temp == null) return false;
        }

        ListNode temp2 = head;
        for (int i = 1; i <= n; i++) {
            temp2 = temp2.next;
            if (temp2 == null) return false;
        }

        ListNode after = temp2.next;
        temp2.next = null;
        prev.next = revert(prev.next);
        temp.next = after;
        return true;
    }

    private static ListNode revert(ListNode head) {
        ListNode prev = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }
}
