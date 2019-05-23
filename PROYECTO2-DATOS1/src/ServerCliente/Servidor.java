package ServerCliente;

import Esquemas.Arboles;
import Esquemas.DatosEsquema;
import Esquemas.Esquema;
import Estructuras.*;
import com.google.gson.*;
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/*
 *Es la clase servidor, es el encargado de controlar y procesar la logica
 * @author Isaac Araya
 * @author Adrián López
 */

public class Servidor  <T> implements Serializable {
    /*
     * Declaración de atributos
     */
    private static ServerSocket servidor;
    private static String ip = "172.18.215.157";
    private static boolean ciclo = true;
    private static ObjectInputStream entrada;
    private static String mensaje;
    private static ObjectOutputStream salida;
    private static Socket conexion;
    private static ListaEnlazada globallista =new ListaEnlazada();
    private static ListaEnlazada globallistaarboles =new ListaEnlazada();



    /*
     * Inicializa el servidor
     * @throws Main_lanza una excepción IOException si existen problemas en la conexión
     * */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        servidor = new ServerSocket(6043);
        System.out.println("Esperando Cliente...");
        conexion = servidor.accept();

        while (ciclo) {
            entrada = new ObjectInputStream(conexion.getInputStream());
            mensaje = (String) entrada.readObject();

            JsonParser parser=new JsonParser();
            JsonObject tipo =(JsonObject) parser.parse(mensaje);
            JsonObject esquemajson =new JsonObject();
            JsonObject esquemadatos= new JsonObject();
            String nombreEsquema;


            /*
             * Crea un esquema
             * Se recibe un json objecto del servidor y si entra aqui, añade un nodo a la lista global con sus respectivo nombre y tipos,
             */
            if (tipo.keySet().contains("modo1")){
                Esquema esquema=new Esquema();
                ListaEnlazada listaEnlazada=new ListaEnlazada();
                ListaEnlazada patito= new ListaEnlazada();
                /*
                 * Sacar el modo
                 */
                esquemajson= (JsonObject) parser.parse(tipo.get("modo1").toString());
                nombreEsquema = nombreEsquem(esquemajson);

                /*
                 *Obtener nombre del esquema y los valores
                 *  */

                esquemadatos= (JsonObject) esquemajson.get(nombreEsquema);

                /*
                 *Extraer del json
                 */
                esquema.setNombre(nombreEsquema);
                Set<String> keys1 = esquemadatos.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                /*
                 *Obtener llaves y cantidad
                 */
                for (int i = 0; i <list1.size(); i++) {
                    listaEnlazada.addToTail(esquemadatos.get(list1.get(i)),list1.get(i));
                }
                esquema.setDatos(listaEnlazada);
                esquema.setAtributos(patito);
                esquema.getDatos().printAll();

                globallista.addToTail(esquema,esquema.getNombre());
                globallista.printAll();
                globallista.find("persona");
                nombreColumnas();
            }


            /*
             * Añadir datos a un esquema
             * Se recibe un json objecto del servidor y si este posee como primer parámetro modo 2, añade un nodo a un esquema dado los valores digitados por el usuario a la lista enlzada atributos con su respectivo tipo
             */
            if (tipo.keySet().contains("modo2")){
                JsonObject nuevo= new JsonObject();
                JsonObject extraernombre =new JsonObject();

                ListaEnlazada atributos = new ListaEnlazada();
                esquemajson= (JsonObject) parser.parse(tipo.get("modo2").toString());
                nombreEsquema=nombreEsquem(esquemajson);
                extraernombre= (JsonObject) esquemajson.get(nombreEsquema);
                Set<String> keys1 = extraernombre.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                ListaEnlazada esquemaatributos= new ListaEnlazada();
                globallista.printAll();
                Esquema valores = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada valores1 = valores.getDatos();
                valores1.printAll();

                /*
                 *for va almacenando los keys
                 */
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                DatosEsquema  datosEsquema= new DatosEsquema();
                ListaEnlazada l1=esquemaactual.getAtributos();

                for (int i = 0; i <list1.size(); i++) {
                    nuevo= (JsonObject) extraernombre.get(list1.get(i));
                    Set<String> keys2 = nuevo.keySet();
                    List<String> list2 = new ArrayList<String>(keys2);
                    atributos = new ListaEnlazada();
                    for (int j = 0; j <list2.size(); j++) {
                        String tipodedato = String.valueOf(valores1.find(list2.get(j)));
                        atributos.addToTail(tipodedato,nuevo.get(list2.get(j)));
                    }

                    datosEsquema.setListaEnlazada(atributos);
                    datosEsquema.setKey(list1.get(i));
                    String tipodedato = (String) valores1.find(list1.get(i));
                    datosEsquema.setTipo(tipodedato);
                    atributos.printAll();
                    esquemaatributos.addToTail(datosEsquema,list1.get(i));
                    esquemaatributos.printAll();
                    l1.addToTail(datosEsquema,list1.get(i));
                }
                esquemaatributos.printAll();
                Esquema esquemaactual1 = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada go =new ListaEnlazada();
                go = esquemaactual1.getAtributos();
                go.printAll();
            }

            /*
             *Modificar lista enlazada
             * Si el json presenta modo3 modifica los datos para mandarlos al cliente si existe join une los nodos
             */

            if (tipo.keySet().contains("modo3")){
                nombreEsquema= String.valueOf(tipo.get("modo3"));
                nombreEsquema=eliminarComillas(nombreEsquema);
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada filas = esquemaactual.getAtributos();
                ListaEnlazada filas2 = esquemaactual.getDatos();
                int tamanio=filas.size();
                int atributossize = 100000;
                Esquema esquemanuevo = null;
                ListaEnlazada nuevostipos=null;
                ListaEnlazada laux2=esquemaactual.getDatos();
                /*
                 *Recorre los datos
                 */

                for (int i=0;i<tamanio;i++){
                    nuevostipos=new ListaEnlazada();
                    String keymaster = (String) filas.recorrernombre(i);
                    DatosEsquema dt= (DatosEsquema) filas.find(keymaster);
                    ListaEnlazada atributos21=dt.getListaEnlazada();
                    ListaEnlazada newatributos=new ListaEnlazada();
                    atributos21.printAll();
                    atributossize=atributos21.size();

                    /*
                     *Recorre los atributos
                     */

                    for (int z=0;z<atributossize;z++){
                        String at= String.valueOf(atributos21.recorrernombre(z));
                        String tipos= String.valueOf(atributos21.find(atributos21.recorrernombre(z)));
                        tipos=eliminarComillas(tipos);
                        at=eliminarComillas(at);


                        /*
                         *Si no hay join
                         */
                        if (tipos.equals("Integer")||tipos.equals("String")||tipos.equals("Float")||tipos.equals("Long")||tipos.equals("Double")){
                            newatributos.addToTail(tipos,at);
                        }
                        /*
                         *Si hay join
                         */
                        else {
                            esquemanuevo = (Esquema) globallista.find(tipos);
                            ListaEnlazada filajoin=esquemanuevo.getAtributos();
                            filajoin.printAll();
                            DatosEsquema dtjoin= (DatosEsquema) filajoin.find(at);
                            ListaEnlazada aux=dtjoin.getListaEnlazada();
                            aux.printAll();
                            String TIPODELKEY = String.valueOf(esquemanuevo.getDatos().find(esquemanuevo.getDatos().recorrernombre(0)));
                            TIPODELKEY=eliminarComillas(TIPODELKEY);
                            newatributos.addToTail(TIPODELKEY,dtjoin.getKey());

                            for(int r=0;r<aux.size();r++){
                                String datajoin= String.valueOf(aux.recorrernombre(r));
                                String tipojoin= String.valueOf(aux.find(aux.recorrernombre(r)));
                                datajoin=eliminarComillas(datajoin);
                                tipojoin=eliminarComillas(tipojoin);
                                newatributos.addToTail(tipojoin,datajoin);
                            }
                            ListaEnlazada laux=esquemanuevo.getDatos();

                            for (int d=0;d<laux2.size();d++){

                                if(d==z+1){

                                    for (int r=0;r<laux.size();r++){
                                        nuevostipos.addToTail(laux.find(laux.recorrernombre(r)),laux.recorrernombre(r));}
                                }
                                else {
                                    nuevostipos.addToTail(laux2.find(laux2.recorrernombre(d)),laux2.recorrernombre(d));
                                }
                            }

                            /*
                             *Genera unos nuevos atributos
                             */
                            esquemaactual.setDatos(nuevostipos);}
                        newatributos.printAll();
                        dt.setListaEnlazada(newatributos);
                        dt.getListaEnlazada().printAll();
                    }
                }
                esquemaactual.getDatos().printAll();
                JsonObject unirvalores = new JsonObject();
                JsonObject nombreesquema= new JsonObject();
                JsonObject mode= new JsonObject();
                JsonObject tmp1= new JsonObject();
                JsonObject tmp2= new JsonObject();
                Esquema esquemafinal = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada listaatributos=esquemafinal.getAtributos();

                /*
                 *Enviar datos por tabla al cliente
                 */

                for(int contador=0; contador<listaatributos.size();contador++){
                    DatosEsquema datosEsquema= (DatosEsquema) listaatributos.find(listaatributos.recorrernombre(contador));
                    JsonObject valores = new JsonObject();

                    for(int contador2=0; contador2<datosEsquema.getListaEnlazada().size();contador2++){
                        String valor = String.valueOf(datosEsquema.getListaEnlazada().recorrernombre(contador2));
                        valores.addProperty(valor, String.valueOf(datosEsquema.getListaEnlazada().find(datosEsquema.getListaEnlazada().recorrernombre(contador2))));
                    }
                    unirvalores.add(datosEsquema.getKey(),valores);
                }
                nombreesquema.add(nombreEsquema,unirvalores);

                for(int k = 0; k<esquemaactual.getDatos().size();k++){
                    tmp1.addProperty(String.valueOf(esquemaactual.getDatos().recorrernombre(k)), "");
                }
                mode.add("NombreColumna", tmp1);
                mode.add("tabla",nombreesquema);
                tmp2.add("LeerTabla", mode);
                enviarInfo(tmp2.toString());
            }

            /*
             *Borrar una lista enlazada
             * Si el json presenta modo4 utiliza el nombre del Esquema para eliminar el nodo de la lista enlazada globallista
             */

            if (tipo.keySet().contains("modo4")){
                nombreEsquema= String.valueOf(tipo.get("modo4"));
                nombreEsquema=eliminarComillas(nombreEsquema);
                int posDato=encuentraDato(globallista, nombreEsquema);
                globallista.delete(globallista.recorrernombre(posDato));
                globallista.printAll();
                nombreColumnas();
            }

            /*
             *Borrar en un esquema
             * Si el json presenta modo5 utiliza el nombre del Esquema y una key para eliminar una key de  la lista enlazada atributos
             */
            if (tipo.keySet().contains("modo5")){
                String key;
                esquemajson= (JsonObject) parser.parse(tipo.get("modo5").toString());
                nombreEsquema = nombreEsquem(esquemajson);
                key= String.valueOf(esquemajson.get(nombreEsquema));
                key=eliminarComillas(key);
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada li=esquemaactual.getAtributos();
                int posDato=encuentraDato(li, key);
                li.delete(li.recorrernombre(posDato));
                li.printAll();
                Esquema esquemaactual1 = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada listads=esquemaactual1.getAtributos();
                listads.printAll();
            }

            /*
             *Instanciar los arboles
             * Si el json presenta modo6 utiliza el indice que se le pasa en el json para crear un arbol del tipo seleccionado
             */
            if (tipo.keySet().contains("modo6")){
                esquemajson= (JsonObject) parser.parse(tipo.get("modo6").toString());
                nombreEsquema = nombreEsquem(esquemajson);
                JsonObject aux1= (JsonObject) esquemajson.get(nombreEsquema);
                Set<String> keys1 =aux1.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                String indice = list1.get(0);
                String tipoArbol= String.valueOf(aux1.get(indice));
                tipoArbol=eliminarComillas(tipoArbol);
                Arboles arboles=new Arboles();
                arboles.setIndice(indice);
                arboles.setTipo(tipoArbol);

                /*
                 *Switch-case con los tipos de arboles
                 */
                switch(tipoArbol){
                    case "B":
                        BTree bTree=new BTree();
                        arboles.setArbol(bTree);

                        break;

                    case "B+":
                        break;

                    case "Binario":
                        BinaryTree binaryTree=new BinaryTree();
                        arboles.setArbol(binaryTree);
                        break;

                    case "AVLTree":
                        AVLTree avlTree=new AVLTree();
                        arboles.setArbol(avlTree);
                        break;

                    case "Splay":
                        SplayTree splayTree=new SplayTree();
                        arboles.setArbol(splayTree);
                        break;

                    case "AA":
                        AATree aaTree=new AATree();
                        arboles.setArbol(aaTree);
                        break;

                    case "Rojo y Negro":
                        RedBlackTree redBlackTree=new RedBlackTree();
                        arboles.setArbol(redBlackTree);
                        break;
                }

                /*
                 *Lista globallistaarboles, añade el arbol creado a esta lista
                 */
                globallistaarboles.addToTail(arboles,indice);
            }


            /*
             * Busacar en un indice
             * Si el json posee modo 7, entra y utiliza los parametros indice y valorabuscar para buscar en el arbol el valor
             */

            if (tipo.keySet().contains("modo7")) {
                esquemajson = (JsonObject) parser.parse(tipo.get("modo7").toString());
                nombreEsquema = nombreEsquem(esquemajson);
                JsonObject aux1 = (JsonObject) esquemajson.get(nombreEsquema);
                Set<String> keys1 = aux1.keySet();
                List<String> list1 = new ArrayList<String>(keys1);
                String indice = list1.get(0);
                String valorabuscar= String.valueOf(aux1.get(indice));
                valorabuscar=eliminarComillas(valorabuscar);
                ListaEnlazada obtenido=null;
                long time=0;
                JsonObject auxiliar1=new JsonObject();
                JsonObject auxiliar2=new JsonObject();
                JsonObject auxiliar3=new JsonObject();
                Esquema esquemaactual = (Esquema) globallista.find(nombreEsquema);
                ListaEnlazada li=esquemaactual.getAtributos();

                /*
                 * Si la búsqueda seleccionada es lineal
                 * */
                if (indice.equals("Lineal")){
                    time=System.nanoTime();
                    Esquema esquemabuscar= (Esquema) globallista.find(nombreEsquema);
                    DatosEsquema datosEsquema= (DatosEsquema) esquemabuscar.getAtributos().find(valorabuscar);
                    /*
                     * Busqueda lineal en el esquema
                     * */
                    if(datosEsquema==null) {}
                    else {
                        obtenido = datosEsquema.getListaEnlazada();}

                    time=System.nanoTime()-time;
                }

                /*
                 * Si no es lineal
                 * */
                else{
                    Arboles arbolbusqueda= (Arboles) globallistaarboles.find(indice);
                    String tipoarbol =arbolbusqueda.getTipo();
                    Object arbol=arbolbusqueda.getArbol();
                    try {
                        /*
                         * Dependiendo del tipo del arbol añade los valores al árbol y realiza la búsqueda, además devuelve el tiempo
                         */
                        switch (tipoarbol) {
                            case "B":
                                BTree B = (BTree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    B.insertar(dt);
                                }
                                time = System.nanoTime();
                                obtenido = B.buscar(valorabuscar);
                                time = System.nanoTime() - time;
                                break;

                            case "B+":
                                break;

                            case "Binario":
                                BinaryTree Binary = (BinaryTree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    Binary.insert(dt);
                                }
                                time = System.nanoTime();
                                obtenido = Binary.contains(valorabuscar);
                                time = System.nanoTime() - time;
                                break;

                            case "AVLTree":
                                AVLTree avl = (AVLTree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    avl.insertar(dt);
                                }
                                time = System.nanoTime();
                                obtenido = avl.buscar(valorabuscar);
                                time = System.nanoTime() - time;
                                break;

                            case "Splay":
                                SplayTree splay = (SplayTree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    splay.insertar(dt);
                                }
                                time = System.nanoTime();
                                obtenido = splay.buscar(valorabuscar);
                                time = System.nanoTime() - time;
                                break;

                            case "AA":
                                AATree aa = (AATree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    aa.insertar(dt);
                                }
                                time = System.nanoTime();
                                obtenido = aa.buscar(valorabuscar);
                                time = System.nanoTime() - time;
                                break;

                            case "Rojo y Negro":
                                RedBlackTree rb = (RedBlackTree) arbol;
                                for(int i=0; i<li.size();i++){
                                    DatosEsquema dt= (DatosEsquema) li.find(li.recorrernombre(i));
                                    rb.insertar(dt);
                                }
                                time = System.nanoTime();
                                obtenido = rb.buscar(valorabuscar);
                                time = System.nanoTime() - time;
                                break;
                        }
                    }
                    catch (Exception d){
                        JOptionPane.showInputDialog(null,"No encontrado");
                    }
                }

                /*
                 *Genera un json para el cliente que devuelve el resultado de la búsqueda
                 * */
                if (obtenido!=null) {
                    obtenido.printAll();
                    for(int i=0;i<obtenido.size();i++){
                        auxiliar1.addProperty(String.valueOf(obtenido.recorrernombre(i)), String.valueOf(obtenido.find(obtenido.recorrernombre(i))));
                    }
                }
                else {
                    auxiliar1.addProperty("No hay elementos","");
                }
                auxiliar2.add(String.valueOf(time),auxiliar1);
                auxiliar3.add("busqueda",auxiliar2);
                enviarInfo(auxiliar3.toString());
            }

            /*
             *Eliminar un indice
             * Si el json presenta modo8 busca el indice en globallistaarboles y lo elimina
             */
            if (tipo.keySet().contains("modo8")) {
                String indice= String.valueOf(tipo.get("modo8"));
                indice=eliminarComillas(indice);
                int posDato=encuentraDato(globallistaarboles, indice);
                globallistaarboles.delete(globallistaarboles.recorrernombre(posDato));
                globallistaarboles.printAll();
            }
        }
    }

    /*
     * El método envía el objeto al cliente
     * @author Adrián López
     * @param mensaje_es una versión string del jsonObject generico
     * @throw IOException_si no logra enviar el jsonobject
     */

    public static void enviarInfo(String mensaje) throws IOException {
        salida = new ObjectOutputStream(conexion.getOutputStream());
        salida.writeObject(mensaje);
        salida.flush();
    }


    /*
     * Recorre una lista y la manda al cliente mediante un json
     * @author Kevin Alanis
     */
    public static void nombreColumnas(){
        int cantidaddeesquemas=globallista.size();
        JsonObject aux2=new JsonObject();
        JsonObject aux3=new JsonObject();

        for(int i = 0; i<cantidaddeesquemas; i++){
            JsonObject aux1=new JsonObject();
            String nombresesquema= (String) globallista.recorrernombre(i);
            Esquema esquemaactual= (Esquema) globallista.find(nombresesquema);
            ListaEnlazada datoslist=esquemaactual.getDatos();

            for(int j = 0; j<datoslist.size(); j++){
                aux1.addProperty(datoslist.recorrernombre(j).toString(),datoslist.find(datoslist.recorrernombre(j)).toString());
            }
            aux2.add(esquemaactual.getNombre(),aux1);
        }
        aux3.add("tipos",aux2);
        try {
            enviarInfo(aux3.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * El método obtiene las keys de un json
     * @author Adrián López
     * @param esquemajson, json a obtener keys
     */
    public static String nombreEsquem(JsonObject esquemajson){
        String nombreEsquema;
        Set<String> keys = esquemajson.keySet();
        List<String> list = new ArrayList<String>(keys);
        nombreEsquema = list.get(0);
        return nombreEsquema;
    }


    /*
     * El método permite encontrar una llave en una lista
     * @author Adrián López
     * @param lista, lista de donde se busca
     *  @param key, key para buscar
     */
    public static int encuentraDato(ListaEnlazada lista, String key){
        int pos = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.recorrernombre(i).toString().equals(key)) {
                pos = i;
                break;
            }
        }
        return pos;

    }

    /*
     * El método le quita las comillas a un dato
     * @author Adrián López
     * @param dato, dato a quitar comillas
     */
    public static String eliminarComillas(String dato){
        while (dato.contains("\"")) {
            dato = dato.replace("\"", "");
        }
        return dato;
    }
}