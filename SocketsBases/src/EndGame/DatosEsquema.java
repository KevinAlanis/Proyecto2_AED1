package EndGame;

import Estructuras.ListaEnlazada;

public class DatosEsquema {
    ListaEnlazada listaEnlazada;
    String key;
    String tipo;

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

    public void setListaEnlazada(ListaEnlazada listaEnlazada) {
        this.listaEnlazada = listaEnlazada;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
