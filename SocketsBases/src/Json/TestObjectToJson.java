package Json;

import com.google.gson.*;

import java.io.Serializable;

public class TestObjectToJson implements Serializable {

    public static String jsonhacer() {
        Carro[] c1 = new Carro[2];

        c1[0] = new Carro("Toyota", 2018, 123456);
        c1[1] = new Carro("Ferrari", 2019, 1234561);

        Gson gson = new Gson();



        //convert java object to JSON format
        String json = gson.toJson(c1);

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("modo2",json);
        jsonObject.get("modo2");
        JsonParser parser=new JsonParser();
        JsonArray array=new JsonArray();
        array = (JsonArray) parser.parse(jsonObject.get("modo2").toString());
        System.out.println(array);

        System.out.println(jsonObject.toString());


        return json;


    }

}