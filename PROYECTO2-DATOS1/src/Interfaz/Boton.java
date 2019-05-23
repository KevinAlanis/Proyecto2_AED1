package Interfaz;
/*
 *Es una clase que crea un botón
 * @author Isaac Araya
 */
import javax.swing.*;

public class Boton extends JButton {
    String Text = "";
    int posx;
    int posy;
    int sizex;
    int sizey;

    /*
     *Método que asigna los valores recibidos por parámetro a las variables locales
     * @param Text_indica lo que va a decir el boton
     * @param posx_indica la posición en X donde se creará el botón
     * @param posy_indica la posición en Y donde se creará el botón
     * @param sizex_indica el largo del botón
     * @param sizey_indica el alto del botón
     */
    public Boton(String Text, int posx, int posy, int sizex, int sizey){
        this.Text = Text;
        this.posx = posx;
        this.posy = posy;
        this.sizex = sizex;
        this.sizey = sizey;
        setText(this.Text);
        setBounds(posx,posy,sizex,sizey);
        setVisible(true);
    }

}
