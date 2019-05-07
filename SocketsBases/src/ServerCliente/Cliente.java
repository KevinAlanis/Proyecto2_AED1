package ServerCliente;

import Json.CreacionJson;
import Json.TestObjectToJson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente implements Serializable {


    private static ServerSocket servidor;
    private static String ip = "192.168.2.55";
    private static boolean ciclo=true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static String mensajeenviar;
    private static Socket cliente;


    public static void main(String [] args) throws IOException, ClassNotFoundException {
        System.out.println("Esperando Servidor...");
        cliente = new Socket(InetAddress.getByName(ip),6043);
        System.out.println("Conectado a: "+cliente.getInetAddress().getHostName());
        Frame f=new Frame("Button");
        Button b=new Button("Enviar json");
        b.setBounds(0,0,200,200);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Enviar mensaje");
                try {
                    enviarDatos();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        f.add(b);
        f.setSize(200,200);
        f.setLayout(null);
        f.setVisible(true);


        while(ciclo){
            entrada = new ObjectInputStream(cliente.getInputStream());
            mensaje = (String) entrada.readObject();
            System.out.println("Servidor >>" +mensaje);






        }



    }

    private static void enviarDatos() throws IOException {
        String jsonString=TestObjectToJson.jsonhacer();


        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.writeObject(jsonString);
        salida.flush();



    }
}
