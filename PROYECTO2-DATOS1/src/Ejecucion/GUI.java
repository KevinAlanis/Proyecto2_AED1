package Ejecucion;

/*
 *Definición de los paquetes o librerias que se requieren en la creación de la interfaz
 * @author Isaac Araya
 * @see (Interfaz.*; Interfaz.Canvas; ServerCliente.Cliente; Json.TestObjectToJson#jsonhacer(); ServerCliente.Cliente#enviarDatos();ServerCliente.Cliente#enviarjson1())
 */

import Interfaz.*;
import Interfaz.Canvas;
import ServerCliente.Cliente;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;

import static Json.TestObjectToJson.jsonhacer;
import static ServerCliente.Cliente.enviarDatos;
import static ServerCliente.Cliente.enviarjson1;

/*
* Definición e inicialización de variables y atributos
=============Colores================================================
*/


public class GUI extends JFrame implements ActionListener {

    /*
    * Definición e inicialización de variables y atributos
    =============Colores================================================
    */
    Color Azul = new Color(30,60,90);
    Color Celeste = new Color(60,120,180);
    Color Gris = new Color(160,160,160);
    Color AzulOscuro = new Color(15,45,75);
    Color Blanco = new Color(240,240,240);

    Cliente cliente;
    Pantalla Window = new Pantalla(1280, 720, Blanco , "Base de Datos");
    private Canvas Canvas1 = new Canvas(0, 0, 1280, 720, Azul);

    Boton AnadirEsquema, register, closeRegister, anade, verTabla, ingresaDatos, buscar, ingreso, cancel, Busqueda, cancelBusqueda, agregaColumna;
    Boton EliminarEsquema, EliminarFilas, eliminarDato, cancelEliminar, aprobar, cancelAprobar, ingresaIndice, cancelaIndice, abrirIndices;
    Boton Ok, elimiIndice, cancelElimiIndice, eliminacionIndices;
    PantallaExtra PRegistro, PIngreso, PBusqueda, PEliminar, PEstaSeguro, PIndice, PResult, PEliminarIndice;
    JTextField esquema, column, e1, e2, e3, e4, e5, e6 ,e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, JTBuscar, JTIndice;
    JScrollPane scrollPane;
    ComboBox comboTipo, indices;
    JComboBox ComboEsquema, esquemaJoin, arboles, comboIndiceEliminar;
    JTable T1;
    String nameEsquema;
    LinkedList<String> atributos = new LinkedList<String>();
    LinkedList tiposAtributos = new LinkedList();
    JLabel Title;
    Etiqueta Register;

    JTextField[] Entrys = {e1, e2, e3, e4, e5, e6 ,e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20};

   /*
================Declaraciones de variables==========================
*/

    LinkedList ListaEsquemas = new LinkedList();

    String[] arbolesLista = {"AVLTree","B","B+","AA","Splay","Binario","Rojo y Negro" };
    LinkedList ListaIndices = new LinkedList();

    String nombreEsquema;
    String[] columnNames;
    String[] tipoColumnas;

    static LinkedList variable = new LinkedList();
    static LinkedList ListaFilasFINAL = new LinkedList();
    static LinkedList ListaColumnasFINAL = new LinkedList();



    /*
     *Métodos
     */

    /*
     * Constructor de la interfaz
     * */

    public GUI(){
    }

    /*
     * Método que inicializa la pantalla inicial
     * */

    public void display(){
        Canvas1.setLayout(null);
        Window.getContentPane().add(Canvas1);
        iniciaBotones();
        iniciaEtiquetas();
        iniciaCombo();
        Window.setVisible(true);
    }

    /*
     * Método que inicializa los botones utilizados en la pantalla inicial
     * */

