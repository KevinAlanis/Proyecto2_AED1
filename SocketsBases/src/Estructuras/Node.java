package Estructuras;//Clase Nodo

public class Node<T extends Comparable<T>> {
    T element;
    Node left;
    Node right;

    public Node(T element){
        this.element = element;
        this.right = this.left = null;
    }
}

//Clase Arbol



//La mayoria de metodos van en pareja debido a que al ser recursivos se necesita
//llamar por parametros la posicion actual y no se desea exponer la raiz al que usa
//dichos metodos

//Insertar el arbol

