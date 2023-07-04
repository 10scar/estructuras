import java.util.*;
import java.util.Scanner;
public class Main{
static public class ArbolAVL<T extends Comparable<T>> {
    private Node root;

    public ArbolAVL() {
        root = null;
    }

    public void insertAVL(T data) {
        root = insert(data, root);
    }

    private Node insert(T data, Node p) {
        if (p == null) {
            p = new Node(data);
        } else {
            if (data.compareTo(p.data) < 0) {
                p.left = insert(data, p.left);
            } else if (data.compareTo(p.data) > 0) {
                p.right = insert(data, p.right);
            } else {
                System.out.println("Item no insertado");
            }
        }
    
        // Actualizar la altura del nodo
        p.height = 1 + Math.max(height(p.left), height(p.right));
    
        p= balance(p);
    
        return p;
    }
    
    private Node balance(Node p){
        int balance = getBalance(p);
        if (balance > 1) {
            if (getBalance(p.left) >= 0) {
                p = rotateRight(p);
            } else {
                p.left = rotateLeft(p.left);
                p = rotateRight(p);
            }
        } else if (balance < -1) {
            if (getBalance(p.right) <= 0) {
                p = rotateLeft(p);
            } else {
                p.right = rotateRight(p.right);
                p = rotateLeft(p);
            }
        }
        return p;
    }

    private Node findMin(Node p) {
        if (p != null) {
            while (p.left != null)
                p = p.left;
        }
        return p;
    }

    
    private int height(Node p) {
        if (p == null) {
            return 0;
        }
        return p.height;
    }
    
    private int getBalance(Node p) {
        if (p == null) {
            return 0;
        }
        return height(p.left) - height(p.right);
    }
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
    
        // Realizar la rotación
        y.left = x;
        x.right = T2;
    
        // Actualizar las alturas
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
    
        return y;
    }
    
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
    
        // Realizar la rotación
        x.right = y;
        y.left = T2;
    
        // Actualizar las alturas
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
    
        return x;
    }
    public int distanciaEntreNodos( T a, T b){
        T temp;
        if(a.compareTo(b) > 0){
            temp = a;
            a = b;
            b = temp;
        }
        return rangoNodos(root,a,b);
    }
    public int rangoRoot(Node root, T x) {
        if (root.data.equals(x)){
            return 0;
        }
        else if (root.data.compareTo(x) > 0){
            return 1 + rangoRoot(root.left, x);
        }
        return 1 + rangoRoot(root.right, x);
    }
    
    public int rangoNodos(Node root, T a, T b) {
        if (root == null){
            return 0;
        }

        // Descender por la izquierda
        if (root.data.compareTo(a) > 0 && root.data.compareTo(b) > 0)
            return rangoNodos(root.left, a, b);
    
        // Descender por la derecha
        if (root.data.compareTo(a) < 0 && root.data.compareTo(b) < 0)
            return rangoNodos(root.right, a, b);
    
        // Encontró la bifurcación y calcula la distancia hasta cada nodo desde la raíz dada
        if (root.data.compareTo(a) >= 0 && root.data.compareTo(b) <= 0)
            return rangoRoot(root, a) + rangoRoot(root, b);
    
        return 0;
    }
    

    // Inner Class: Node
    private class Node {
        private Node left;
        private T data;
        private Node right;
        private int height;

        public Node(T data) {
            left = null;
            this.data = data;
            right = null;
        }
    }

}
public static void insertS(ArbolAVL<String> arbol,String str){
    String[] tokens = str.split(" ");
    // Recorremos las palabras
    for (String word : tokens) {
        // Insertamos
        arbol.insertAVL(word);
    }
}


    public static void main(String[] args) {
        String lugares = "Mongui Sachica Tinjaca Combita Chiquiza Sutamarchan Tibasosa Toca Guican Chivata Topaga Soraca Gameza Guayata Raquira Nobsa Tenza Aquitania";
        ArbolAVL<String> arbol = new ArbolAVL<>();
        Scanner sc = new Scanner(System.in);

        //llenar el arbol
        insertS(arbol,lugares);

        String lugares_comparar = sc.nextLine();
        String[] tokens = lugares_comparar.split(" ");
        String lugar_a = tokens[0];
        String lugar_b = tokens[1];

        int dis = arbol.distanciaEntreNodos(lugar_a,lugar_b);
        System.out.print(dis);
        sc.close();
    }
}
