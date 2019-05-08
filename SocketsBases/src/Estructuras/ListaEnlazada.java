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
            System.out.print(" "+tmp.nombreNodo + " " + tmp.data+ " ");
    }

    public void delete(T el) {
        if (!isEmpty())
            if (first == tail && el == first.data)
                first = tail = null;
            else if (el == first.data)
                first = first.next;
            else {
                NodeLista pred, tmp;
                for (pred = first, tmp = first.next;
                     tmp != null && tmp.data != el;
                     pred = pred.next, tmp = tmp.next);
                if (tmp != null) {
                    pred.next = tmp.next; if (tmp == tail)
                        tail = pred;
                }


            }}


    public static void main(String[] args){
        ListaEnlazada l =new ListaEnlazada();
        l.addToTail("Mate,324",1);
        l.addToTail("Fisica,633",2);
        l.addToTail("aDA2",3);
        l.addToTail("aDA3",4);
        l.printAll();
        l.delete("aDA2");
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


