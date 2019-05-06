package ServerCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
//import Json.CreateJson;

public class Cliente {


    private static ServerSocket servidor;
    private static String ip = "192.168.100.14";
    private static boolean ciclo=true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static String mensajeenviar;
    private static Socket cliente;


    public static void mainClient() throws IOException, ClassNotFoundException {
        System.out.println("Esperando Servidor...");
        cliente = new Socket(InetAddress.getByName(ip),4999);
        System.out.println("Conectado a: "+cliente.getInetAddress().getHostName());


        while(ciclo){
            entrada = new ObjectInputStream(cliente.getInputStream());
            mensaje = (String) entrada.readObject();
            System.out.println("Servidor >>" +mensaje);

            salida = new ObjectOutputStream(cliente.getOutputStream());
            mensajeenviar = JOptionPane.showInputDialog("Cliente: Ingrese mensaje");
            salida.writeObject(mensajeenviar);
            System.out.println("Cliente >>" + mensajeenviar);
            salida.flush();
            //CreateJson cr = new CreateJson();
            //cr.getJSonValue();

            /*Frame f=new Frame("Button");
            Button b=new Button("Enviar json");
            b.setBounds(0,0,200,200);
            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.out.println("Enviar mensaje");
                    enviarDatos();
                }
            });
            f.add(b);
            f.setSize(200,200);
            f.setLayout(null);
            f.setVisible(true);

        }


        private void enviarDatos(){
            lista=Controller1.listaPalabras();
            CreateJson.crearjson(lista);
            try{
                salida = new ObjectOutputStream(cliente.getOutputStream());
                salida.writeObject(lista.toString());
                salida.flush();
                main.mostrarMensaje("Enviar Json al servidor");
            }catch (IOException ioException){
                main.mostrarMensaje("Error escribiendo Mensaje ");
            }
        }*/





        }



    }

}
