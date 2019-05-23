package ServerCliente;

/*
 *Es la clase cliente, es el mediador entre la interfaz y el server. Se comunica con el server por medio de un Json que lleva la información
 *  através de los sockets. Recibe un Json del server y lo transforma en una lista para poder mostrar los resultados en la interfaz
 * @author Isaac Araya
 * @author Adrián López
 * @author Kevin Alanis
 */

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Cliente implements Serializable {

    /*
     * Definición e inicialización de variables y atributos
     * */
    private static ServerSocket servidor;
    private static String ip = "192.168.100.27";
    private static boolean ciclo=true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static Socket cliente;
    public static LinkedList ListaEsquemasFinal = new LinkedList();
    public static LinkedList ListaColumnas = new LinkedList();
    public static LinkedList ListaFilasTMP = new LinkedList();
    public static String buscaresultado = "";
    public static String time = "";

    public Cliente() {
    }

    /*
     * Inicializa el socket para establecer la conexión con el server
     * @throws IOException_lanza una excepción IOException si no encuentra la clase
     * */
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        System.out.println("Esperando Servidor...");
        cliente = new Socket(InetAddress.getByName(ip),6043);
        System.out.println("Conectado a: "+cliente.getInetAddress().getHostName());

        while(ciclo){
            entrada = new ObjectInputStream(cliente.getInputStream());
            mensaje = (String) entrada.readObject();

            JsonParser parser=new JsonParser();
            JsonObject modo =(JsonObject) parser.parse(mensaje);

            /*
             *Crea una lista que contiene la columna y el tipo de dato de la columna y lo envía a la interfaz
             */
            if (modo.keySet().contains("tipos")){
                JsonObject Aux1= new JsonObject();
                JsonObject Aux2= new JsonObject();
                Aux1= (JsonObject) parser.parse(modo.get("tipos").toString());
                Set<String> keys = Aux1.keySet();
                List<String> list = new ArrayList<String>(keys);
                ListaEsquemasFinal = new LinkedList();

                for(int i = 0; i<list.size(); i++){
                    LinkedList esquemas = new LinkedList();
                    Aux2= (JsonObject) Aux1.get(list.get(i));
                    Set<String> keys2 = Aux2.keySet();
                    List<String> list2 = new ArrayList<String>(keys2);
                    esquemas.add(list.get(i));

                    for(int j = 0; j<Aux2.size(); j++){
                        String tipodato= String.valueOf(Aux2.get(list2.get(j)));
                        while (tipodato.contains("\"")||tipodato.contains("\\")) {
                            tipodato=tipodato.replace("\"","");
                            tipodato=tipodato.replace("\\","");
                        }
                        esquemas.add(list2.get(j)+":"+tipodato);
                    }
                    ListaEsquemasFinal.add(esquemas);
                }

                for(int a = 0; a< ListaEsquemasFinal.size(); a++){
                    LinkedList tmp = (LinkedList) ListaEsquemasFinal.get(a);
                }
            }
            /*
             * Crea las listas que contienen la fila y columna lo envía a la interfaz para que muestre la tabla
             */
            if (modo.keySet().contains("LeerTabla")){
                JsonObject extra1= new JsonObject();
                JsonObject extra2= new JsonObject();
                extra1= (JsonObject) parser.parse(modo.get("LeerTabla").toString());
                Set<String> keys = extra1.keySet();
                List<String> list = new ArrayList<String>(keys);

                JsonObject tipos=new JsonObject();
                JsonObject datos=new JsonObject();

                ListaFilasTMP = new LinkedList();
                ListaColumnas = new LinkedList();

                tipos= (JsonObject) extra1.get(list.get(0));
                Set<String> keys1 = tipos.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                datos= (JsonObject) extra1.get(list.get(1));
                Set<String> keys2 = datos.keySet();
                List<String> list2 = new ArrayList<String>(keys2);
                JsonObject aux2= new JsonObject();
                String nombreEsquema=list2.get(0);
                aux2= (JsonObject) datos.get(nombreEsquema);
                Set<String> keys3 = aux2.keySet();
                List<String> list3 = new ArrayList<String>(keys3);
                JsonObject aux = new JsonObject();
                LinkedList tipoColumnas = new LinkedList();


                for(int k= 0; k<list3.size(); k++){
                    LinkedList tmp = new LinkedList();
                    aux= (JsonObject) aux2.get(list3.get(k));
                    tmp.add(list3.get(k));
                    Set<String> keys4 = aux.keySet();
                    List<String> list4 = new ArrayList<String>(keys4);

                    for(int i= 0; i<aux.size(); i++){
                        tmp.add(list4.get(i));
                    }
                    ListaFilasTMP.add(toStringArray(tmp));
                }

                for(int i= 0; i<list1.size(); i++){
                    ListaColumnas.add(list1.get(i));
                }
            }
            /*
             *Crea una lista que contiene el resultado de la búsqueda y lo envía a la interfaz
             */
            if (modo.keySet().contains("busqueda")){
                JsonObject aux1=new JsonObject();
                aux1= (JsonObject) modo.get("busqueda");
                Set<String> keys1 = aux1.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                time=list1.get(0);
                JsonObject aux2=new JsonObject();
                aux2= (JsonObject) aux1.get(time);
                Set<String> keys2 = aux2.keySet();
                List<String> list2 = new ArrayList<String>(keys2);
                if(list2.get(0).equals("No hay elementos")){
                    buscaresultado = "No hay coincidencias de busqueda";
                }

                else {
                    for(int j=0;j<list2.size();j++){
                        buscaresultado= buscaresultado+", "+list2.get(j) +" ("+aux2.get(list2.get(j))+") ";
                        buscaresultado=eliminarComillas(buscaresultado);
                        buscaresultado= eliminarSlash(buscaresultado);
                    }
                }
            }
        }
    }

    /*
     * El método elimina las comillas que tienen algunos strings para poder usarlo y compararlo correctamente
     * @author Kevin Alanis
     * @param dato_es un dato generico string
     */
    public static String eliminarComillas(String dato){
        while (dato.contains("\"")) {
            dato = dato.replace("\"", "");
        }
        return dato;
    }

    /*
     * El método elimina los backslash que tienen algunos strings para poder usarlo y compararlo correctamente
     * @author Kevin Alanis
     * @param dato_es un dato generico string
     */
    public static String eliminarSlash(String dato){
        while (dato.contains("\\")) {
            dato = dato.replace("\\", "");
        }
        return dato;
    }

    /*
     * El método envía el objeto al server
     * @author Kevin Alanis
     * @author Isaac Araya
     * @author Adrián López
     * @param jsonObject_es un objeto generico
     * @throw IOException_si no logra enviar el jsonobject
     */
    public static void enviarjson1(Object jsonObject) throws IOException {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.writeObject(jsonObject.toString());
        salida.flush();
    }

    /*
     * El método envía el objeto al server
     * @author Adrián López
     * @param mensaje_es una versión string del jsonObject generico
     * @throw IOException_si no logra enviar el jsonobject
     */
    public static void enviarDatos(String mensaje) throws IOException {

        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.writeObject(mensaje);
        salida.flush();
    }

    /*
     * El método convierte la lista enlazada en un array
     * @author Isaac Araya
     * @param L1_es una lista enlazada generica
     */
    public static String[] toStringArray(LinkedList L1){
        String[] result = new String[L1.size()];
        for(int i = 0; i < L1.size(); i++){
            result[i] = String.valueOf(L1.get(i));
        }
        return result;
    }
}