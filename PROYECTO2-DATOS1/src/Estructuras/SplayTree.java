package Estructuras;
import Esquemas.DatosEsquema;

/*
 *Clase del Splay Tree
 * @author Andres Barquero
 */
public class SplayTree <T extends Comparable<T>>{
    private NodoSplay raiz;
    private int contador = 0;

    public SplayTree() {
        raiz = null;
    }
    /*
     *Insertar en el árbol
     * @param_indice mediante un nodo temporal va comparando las keys para así obtener la posición en la que el nodo tiene que insertarse
     */
    public void insertar (DatosEsquema indice){
        NodoSplay referencia = raiz;
        NodoSplay temporal = null;
        while(referencia != null){
            temporal = referencia;
            if (indice.getKey().compareTo(temporal.valor.getKey()) > 0) {
                referencia = referencia.derecho;
            }else{
                referencia = referencia.izquierdo;
            }
        }
        referencia = new NodoSplay();
        referencia.valor = indice;
        referencia.padre = temporal;
        if (temporal == null) {
            raiz = referencia;
        }else if(indice.getKey().compareTo(temporal.valor.getKey()) > 0){
            temporal.derecho = referencia;
        }else{
            temporal.izquierdo = referencia;
        }
        contador ++;
    }

    public NodoSplay obtenerRaiz () {
        return raiz;
    }
    /*
     *Búsqueda en el árbol
     * @param contador valor a buscar
     * @nuevo nodo para buscar que va a desplazarse según la condición de comparación
     * @author Andres Barquero
     */
    public ListaEnlazada buscar(T contador , NodoSplay nuevo){
        if (nuevo == null){
            return null;
        }else if (contador.compareTo((T) nuevo.valor.getKey()) == 0){
            return nuevo.valor.getListaEnlazada();
        }else if (contador.compareTo((T) nuevo.valor.getKey())>0){
            return buscar(contador, nuevo.derecho);
        }else{
            return buscar(contador,nuevo.izquierdo);
        }
    }
    public ListaEnlazada buscar(T valor){
        return this.buscar(valor,raiz);
    }
}

/*
 *Clase del Nodo Splay
 * @author Andres Barquero
 */

class NodoSplay {
    DatosEsquema valor;
    NodoSplay izquierdo;
    NodoSplay derecho;
    NodoSplay padre;


    /*
     * Constructor del árbol
     * @author Andres Barquero
     */
    public NodoSplay(DatosEsquema valor, NodoSplay izquierdo, NodoSplay derecho, NodoSplay padre) {
        this.valor = valor;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.padre = padre;
    }

    public NodoSplay(DatosEsquema valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }

    public NodoSplay() {
        this.valor = null;
        this.izquierdo = null;
        this.derecho = null;
        this.padre = null;
    }

    public DatosEsquema getValor() {
        return valor;
    }

    public void setValor(DatosEsquema valor) {
        this.valor = valor;
    }
}
