package Execution;


import Interface.*;
import Interface.Button;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        Color azul = new Color(30, 60, 90);
        Pantalla Window = new Pantalla(1280,720, azul, "Nueva Ventana");
        Window.init();
        Button B1 = new Button(Window, 80, 30, 10, 10, "Prueba");
        B1.colocar(false);
        Etiqueta L1 = new Etiqueta(Window, 150, 100, 500, 500, "Prueba");
        L1.colocar();
        Button B2 = new Button(Window, 80, 30, 120, 100, "Prueba2");
        B2.colocar(false);
    }


}
