package Execution;


import ServerCliente.Cliente;
import ServerCliente.Servidor;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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