    public void iniciaBotones(){

        AnadirEsquema = new Boton("Agregar nuevo esquema", 10, 140, 200, 40);
        AnadirEsquema.setFont(new Font("Times New Roman", Font.BOLD, 15));
        AnadirEsquema.setBackground(Azul);
        AnadirEsquema.setForeground(Blanco);
        AnadirEsquema.setBorderPainted(false);
        AnadirEsquema.addActionListener(this);
        Canvas1.add(AnadirEsquema);


        buscar = new Boton("Buscar", 20, 10, 200, 40);
        buscar.setFont(new Font("Times New Roman", 0, 15));
        buscar.setBackground(Gris);
        buscar.setForeground(AzulOscuro);
        buscar.setBorderPainted(true);
        buscar.addActionListener(this);
        Canvas1.add(buscar);

        verTabla = new Boton("Ver datos", 7, 190, 105, 20);
        verTabla.setFont(new Font("Times New Roman", 0, 15));
        verTabla.setBackground(Azul);
        verTabla.setForeground(Blanco);
        verTabla.setBorderPainted(false);
        verTabla.addActionListener(this);
        Canvas1.add(verTabla);

        ingresaDatos = new Boton("Ingresar datos", 7, 230, 130, 20);
        ingresaDatos.setFont(new Font("Times New Roman", 0, 15));
        ingresaDatos.setBackground(Azul);
        ingresaDatos.setForeground(Blanco);
        ingresaDatos.addActionListener(this);
        ingresaDatos.setBorderPainted(false);
        Canvas1.add(ingresaDatos);

        EliminarEsquema = new Boton("Eliminar esquema", 7, 270, 150, 20);
        EliminarEsquema.setFont(new Font("Times New Roman", 0, 15));
        EliminarEsquema.setBackground(Azul);
        EliminarEsquema.setForeground(Blanco);
        EliminarEsquema.addActionListener(this);
        EliminarEsquema.setBorderPainted(false);
        Canvas1.add(EliminarEsquema);

        EliminarFilas = new Boton("Eliminar datos", 7, 310, 130, 20);
        EliminarFilas.setFont(new Font("Times New Roman", 0, 15));
        EliminarFilas.setBackground(Azul);
        EliminarFilas.setForeground(Blanco);
        EliminarFilas.addActionListener(this);
        EliminarFilas.setBorderPainted(false);
        Canvas1.add(EliminarFilas);

        abrirIndices = new Boton("Crear un indice", 9, 350, 130, 20);
        abrirIndices.setFont(new Font("Times New Roman", 0, 15));
        abrirIndices.setBackground(Azul);
        abrirIndices.setForeground(Blanco);
        abrirIndices.addActionListener(this);
        abrirIndices.setBorderPainted(false);
        Canvas1.add(abrirIndices);

        eliminacionIndices = new Boton("Borrar un indice", 11, 390, 130, 20);
        eliminacionIndices.setFont(new Font("Times New Roman", 0, 15));
        eliminacionIndices.setBackground(Azul);
        eliminacionIndices.setForeground(Blanco);
        eliminacionIndices.addActionListener(this);
        eliminacionIndices.setBorderPainted(false);
        Canvas1.add(eliminacionIndices);
    }

    /*
     * Método que inicializa la etiqueta del título y el panel
     * en el que se coloca la tabla
     * */

    public void iniciaEtiquetas(){
        Title = new JLabel("Base de Datos");
        Title.setFont(new Font("Impact",0, 40));
        Title.setBackground(Azul);
        Title.setForeground(Blanco);
        Title.setVisible(true);
        Title.setBounds(1000,5,300,50);
        Canvas1.add(Title);

        JPanel PanelPrueba =new JPanel();
        PanelPrueba.setBounds(230,80, 1023,590);
        PanelPrueba.setBackground(Celeste);
        PanelPrueba.setVisible(true);
        Canvas1.add(PanelPrueba);
    }
    /*
     * Método que inicializa la tabla con la información que recibe
     * @param L1_ recibe los datos de la tabla, las filas
     * @param L2_ recibe los nombres de las columnas de la tabla
     * */
    public void iniciaTabla(String[][] L1, String[] L2){
        if(L1 == null){
            LinkedList tmp = new LinkedList();
            String[] aux = new String[L2.length];
            for(int i = 0; i< L2.length; i++){
                aux[i] = "";
            }
            tmp.add(aux);
            String [][] prueba = (String[][]) toStringMatrix(tmp);
            T1 = new JTable(prueba,L2);
        }
        else if(L1 != null){
            T1 = new JTable(L1,L2);
        }

        T1.setPreferredScrollableViewportSize(new Dimension(980,520));
        T1.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(T1);
        scrollPane.setBounds(240, 90, 1002,570);
        Window.add(scrollPane);
    }

