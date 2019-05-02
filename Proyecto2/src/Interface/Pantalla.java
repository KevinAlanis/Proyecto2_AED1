package Interface;


import javax.swing.*;
import java.awt.*;

public class Pantalla {
    Color Fondo = new Color(30,60,90);
    int sizeX = 800;
    int sizeY = 600;
    String title = "";
    JFrame window;

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
        JFrame Window = new JFrame(title);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.setSize(this.sizeX,this.sizeY);
        Window.setVisible(true);
        Container c = Window.getContentPane();
        c.setBackground(this.Fondo);
        this.window = Window;
    }

}
