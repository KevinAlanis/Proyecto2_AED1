package Execution;

import Interface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    Color Azul = new Color(30,60,90);
    Pantalla Window = new Pantalla(800, 600, Azul , "Base de Datos");
    JButton Button1;
    JButton Button2;

    public GUI(){
    }

    public void display(){
        Window.init();
        iniciaBotones();
    }

    public void iniciaBotones(){
        Button1 = new JButton("Cerrar");
        this.Window.window.getContentPane().add(Button1);
        Button1.setBounds(100,100,100,40);
        Button1.addActionListener(this);

        Button2 = new JButton("Prueba2");
        this.Window.window.getContentPane().add(Button2);
        Button2.setBounds(100,200,100,40);
        Button2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Button1){
            this.Window.withdraw();
            this.Window.show();
        }
        else if(e.getSource() == Button2){
            JOptionPane.showMessageDialog(null, "Error");
        }

    }
}
