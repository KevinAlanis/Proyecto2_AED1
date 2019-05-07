package Interface;

import javax.swing.*;

public class Boton extends JButton {
    String Text = "";
    int posx;
    int posy;
    int sizex;
    int sizey;

    public Boton(String Text, int posx, int posy, int sizex, int sizey){
        this.Text = Text;
        this.posx = posx;
        this.posy = posy;
        this.sizex = sizex;
        this.sizey = sizey;
        setText(this.Text);
        setBounds(posx,posy,sizex,sizey);
    }

}
