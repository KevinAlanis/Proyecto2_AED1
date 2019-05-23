package Interfaz;
/*
*
* @param
* */
/*
 *Es una clase que crea una ventana
 * @author Isaac Araya
 */

import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame{
    Color Fondo = new Color(30,60,90);
    int sizeX = 1280;
    int sizeY = 720;
    String title = "";

    /*
     *Método que asigna los valores recibidos por parámetro a las variables locales
     * @param Fondo_indica el color que va a llevar la ventana
     * @param title_indica el nombre de la ventana
     * @param sizex_indica el largo de la ventana
     * @param sizey_indica el alto de la ventana
     */
    public Pantalla( int sizeX, int sizeY, Color fondo, String title) {
        this.Fondo = fondo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.title = title;
        setBounds(70,20,this.sizeX,this.sizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setResizable(false);
        Container c = getContentPane();
        c.setBackground(this.Fondo);
    }
}
