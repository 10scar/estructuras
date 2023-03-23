import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static class DoublyLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    /**
   * Constructor para inicializar una lista vacía
   */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public  Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    /**
     * Inserta un nuevo elemento al frente de la lista
     * @param data el dato a insertar
     */
    public void pushFront(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        if (head != null) {
            head.setPrev(newNode);
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * Inserta un nuevo elemento al final de la lista
     * @param data el dato a insertar
     */
    public void pushBack(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setPrev(tail);
        if (tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }


    /**
     * Devuelve el número de elementos en la lista
     * @return el tamaño de la lista
     */
    public int getSize() {
        return size;
    }



    public void delete_node(Node<T> node) {
        if (node == null) {
            return;
        }
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.next = null;
        size--;
    }


    /**
       * Imprime los elementos de la lista en la consola
       */
      public void printList() {
        Node<T> current = head;
        while (current != null) {
          System.out.print(current.getData());
          if(current.getNext()!= null){
              System.out.print(" ");
          }
            current = current.getNext();
        }
    }


        private static class Node<T> {
            private T data;
            private Node<T> next;
            private Node<T> prev;
    
            /**
             * Constructor para crear un nuevo nodo con el dato dado
             * @param data el dato que se almacenará en el nodo
             */
            public Node(T data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
    
            /**
             * Devuelve el dato almacenado en el nodo
             * @return el dato almacenado en el nodo
             */
            public T getData() {
                return data;
            }
    

    
            /**
             * Devuelve el siguiente nodo en la lista
             * @return el siguiente nodo en la lista
             */
            public Node<T> getNext() {
                return next;
            }
    
            /**
             * Establece el siguiente nodo en la lista
             * @param next el nuevo siguiente nodo en la lista
             */
            public void setNext(Node<T> next) {
                this.next = next;
            }
    
            /**
             * Devuelve el nodo anterior en la lista
             * @return el nodo anterior en la lista
             */
            public Node<T> getPrev() {
                return prev;
            }
    
            /**
             * Establece el nodo anterior en la lista
             * @param prev el nuevo nodo anterior en la lista
             */
            public void setPrev(Node<T> prev) {
                this.prev = prev;
            }
        }
  

    }

            /**
           * funcion para UNCODE
           * @return el nodo siguiente en la lista
           */
          public static void addStringOfNumbers(DoublyLinkedList<Integer> arr, Scanner sc) {
         
                //System.out.println("Ingrese una cadena de texto separada por espacios:");

                String input = sc.nextLine();
                  // Creamos un array de strings a partir del input
                String[] tokens = input.split(" ");
    
                // Recorremos las palabras
                for (String word : tokens) {
                    // Insertamos
                    int cast = Integer.parseInt(word);
                    arr.pushBack(cast);
                }
            
        }


        public static void main(String[] args){
        
            DoublyLinkedList<Integer> lista1 = new DoublyLinkedList<Integer>();
            DoublyLinkedList<Integer> lista2 = new DoublyLinkedList<Integer>();
            Scanner sc = new Scanner(System.in);
            addStringOfNumbers(lista1,sc);
            addStringOfNumbers(lista2,sc);
    

            int cantidad = lista1.getSize();
            DoublyLinkedList.Node<Integer> lista1_head= lista1.getHead();
            DoublyLinkedList.Node<Integer> lista2_tail = lista2.getTail();
            

            for(int i =0;i<cantidad;i++){
                DoublyLinkedList.Node<Integer> lista1_head_tem = lista1_head.getNext();
                DoublyLinkedList.Node<Integer> lista2_tail_tem = lista2_tail.getPrev();

                if(lista1_head.getData() == lista2_tail.getData()){
                    lista1.delete_node(lista1_head);
                    lista2.delete_node(lista2_tail);
                }
                
                lista1_head = lista1_head_tem;
                lista2_tail = lista2_tail_tem;  
                
                
                
            }

            //imprime los elementos restantes
            lista1.printList();
            System.out.println("");
            lista2.printList();
            

        
        }
        
}
