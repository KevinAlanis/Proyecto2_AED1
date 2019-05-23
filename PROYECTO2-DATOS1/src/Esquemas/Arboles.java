package Esquemas;
/*
*Es una clase que crea un objeto arbol para poder acceder a los datos por medio de set's y get's
* @author Adrián López
*/
public class Arboles {
    public Object getArbol() {
        return arbol;
    }

    /*Método para asignar un valor
    * @param arbol_Objeto generico arbol
    */
    public void setArbol(Object arbol) {
        this.arbol = arbol;
    }

    public String getIndice() {
        return indice;
    }
    /*Método para asignar un valor
    * @param indice_es un valor que identifica la forma de busqueda del árbol
    */

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getTipo() {
        return tipo;
    }

    /*Método para asignar un valor
    * @param tipo_es un valor que dice que tipo de árbol se recibe
    */
    public void setTipo(String tipo) {

        this.tipo = tipo;
    }

    /*
    *Método que asigna los valores recibidos por parámetro a las variables locales
    * @param arbol
    * @param indice
    * @param tipo
    */
    public Arboles(Object arbol, String indice, String tipo) {
        this.arbol = arbol;
        this.indice = indice;
        this.tipo = tipo;
    }

    public Arboles() {
        this(null,null,null);
    }

    Object arbol;
    String indice;
    String tipo;

}
