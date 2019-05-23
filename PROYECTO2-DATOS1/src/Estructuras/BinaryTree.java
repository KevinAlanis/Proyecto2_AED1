package Estructuras;

/*
 *Es la estructura del árbol sin ser instanciado
 * @author Adrián López
 * @author Kevin Alanis
 * @see(Esquemas.DatosEsquema)
 */

import Esquemas.DatosEsquema;

public class BinaryTree <T extends Comparable<T>>{
    private Node root = null;

    public boolean isEmpty(){
        return root == null;
    }

    public ListaEnlazada contains(T e){
        return this.contains(e, this.root);
    }
    private ListaEnlazada contains(T e, Node current){
        if (current == null){
            return null;
        }
        if (e.compareTo((T) current.element.getKey()) < 0){
            return contains(e, current.left);
        } else if (e.compareTo((T) current.element.getKey()) > 0){
            return contains(e, current.right);
        } else {
            return current.element.getListaEnlazada();
        }
    }

    public void insert(DatosEsquema e){
        this.root = this.insert(e, this.root);
    }

    public Node insert(DatosEsquema e, Node current){
        if (current == null){
            return new Node(e);
        }
        if (e.getKey().compareTo( current.element.getKey())< 0){
            current.left = insert(e,current.left);
        } else if (e.getKey().compareTo(current.element.getKey()) > 0) {
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
        Preorden2(e.left);   //recorre subarbol izquierdo
        Preorden2(e.right);     //recorre subarbol derecho
    }

    /*
     *POSORDEN
     */

    public synchronized void Postorder()
    {
        Postorder2(root);
    }

    /*
     *meotod recursivo para recorrido posorden
     */
    private void Postorder2(Node e)
    {
        if( e == null )
            return;

        Postorder2(e.left);
        Postorder2(e.right);
    }
    /*
     *EMPEZAR RECORRIDO ORDEN
     */

    public synchronized void order()
    {
        order2(root);
    }
    /*
     * método recursivo para recorrido orden
     */

    private void order2(Node e)
    {
        if(e == null)
            return;

        order2(e.left);
        order2(e.right);

    }

    /*
     *Borra un nodo
     * @author Adrián López
     * @author Kevin Alanis
     */
    public void delete(T el) {
        Node tmp, node, p = root, prev = null;
        while (p != null && p.element != el) {
            prev = p; //with element el;
            if (el.compareTo((T) p.element.getKey())> 0)
                p = p.right;
            else p = p.left;
        } node = p;
        if (p != null && p.element.getKey() == el) {
            if (node.right == null)
                node = node.left;
            else if (node.left == null)
                node = node.right;
            else {
                tmp = node.left;
                while (tmp.right != null){
                    tmp = tmp.right;   }
                tmp.right =
                        node.right;
                node = node.left;
            } if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
    }


    /*
     *Encuentra el nodo mínimo
     * @author Adrián López
     * @author Kevin Alanis
     */
    public static Node findMin(Node root) {

        while (root.left != null)

            root = root.left;

        return root;

    }
}
/*
 *Es la estructura del nodo sin ser instanciado
 * @author Adrián López
 * @author Kevin Alanis
 */
class Node {
    DatosEsquema element;
    Node left;
    Node right;

    Node(DatosEsquema element){
        this.element = element;
        this.right = this.left = null;
    }
}