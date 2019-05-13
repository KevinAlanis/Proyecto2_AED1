package Json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Pruebajson {

    public static void main(String [] args){
    JsonObject valores = new JsonObject();
    JsonObject unirvalores = new JsonObject();
    JsonObject keyjson= new JsonObject();
    JsonObject nombreesquema=new JsonObject();
    valores.addProperty("marca","Toyota");
    valores.addProperty("modelo","2018");
    String key="123456";
    keyjson.add(key,valores);


    valores = new JsonObject();
    valores.addProperty("marca","Ferrari");
    valores.addProperty("modelo","2019");
    key="654321";
    keyjson.add(key,valores);

    nombreesquema.add("persona",keyjson);

    unirvalores.add("modo2",nombreesquema);



    System.out.println(unirvalores);

}
}
