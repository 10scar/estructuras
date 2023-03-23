package listas_enlazada.generic_lista_doble_enlazada;

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
     * Inserta un nuevo elemento en orden
     * @param data el dato a insertar
     */
    public void insertInOrder(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null || head.getData().compareTo(data) > 0) {
            // Si la lista está vacía o el nuevo elemento debe ir al frente
            pushFront(data);
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null && current.getNext().getData().compareTo(data) <= 0) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        newNode.setPrev(current);
        current.setNext(newNode);
        if (newNode.getNext() != null) {
            newNode.getNext().setPrev(newNode);
        } else {
            tail = newNode;
        }
        size++;
    }

    /**
     * Inserta un nuevo elemento después de un elemento existente en la lista
     * @param prevData el dato después del cual se insertará el nuevo elemento
     * @param newData el nuevo dato a insertar
     */
    public void insertAfter(T prevData, T newData) {
        Node<T> current = head;
        while (current != null && !current.getData().equals(prevData)) {
            current = current.getNext();
        }
        if (current != null) {
            Node<T> newNode = new Node<>(newData);
            newNode.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(newNode);
            } else {
                tail = newNode;
            }
            newNode.setPrev(current);
            current.setNext(newNode);
            size++;
        }
    
    }

    public T popAfter(Node<T> node) {
        if (node == null || node.next == null) {
            return null;
        }
        Node<T> temp = node.next;
        node.next = temp.next;
        if (node.next != null) {
            node.next.prev = node;
        } else {
            tail = node;
        }
        temp.next = null;
        temp.prev = null;
        size--;
        return temp.data;
    }
    
    public T popBefore(Node<T> node) {
        if (node == null || node.prev == null) {
            return null;
        }
        Node<T> temp = node.prev;
        node.prev = temp.prev;
        if (node.prev != null) {
            node.prev.next = node;
        } else {
            head = node;
        }
        temp.prev = null;
        temp.next = null;
        size--;
        return temp.data;
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
       * Elimina un elemento de la lista
       * @param data el dato a eliminar
       */
      public void delete(T data) {
        if (head == null) {
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
            size--;
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null && !current.getNext().getData().equals(data)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
            current.getPrev().setNext(current.getNext());
            size--;
        } else if (current == tail) {
            tail = current.getPrev();
            tail.setNext(null);
            size--;
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo devuelve.
     *
     * @return el dato del primer elemento de la lista
     * @throws NoSuchElementException si la lista está vacía
     */
    public T popFront() throws NoSuchElementException {
      if (head == null) {
          throw new NoSuchElementException("La lista está vacía");
      }
      T data = head.getData();
      head = head.getNext();
      if (head == null) {
          // Si se eliminó el último nodo, la cola debe ser nula también
          tail = null;
      } else {
          head.setPrev(null);
      }
      size--;
      return data;
  }

    /**
     * Elimina el último elemento de la lista y lo devuelve
     */
    public T popBack() throws NoSuchElementException {
      if (tail == null) {
          throw new NoSuchElementException("La lista está vacía");
      }
      T data = tail.getData();
      tail = tail.getPrev();
      if (tail == null) {
          // Si se eliminó el último nodo, la cabeza debe ser nula también
          head = null;
      } else {
          tail.setNext(null);
      }
      size--;
      return data;
    }

    /**
     * Devuelve el número de elementos en la lista
     * @return el tamaño de la lista
     */
    public int getSize() {
        return size;
    }

    /**
     * Devuelve el elemento en una posición dada en la lista
     * @param index la posición del elemento a devolver
     * @return el elemento en la posición dada
     * @throws IndexOutOfBoundsException si la posición dada es inválida
     */
    public T get(int index) {
        if (index < 0 || index >= size){
          throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }


    /**
       * Imprime los elementos de la lista en la consola
       */
      public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getPrev();
            }
        }


    /**
    * Imprime los elementos de la lista en la consola en orden inverso
    */
    public void printReverse() {
        Node<T> current = tail;
        while (current != null) {
        System.out.println(current.getData());
        current = current.getPrev();
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
             * Establece el dato almacenado en el nodo
             * @param data el nuevo dato a almacenar en el nodo
             */
            public void setData(T data) {
                this.data = data;
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
          public static void addStringOfNumbers(DoublyLinkedList<Integer> arr) {
            try (Scanner sc = new Scanner(System.in)) {
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
        }

        public static void main(String[] args) {
    
        }
        
}
