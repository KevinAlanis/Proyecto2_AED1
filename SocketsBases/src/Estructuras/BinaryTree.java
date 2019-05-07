package Estructuras;

import javax.swing.*;

public class BinaryTree<T extends Comparable<T>>{
    private Node root = null;

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T e){
        return this.contains(e, this.root);
    }
    private boolean contains(T e, Node current){
        if (current == null){
            return false;
        }
        if (e.compareTo((T) current.element) < 0){
            return contains(e, current.left);
        } else if (e.compareTo((T) current.element) > 0){
            return contains(e, current.right);
        } else {
            return true;
        }
    }

    public void insert(T e){
        this.root = this.insert(e, this.root);
    }

    public Node insert(T e, Node current){
        if (current == null){
            return new Node(e);
        }
        if (e.compareTo((T) current.element)< 0){
            current.left = insert(e,current.left);
        } else if (e.compareTo((T) current.element) > 0) {
            current.right = insert(e, current.right);
        }
        return current;
    }

    //  PREORDEN
    public synchronized void Preorder()
    {
        Preorden2(root);
    }
    //metodo recursivo preorden

    private void Preorden2(Node e)
    {
        if(e == null)
            return;

        System.out.print(e.element + " ");     //mostrar datos del nodo
        Preorden2(e.left);   //recorre subarbol izquierdo
        Preorden2(e.right);     //recorre subarbol derecho
    }

    // POSORDEN
    public synchronized void Postorder()
    {
        Postorder2(root);
    }

    //meotod recursivo para recorrido posorden
    private void Postorder2(Node e)
    {
        if( e == null )
            return;

        Postorder2(e.left);
        Postorder2(e.right);
        System.out.print(e.element + " ");
    }

    //EMPEZAR RECORRIDO ORDEN
    public synchronized void order()
    {
        order2(root);
    }

    //meoto recursivo para recorrido orden
    private void order2(Node e)
    {
        if(e == null)
            return;

        order2(e.left);
        System.out.print(e.element + " ");
        order2(e.right);

    }


    public void delete(T el) {
        Node tmp, node, p = root, prev = null;
        while (p != null && p.element != el) { // find the node p
            prev = p; //with element el;
            if (el.compareTo((T) p.element)> 0)
                p = p.right;
            else p = p.left;
        } node = p;
        if (p != null && p.element == el) {
            if (node.right == null) // node has no right child: its left
                node = node.left;  // child (if any) is attached to its // parent;
            else if (node.left == null) // node has no left child: its right
                node = node.right; // child is attached to its parent;
            else {                // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null){ // 2. and then right as far as
                    tmp = tmp.right;   }   //    possible;
                tmp.right =      // 3. establish the link between
                        node.right;  //    the rightmost node of the left //    subtree and the right subtree;
                node = node.left;  // 4.

            } if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node; // 5.
        } else if (root != null)
            System.out.println("key " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }


    public static Node findMin(Node root) {

        while (root.left != null)

            root = root.left;

        return root;

    }



    public static void main(String[] args) {
        BinaryTree bn=new BinaryTree();

        int valor;
        String Dato;

        System.out.println("Insertando los siguientes valores: ");

        Dato = JOptionPane.showInputDialog("Inserta el numero de nodos que desea ingresar");
        int n = Integer.parseInt(Dato);

        for(int i = 1; i <= n; i++ )
        {
            Dato = JOptionPane.showInputDialog("Dame el " + i + " valor para colocar en el Arbol");
            valor = Integer.parseInt(Dato);
            System.out.print(valor + " ");
            bn.insert(valor);
        }

        System.out.println("\n\nRecorrido Preorden");
        bn.Preorder();

        System.out.println("\n\nRecorrido Inorden");
        bn.order();


        System.out.println("\n\nRecorrido Postorden");
        bn.Postorder();
        if (bn.contains(3)){
            System.out.println("Hay 3");
        }
        if (bn.contains(5)){
            System.out.println("Hay 5");
        }
        bn.delete(5);
        if (bn.contains(5)){
            System.out.println("Hay 5");
        }
        else {
            System.out.println("No hay 5");
        }
        bn.order();

    }
}








