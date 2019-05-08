package EndGame;

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

    public void setDatos(ListaEnlazada datos) {
        this.datos = datos;
    }

    public ListaEnlazada getAtributos() {
        return atributos;
    }

    public void setAtributos(ListaEnlazada atributos) {
        this.atributos = atributos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
