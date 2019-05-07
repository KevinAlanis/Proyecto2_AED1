package Interface;

import javax.swing.*;
import java.awt.*;

public class Etiqueta extends JLabel{
    Color Fondo = new Color(190,190,190);
    int sizeX = 80;
    int sizeY = 20;
    int posx;
    int posy;
    String text = "";

    public Etiqueta(int sizeX, int sizeY, int posx, int posy, String text) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posx = posx;
        this.posy = posy;
        this.text = text;
        setText(text);
        setSize(this.sizeX,this.sizeY);
        setVisible(true);
        setBounds(this.posx, this.posy, this.sizeX, this.sizeY);
        setFont(new Font("Impact", 1, 40));
        setForeground(Color.WHITE);
        setBackground(this.Fondo);
    }

}
