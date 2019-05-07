package Json;

import com.google.gson.Gson;

import java.io.Serializable;

public class TestObjectToJson implements Serializable {

    public static String jsonhacer() {
        Carro[] c1 = new Carro[2];

        c1[0] = new Carro("Toyota", 2018, 123456);
        c1[1] = new Carro("Ferrari", 2019, 1234561);

        Gson gson = new Gson();

        //convert java object to JSON format
        String json = gson.toJson(c1);


        System.out.println(json);


        return json;


    }

}