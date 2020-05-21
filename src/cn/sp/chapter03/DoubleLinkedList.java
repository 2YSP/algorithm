package cn.sp.chapter03;

/**
 * 双向链表
 * Created by 2YSP on 2019/10/20.
 */
public class DoubleLinkedList<T> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;


    private int size;

    private int capacity;

    private Node<T> head;

    public DoubleLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public DoubleLinkedList(Integer capacity) {
        this.capacity = capacity;
        size = 0;
        head = null;
    }

    /**
     * @param data
     */
    public void add(T data) {
        if (isEmpty()) {
            head = new Node<>(data, null, null);
            size++;
        } else {
            if (isFull()) {
                removeLast();
                insertToBegin(data);
            } else {
                insertToBegin(data);
            }
        }
    }

    private void removeLast() {
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        if (p == null) {
            return;
        }

        Node prev = p.prev;
        prev.next = null;
        p = null;
        size--;
    }

    private void insertToBegin(T data) {
        Node p = head;
        Node newNode = new Node();
        newNode.data = data;
        newNode.prev = null;
        p.prev = newNode;
        newNode.next = p;
        head = newNode;
        size++;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private boolean isEmpty() {
        return size == 0;
    }


    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("不保存null");
        }
        // 头结点
        if (head != null && head.data.equals(data)) {
            removeHead();
            return true;
        }
        Node node = get(data);
        if (node == null) {
            return false;
        }

        Node prev = node.prev;
        Node next = node.next;
        // node是最后一个
        if (next == null) {
//      removeLast();
            prev.next = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        node = null;
        size--;
        return true;
    }


    private void removeHead() {
        Node next = head.next;
        next.prev = null;
        head = next;
        size--;
    }


    public Node get(T data) {
        if (isEmpty()) {
            return null;
        }
        Node p = head;
        while (p != null && p.data != null) {
            if (p.data.equals(data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }


    public int getSize() {
        return this.size;
    }

    public class Node<T> {

        private T data;
        private Node prev;
        private Node next;

        public Node() {

        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + "  ");
            p = p.next;
        }
        System.out.println("大小为：" + this.size);
    }


    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

        list.add(3);
        list.add(6);
        list.add(7);
        list.add(8);
        list.printAll();
//    list.removeHead();
//    list.printAll();
        list.remove(8);
        list.printAll();
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(8);
        list.add(11);
        list.add(12);
        list.printAll();

    }
}
