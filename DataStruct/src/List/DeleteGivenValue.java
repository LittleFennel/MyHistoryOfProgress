/**
 * @program: MyHistoryOfProgress
 * @ClassName DeleteGivenValue
 * @description: 删除链表中的给定值
 * @author: HayashiSama
 * @create: 2023-03-06 16:04
 * @Version 1.0
 **/
package List;

public class DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    public static Node removeValue(Node head, int data) {
        while (head != null) {
            if (head.value != data) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == data) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
