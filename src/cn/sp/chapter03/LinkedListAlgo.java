package cn.sp.chapter03;

/**
 * Created by 2YSP on 2019/10/21.
 */
public class LinkedListAlgo {

    /**
     * 单链表反转
     *
     * @return
     */
    public static Node reverse(Node list) {
        Node curr = list;
        Node pre = null;
        while (curr != null) {
            Node next = curr.next;
            // 当前节点的next指向前面的节点
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    // 删除倒数第k个节点,两个指针一次遍历
    // 参考https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-by-l/
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }

        if (fast == null) {
            return list;
        }

        Node slow = list;
        Node prev = null;

        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }

        return list;
    }

    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0, null);
        // 添加哑节点
        dummy.next = head;
        Node first = dummy;
        Node second = dummy;
        for (int i = 1; i < n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }


    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

}