    /*
     * Método que inicializa el combobox que contiene los esquemas a utilizar
     *
     * */

    public void iniciaCombo(){

        ComboEsquema = new JComboBox();
        ComboEsquema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variable= cliente.ListaEsquemasFinal;
                for(int i = 0; i<variable.size(); i++)
                    try {
                        LinkedList tmp = (LinkedList) variable.get(i);
                        if (ComboEsquema.getSelectedItem().toString().equals(tmp.get(0))) {
                            columnNames = new String[tmp.size() - 1];
                            tipoColumnas = new String[tmp.size() - 1];
                            for (int j = 0; j < columnNames.length; j++) {
                                String[] parts = tmp.get(j + 1).toString().split(":");
                                columnNames[j] = parts[0];
                                tipoColumnas[j] = parts[1];
                            }
                        }
                    } catch (Exception ColumnasVacias) {
                        columnNames = null;
                    }


            }
        });
        ComboEsquema.setVisible(true);
        ComboEsquema.setBackground(Blanco);
        ComboEsquema.setBounds(30,90,100,20);
        Canvas1.add(ComboEsquema);

    }
    /*
     * Método que procesa los eventos de la interfaz para
     * realizar las respectivas acciones
     * @Param e_ es el evento que sucede en cada momento en la interfaz
     * */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == AnadirEsquema){
            PantallaRegistro();
        }

        else if(e.getSource() == ComboEsquema){
            this.nombreEsquema = (String) ComboEsquema.getSelectedItem();
        }

        else if(e.getSource() == abrirIndices){
            PantallaIngresaIndice();
        }

        else if(e.getSource() == elimiIndice){
            JsonObject aux1 = new JsonObject();
            aux1.addProperty("modo8", comboIndiceEliminar.getSelectedItem().toString());
            try {
                enviarjson1(aux1);
            } catch (IOException e21) {
                e21.printStackTrace();
            }

            for(int i = 0; i<ListaIndices.size(); i++){
                String[] tmp = (String[]) ListaIndices.get(i);
                if(tmp[0] == comboIndiceEliminar.getSelectedItem().toString()){
                    ListaIndices.remove(tmp);
                    comboIndiceEliminar.removeItem(tmp[0]);
                }
            }

            PEliminarIndice.dispose();
        }

        else if(e.getSource() == cancelElimiIndice){
            PEliminarIndice.dispose();
        }

        else if(e.getSource() == eliminacionIndices){
            pantallaEliminarIndice();
        }

        else if(e.getSource() == abrirIndices){
            PantallaIngresaIndice();
        }

        else if(e.getSource() == ingresaIndice){
            JsonObject Aux1 = new JsonObject();
            JsonObject Aux2 = new JsonObject();
            JsonObject Aux3 = new JsonObject();
            int a = 0;

            for(int i = 0; i<ListaIndices.size(); i++){
                String[] tmp = (String[]) ListaIndices.get(i);
                if(JTIndice.getText() == tmp[0]){
                    a++;
                    break;
                }
            }


            if(a == 0){
                String[] tmp = {JTIndice.getText(), arboles.getSelectedItem().toString()};
                ListaIndices.add(tmp);
                Aux1.addProperty(JTIndice.getText(),arboles.getSelectedItem().toString());
                Aux2.add(ComboEsquema.getSelectedItem().toString(),Aux1);
                Aux3.add("modo6",Aux2);
                try {
                    enviarjson1(Aux3);
                } catch (IOException e21) {
                    e21.printStackTrace();
                }
                PIndice.dispose();
            }
            else if(a != 0){
                JOptionPane.showMessageDialog(null, "Este indice ya existe");
                PIndice.dispose();
            }


        }

        else if(e.getSource() == cancelaIndice){
            PIndice.dispose();
        }

        else if(e.getSource() == EliminarEsquema){
            pantallaSeguro();
        }

        else if(e.getSource() == cancelAprobar){
            PEstaSeguro.dispose();
        }

        else if(e.getSource() == aprobar) {
            JsonObject eliminaesquema =new  JsonObject();
            eliminaesquema.addProperty("modo4", (String) ComboEsquema.getSelectedItem());
            try {
                enviarjson1(eliminaesquema.toString());
            } catch (IOException e21) {
                e21.printStackTrace();
            }
            ListaEsquemas.remove(ComboEsquema.getSelectedItem());
            ComboEsquema.removeItem(ComboEsquema.getSelectedItem());
            PEstaSeguro.dispose();
        }

        else if(e.getSource() == anade){
            T1.addColumn(new TableColumn());
        }

        else if(e.getSource() == verTabla){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("modo3", String.valueOf(ComboEsquema.getSelectedItem()));
            try {
                enviarjson1(jsonObject);
            } catch (IOException e21) {
                e21.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e21) {
                e21.printStackTrace();
            }

            ListaColumnasFINAL = cliente.ListaColumnas;
            ListaFilasFINAL = cliente.ListaFilasTMP;

            String[][] tmp1 = toStringMatrix(cliente.ListaFilasTMP);
            String[] tmp2 = toStringArray(cliente.ListaColumnas);

            iniciaTabla(tmp1, tmp2);


        }

        else if(e.getSource() == ingresaDatos){
            PantallaIngresaDatos(columnNames);
        }

        else if(e.getSource() == register){
            boolean esquemaRepetido = true;
            for(int i= 0; i<ListaEsquemas.size(); i++){
                if(ListaEsquemas.get(i).equals(this.esquema.getText())){
                    esquemaRepetido = false;
                    break;
                }
            }
            if(esquemaRepetido){
                JsonObject jsonObject=new JsonObject();
                JsonObject esquemajson =new  JsonObject();
                JsonObject tipoesquema =new JsonObject();
                this.nameEsquema = esquema.getText();
                JOptionPane.showMessageDialog(null,"Esquema registrado.");
                for(int i = 0; i<atributos.size(); i++){
                    jsonObject.addProperty(atributos.get(i),tiposAtributos.get(i).toString());
                    esquemajson.add(esquema.getText(),jsonObject);
                }

                tipoesquema.add("modo1",esquemajson);
                System.out.println(tipoesquema.toString());
                try {
                    enviarDatos(tipoesquema.toString());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ListaEsquemas.add(nameEsquema);
                ComboEsquema.addItem(nameEsquema);
                nameEsquema = "";
                atributos.clear();
                tiposAtributos.clear();
                PRegistro.dispose();
                this.variable= cliente.ListaEsquemasFinal;

            }else{
                JOptionPane.showMessageDialog(null,"El esquema " + esquema.getText() + " ya existe.");
                nameEsquema = "";
                atributos.clear();
                tiposAtributos.clear();
                PRegistro.dispose();
            }


        }

        else if(e.getSource() == agregaColumna){
            //JOptionPane.showMessageDialog(null,"Columna " + column.getText() + " de tipo " + comboTipo.getSelectedItem() + " agregada.");
            atributos.add(column.getText());
            if(!comboTipo.getSelectedItem().toString().equals("Join")){
                tiposAtributos.add(comboTipo.getSelectedItem());
            }else if(comboTipo.getSelectedItem().toString().equals("Join")){
                tiposAtributos.add(esquemaJoin.getSelectedItem());
            }
            column.setText("");
        }

        else if(e.getSource() == closeRegister){
            PRegistro.dispose();
        }

        else if(e.getSource() == ingreso){
            String prueba;
            nombreEsquema= String.valueOf(ComboEsquema.getSelectedItem());
            boolean aprobar= validar(tipoColumnas, Entrys);
            if(aprobar){
                prueba = jsonhacer(columnNames, Entrys, nombreEsquema);
                try {
                    enviarjson1(prueba);
                } catch (IOException e21) {
                    e21.printStackTrace();
                }
                PIngreso.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Tipos invalidos ingresados");
                PIngreso.dispose();
            }

        }

        else if(e.getSource() == cancel){
            PIngreso.dispose();
        }

        else if(e.getSource() == buscar){
            PantallaBusqueda();
        }

        else if(e.getSource() == cancelBusqueda){
            PBusqueda.dispose();
        }

        else if(e.getSource() == Busqueda){
            JsonObject buscadato =new  JsonObject();
            JsonObject aux1=new JsonObject();
            JsonObject aux2=new JsonObject();
            aux1.addProperty(indices.getSelectedItem().toString(), JTBuscar.getText());
            aux2.add(ComboEsquema.getSelectedItem().toString(), aux1);
            buscadato.add("modo7", aux2);
            try {
                enviarjson1(buscadato.toString());
            } catch (IOException e21) {
                e21.printStackTrace();
            }
            PBusqueda.dispose();
            pantallaResultado();
        }

        else if(e.getSource() == Ok){
            PResult.dispose();
        }

        else if(e.getSource() == EliminarFilas){
            pantallaEliminarElementos();
        }

        else if(e.getSource() == eliminarDato){
            JsonObject eliminadato =new  JsonObject();
            JsonObject auxjson=new JsonObject();
            eliminadato.addProperty( (String) ComboEsquema.getSelectedItem(),e20.getText());
            auxjson.add("modo5",eliminadato);
            try {
                enviarjson1(auxjson.toString());
            } catch (IOException e21) {
                e21.printStackTrace();
            }
            PEliminar.dispose();
        }

        else if(e.getSource() == cancelEliminar){
            PEliminar.dispose();
        }

    }

    /*
     * Método que valida los tipos de las entradas para comprobar que coincidan
     * con los tipos de dato preestablecidos
     * @Param tipoColumnas_es la lista con los tipos de cada entrada
     * @Param entrys_es la lista que tiene los entrys donde se ingresa la informacion a verificar
     * */
    private boolean validar(String[] tipoColumnas, JTextField[] entrys) {
        for(int i = 0; i< tipoColumnas.length; i++){
            if(tipoColumnas[i].equals("Integer")){
                try{
                    int valor = Integer.parseInt(entrys[i].getText());
                }catch (Exception a) {
                    return false;
                }
            }
            else if(tipoColumnas[i].equals("Long")){
                try{
                    long valor1 = Long.parseLong(entrys[i].getText());
                }catch (Exception a) {
                    return false;
                }
            }
            else if(tipoColumnas[i].equals("Float")){
                try{
                    float valor2 = Float.parseFloat(entrys[i].getText());
                }catch (Exception a) {
                    return false;
                }
            }
            else if(tipoColumnas[i].equals("Double")){
                try{
                    double valor3 = Double.parseDouble(entrys[i].getText());
                }catch (Exception a) {
                    return false;
                }
            }

        }
        return true;
    }

    /*
     * Método que inicializa la pantalla donde se registran los nuevos esquemas
     * */
    public void PantallaRegistro(){
        esquema = new JTextField("Esquema", 30);
        esquema.setForeground(Gris);
        esquema.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                esquema.setText("");
                esquema.setForeground(Color.BLACK);
            }
        });
        column = new JTextField("Columnas", 20);
        column.setForeground(Gris);
        column.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                column.setText("");
                column.setForeground(Color.BLACK);
            }
        });

        this.PRegistro = new PantallaExtra(430, 200, Blanco , "Definir nuevo esquema");
        this.PRegistro.setResizable(false);
        PRegistro.setBounds(450,200, PRegistro.getWidth(), PRegistro.getHeight());

        Canvas C1 = new Canvas(0,0,430,300,Celeste);
        PRegistro.add(C1);

        this.register = new Boton("Registrar", 100, 100, 100, 40);
        register.addActionListener(this);

        this.agregaColumna = new Boton("Agregar Columna", 100, 100, 100, 40);
        agregaColumna.addActionListener(this);

        this.closeRegister = new Boton("Cancelar", 100, 100, 100, 40);
        closeRegister.addActionListener(this);

        this.Register = new Etiqueta(100,100,0,0,"Registro:");

        esquema.addActionListener(this);
        column.addActionListener(this);

        esquemaJoin = new JComboBox();
        esquemaJoin.setVisible(false);


        comboTipo = new ComboBox();
        comboTipo.addItem("Integer");
        comboTipo.addItem("String");
        comboTipo.addItem("Float");
        comboTipo.addItem("Long");
        comboTipo.addItem("Double");
        comboTipo.addItem("Join");

        comboTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboTipo.getSelectedItem().toString().equals("Join")){
                    for(int i = 0; i<ListaEsquemas.size();i++){
                        esquemaJoin.addItem(ListaEsquemas.get(i));
                    }
                    esquemaJoin.setVisible(true);
                }
                else if(!comboTipo.getSelectedItem().toString().equals("Join")){
                    esquemaJoin.setVisible(false);
                    esquemaJoin.removeAllItems();
                }
            }

        });

        C1.add(Register);
        C1.add(esquema);
        C1.add(column);
        C1.add(comboTipo);
        C1.add(esquemaJoin);
        C1.add(agregaColumna);
        C1.add(register);
        C1.add(closeRegister);

    }

    /*
     * Método que inicializa la pantalla de ingresar datos
     * @Param lista_es la lista que contiene los nombres de cada columna de un esquema
     * */

    public void PantallaIngresaDatos(String[] lista) {
        this.PIngreso = new PantallaExtra(400, 120+ 33*lista.length-1, Celeste , "Ingreso de Datos");
        this.PIngreso.setResizable(false);
        PIngreso.setBounds(450,100, PIngreso.getWidth(), PIngreso.getHeight());
        PIngreso.setLayout(new FlowLayout());

        JLabel L1 = new JLabel("Ingrese los datos:");
        L1.setVisible(true);
        L1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        L1.setForeground(Color.WHITE);
        L1.setBackground(this.Celeste);
        PIngreso.add(L1);

        for(int i= 0; i < lista.length; i++){
            Entrys[i] = new JTextField(25);
            Entrys[i].setText(lista[i] + " ( " + tipoColumnas[i] + " ) ");
            PIngreso.add(Entrys[i]);
            Entrys[i].setForeground(Gris);
            int finalI = i;
            Entrys[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    Entrys[finalI].setText("");
                    Entrys[finalI].setForeground(Color.BLACK);
                }
            });
        }
        this.ingreso = new Boton("Ingresar Datos", 100, 300, 120, 20);
        ingreso.addActionListener(this);

        this.cancel = new Boton("Cancelar", 100, 300, 120, 20);
        cancel.addActionListener(this);

        PIngreso.add(ingreso);
        PIngreso.add(cancel);
    }

    /*
     * Método que inicializa la pantalla de ingresar y crear indices
     * */

    public void PantallaIngresaIndice() {
        this.PIndice = new PantallaExtra(400, 200, Celeste , "Ingreso de Indices");
        this.PIndice.setResizable(false);
        PIndice.setBounds(450,100, PIndice.getWidth(), PIndice.getHeight());
        PIndice.setLayout(new FlowLayout());

        JLabel L10 = new JLabel("Ingrese el numero del indice que quiere asignar");
        L10.setVisible(true);
        L10.setFont(new Font("Times New Roman", Font.BOLD, 18));
        L10.setForeground(Color.WHITE);
        L10.setBackground(this.Celeste);
        PIndice.add(L10);

        JTIndice = new JTextField(20);
        JTIndice.setForeground(Gris);
        JTIndice.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JTIndice.setText("");
                JTIndice.setForeground(Color.BLACK);
            }
        });
        PIndice.add(JTIndice);


        arboles = new JComboBox(arbolesLista);
        PIndice.add(arboles);

        this.ingresaIndice = new Boton("Ingresar Indice", 100, 300, 120, 20);
        ingresaIndice.addActionListener(this);

        this.cancelaIndice = new Boton("Cancelar", 100, 300, 120, 20);
        cancelaIndice.addActionListener(this);

        PIndice.add(ingresaIndice);
        PIndice.add(cancelaIndice);
    }

    /*
     * Método que inicializa la pantalla para realizar la búsqueda de filas en un esquema determinado
     * */

    public void PantallaBusqueda(){
        this.PBusqueda = new PantallaExtra(400, 250, Celeste , "Busqueda");
        this.PBusqueda.setResizable(false);
        PBusqueda.setBounds(450,100, PBusqueda.getWidth(), PBusqueda.getHeight());
        PBusqueda.setLayout(new FlowLayout());

        JLabel L2 = new JLabel("                  Seleccione el indice de busqueda,                   ");
        L2.setVisible(true);
        L2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L2.setForeground(Color.WHITE);
        L2.setBackground(this.Celeste);
        PBusqueda.add(L2);

        JLabel L3 = new JLabel("                  luego escriba lo que desea buscar.                  ");
        L3.setVisible(true);
        L3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L3.setForeground(Color.WHITE);
        L3.setBackground(this.Celeste);
        PBusqueda.add(L3);

        JLabel L4 = new JLabel("                      Indice:               ");
        L4.setVisible(true);
        L4.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L4.setForeground(Color.WHITE);
        L4.setBackground(this.Celeste);
        PBusqueda.add(L4);

        indices = new ComboBox();
        indices.addActionListener(this);
        indices.addItem("Lineal");

        try{
            for(int i = 0; i< ListaIndices.size(); i++){
                String[] tmp = (String[]) ListaIndices.get(i);
                indices.addItem(tmp[0]);
            }
        }catch (Exception e){
            System.err.println("Error");
        }
        PBusqueda.add(indices);

        JLabel L6 = new JLabel("                      Digite lo que desea buscar:                     ");
        L6.setVisible(true);
        L6.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L6.setForeground(Color.WHITE);
        L6.setBackground(this.Celeste);
        PBusqueda.add(L6);

        JTBuscar = new JTextField(25);
        JTBuscar.setText("Buscar");
        JTBuscar.setForeground(Gris);
        JTBuscar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JTBuscar.setText("");
                JTBuscar.setForeground(Color.BLACK);
            }
        });
        PBusqueda.add(JTBuscar);

        this.Busqueda = new Boton("Buscar", 100, 300, 120, 20);
        Busqueda.addActionListener(this);
        PBusqueda.add(Busqueda);

        this.cancelBusqueda = new Boton("Cancelar", 100, 300, 120, 20);
        cancelBusqueda.addActionListener(this);
        PBusqueda.add(cancelBusqueda);

    }

    /*
     * Método que inicializa la pantalla para eliminar filas de un esquema específico
     * */

    public void pantallaEliminarElementos(){
        this.PEliminar = new PantallaExtra(400, 150, Celeste , "Eliminar elementos");
        this.PEliminar.setResizable(false);
        PEliminar.setBounds(450,100, PEliminar.getWidth(), PEliminar.getHeight());
        PEliminar.setLayout(new FlowLayout());

        JLabel L10 = new JLabel("Ingrese el key del elemento que desea eliminar:");
        L10.setVisible(true);
        L10.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L10.setForeground(Color.WHITE);
        L10.setBackground(this.Celeste);
        PEliminar.add(L10);

        e20 = new JTextField(25);
        e20.setForeground(Gris);
        e20.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                e20.setText("");
                e20.setForeground(Color.BLACK);
            }
        });
        PEliminar.add(e20);

        this.eliminarDato = new Boton("Eliminar", 100, 300, 120, 20);
        eliminarDato.addActionListener(this);
        PEliminar.add(eliminarDato);

        this.cancelEliminar = new Boton("Cancelar", 100, 300, 120, 20);
        cancelEliminar.addActionListener(this);
        PEliminar.add(cancelEliminar);

    }

    /*
     * Método que inicializa la pantalla para confirmar la eliminación de un esquema
     * */

    public void pantallaSeguro(){
        this.PEstaSeguro = new PantallaExtra(400, 150, Celeste , "Eliminar Esquema");
        this.PEstaSeguro.setResizable(false);
        PEstaSeguro.setBounds(600,300, PEstaSeguro.getWidth(), PEstaSeguro.getHeight());
        PEstaSeguro.setLayout(new FlowLayout());

        JLabel L11 = new JLabel("Seguro que desea eliminar el esquema " + ComboEsquema.getSelectedItem() + "?");
        L11.setVisible(true);
        L11.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L11.setForeground(Color.WHITE);
        L11.setBackground(this.Celeste);
        PEstaSeguro.add(L11);

        this.aprobar = new Boton("Eliminar", 100, 300, 120, 20);
        aprobar.addActionListener(this);
        PEstaSeguro.add(aprobar);

        this.cancelAprobar = new Boton("Cancelar", 100, 300, 120, 20);
        cancelAprobar.addActionListener(this);
        PEstaSeguro.add(cancelAprobar);

    }

    /*
     * Método que inicializa la pantalla que muestra los resultados de la búsqueda
     * */

    public void pantallaResultado(){
        this.PResult = new PantallaExtra(450, 170, Celeste , "Resultado");
        this.PResult.setResizable(false);
        PResult.setBounds(600,300, PResult.getWidth(), PResult.getHeight());
        PResult.setLayout(new FlowLayout());

        JLabel L3 = new JLabel("               Resultado de la busqueda:               ");
        L3.setVisible(true);
        L3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L3.setForeground(Color.WHITE);
        L3.setBackground(this.Celeste);
        PResult.add(L3);

        JTextArea datosBuscados = new JTextArea();
        datosBuscados.setFont(new Font("Times New Roman", Font.BOLD, 14));
        datosBuscados.setSize(250, 30);
        datosBuscados.setForeground(Color.WHITE);
        datosBuscados.setBackground(this.Celeste);
        if(cliente.buscaresultado == "No hay coincidencias de busqueda"){
            datosBuscados.setText("                      " + cliente.buscaresultado + "                      ");
            cliente.buscaresultado = "";
            PResult.add(datosBuscados);
        }else if(cliente.buscaresultado != "No hay coincidencias de busqueda"){
            datosBuscados.setText("         " + JTBuscar.getText() +" "+ cliente.buscaresultado + "         ");
            cliente.buscaresultado = "";
            PResult.add(datosBuscados);
        }


        JTextArea tiempo = new JTextArea();
        tiempo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        tiempo.setForeground(Color.WHITE);
        tiempo.setBackground(this.Celeste);
        tiempo.setText("                Tiempo de busqueda:   " + cliente.time + "  nanosegundos             ");
        cliente.time = "";
        PResult.add(tiempo);

        this.Ok = new Boton("Ok", 100, 170, 120, 20);
        Ok.addActionListener(this);
        Ok.setSize(120, 30);
        PResult.add(Ok);

    }

    /*
     * Método que inicializa la pantalla que elimina los indices
     * */

    public void pantallaEliminarIndice(){
        this.PEliminarIndice = new PantallaExtra(400, 150, Celeste , "Eliminar Indice");
        this.PEliminarIndice.setResizable(false);
        PEliminarIndice.setBounds(450,100, PEliminarIndice.getWidth(), PEliminarIndice.getHeight());
        PEliminarIndice.setLayout(new FlowLayout());

        JLabel L10 = new JLabel("         Seleccione el indice que desea eliminar         ");
        L10.setVisible(true);
        L10.setFont(new Font("Times New Roman", Font.BOLD, 14));
        L10.setForeground(Color.WHITE);
        L10.setBackground(this.Celeste);
        PEliminarIndice.add(L10);

        comboIndiceEliminar = new JComboBox();

        for(int i = 0; i<ListaIndices.size(); i++){
            String[] tmp = (String[]) ListaIndices.get(i);
            comboIndiceEliminar.addItem(tmp[0]);
        }

        PEliminarIndice.add(comboIndiceEliminar);

        this.elimiIndice = new Boton("Eliminar", 100, 300, 120, 20);
        elimiIndice.addActionListener(this);
        PEliminarIndice.add(elimiIndice);

        this.cancelElimiIndice = new Boton("Cancelar", 100, 300, 120, 20);
        cancelElimiIndice.addActionListener(this);
        PEliminarIndice.add(cancelElimiIndice);

    }

    /*
     * Método que transforma una linked list en un String[][]
     * */

    public static String[][] toStringMatrix(LinkedList L1){
        try{
            String[] tmp = (String[]) L1.get(0);
            String[][] result = new String[L1.size()][tmp.length];
            for(int i = 0; i < L1.size(); i++){
                tmp = (String[]) L1.get(i);
                for(int j = 0; j < tmp.length; j++){
                    result[i][j] = tmp[j];
                }
            }
            return result;
        }catch (Exception e){
            String[][] aux = null;
            return aux;
        }

    }

    /*
     * Método que transforma una LinkedList en un array
     * */

    public static String[] toStringArray(LinkedList L1){
        String[] result = new String[L1.size()];
        for(int i = 0; i < L1.size(); i++){
            result[i] = String.valueOf(L1.get(i));
        }
        return result;
    }
}
