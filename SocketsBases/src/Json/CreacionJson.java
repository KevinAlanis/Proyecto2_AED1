package Json;


import java.io.*;


public class CreacionJson {


    public ObjectOutputStream crearJson(Object objeto) {
        ObjectOutputStream json=null;
        try {

            json = new ObjectOutputStream(new FileOutputStream("C:\\Users\\adril\\IdeaProjects\\Json_Proyecto2\\json.txt"));

            json.writeObject(objeto);
            json.close();

        } catch (Exception e) {
        }
        return json;
    }

    public static ObjectOutputStream hacerJson()

    {
        Carro[] c1 = new Carro[2];

        c1[0] = new Carro("Toyota", 2018, 123456);
        c1[1] = new Carro("Ferrari", 2019, 1234561);
        //c1[1] = new Carro("Nissan",2019,125487);

        CreacionJson json1 = new CreacionJson();
        ObjectOutputStream json = json1.crearJson(c1);

        return json;
    }

    public static void jsonaString() {
        try {
            ObjectInputStream leyendo = new ObjectInputStream(new FileInputStream("C:\\Users\\adril\\IdeaProjects\\Json_Proyecto2\\json.txt"));
            System.out.println(leyendo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void leerJson() {
        try {
            ObjectInputStream leyendo = new ObjectInputStream(new FileInputStream("C:\\Users\\adril\\IdeaProjects\\Json_Proyecto2\\json.txt"));

            Carro[] buffer1 = (Carro[]) leyendo.readObject();

            leyendo.close();


            for (Carro c : buffer1) {
                System.out.println(c);
            }
        } catch (
                Exception e) {

        }
    }

    public static void main(String [] args){
        hacerJson();
        jsonaString();
        leerJson();
    }
}
