package Json;

import com.google.gson.*;

import java.io.Serializable;

public class TestObjectToJson implements Serializable {

    public static String jsonhacer() {

        JsonObject valores = new JsonObject();
        JsonObject unirvalores = new JsonObject();
        JsonObject keyjson= new JsonObject();
        JsonObject nombreesquema= new JsonObject();
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




        return unirvalores.toString();
    }

}