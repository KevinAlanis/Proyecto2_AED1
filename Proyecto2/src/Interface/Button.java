package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JFrame implements ActionListener {
    Color Fondo = new Color(190,190,190);
    int sizeX = 80;
    int sizeY = 20;
    int posx;
    int posy;
    String text = "";
    Pantalla Window;
    public JButton boton;

    public Button(Pantalla window, int sizeX, int sizeY, int posx, int posy, String text) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posx = posx;
        this.posy = posy;
        this.text = text;
        this.Window = window;
    }

    public void colocar(boolean bordes){
        JButton Button1 = new JButton(text);
        Button1.setSize(this.sizeX,this.sizeY);
        Button1.setVisible(true);
        Button1.setBounds(this.posx, this.posy, this.sizeX, this.sizeY);
        Button1.setBorderPainted(bordes);
        Button1.setForeground(Color.WHITE);
        Button1.addActionListener(this);
        if(bordes){
            Button1.setBackground(this.Fondo);
        }
        else if(bordes == false){
            Button1.setBackground(Window.Fondo);
        }
        this.Window.window.add(Button1);
        this.boton = Button1;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.boton){
            JOptionPane.showMessageDialog(null,"Boton1");
        }

    }
}
