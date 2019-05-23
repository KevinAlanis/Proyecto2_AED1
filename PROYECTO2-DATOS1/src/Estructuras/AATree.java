package Estructuras;

/*
 *Es la estructura del árbol sin ser instanciado
 * @author Andrés Barquero
 * @see(Esquemas.DatosEsquema)
 */
import Esquemas.DatosEsquema;

public class AATree <T extends Comparable<T>> {
    public NodoAA raiz;
    private static NodoAA nulo = new NodoAA();

    public AATree() {
        raiz = nulo;
    }

    /*
     *Llama al método de insertar datos
     * @param indice_lista de los datos de las filas
     */
    public void insertar (DatosEsquema indice){
        raiz = insertarAA(indice, raiz);
    }

    /*
     *Inserta un valor en el nodo
     * @param Referencia_ nodo de referencia
     * @param indice_lista de los datos de las filas
     */
    public NodoAA insertarAA (DatosEsquema indice, NodoAA Referencia){
        if (Referencia == nulo) {
            Referencia = new NodoAA(indice,nulo,nulo);
        }else if (indice.getKey().compareTo(Referencia.valor.getKey()) > 0) {
            Referencia.izquierdo = insertarAA(indice,Referencia.izquierdo);
        } else if (indice.getKey().compareTo(Referencia.valor.getKey()) < 0) {
            Referencia.derecho = insertarAA(indice, Referencia.derecho);
        }else{
            return Referencia;
        }
        Referencia = torsion(Referencia);
        Referencia = division(Referencia);
        return Referencia;
    }

    /*
     *Balancea el árbol
     * @param nodoT_ nodo a acomodar
     */
    private NodoAA torsion(NodoAA nodoT){
        if (nodoT == nulo) {
            return nulo;
        } else if (nodoT.izquierdo == nulo) {
            return nodoT;
        }else if(nodoT.izquierdo.nivel == nodoT.nivel){
            NodoAA L = nodoT.izquierdo;
            nodoT.izquierdo = L.derecho;
            L.derecho = nodoT;
            return L;
        }else{
            return nodoT;
        }
    }
    /*
     *Balancea el árbol
     * @param nodoD_ nodo a acomodar
     */
    private NodoAA division (NodoAA nodoD){
        if (nodoD == nulo){
            return nulo;
        } else if (nodoD.derecho == nulo && nodoD.derecho.derecho == nulo) {
            return nodoD;
        } else if (nodoD.nivel == nodoD.derecho.derecho.nivel) {
            NodoAA R = nodoD.derecho;
            nodoD.derecho = R.izquierdo;
            R.izquierdo = nodoD;

            R.nivel = R.nivel + 1;
            return R;
        }else{
            return nodoD;
        }
    }
    /*
     *Obtiene la raíz
     */
    public NodoAA obtenerRaiz (){
        return raiz;
    }

    /*
     *Busca el valor en los nodos
     * @param valor_ valor generico a buscar
     */
    public ListaEnlazada buscarAA(T contador , NodoAA nuevo){
        if (nuevo == null) {
            return null;
        }
        while(nuevo != null){
            if (contador.compareTo((T)nuevo.valor.getKey()) > 0) {
                nuevo = nuevo.izquierdo;
            } else if (contador.compareTo((T)nuevo.valor.getKey())< 0) {
                nuevo = nuevo.derecho;
            } else{
                return nuevo.valor.getListaEnlazada();
            }
        }
        return null;
    }

    /*
     *Llama a otro método y le pasa el valor y el nodo a buscar
     * @param valor_ valor generico a buscar
     */
    public ListaEnlazada buscar(T valor){
        return this.buscarAA(valor,raiz);
    }

}

/*
 *Es la estructura del nodo sin ser instanciado
 * @author Andrés Barquero
 */
class NodoAA {
    DatosEsquema valor;
    int nivel;
    NodoAA derecho;
    NodoAA izquierdo;

    public NodoAA() {
        this.valor = null;
        this.nivel = 0;
        this.derecho = this;
        this.izquierdo = this;
    }

    /*
     *Recibe los datos que se le asignaran al nodo y los asigna a las variables locales
     * @author Andrés Barquero
     * @param valor
     * @param derecho
     * @param izquierdo
     */
    public NodoAA(DatosEsquema valor, NodoAA derecho, NodoAA izquierdo) {
        this.valor = valor;
        this.derecho = derecho;
        this.izquierdo = izquierdo;
        this.nivel = 1;
    }

    /*
     *Recibe los datos que se le asignaran al nodo y los asigna a las variables locales
     * @author Andrés Barquero
     * @param valor
     */
    public NodoAA(DatosEsquema valor) {
        this.valor = valor;
        this.derecho = null;
        this.izquierdo = null;
    }

    public DatosEsquema getValor() {
        return valor;
    }

    /*
     *Asigna valor a un nodo
     * @author Andrés Barquero
     * @param valor
     */
    public void setValor(DatosEsquema valor) {
        this.valor = valor;
    }
}