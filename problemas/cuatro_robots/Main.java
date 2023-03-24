
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    
    public static  class Array<E> {
        private E[] data; // arreglo interno
        private int size; // cantidad de elementos en el arreglo
        private int capacity; // capacidad actual del arreglo
        private int head; // índice del primer elemento en el arreglo
        private int tail; // índice del último elemento en el arreglo
        
        // Constructor
        public Array(int initialCapacity) {
            this.data = (E[]) new Object[initialCapacity];
            this.size = 0;
            this.capacity = initialCapacity;
            this.head = 0;
            this.tail = -1;
        }
        
        // Métodos públicos
        
        
        // Devuelve el elemento en la posición index del arreglo
        public E get(int index) {
            if (index < 0 || index >= size) { // se verifica que el índice sea válido
                throw new IndexOutOfBoundsException();
            }
            int actualIndex = (head + index) % capacity; // se obtiene el índice real del elemento en el arreglo
            return data[actualIndex];
        }
        
        // Devuelve el tamaño actual del arreglo
        public int getsize() {
            return size;
        }
        public int getTail(){
            return tail;
        }
        
        // Devuelve la capacidad actual del arreglo
        public int getcapacity() {
            return capacity;
        }

        public void pushFront(E element) {
            if (size == capacity) { // si el arreglo está lleno, se aumenta su capacidad
                grow();
            }
            head = (head - 1 + capacity) % capacity; // se actualiza el índice del primer elemento
            data[head] = element; // se añade el nuevo elemento al inicio
            size++; // se incrementa el tamaño del arreglo
        }

            // Añade un elemento al final del arreglo
        public void pushBack(E element) {
            if (size == capacity) { // si el arreglo está lleno, se aumenta su capacidad
                grow();
            }
            tail = (tail + 1) % capacity; // se actualiza el índice del último elemento
            data[tail] = element; // se añade el nuevo elemento al final
            size++; // se incrementa el tamaño del arreglo
        }
        
    // Elimina y devuelve el primer elemento del arreglo
    public E popFront() {
        if (size == 0) { // si el arreglo está vacío, no se puede eliminar ningún elemento
            throw new NoSuchElementException();
        }
        E element = data[head]; // se obtiene el primer elemento
        data[head] = null; // se elimina la referencia al primer elemento para permitir la recolección de basura
        head = (head + 1) % capacity; // se actualiza el índice del primer elemento
        size--; // se decrementa el tamaño del arreglo
        return element; // se devuelve el primer elemento
    }

    // Elimina y devuelve el último elemento del arreglo
    public E popBack() {
        if (size == 0) { // si el arreglo está vacío, no se puede eliminar ningún elemento
            throw new NoSuchElementException();
        }
        data[tail] = null; // se elimina la referencia al último elemento para permitir la recolección de basura
        tail = (tail - 1 + capacity) % capacity; // se actualiza el índice del último elemento
        E element = data[tail]; // se obtiene el último elemento
        size--; // se decrementa el tamaño del arreglo
        return element; // se devuelve el último elemento
    }

    

    public void printArray() {
        for (int i = 0; i <size; i++) {
            int actualIndex = (head + i) % capacity;
            System.out.print(data[actualIndex]);
            if (i <size-1) {
                System.out.print(" ");
            }
        }
    }
        
        // Métodos privados
        
        // Aumenta la capacidad del arreglo en un 50%
        private void grow() {
            int newCapacity = capacity + capacity / 2;
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                int actualIndex = (head + i) % capacity;
                newData[i] = data[actualIndex];
            }
            data = newData;
            capacity = newCapacity;
            head = 0;
            tail = size - 1;
        }
    }

    


    public static void addStringOfNumbers(Array<Integer> arr1,Array<Integer> arr2) {
        try (Scanner sc = new Scanner(System.in)) {
			String input = sc.nextLine();
			  // Creamos un array de strings a partir del input
			String[] tokens = input.split(" ");

			// Recorremos las palabras
			for (String word : tokens) {
			    // Insertamos
                int cast = Integer.parseInt(word);
                if(cast>=0){
                    arr1.pushBack(cast);
                }else{
                    arr2.pushBack(cast);
                }
			}
		}
    }

    public static void main(String[] args) {


        Array<Integer>  robost_1_pila = new Array<>(2);
        Array<Integer>  robost_2_cola = new Array<>(2);
        addStringOfNumbers(robost_1_pila,robost_2_cola);


        while(robost_2_cola.getsize()!=0 && robost_1_pila.getsize()!=0){
            //System.out.println("Se enfrentan los siguientes"+ robost_1_pila.get(robost_1_pila.getTail())+" y " +robost_2_cola.get(0));
           if(robost_1_pila.get(robost_1_pila.getTail())>robost_2_cola.get(0)*-1){
                robost_2_cola.popFront();
           }else if(robost_1_pila.get(robost_1_pila.getTail())<robost_2_cola.get(0)*-1){
            robost_1_pila.popBack();
           }else{
            robost_2_cola.popFront();
            robost_1_pila.popBack();
           }
        }

        
        robost_1_pila.printArray();
        robost_2_cola.printArray();
        if(robost_2_cola.getsize()==0 && robost_1_pila.getsize()==0){
            System.out.print("No quedaron robots!");
        }
       
        
    

    }
}
