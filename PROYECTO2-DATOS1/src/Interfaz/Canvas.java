package Interfaz;

/*
 *Es una clase que crea un panel
 * @author Isaac Araya
 */
import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    Color Fondo;
    int sizeX;
    int sizeY;
    int posx;
    int posy;

    /*
     *Método que asigna los valores recibidos por parámetro a las variables locales
     * @param Fondo_indica el color que va a llevar el panel
     * @param posx_indica la posición en X donde se creará la ventana
     * @param posy_indica la posición en Y donde se creará la ventana
     * @param sizex_indica el largo del panel
     * @param sizey_indica el alto del panel
     */
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

