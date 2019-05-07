package Interface;

import javax.swing.*;
import java.awt.*;

public class PantallaExtra extends JFrame{
    Color Fondo = new Color(30,60,90);
    int sizeX = 1280;
    int sizeY = 720;
    String title = "";

    public PantallaExtra( int sizeX, int sizeY, Color fondo, String title) {
        this.Fondo = fondo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.title = title;
        setSize(this.sizeX,this.sizeY);
        setTitle(title);
        setResizable(false);
        Container c = getContentPane();
        c.setBackground(this.Fondo);
        setVisible(true);
    }

    public void withdraw(){
        dispose();
    }

}
