package Interface;


import javax.swing.*;
import java.awt.*;

public class Pantalla {
    Color Fondo = new Color(30,60,90);
    int sizeX = 1280;
    int sizeY = 720;
    String title = "";
    public JFrame window;

    public Pantalla() {
    }

    public Pantalla(int sizeX, int sizeY, Color fondo) {
        Fondo = fondo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Pantalla( int sizeX, int sizeY, Color fondo, String title) {
        this.Fondo = fondo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.title = title;
    }

    public void init(){
        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(this.sizeX,this.sizeY);
        window.setVisible(true);
        Container c = window.getContentPane();
        c.setBackground(this.Fondo);
    }

    public void withdraw(){
        window.setVisible(false);
    }

    public void show(){
        window.setVisible(true);
    }

}
