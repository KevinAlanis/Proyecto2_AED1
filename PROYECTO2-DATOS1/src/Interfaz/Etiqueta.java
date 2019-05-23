package Interfaz;

/*
 *Es una clase que crea una etiqueta
 * @author Isaac Araya
 */
import javax.swing.*;
import java.awt.*;

public class Etiqueta extends JLabel{
    Color Fondo = new Color(190,190,190);
    public int sizeX = 80;
    public int sizeY = 20;
    public int posx;
    public int posy;
    String text = "";

    /*
     *Método que asigna los valores recibidos por parámetro a las variables locales
     * @param text_indica lo que va a decir la etiqueta
     * @param posx_indica la posición en X donde se creará la etiqueta
     * @param posy_indica la posición en Y donde se creará la etiqueta
     * @param sizex_indica el largo de la etiqueta
     * @param sizey_indica el alto de la etiqueta
     */
    public Etiqueta( int posx, int posy, int sizeX, int sizeY, String text) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posx = posx;
        this.posy = posy;
        this.text = text;
        setText(text);
        setSize(this.sizeX,this.sizeY);
        setVisible(true);
        setBounds(posx,posy,sizeX, sizeY);
        setFont(new Font("Impact", 1, 40));
        setForeground(Color.WHITE);
        setBackground(this.Fondo);
    }

}
