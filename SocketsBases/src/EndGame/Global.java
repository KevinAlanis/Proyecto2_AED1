package EndGame;

import Estructuras.ListaEnlazada;
import ServerCliente.Servidor;

public class Global extends Servidor {
    private ListaEnlazada global;

    public Global(ListaEnlazada global) {
        this.global = global;
    }

    public Global() {
       this(null);
    }

    public ListaEnlazada getGlobal() {
        return global;
    }

    public void setGlobal(ListaEnlazada global) {
        this.global = global;
    }
}
