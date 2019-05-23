package Estructuras;
/*
 *Es la clase Lista enlazada que es utilizada como estructura base

 * @author Adrián López

 */

public class ListaEnlazada<T> {

    private NodeLista first,tail;

    public ListaEnlazada(){
        first=tail=null;
    }


    /*
     *Metodo que verifica si la lista esta vacia
     * @author Adrián López
     */

    public boolean isEmpty() {
        return first == null;
    }

    /*
     *Metodo que añade a la cola de la lista
     * @author Adrián López
     * @param data_objeto del nodo
     * @nombreNodo_nombre del Nodo
     */

    public void addToTail(T data,T nombreNodo) {
        if (!isEmpty()) {
            tail.next = new NodeLista(data,nombreNodo);
            tail = tail.next;
        }
        else first = tail = new NodeLista(data,nombreNodo);
    }

    /*
     *Imprimer los valores de la lista
     * @author Adrián López
     */

    public void printAll() {
        for (NodeLista tmp = first;
             tmp != null;
             tmp = tmp.next)
            System.out.println(" "+tmp.nombreNodo + " " + tmp.data+ " ");
    }

    /*
     *Metodo que elimina de la lista
     * @author Adrián López
     * @param T_nombre del nodo para buscar el nodo a eliminar
     */

    public void delete(T el) {
        if (!isEmpty())
            if (first == tail && el == first.nombreNodo)
                first = tail = null;
            else if (el == first.nombreNodo)
                first = first.next;
            else {
                NodeLista pred, tmp;
                for (pred = first, tmp = first.next;
                     tmp != null && tmp.nombreNodo != el;
                     pred = pred.next, tmp = tmp.next);
                if (tmp != null) {
                    pred.next = tmp.next; if (tmp == tail)
                        tail = pred;
                }


            }}

    /*
     *Metodo que determina el tamaño de la lista
     * @author Adrián López
     */

    public int size(){
        int tamanio=0;
        for (NodeLista tmp = first; tmp != null; tmp = tmp.next)
        {
            tamanio=tamanio+1;
        }

        return tamanio;
    }

    public Object recorrernombre(int posicion){
        NodeLista tmp = first;
        for (int i=0;i<posicion;i++) {
            tmp = tmp.next;
        }
        return tmp.nombreNodo;

    }
    public NodeLista obtenernodo(int posicion){
        NodeLista tmp = first;
        for (int i=0;i<posicion;i++) {
            tmp = tmp.next;
        }
        return tmp.next;

    }
    /*
     *Main
     * @author Adrián López
     */


    public static void main(String[] args){
        ListaEnlazada l =new ListaEnlazada();
        l.addToTail("Mate,324","adf");
        l.addToTail("Fisica,633","bsfd");
        l.addToTail("aDA2","cgf");
        l.addToTail("aDA3","hgf");
        System.out.println(l.recorrernombre(0));
        l.recorrernombre(1);
        l.recorrernombre(2);
        l.recorrernombre(3);

        ListaEnlazada l2 =new ListaEnlazada();
        l2.addToTail("jfd","fgt");
        l2.addToTail("gfs","yrrt");
        l2.addToTail("ofof","ette");
        l2.addToTail("ror","fdg");
        System.out.println(l.size());
        l.printAll();
        l.delete("cgf");
        System.out.println(" ");
        l.printAll();
        System.out.println(" ");
        System.out.println("Encontrado "+l.find(2).toString());
    }

    /*
     *Metodo que busca en la lista
     * @author Adrián López
     * @param value_nombre del nodo a buscar
     * @return_retorna el dato del nodo
     */


    public T find(T value) {
        NodeLista tmp = first;
        for ( ; tmp != null && !value.equals(tmp.nombreNodo);
              tmp = tmp.next);
        if (tmp == null) {
            return null;
        }
        else {
            return (T) tmp.data;
        }
    }


}



/*
 *Clase nodo para la lista enlazada
 * @author Adrián López
 */

class NodeLista<T> {



    public T data;

    public NodeLista next;
    public T nombreNodo;


    /*
     *Constructores del nodo
     */

    public NodeLista(T data,T nombreNodo) {
        this(data,nombreNodo,null);
    }

    public NodeLista(T data,T nombreNodo, NodeLista n) {
        this.next=n;
        this.data = data;
        this.nombreNodo=nombreNodo;

    }

}


