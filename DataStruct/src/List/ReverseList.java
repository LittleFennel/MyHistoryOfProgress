/**
 * @program: MyHistoryOfProgress
 * @ClassName ReverseList
 * @description: 反转单双链表
 * @author: HayashiSama
 * @create: 2023-03-06 15:29
 * @Version 1.0
 **/
package List;

public class ReverseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode lastNode;
        public DoubleNode nextNode;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.nextNode;
            head.nextNode = pre;
            head.lastNode = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
