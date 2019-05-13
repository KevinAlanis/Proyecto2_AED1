package ServerCliente;

import EndGame.DatosEsquema;
import EndGame.Esquema;
import EndGame.Global;
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



        Global global=new Global();

        ListaEnlazada globallista =new ListaEnlazada();




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

            //crear esquema
            if (tipo.keySet().contains("modo1")){
                Esquema esquema=new Esquema();
                ListaEnlazada listaEnlazada=new ListaEnlazada();
                System.out.println("Me cago en 1");


                //Sacar el modo
                esquemajson= (JsonObject) parser.parse(tipo.get("modo1").toString());
                Set<String> keys = esquemajson.keySet();
                List<String> list = new ArrayList<String>(keys);
                nombreEsquema = list.get(0);
                //Obtener nombre del esquema y los valores
                System.out.println(nombreEsquema);
                esquemadatos= (JsonObject) esquemajson.get(nombreEsquema);
                // esquemadatos datos del coso
                System.out.println(esquemadatos.toString()+"sfds "+esquemajson.toString());
                esquema.setNombre(nombreEsquema);
                Set<String> keys1 = esquemadatos.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                System.out.println(keys1);
                //Obtener llaves y cantidad
                System.out.println(list1.size());
                for (int i = 0; i <list1.size(); i++) {
                    listaEnlazada.addToTail(esquemadatos.get(list1.get(i)),list1.get(i));
                    System.out.println("entre"+esquemadatos.get(list1.get(i))+list1.get(i));
                }

                esquema.setDatos(listaEnlazada);

                System.out.println(listaEnlazada.find("cedula"));

                System.out.println(esquemajson);


                System.out.println("El esquema es" + esquema.getNombre());
                esquema.getDatos().printAll();

                globallista.addToTail(esquema,esquema.getNombre());
                globallista.printAll();
                globallista.find("persona");
                global.setGlobal(globallista);

            }
