import java.util.NoSuchElementException;

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
        public int size() {
            return size;
        }
        
        // Devuelve la capacidad actual del arreglo
        public int capacity() {
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
        
        // Añade un elemento antes de la posición index del arreglo
        public void addBefore(int index, E element) {
            if (index < 0 || index > size) { // se verifica que el índice sea válido
                throw new IndexOutOfBoundsException();
            }
            if (size == capacity) { // si el arreglo está lleno, se aumenta su capacidad
                grow();
            }
            if (index == 0) { // si se quiere añadir al inicio, se llama al método pushFront
                pushFront(element);
                return;
            }
            int actualIndex = (head + index) % capacity; // se obtiene el índice real del elemento en el arreglo
            // se mueven los elementos a la derecha del índice para hacer espacio al nuevo elemento
            for (int i = size - 1; i >= index; i--) {
                int actualIndexI = (head + i) % capacity;
                int actualIndexI1 = (head + i + 1) % capacity;
                data[actualIndexI1] = data[actualIndexI];
            }
            data[actualIndex] = element; // se añade el nuevo elemento en la posición indicada
            tail = (tail + 1) % capacity; // se actualiza el índice del último elemento
            size++; // se incrementa el tamaño del arreglo
        }

        // Añade un elemento después de la posición index del arreglo
    public void addAfter(int index, E element) {
        if (index < 0 || index >= size) { // se verifica que el índice sea válido
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity) { // si el arreglo está lleno, se aumenta su capacidad
            grow();
        }
        if (index == size - 1) { // si se quiere añadir al final, se llama al método add
            add(element);
            return;
        }
        int actualIndex = (head + index + 1) % capacity; // se obtiene el índice real del elemento en el arreglo
        // se mueven los elementos a la derecha del índice para hacer espacio al nuevo elemento
        for (int i = size - 1; i > index; i--) {
            int actualIndexI = (head + i) % capacity;
            int actualIndexI1 = (head + i + 1) % capacity;
            data[actualIndexI1] = data[actualIndexI];
        }
        data[actualIndex] = element; // se añade el nuevo elemento en la posición indicada
        tail = (tail + 1) % capacity; // se actualiza el índice del último elemento
        size++; // se incrementa el tamaño del arreglo
    }
    
    // Elimina y devuelve el último elemento del arreglo
    public E popBack() {
        if (size == 0) { // si el arreglo está vacío, no se puede eliminar ningún elemento
            throw new NoSuchElementException();
        }
        tail = (tail - 1 + capacity) % capacity; // se actualiza el índice del último elemento
        E element = data[tail]; // se obtiene el último elemento
        data[tail] = null; // se elimina la referencia al último elemento para permitir la recolección de basura
        size--; // se decrementa el tamaño del arreglo
        return element; // se devuelve el último elemento
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % capacity; // se obtiene el índice real del elemento en el arreglo
            sb.append(data[actualIndex]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void printArray() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % capacity;
            System.out.print(data[actualIndex]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
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

    public static void main(String[] args) {

    }
}
