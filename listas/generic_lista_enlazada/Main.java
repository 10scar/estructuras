import java.util.NoSuchElementException;

public class Main {

  public static class LinkedList<T extends Comparable<T>> {
      private Node<T> head;
      private Node<T> tail;
      private int size;

      /**
       * Constructor para inicializar una lista vacía
       */
      public LinkedList() {
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
          head = newNode;
          if (tail == null) {
            //Si la lista está vacía, el nuevo nodo es la cola también
            tail = newNode;
        }
          size++;
      }

      /**
       * Inserta un nuevo elemento al final de la lista
       * @param data el dato a insertar
       */
      public void pushBack(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            // Si la lista está vacía, el nuevo nodo es la cabeza también
            head = newNode;
        } else {
            // Si la lista no está vacía, se agrega el nuevo nodo después de la cola
            tail.setNext(newNode);
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
            newNode.setNext(head);
            head = newNode;
            if (tail == null) {
                // Si la lista está vacía, el nuevo nodo es la cola también
                tail = newNode;
            }
        } else {
            // Si el nuevo elemento debe ir después del primer elemento
            Node<T> current = head;
            while (current.getNext() != null && current.getNext().getData().compareTo(data) <= 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (current == tail) {
                // Si el nuevo nodo es el último, actualizar la cola
                tail = newNode;
            }
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

          //solo si lo encuentra
          if (current != null) {
              Node<T> newNode = new Node<>(newData);
              newNode.setNext(current.getNext());
              current.setNext(newNode);
              size++;
              //actualiza tail
              if(current == tail){
                tail = current;
              }
          }
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
              size--;
              return;
          }
          Node<T> current = head;
          while (current.getNext() != null && !current.getNext().getData().equals(data)) {
              current = current.getNext();
          }
          if (current.getNext() != null) {
              current.setNext(current.getNext().getNext());
              size--;
          }

          //verifica tail
          if(current.getNext()==null){
            tail=current.getNext();

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
        }
        size--;
        return data;
    }

      /**
       * Elimina el último elemento de la lista y lo devuelve
       */
      public T popBack() throws NoSuchElementException {
        
        if (head == null) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T data = head.getData();
        if (head == tail) {
            head = null;
            tail = null;
          
        } else {
            Node current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            data =(T) current.getNext().getData();
            current.setNext(null);
            tail = current;
            
        }
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
              current = current.getNext();
          }
      }

      /**
       * Metodo que casteas las entradas en UN y las agrega
       * @param <T> el tipo de datos almacenado en el nodo
       */

      public void insertFromString(T str) {
        // Dividimos la cadena en palabras
        String[] words = ((String) str).split(" ");
    
        // Recorremos las palabras
        for (String word : words) {
            // Insertamos cada palabra en orden alfabético
            insertInOrder((T) word);
        }
    }
  
      /**
       * Clase interna para representar un nodo de la lista enlazada
       * @param <T> el tipo de datos almacenado en el nodo
       */
      public class Node<T> {
          private T data;
          private Node<T> next;
  
          /**
           * Constructor para crear un nuevo nodo con un dato dado
           * @param data el dato a almacenar en el nodo
           */
          public Node(T data) {
              this.data = data;
              next = null;
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
           * @param data el dato a establecer en el nodo
           */
          public void setData(T data) {
              this.data = data;
          }
  
          /**
           * Devuelve el nodo siguiente en la lista
           * @return el nodo siguiente en la lista
           */
          public Node<T> getNext() {
              return next;
          }
  
          /**
           * Establece el nodo siguiente en la lista
           * @param next el nodo siguiente a establecer en la lista
           */
          public void setNext(Node<T> next) {
              this.next = next;
          }
      }
  
  }
  
  public static void main(String[] args) {
      LinkedList<Integer> list = new LinkedList<>();
      list.pushFront(5);
      list.insertInOrder(2);
      list.pushBack(10);
      list.insertAfter(5, 7);
      list.printList();
      list.delete(5);
      System.out.println("elimine"+list.popFront());
      System.out.println("elimine"+list.popBack());
      System.out.println(list.getSize());
      System.out.println("Element at index 1: " + list.get(0));
      System.out.println("Size of the list: " + list.getSize());
  }
}  
