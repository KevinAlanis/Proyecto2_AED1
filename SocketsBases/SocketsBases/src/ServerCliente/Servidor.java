package ServerCliente;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import Json.CreateJson;

public class Servidor {
    private static ServerSocket servidor;
    private static String ip = "192.168.100.14";
    private static boolean ciclo=true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static String mensajeenviar;


    public static void inicio() throws IOException, ClassNotFoundException {
        servidor = new ServerSocket(4999, 100);
        System.out.println("Esperando Cliente...");
        Socket conexion = servidor.accept();

        while(ciclo){
            salida = new ObjectOutputStream(conexion.getOutputStream());
            mensajeenviar = JOptionPane.showInputDialog("Servidor: Ingrese mensaje");
            salida.writeObject(mensajeenviar);
            System.out.println("Servidor >>" + mensajeenviar);
            salida.flush();


            entrada = new ObjectInputStream(conexion.getInputStream());
            mensaje = (String) entrada.readObject();
            System.out.println("Cliente >>" +mensaje);
            //CreateJson cr = new CreateJson();
            //cr.getJSonValue();



        }



    }


}
