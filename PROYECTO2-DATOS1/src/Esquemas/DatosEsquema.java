package Esquemas;

/*
 *Es una clase que crea una lista de objetos para poder acceder a los datos por medio de set's y get's
 * @author Adrián López
 * @see(Estructuras.ListaEnlazada)
 */
import Estructuras.ListaEnlazada;

public class DatosEsquema {
    ListaEnlazada listaEnlazada;
    String key;
    String tipo;

    /*
     *Método que asigna los valores recibidos por parámetro a las variables locales
     * @param listaEnlazada
     * @param key
     * @param tipo
     */
    public DatosEsquema(ListaEnlazada listaEnlazada, String key, String tipo) {
        this.listaEnlazada = listaEnlazada;
        this.key = key;
        this.tipo = tipo;
    }

    public DatosEsquema() {
        this(null, null, null);
    }

    public ListaEnlazada getListaEnlazada() {
        return listaEnlazada;
    }

    /*Método para asignar un valor
     * @param listaEnlazada_ListaEnlazada generico
     */
    public void setListaEnlazada(ListaEnlazada listaEnlazada) {
        this.listaEnlazada = listaEnlazada;
    }

    public String getKey() {
        return key;
    }

    /*Método para asignar un valor
     * @param key_es un valor que guarda el key de la lista
     */
    public void setKey(String key) {
        this.key = key;
    }

    public String getTipo() {
        return tipo;
    }

    /*Método para asignar un valor
     * @param tipo_es un valor que dice que tipo de key se recibe
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
