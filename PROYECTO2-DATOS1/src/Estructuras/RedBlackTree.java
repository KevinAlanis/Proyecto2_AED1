package Estructuras;

import Esquemas.DatosEsquema;

/*
 *Definición de la estructura del arbol rojo y negro
 * @author Andres Barquero
 * @see (Esquemas.DatosEsquema)
 */


public class RedBlackTree <T extends Comparable<T>> {
    private NodoRN raiz;
    private NodoRN nulo;
    /*
     *Inicializa el arbol rojo y negro
     */

    public RedBlackTree() {
        nulo = new NodoRN();
        nulo.color = 0;
        nulo.izquierda = null;
        nulo.derecha = null;
        raiz = nulo;
    }
    /*
     *Realiza la busqueda de informacion en el arbol
     * @Param referencia_es el nodo desde el que se parte en la busqueda
     * @Param llave_es el valor buscado
     */
    private ListaEnlazada buscarAyuda(NodoRN referencia, T llave) {

        if (referencia==nulo || llave.compareTo((T) referencia.valor.getKey())==0) {
            return referencia.valor.getListaEnlazada();
        }

        if (llave.compareTo((T)referencia.valor.getKey()) > 0) {
            return buscarAyuda(referencia.izquierda, llave);
        }if(llave.compareTo((T) referencia.valor.getKey())<0) {
            return buscarAyuda(referencia.derecha, llave);
        }else{
            return null;
        }
    }
    /*
     *Inicializa la busqueda
     * @Param valor_es el elemento a buscar
     */
    public ListaEnlazada buscar(T valor) {
        return buscarAyuda(this.raiz, valor);
    }

    /*
     *Ingresa elementos nuevos al arbol
     *@Param valor_ es el valor al insertar en el arbol
     */

    public void insertar(DatosEsquema valor) {
        NodoRN auxiliar = new NodoRN();
        auxiliar.padre = null;
        auxiliar.valor = valor;
        auxiliar.izquierda = nulo;
        auxiliar.derecha = nulo;
        auxiliar.color = 1;

        NodoRN cola = null;
        NodoRN cabeza = this.raiz;

        while (cabeza != nulo) {
            cola = cabeza;
            if (auxiliar.valor.getKey().compareTo(cabeza.valor.getKey()) > 0) {
                cabeza = cabeza.izquierda;
            } else {
                cabeza = cabeza.derecha;
            }
        }

        // cola is parent of cabeza
        auxiliar.padre = cola;
        if (cola == null) {
            raiz = auxiliar;
        } else if (auxiliar.valor.getKey().compareTo(cola.valor.getKey()) > 0) {
            cola.izquierda = auxiliar;
        } else {
            cola.derecha = auxiliar;
        }

        // if new auxiliar is a root auxiliar, simply return
        if (auxiliar.padre == null){
            auxiliar.color = 0;
            return;
        }

        // if the grandparent is null, simply return
        if (auxiliar.padre.padre == null) {
            return;
        }

        // Fix the tree
        arreglar(auxiliar);
    }

    /*
     *Realiza el balanceo del arbol
     * @Param referencia_es el nodo de referencia ulilizado en el balanceo
     */
    private void arreglar(NodoRN referencia){
        NodoRN auxiliar;
        while (referencia.padre.color == 1) {
            if (referencia.padre == referencia.padre.padre.derecha) {
                auxiliar = referencia.padre.padre.izquierda; // uncle
                if (auxiliar.color == 1) {
                    // case 3.1
                    auxiliar.color = 0;
                    referencia.padre.color = 0;
                    referencia.padre.padre.color = 1;
                    referencia = referencia.padre.padre;
                } else {
                    if (referencia == referencia.padre.izquierda) {
                        // case 3.2.2
                        referencia = referencia.padre;
                        rotacionDerecha(referencia);
                    }
                    // case 3.2.1
                    referencia.padre.color = 0;
                    referencia.padre.padre.color = 1;
                    rotacionIzquierda(referencia.padre.padre);
                }
            } else {
                auxiliar = referencia.padre.padre.derecha; // uncle

                if (auxiliar.color == 1) {
                    // mirror case 3.1
                    auxiliar.color = 0;
                    referencia.padre.color = 0;
                    referencia.padre.padre.color = 1;
                    referencia = referencia.padre.padre;
                } else {
                    if (referencia == referencia.padre.derecha) {
                        // mirror case 3.2.2
                        referencia = referencia.padre;
                        rotacionIzquierda(referencia);
                    }
                    // mirror case 3.2.1
                    referencia.padre.color = 0;
                    referencia.padre.padre.color = 1;
                    rotacionDerecha(referencia.padre.padre);
                }
            }
            if (referencia == raiz) {
                break;
            }
        }
        raiz.color = 0;
    }
    /*
     *Rota el arbol hacia la izquierda para balancearlo
     * @Param nodo1_es el primer nodo para iniciar la rotación
     */
    public void rotacionIzquierda(NodoRN nodo1) {
        NodoRN nodo2 = nodo1.derecha;
        nodo1.derecha = nodo2.izquierda;
        if (nodo2.izquierda != nulo) {
            nodo2.izquierda.padre = nodo1;
        }
        nodo2.padre = nodo1.padre;
        if (nodo1.padre == null) {
            this.raiz = nodo2;
        } else if (nodo1 == nodo1.padre.izquierda) {
            nodo1.padre.izquierda = nodo2;
        } else {
            nodo1.padre.derecha = nodo2;
        }
        nodo2.izquierda = nodo1;
        nodo1.padre = nodo2;
    }

    /*
     *Rota el arbol hacia la derecha para balancearlo
     * @Param nodo1_es el primer nodo para iniciar la rotación
     */
    public void rotacionDerecha(NodoRN nodo1) {
        NodoRN nodo2 = nodo1.izquierda;
        nodo1.izquierda = nodo2.derecha;
        if (nodo2.derecha != nulo) {
            nodo2.derecha.padre = nodo1;
        }
        nodo2.padre = nodo1.padre;
        if (nodo1.padre == null) {
            this.raiz = nodo2;
        } else if (nodo1 == nodo1.padre.derecha) {
            nodo1.padre.derecha = nodo2;
        } else {
            nodo1.padre.izquierda = nodo2;
        }
        nodo2.derecha = nodo1;
        nodo1.padre = nodo2;
    }



    public NodoRN getraiz(){
        return this.raiz;
    }
}
/*
 *Definición de la estructura del nodo del arbol rojo y negro
 * @author Andres Barquero
 */

class NodoRN {
    DatosEsquema valor;
    NodoRN padre;
    NodoRN izquierda;
    NodoRN derecha;
    int color;
}