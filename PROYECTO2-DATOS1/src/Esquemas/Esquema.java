package Esquemas;
/*
 *Es una clase que crea un objeto que contiene listas y a su vez, esta dentro de la lista datosEsquemalista. Para poder acceder a los datos por medio de set's y get's
 * @author Adrián López
 * @see(Estructuras.ListaEnlazada)
 */
import Estructuras.ListaEnlazada;

public class Esquema {
    ListaEnlazada datos;
    ListaEnlazada atributos;
    String nombre;

    public Esquema() {
        datos=null;
        atributos=null;
        nombre=null;
    }

    public ListaEnlazada getDatos() {
        return datos;
    }

    /*Método para asignar un valor
     * @param datos_ListaEnlazada de los datos y su respectivo tipo, ingresados por el usuario(columnas)
     */
    public void setDatos(ListaEnlazada datos) {
        this.datos = datos;
    }

    public ListaEnlazada getAtributos() {
        return atributos;
    }

    /*Método para asignar un valor
     * @param atributos_ListaEnlazada de los atributos y su respectivo tipo, ingresados por el usuario(filas)
     */
    public void setAtributos(ListaEnlazada atributos) {
        this.atributos = atributos;
    }

    public String getNombre() {
        return nombre;
    }

    /*Método para asignar un valor
     * @param tipo_es un valor que permite buscar el nombre de la lista a la que pertenece, encontrar el nodo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
