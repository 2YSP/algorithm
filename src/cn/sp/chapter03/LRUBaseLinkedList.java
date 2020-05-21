package cn.sp.chapter03;

import java.util.Scanner;

/**
 * 基于单链表的LRU
 * Created by 2YSP on 2019/10/20.
 */
public class LRUBaseLinkedList<T> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        // 如果链表中存在，删除原数据再插入到链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            insertElemAtBegin(data);
        } else {
            if (length >= this.capacity) {
                // 删除尾节点
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        if (ptr.getNext() == null) {
            return;
        }
        // 倒数第二个节点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void insertElemAtBegin(T data) {
        SNode p = headNode;
        headNode.setNext(new SNode(p.getElement(), p.next));
        headNode.setElement(data);
        p = null;
        length++;
    }

    /**
     * 删除preNode节点下一个元素
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.next;
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 获取查找元素的前一个节点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }

        return null;
    }

    private void printAll() {
        SNode node = headNode;
        while (node != null) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        System.out.println();
    }


    public class SNode<T> {

        private T element;

        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {
        LRUBaseLinkedList<Object> list = new LRUBaseLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}
