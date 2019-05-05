package Execution;

import Interface.*;
import Interface.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    Boton AñadirEsquema, B2, B1;
    PantallaExtra P1;


    public GUI(){
    }

    public void display(){
        iniciaComps();
    }

    public void iniciaComps(){
        AñadirEsquema = new Boton("Añadir nuevo esquema", 10, 10, 160, 40);
        AñadirEsquema.setFont(new Font("Times New Roman", 0, 13));
        AñadirEsquema.setBackground(Azul);
        AñadirEsquema.setForeground(Blanco);
        AñadirEsquema.setBorderPainted(false);
        AñadirEsquema.addActionListener(this);
        Window.add(AñadirEsquema);

        Canvas C1 = new Canvas(0,0,300, 720, Azul);
        Window.add(C1);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == AñadirEsquema){
            PantallaRegistro();
        }
        else if(e.getSource() == B2){
            JOptionPane.showMessageDialog(null,"Error");
        }

        else if(e.getSource() == B1){
            P1.withdraw();
        }


    }

    public void PantallaRegistro(){
        this.P1 = new PantallaExtra(400, 300, Celeste , "Definir nuevo esquema");
        this.P1.setResizable(false);
        P1.setBounds(450,200, P1.getWidth(), P1.getHeight());
        this.B1 = new Boton("Cerrar", 0, 0, 100, 40);
        this.P1.add(this.B1);
    }

}
