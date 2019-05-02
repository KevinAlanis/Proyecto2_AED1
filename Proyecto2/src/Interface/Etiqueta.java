package Interface;

import javax.swing.*;
import java.awt.*;

public class Etiqueta {
    Color Fondo = new Color(190,190,190);
    int sizeX = 80;
    int sizeY = 20;
    int posx;
    int posy;
    String text = "";
    Pantalla Window;

    public Etiqueta(Pantalla window, int sizeX, int sizeY, int posx, int posy, String text) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posx = posx;
        this.posy = posy;
        this.text = text;
        this.Window = window;
    }

    public void colocar(){
        JLabel L1 = new JLabel(text);
        L1.setSize(this.sizeX,this.sizeY);
        L1.setVisible(true);
        L1.setBounds(this.posx, this.posy, this.sizeX, this.sizeY);
        L1.setFont(new Font("Impact", 1, 40));
        L1.setForeground(Color.WHITE);
        L1.setBackground(this.Fondo);
        this.Window.window.add(L1);
    }
}
