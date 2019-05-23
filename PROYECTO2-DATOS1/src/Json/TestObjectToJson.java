package Json;

/*
 *Es una clase que serializa
 * @author Adrián López
 */

import com.google.gson.*;
import javax.swing.*;
import java.io.Serializable;

public class TestObjectToJson implements Serializable {

    /*
    *Método que escribe los datos en el json
    * @param columnas_es una lista de los datos que se ingresaron en las columnas
    * @param entrys_es una lista de las datos ingresados en las filas del esquema
    * @param esquema_es el nombre del esquema que se esta registrando
    * */
    public static String jsonhacer(String[] columnas, JTextField[] entrys, String esquema) {

        JsonObject valores = new JsonObject();
        JsonObject unirvalores = new JsonObject();
        JsonObject keyjson= new JsonObject();
        JsonObject nombreesquema= new JsonObject();
        String key="";
        for(int i=1; i<columnas.length;i++) {
            valores.addProperty(columnas[i], entrys[i].getText());
            key = entrys[0].getText();
        }

        keyjson.add(key,valores);
        nombreesquema.add(esquema,keyjson);
        unirvalores.add("modo2",nombreesquema);

        return unirvalores.toString();
    }

}