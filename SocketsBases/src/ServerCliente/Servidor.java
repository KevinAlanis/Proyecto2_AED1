package ServerCliente;

import Json.Carro;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;


public class Servidor implements Serializable {
    private static ServerSocket servidor;
    private static String ip = "192.168.2.55";
    private static boolean ciclo = true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static String mensajeenviar;


    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        servidor = new ServerSocket(6043);
        System.out.println("Esperando Cliente...");
        Socket conexion = servidor.accept();

        while (ciclo) {
            Gson g=new Gson();

            entrada = new ObjectInputStream(conexion.getInputStream());

            mensaje = (String) entrada.readObject();
            System.out.println("Mensaje"+mensaje);
            JsonParser parser=new JsonParser();
            JsonArray obj = (JsonArray) parser.parse(mensaje);
            System.out.println(obj.get(1));

            for (int i = 0; i < obj.size(); i++) {
                JsonObject jsonObject = g.fromJson(obj.get(i), JsonObject.class);




                Carro car1 = g.fromJson(obj.get(i), Carro.class);

                System.out.println(car1.marca);
            }

            for (int z = 0; z < obj.size(); z++) {
                //Otro ARRAY

                String[] parts = obj.get(z).toString().split(",");
                for (int j = 0; j < parts.length; j++) {
                    // Valores dentro del ARRAY
                    String corte=parts[j];
                    while (corte.startsWith("{")) {
                        corte=corte.substring(1);
                    }
                    while (corte.endsWith("}")||corte.endsWith("\"")) {
                        corte=corte.substring(0,corte.length()-1);
                    }
                    while (corte.contains("\"")) {
                        corte=corte.replace("\"","");
                    }
                    System.out.println("Parte"+" "+corte);
                }

            }
            }






          }

    }





