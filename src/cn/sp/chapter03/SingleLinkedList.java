package cn.sp.chapter03;

/**
 * 单链表
 * Created by 2YSP on 2019/10/19.
 */
public class SingleLinkedList {

  private Node head = null;

  public Node findByValue(int value) {
    Node p = head;
    while (p != null && p.data != value) {
      p = p.next;
    }
    return p;
  }


  public Node findByIndex(int index) {
    Node p = head;
    int pos = 0;
    while (p != null && pos != index) {
      p = p.next;
      ++pos;
    }
    return p;
  }

  /**
   * 无头结点
   * 表头部插入
   * 这种操作将与输入的顺序相反，逆序
   */
  public void insertToHead(int value) {
    Node newNode = new Node(value, null);
    insertToHead(newNode);

  }

  private void insertToHead(Node newNode) {
    if (head == null) {
      head = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }
  }

  /**
   * 顺序插入
   * 链表尾部插入
   */
  public void insertTail(int value) {
    Node newNode = new Node(value, null);
    // 空链表，可以插入新节点作为head,也可以不操作
    if (head == null) {
      head = newNode;
    } else {
      Node p = head;
      while (p != null && p.next != null) {
        p = p.next;
      }
      p.next = newNode;
    }

  }

  public void insertAfter(Node p, int value) {
    Node newNode = new Node(value, null);
    insertAfter(p, newNode);
  }

  public void insertAfter(Node p, Node newNode) {
    if (p == null) {
      return;
    }
    newNode.next = p.next;
    p.next = newNode;
  }

  public void insertBefore(Node p, int value) {
    Node newNode = new Node(value, null);
    insertBefore(p, newNode);
  }

  public void insertBefore(Node p, Node newNode) {
    if (p == null) {
      return;
    }
    if (head == p) {
      insertToHead(newNode);
      return;
    }

    Node q = head;
    // 循环找到p的前驱节点
    while (q != null && q.next != p) {
      q = q.next;
    }

    if (q == null) {
      return;
    }

    newNode.next = p;
    q.next = newNode;

  }

  public void deleteByNode(Node p) {
    if (p == null || head == null) {
      return;
    }

    if (head == p) {
      head = head.next;
      return;
    }

    Node q = head;
    while (q != null && q.next != p) {
      q = q.next;
    }

    if (q == null) {
      return;
    }

    q.next = q.next.next;
  }

  public void deleteByValue(int value) {
    if (head == null) {
      return;
    }
    Node p = head;
    Node q = null;
    while (p != null && p.data != value) {
      q = p;
      p = p.next;
    }

    if (p == null) {
      return;
    }

    if (q == null) {
      // ??就是head
      head = head.next;
    } else {
      q.next = q.next.next;
    }

  }

  public void printAll() {
    Node p = head;
    while (p != null) {
      System.out.printf(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }


  /**
   * 判断是否回文串
   */
  public boolean palindrome() {
    if (head == null) {
      return false;
    } else {
      System.out.println("开始执行找到中间点");
      Node p = head;
      Node q = head;
      if (p.next == null) {
        System.out.println("只有一个元素");
        return true;
      }
      while (q.next != null && q.next.next != null) {
        p = p.next;
        q = q.next.next;
      }

      System.out.println("中间节点" + p.data);
      System.out.println("开始执行奇数节点的回文判断");
      Node leftLink = null;
      Node rightLink = null;

      if (q.next == null) {
        // p 一定为整个链表的中点，且节点数目为奇数
        rightLink = p.next;
        leftLink = inverseLinkList(p).next;
        System.out.println("左边第一个节点" + leftLink.data);
        System.out.println("右边第一个节点" + rightLink.data);
      } else {
        // q p 均为中点
        rightLink = p.next;
        leftLink = inverseLinkList(p);
      }
      return IFResult(leftLink, rightLink);
    }
  }

  private boolean IFResult(Node left, Node right) {
    Node l = left;
    Node r = right;

    System.out.println("left_:"+l.data);
    System.out.println("right_:"+r.data);
    while (l != null && r != null){
      if (l.data == r.data){
        l = l.next;
        r = r.next;
        continue;
      }else {
        break;
      }
    }

    System.out.println("什么结果");
    if ( l == null && r == null){
      System.out.println("");
      return true;
    }else {
      return false;
    }
  }

  /**
   * 无头结点的链表翻转
   */
  private Node inverseLinkList(Node p) {
    Node pre = null;
    Node r = head;
    System.out.println("z--" + r.data);
    Node next = null;
    while (r != p) {
      next = r.next;
      r.next = pre;
      pre = r;
      r = next;
    }

    r.next = pre;
    // 返回左半部分的中点之前的那个节点
    // 从此处开始同步 向两边比较
    return r;
  }


  private static class Node {

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


  public static Node createNode(int value) {
    return new Node(value, null);
  }


  public static void main(String[] args) {
    SingleLinkedList linkedList = new SingleLinkedList();
    int data[] = {1, 2, 5, 2, 1};

    for (int i = 0; i < data.length; i++) {
//      linkedList.insertToHead(data[i]);
      linkedList.insertTail(data[i]);
    }

    linkedList.printAll();

    if (linkedList.palindrome()){
      System.out.println("是回文");
    }else{
      System.out.println("不是回文");
    }
  }
}
