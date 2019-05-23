package Estructuras;

import Esquemas.DatosEsquema;

/*
 *Clase del árbol B
 * @author Andres Barquero
 */


public class BTree  <T extends Comparable<T>> {
   private NodoB raiz;

   public BTree() {
      raiz = null;
   }

   /*
    *Método para insertar al árbol
    * @valor_utiliza el valor y lo inserta en un nodo
    * @author Andres Barquero
    */

   public void insertar(DatosEsquema valor) {
      insertarB(valor, raiz, null, false);
   }

   /*
    *Método para insertar al árbol
    * @valor_valor a insertar en el nodo
    * @referencia_Nodo que permite usar el compare para moverse en el árbol
    * @padre_permie identificar el padre
    * @derecho_booleno para ver si es derecho
    * @author Andres Barquero
    */

   public void insertarB(DatosEsquema valor, NodoB referencia, NodoB padre, boolean derecho) {
      if (referencia == null) {
         if (padre == null) {
            raiz = referencia = new NodoB(valor, padre);
         } else if (derecho) {
            padre.derecho = referencia = new NodoB(valor, padre);
         } else {
            padre.izquierdo = referencia = new NodoB(valor, padre);
         }
      } else if (valor.getKey().compareTo(referencia.valor.getKey()) == 0) {
         referencia.valor = valor;
      } else if (valor.getKey().compareTo(referencia.valor.getKey()) < 0) {
         insertarB(valor, referencia.derecho, referencia, true);
      } else {
         insertarB(valor, referencia.izquierdo, referencia, false);
      }
   }

   /*
    *Método obtener la raiz del árbol
    * @author Andres Barquero
    */
   public NodoB obtenerRaiz() {
      return raiz;
   }



   /*
    *Búsqueda en el árbol
    * @param_recorre el árbol con el compare hasta llegar al mismo nombre
    * @return_Retorna la lista en el nodo que encontró
    * @author Andres Barquero
    */
   public ListaEnlazada buscar(T indice, NodoB referencia) {
      if (referencia == null) {
         return null;
      } else if (indice.compareTo((T) referencia.valor.getKey()) == 0) {
         return referencia.valor.getListaEnlazada();
      } else if (indice.compareTo((T) referencia.valor.getKey()) < 0) {
         return buscar(indice, referencia.derecho);
      } else {
         return buscar(indice, referencia.izquierdo);
      }
   }

   public ListaEnlazada buscar(T indice) {
      return this.buscar(indice, raiz);
   }


   /*
    *Clase nodo del árbol B
    * @author Andres Barquero
    */
}
   class NodoB {
      DatosEsquema valor;
      int nivel;
      NodoB padre;
      NodoB derecho;
      NodoB izquierdo;

      /*
       *Contructor del árbol
       * @author Andres Barquero
       */
      public NodoB() {
         this.valor = null;
         this.nivel = 0;
         this.padre = this;
         this.derecho = this;
         this.izquierdo = this;
      }

      public NodoB(DatosEsquema valor, NodoB derecho, NodoB izquierdo) {
         this.valor = valor;
         this.derecho = derecho;
         this.izquierdo = izquierdo;
         this.nivel = 1;
      }

      public NodoB(DatosEsquema valor,NodoB padre) {
         this.valor = valor;
         this.padre = null;
         this.derecho = null;
         this.izquierdo = null;
      }

      public DatosEsquema getValor() {
         return valor;
      }

      public void setValor(DatosEsquema valor) {
         this.valor = valor;
      }
   }
