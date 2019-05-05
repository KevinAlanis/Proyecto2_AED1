package Interface;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    Color Fondo;
    int sizeX;
    int sizeY;
    int posx;
    int posy;

    public Canvas(int posx, int posy, int sizeX, int sizeY, Color fondo) {
        this.Fondo = fondo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posx = posx;
        this.posy = posy;
        setBounds(this.posx,this.posy,this.sizeX,this.sizeY);
        setBackground(this.Fondo);
        setVisible(true);
    }
}

