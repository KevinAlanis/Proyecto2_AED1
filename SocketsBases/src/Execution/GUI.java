package Execution;

import Interface.*;
import Interface.Canvas;
import ServerCliente.Cliente;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import static ServerCliente.Cliente.enviarDatos;


public class GUI extends JFrame implements ActionListener {
    Color Azul = new Color(30,60,90);
    Color Celeste = new Color(60,120,180);
    Color Gris = new Color(140,140,140);
    Color GrisOscuro = new Color(90,90,90);
    Color Naranja = new Color(210,90,50);
    Color Rojo = new Color(95,35,20);
    Color Morado = new Color(60,30,145);
    Color AzulOscuro = new Color(15,45,75);
    Color Blanco = new Color(240,240,240);

    Pantalla Window = new Pantalla(1280, 720, Celeste , "Base de Datos");

    Boton AñadirEsquema, B2, register, closeRegister, enviar;
    PantallaExtra P1;
    Etiqueta Register, L1;
    JTextField esquema, column;
    Etiqueta Title  =new Etiqueta(300, 50, 1050, 20, "Data Base");
    ComboBox combo;

    String nameEsquema;
    LinkedList atributos = new LinkedList();
    LinkedList tiposAtributos = new LinkedList();


    public GUI(){
    }

    public void display(){
        iniciaComps();
        this.Title.setForeground(AzulOscuro);
        Window.add(Title);
        Tabla t1 = new Tabla();
        Window.add(t1);
    }

    public void iniciaComps(){
        AñadirEsquema = new Boton("Añadir nuevo esquema", 10, 10, 200, 40);
        AñadirEsquema.setFont(new Font("Times New Roman", 0, 15));
        AñadirEsquema.setBackground(Azul);
        AñadirEsquema.setForeground(Blanco);
        AñadirEsquema.setBorderPainted(false);
        AñadirEsquema.addActionListener(this);
        Window.add(AñadirEsquema);

        enviar = new Boton("Enviar", 80, 80, 100, 40);
        enviar.setFont(new Font("Times New Roman", 0, 15));
        enviar.setBackground(GrisOscuro);
        enviar.setForeground(Blanco);
        enviar.setBorderPainted(false);
        enviar.addActionListener(this);
        Window.add(enviar);

        Canvas C1 = new Canvas(0,0,300, 720, Azul);
        Window.add(C1);
    }

    //ACÁ SE COLOCAN LAS ACCIONES DE CADA BOTÓN
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == AñadirEsquema){
            PantallaRegistro();
        }

        else if(e.getSource() == B2){
        }

        else if(e.getSource() == enviar){
            JOptionPane.showMessageDialog(null,"Error");
        }

        else if(e.getSource() == register){
            JsonObject jsonObject=new JsonObject();
            JsonObject esquemajson =new  JsonObject();
            JsonObject tipoesquema =new JsonObject();
            this.nameEsquema = esquema.getText();
            JOptionPane.showMessageDialog(null,"Esquema registrado.");
            System.out.println("_______Título:_______");
            System.out.println(nameEsquema);
            System.out.println("_____________________");
            System.out.println("______Columnas:______");
            for(int i = 0; i<atributos.size(); i++){

                System.out.println(atributos.get(i));
                System.out.println("Tipo:");
                System.out.println(tiposAtributos.get(i));
                System.out.println("____________________________");
                jsonObject.addProperty(atributos.get(i).toString(),tiposAtributos.get(i).toString());
                esquemajson.add(esquema.getText(),jsonObject);
            }
            tipoesquema.add("modo1",esquemajson);
            System.out.println(tipoesquema.toString());
            try {
                enviarDatos(tipoesquema.toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            P1.dispose();
            nameEsquema = "";
            atributos.clear();
            tiposAtributos.clear();
        }

        else if(e.getSource() == column){
            JOptionPane.showMessageDialog(null,"Columna " + column.getText() + " de tipo " + combo.getSelectedItem() + " añadida, si desea añadir otra escribala y seleccione el tipo, sino, presione registrar para finalizar el proceso.");
            atributos.add(column.getText());
            tiposAtributos.add(combo.getSelectedItem());
            column.setText("");
        }

        else if(e.getSource() == closeRegister){
            P1.dispose();
        }

    }

    public void PantallaRegistro(){
        esquema = new JTextField("Esquema", 30);
        column = new JTextField("Columas", 20);

        this.P1 = new PantallaExtra(400, 200, Blanco , "Definir nuevo esquema");
        this.P1.setResizable(false);
        P1.setBounds(450,200, P1.getWidth(), P1.getHeight());

        Canvas C1 = new Canvas(0,0,400,300,Celeste);
        P1.add(C1);

        this.register = new Boton("Registrar", 100, 100, 100, 40);
        register.addActionListener(this);

        this.closeRegister = new Boton("Cancelar", 100, 100, 100, 40);
        closeRegister.addActionListener(this);

        this.Register = new Etiqueta(100,100,0,0,"Registro:");

        esquema.addActionListener(this);
        column.addActionListener(this);


        combo = new ComboBox();
        combo.addActionListener(this);
        combo.addItem("Integer");
        combo.addItem("String");
        combo.addItem("Float");
        combo.addItem("Long");
        combo.addItem("Double");
        combo.addItem("Join");



        C1.add(Register);
        C1.add(esquema);
        C1.add(column);
        C1.add(combo);
        C1.add(register);
        C1.add(closeRegister);

        JOptionPane.showMessageDialog(null,"Para ingresar columanas, primero seleccione el tipo, luego escriba el nombre y luego presione Enter por cada comlumna que quiera crear.");

    }

    public void Prueba(){
    }

}
