package Estructuras;


public class ListaEnlazada<T extends Comparable> {

    private NodeLista first,tail;

    public ListaEnlazada(){
        first=tail=null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addToTail(T data) {
        if (!isEmpty()) {
            tail.next = new NodeLista(data);
            tail = tail.next;
        }
        else first = tail = new NodeLista(data);
    }

    public void printAll() {
        for (NodeLista tmp = first;
             tmp != null;
             tmp = tmp.next)
            System.out.print(tmp.data + " ");
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
        l.addToTail("aDA");
        l.addToTail("aDA1");
        l.addToTail("aDA2");
        l.addToTail("aDA3");
        l.printAll();
        l.delete("aDA2");
        System.out.println(" ");
        l.printAll();
        System.out.println(" ");
        System.out.println("Encontrado "+l.find("aDA1").toString());
    }

    public T find(T value) {
        NodeLista tmp = first;
        for ( ; tmp != null && !value.equals(tmp.data);
              tmp = tmp.next);
        if (tmp == null) {
            return null;
        }
        else {
            return (T) tmp.data;
        }
    }


}



class NodeLista<T extends Comparable> {



    public T data;

    public NodeLista next;



    public NodeLista(T data) {
        this(data,null);
    }

    public NodeLista(T data, NodeLista n) {
        this.next=n;
        this.data = data;

    }

}


