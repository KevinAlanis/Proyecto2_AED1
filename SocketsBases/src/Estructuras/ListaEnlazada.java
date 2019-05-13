package Estructuras;


public class ListaEnlazada<T> {

    private NodeLista first,tail;

    public ListaEnlazada(){
        first=tail=null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addToTail(T data,T nombreNodo) {
        if (!isEmpty()) {
            tail.next = new NodeLista(data,nombreNodo);
            tail = tail.next;
        }
        else first = tail = new NodeLista(data,nombreNodo);
    }

    public void printAll() {
        for (NodeLista tmp = first;
             tmp != null;
             tmp = tmp.next)
            System.out.println(" "+tmp.nombreNodo + " " + tmp.data+ " ");
    }

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
        System.out.println(l.size());
        l.printAll();
        l.delete("cgf");
        System.out.println(" ");
        l.printAll();
        System.out.println(" ");
        System.out.println("Encontrado "+l.find(2).toString());
    }

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



class NodeLista<T> {



    public T data;

    public NodeLista next;
    public T nombreNodo;



    public NodeLista(T data,T nombreNodo) {
        this(data,nombreNodo,null);
    }

    public NodeLista(T data,T nombreNodo, NodeLista n) {
        this.next=n;
        this.data = data;
        this.nombreNodo=nombreNodo;

    }

}