// anadir datos al esquema
            if (tipo.keySet().contains("modo2")){
                JsonObject nuevo= new JsonObject();
                JsonObject extraernombre =new JsonObject();

                ListaEnlazada atributos = new ListaEnlazada();
                System.out.println("Me cago en 2");
                esquemajson= (JsonObject) parser.parse(tipo.get("modo2").toString());
                Set<String> keys = esquemajson.keySet();
                List<String> list = new ArrayList<String>(keys);
                nombreEsquema = list.get(0);
                System.out.println(nombreEsquema);

                extraernombre= (JsonObject) esquemajson.get(nombreEsquema);
                System.out.println(extraernombre);
                Set<String> keys1 = extraernombre.keySet();
                List<String> list1 = new ArrayList<String>(keys1);

                ListaEnlazada esquemaatributos= new ListaEnlazada();

                Esquema valores = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada valores1 = valores.getDatos();


                //for va almacenando los keys

                for (int i = 0; i <list1.size(); i++) {
                    nuevo= (JsonObject) extraernombre.get(list1.get(i));
                    Set<String> keys2 = nuevo.keySet();
                    List<String> list2 = new ArrayList<String>(keys2);
                    System.out.println(list2.size());
                    atributos = new ListaEnlazada();
                    DatosEsquema  datosEsquema= new DatosEsquema();
                    for (int j = 0; j <list2.size(); j++) {
                        String tipodedato = (String) valores1.find(list2.get(j));
                        atributos.addToTail(nuevo.get(list2.get(j)),tipodedato);

                    }
                    datosEsquema.setListaEnlazada(atributos);
                    datosEsquema.setKey(list1.get(i));

                    String tipodedato = (String) valores1.find(list1.get(i));
                    datosEsquema.setTipo(tipodedato);

                    atributos.printAll();
                    esquemaatributos.addToTail(datosEsquema,list1.get(i));
                    System.out.println("separacion");
                    esquemaatributos.printAll();
                    }
                    esquemaatributos.printAll();


                    Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                    System.out.println("Mi nombre es "+esquemaactual.getNombre());
                    esquemaactual.setAtributos(esquemaatributos);


                    Esquema esquemaactual1 = (Esquema) globallista.find(nombreEsquema);
                    System.out.println(esquemaactual1.getNombre());
                    ListaEnlazada go =new ListaEnlazada();
                    go = esquemaactual1.getAtributos();
                    go.printAll();
                    System.out.println(esquemaactual1.getDatos());



                }

            // modificar lista enlazada
            if (tipo.keySet().contains("modo3")){
                JsonObject valores = new JsonObject();
                JsonObject unirvalores = new JsonObject();
                JsonObject nombreesquema= new JsonObject();
                esquemajson= (JsonObject) parser.parse(tipo.get("modo3").toString());
                Set<String> keys = esquemajson.keySet();
                List<String> list = new ArrayList<String>(keys);
                nombreEsquema = list.get(0);
                System.out.println(nombreEsquema);
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada filas = esquemaactual.getAtributos();
                int tamanio=filas.size();
                List<Object> llaves = new ArrayList<>();


                for (int i=0;i<tamanio;i++){
                    String keymaster = (String) filas.recorrernombre(i);
                    DatosEsquema dt= (DatosEsquema) filas.find(keymaster);
                    ListaEnlazada atributos=dt.getListaEnlazada();
                    ListaEnlazada newatributos=new ListaEnlazada();
                    int atributossize=atributos.size();
                    for (int z=0;z<atributossize;z++){
                        String at= (String) atributos.recorrernombre(z);
                        String tipod= (String) atributos.find(at);
                        if (tipod.equals("Integer")||tipod.equals("String")||tipod.equals("Float")||tipod.equals("Long")||tipod.equals("Double")){
                            Esquema esquemanuevo = (Esquema) globallista.find(tipod);
                            ListaEnlazada filajoin=esquemanuevo.getAtributos();
                            DatosEsquema dtjoin= (DatosEsquema) filajoin.find(at);
                            ListaEnlazada aux=dtjoin.getListaEnlazada();
                            for(int r=0;r<aux.size();r++){
                                newatributos.addToTail(aux.find(aux.recorrernombre(i)),aux.recorrernombre(r));
                            }
                        }

                        dt.setListaEnlazada(newatributos);
                    }



                }










            }
            //borrar un esquema
            if (tipo.keySet().contains("modo4")){
                nombreEsquema= String.valueOf(tipo.get("modo4"));
                while (nombreEsquema.contains("\"")) {
                        nombreEsquema=nombreEsquema.replace("\"","");
                    }
                System.out.println(nombreEsquema);
                int pos=0;
                for(int i=0; i<globallista.size();i++){
                    if (globallista.recorrernombre(i).toString().equals(nombreEsquema)){
                        pos=i;
                        break;
                    }

                }

                globallista.delete(globallista.recorrernombre(pos));

                globallista.printAll();
                System.out.println("mi casa es");
            }

            //borrar datos en un esquema
            if (tipo.keySet().contains("modo5")){
                String key;

                esquemajson= (JsonObject) parser.parse(tipo.get("modo5").toString());
                Set<String> keys = esquemajson.keySet();
                List<String> list = new ArrayList<String>(keys);
                nombreEsquema = list.get(0);
                System.out.println(nombreEsquema);
                key= String.valueOf(esquemajson.get(nombreEsquema));
                while (key.contains("\"")) {
                    key=key.replace("\"","");
                }
                System.out.println(key);
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada li=esquemaactual.getAtributos();
                int pos=0;
                for(int i=0; i<li.size();i++){
                    if (li.recorrernombre(i).toString().equals(key)){
                        pos=i;
                        break;
                    }

                }
                li.delete(li.recorrernombre(pos));
                li.printAll();

                //pruebas
                Esquema esquemaactual1 = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada listads=esquemaactual1.getAtributos();
                listads.printAll();


            }










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


















