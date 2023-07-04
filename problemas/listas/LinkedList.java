public class LinkedList {
  private Node head;

  public LinkedList() {
    head = null;
  }

  public void pushFront(int data) {
    Node nodo = new Node(data);
    nodo.setNext(head);
    head = nodo;
  }
  public void pushBack(int data){
    Node nodo = new Node(data);
    
  }
  public void printList() {
    Node flag = head;
    while (flag != null) {
      System.out.println(flag.getData());
      flag = flag.getNext();
    }
  }

  public void insertList(int Item){
    
    while(head!= null || )
  }
  public class Node {
    private int data;
    private Node next;
  
    public Node(int data) {
      this.data = data;
      next = null;
    } 
  
    public int getData() {
      return data;
    } 
  
    public void setData(int data) {
      this.data = data;
    } 
  
    public Node getNext() {
      return next;
    } 
  
    public void setNext(Node next) {
      this.next = next;
    }
  }
  public static void main(String[] args) {
    System.out.println("Hello world!");
    LinkedList list = new LinkedList();
    list.pushFront(5);
    list.pushFront(2);
    list.printList();
  }
}