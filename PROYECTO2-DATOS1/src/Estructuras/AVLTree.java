package Estructuras;

/*
 *Es la estructura del árbol sin ser instanciado
 * @author Andrés Barquero
 * @see(Esquemas.DatosEsquema)
 */

import Esquemas.DatosEsquema;

public class AVLTree  <T extends Comparable<T>> {
    /*
     *Atributos del Arbol AVL
     */
    private Nodo raiz;

    /*
     *Constructor
     */
    public AVLTree() {
        raiz = null;
    }

    /*
     *Metodos
     */

    /*
     *Llama a otro método y le pasa el valor y el nodo a buscar
     * @author Andrés Barquero
     */
    public ListaEnlazada buscar(T valor){
        return this.buscar(valor,raiz);
    }

    /*
     *Busca el valor en los nodos
     * @param indice_ valor generico a buscar
     * @param referencia_ nodo de referencia
     */
    public ListaEnlazada buscar(T indice, Nodo referencia){
        if (referencia == null){
            return null;
        }
        else if (indice.compareTo((T) referencia.valor.getKey())>0){
            return buscar(indice, referencia.derecho);
        }else if (indice.compareTo((T) referencia.valor.getKey())<0) {
            return buscar(indice, referencia.izquierdo);
        }
        else{
            return referencia.valor.getListaEnlazada();
        }
    }

    /*
     *Indica si el árbol está equilibrado
     * @param x_ nodo de referencia
     */
    public int obtenerFactor (Nodo x){
        if (x==null){
            return -1;
        }else{
            return x.factorE;
        }
    }

    /*
     *Rota el árbol hacia la izquierda
     * @param contador_ nodo de referencia
     */
    public Nodo rotacionIzquierda(Nodo contador){
        Nodo auxiliar = contador.izquierdo;
        contador.izquierdo = auxiliar.derecho;
        auxiliar.derecho = contador;
        contador.factorE = Math.max(obtenerFactor(contador.izquierdo), obtenerFactor(contador.derecho)) + 1;
        auxiliar.factorE = Math.max(obtenerFactor(auxiliar.izquierdo), obtenerFactor(auxiliar.derecho)) + 1;
        return auxiliar;
    }

    /*
     *Rota el árbol hacia la derecho
     * @param contador_ nodo de referencia
     */
    public Nodo rotacionDerecha(Nodo contador){
        Nodo auxiliar = contador.derecho;
        contador.derecho = auxiliar.izquierdo;
        auxiliar.izquierdo = contador;
        contador.factorE = Math.max(obtenerFactor(contador.izquierdo), obtenerFactor(contador.derecho)) + 1;
        auxiliar.factorE = Math.max(obtenerFactor(auxiliar.izquierdo), obtenerFactor(auxiliar.derecho)) + 1;
        return auxiliar;
    }

    /*
     *Rotación doble del árbol hacia la izquierda
     * @param contador_ nodo de referencia
     */
    public Nodo rotacionDobleIzquierda(Nodo contador){
        Nodo temporal;
        contador.izquierdo = rotacionDerecha(contador.izquierdo);
        temporal = rotacionIzquierda(contador);
        return temporal;
    }

    /*
     *Rotación doble del árbol hacia la derecha
     * @param contador_ nodo de referencia
     */
    public Nodo rotacionDobleDerecha(Nodo contador){
        Nodo temporal;
        contador.derecho = rotacionIzquierda(contador.derecho);
        temporal = rotacionDerecha(contador);
        return temporal;
    }

    /*
     *Inserta un valor en el nodo
     * @param nuevo_ nuevo nodo a insertar
     * @param subArbol_nodo de referencia
     */
    public Nodo insertarAVL(Nodo nuevo, Nodo subArbol){
        Nodo nuevoPadre = subArbol;
        if (nuevo.valor.getKey().compareTo(subArbol.valor.getKey())<0){
            if(subArbol.izquierdo == null){
                subArbol.izquierdo = nuevo;
            }else{
                subArbol.izquierdo = insertarAVL(nuevo, subArbol.izquierdo);
                if((obtenerFactor(subArbol.izquierdo) - obtenerFactor(subArbol.derecho) == 2)){
                    if(nuevo.valor.getKey().compareTo(subArbol.izquierdo.valor.getKey()) < 0){
                        nuevoPadre = rotacionIzquierda(subArbol);
                    }else{
                        nuevoPadre = rotacionDobleIzquierda(subArbol);
                    }
                }
            }
        }else if(nuevo.valor.getKey().compareTo(subArbol.valor.getKey()) > 0){
            if (subArbol.derecho == null){
                subArbol.derecho = nuevo;
            }else{
                subArbol.derecho = insertarAVL(nuevo , subArbol.derecho);
                if((obtenerFactor(subArbol.derecho) - obtenerFactor(subArbol.izquierdo) == 2)){
                    if (nuevo.valor.getKey().compareTo(subArbol.derecho.valor.getKey()) > 0){
                        nuevoPadre = rotacionDerecha(subArbol);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subArbol);
                    }
                }
            }
        }else{
            System.out.println("Nodo Duplicado");
        }
        if((subArbol.izquierdo == null) && (subArbol.derecho != null)){
            subArbol.factorE = subArbol.derecho.factorE + 1;
        }else if((subArbol.derecho == null) && (subArbol.izquierdo != null)){
            subArbol.factorE = subArbol.izquierdo.factorE + 1;
        }else{
            subArbol.factorE = Math.max(obtenerFactor(subArbol.izquierdo),obtenerFactor(subArbol.derecho))+1;
        }
        return nuevoPadre;
    }

    /*
     *Inserta los datos
     * @param valor_lista de los datos de las filas
     */
    public Nodo insertar(DatosEsquema valor){
        Nodo nuevo = new Nodo(valor);
        if(raiz == null){
            raiz = nuevo;
        }else{
            raiz=insertarAVL(nuevo,raiz);
        }
        return nuevo;
    }

    public Nodo obtenerRaiz (){
        return raiz;
    }
}

/*
 *Es la estructura del nodo sin ser instanciado
 * @author Andrés Barquero
 */
class Nodo {
    //Atributos Nodo Generales
    DatosEsquema valor;
    int factorE;
    Nodo izquierdo;
    Nodo derecho;

    /*
     *Constructor
     */
    public Nodo(DatosEsquema valor) {
        this.valor = valor;
        this.factorE = 0;
        this.derecho = null;
        this.izquierdo = null;
    }

    /*
     *Gets & Sets
     */
    public DatosEsquema getValor() {
        return valor;
    }

    public void setValor(DatosEsquema valor) {
        this.valor = valor;
    }

    public int getFactorE() {
        return factorE;
    }

    public void setFactorE(int factorE) {
        this.factorE = factorE;
    }
}