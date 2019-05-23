package Ejecucion;

/*
* Clase main, es un Facade
* @author Isaac Araya
* @see (ServerCliente.Cliente)
* */
import ServerCliente.Cliente;


import java.io.IOException;
/*
* Inicializa la interfaz de usuario y luego al cliente
* @throws Main_lanza una excepción IOException si no encuentra la clase Cliente
* */
public class Main {
    public static void main(String[] args){
        GUI BaseDD = new GUI();
        BaseDD.display();
        try {
            Cliente.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
