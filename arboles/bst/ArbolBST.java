import java.util.*;

public class ArbolBST<T extends Comparable<T>> {
    private Node root;

    public ArbolBST() {
        root = null;
    }

    public void insertBST(T data) {
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
        return p;
    }
    
    public void removeBST(T data) {
        root = remove(data, root);
    }

    private Node remove(T data, Node p) {
        if (p != null) {
            if (data.compareTo(p.data) < 0) {
                p.left = remove(data, p.left);
            } else if (data.compareTo(p.data) > 0) {
                p.right = remove(data, p.right);
            } else {
                if (p.left == null && p.right == null) {
                    p = null;
                } else if (p.left == null) {
                    p = p.right;
                } else if (p.right == null) {
                    p = p.left;
                } else {
                    Node t = findMin(p.right);
                    p.data = t.data;
                    p.right = remove(p.data, p.right);
                }
            }
        } else {
            System.out.println("Item no está en el árbol");
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

    public void inOrderBST() {
        System.out.print("Recorrido en inorden::");
        if (root != null)
            inOrder(root);
        else
            System.out.print(" " + "Vacio");
        System.out.println();
    }

    public void preOrderBST() {
        System.out.print("Recorrido en preorden:");
        if (root != null)
            preOrder(root);
        else
            System.out.print(" " + "Vacio");
        System.out.println();
    }
    
    public void postOrderBST() {
        System.out.print("Recorrido en postorden:");
        if (root != null)
            postOrder(root);
        else
            System.out.print(" " + "Vacio");
        System.out.println();
    }
    

    private void inOrder(Node ptr) {
        if (ptr.left != null)
            inOrder(ptr.left);
        System.out.print(" " + ptr.data);
        if (ptr.right != null)
            inOrder(ptr.right);
    }

    private void preOrder(Node ptr) {
        System.out.print(" " + ptr.data);
        if (ptr.left != null)
            preOrder(ptr.left);
        if (ptr.right != null)
            preOrder(ptr.right);
    }
    
    private void postOrder(Node ptr) {
        if (ptr.left != null)
            postOrder(ptr.left);
        if (ptr.right != null)
            postOrder(ptr.right);
        System.out.print(" " + ptr.data);
    }
    

    // Inner Class: Node
    private class Node {
        private Node left;
        private T data;
        private Node right;

        public Node() {
            this(null);
        }

        public Node(T data) {
            left = null;
            this.data = data;
            right = null;
        }
    }

    public static void main(String[] args) {
        ArbolBST<Integer> arbol = new ArbolBST<>();
        arbol.insertBST(5);
        arbol.insertBST(9);
        arbol.insertBST(2);
        arbol.insertBST(8);

        arbol.removeBST(2);
        arbol.removeBST(6);
        arbol.inOrderBST();
        arbol.postOrderBST();
        arbol.preOrderBST();
    }
}
