package ServerCliente;

import EndGame.Esquema;
import Estructuras.ListaEnlazada;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class Servidor implements Serializable {
    private static ServerSocket servidor;
    private static String ip = "192.168.2.55";
    private static boolean ciclo = true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static String mensajeenviar;


    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {



        ListaEnlazada global =new ListaEnlazada();




        servidor = new ServerSocket(6043);
        System.out.println("Esperando Cliente...");
        Socket conexion = servidor.accept();

        while (ciclo) {
            Gson g=new Gson();


            entrada = new ObjectInputStream(conexion.getInputStream());

            mensaje = (String) entrada.readObject();
            System.out.println("Mensaje"+mensaje);

            JsonParser parser=new JsonParser();
            JsonObject tipo =(JsonObject) parser.parse(mensaje);
            JsonObject esquemajson =new JsonObject();
            JsonObject esquemadatos= new JsonObject();
            System.out.println(tipo.keySet());
            String nombreEsquema;

            if (tipo.keySet().contains("modo1")){
                Esquema esquema=new Esquema();
                ListaEnlazada listaEnlazada=new ListaEnlazada();
                System.out.println("Me cago en 1");
                esquemajson= (JsonObject) parser.parse(tipo.get("modo1").toString());
                Set<String> keys = esquemajson.keySet();
                List<String> list = new ArrayList<String>(keys);
                nombreEsquema = list.get(0);
                System.out.println(nombreEsquema);
                esquemadatos= (JsonObject) esquemajson.get(nombreEsquema);
                System.out.println(esquemadatos.toString()+"sfds "+esquemajson.toString());
                esquema.setNombre(nombreEsquema);
                Set<String> keys1 = esquemadatos.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                System.out.println(keys1);
                System.out.println(list1.size());
                for (int i = 0; i <list1.size(); i++) {
                    listaEnlazada.addToTail(esquemadatos.get(list1.get(i)),list1.get(i));
                    System.out.println("entre"+esquemadatos.get(list1.get(i))+list1.get(i));
                }

                esquema.setDatos(listaEnlazada);

                System.out.println(esquemajson);


                System.out.println("El esquema es" + esquema.getNombre());
                esquema.getDatos().printAll();

                global.addToTail(esquema,esquema.getNombre());
            }

            if (tipo.keySet().contains("modo2")){
                System.out.println("Me cago en 2");
            }



            /*JsonParser parser=new JsonParser();
            JsonArray obj = (JsonArray) parser.parse(mensaje);
            System.out.println(obj.get(1));
            ListaEnlazada l1=new ListaEnlazada();

            for (int i = 0; i < obj.size(); i++) {
                JsonObject jsonObject = g.fromJson(obj.get(i), JsonObject.class);
                String key=jsonObject.get("placa").toString();
                jsonObject.remove("placa");
                System.out.println("Holadsasasd"+jsonObject.keySet());
                System.out.println("La marca es:"+jsonObject.get("marca"));
                l1.addToTail(jsonObject,key);
                System.out.println(jsonObject);
                System.out.println("Hola");
                l1.printAll();
                JsonObject a=new JsonObject();
                System.out.println("Hola2");
                System.out.println(l1.find(key)+"encontrado");*/




//                Carro car1 = g.fromJson(obj.get(i), Carro.class);
//
//                System.out.println(car1.marca);
            }

//            for (int z = 0; z < obj.size(); z++) {
//                //Otro ARRAY
//
//                String[] parts = obj.get(z).toString().split(",");
//                for (int j = 0; j < parts.length; j++) {
//                    // Valores dentro del ARRAY
//                    String corte=parts[j];
//                    while (corte.startsWith("{")) {
//                        corte=corte.substring(1);
//                    }
//                    while (corte.endsWith("}")||corte.endsWith("\"")) {
//                        corte=corte.substring(0,corte.length()-1);
//                    }
//                    while (corte.contains("\"")) {
//                        corte=corte.replace("\"","");
//                    }
//                    System.out.println("Parte"+" "+corte);
//                }
//
//            }
            }










          }







