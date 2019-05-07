package Estructuras;

import java.util.HashMap;

public class HashMapdata {
    public static void main(String[] args) {

        // Create a HashMap object called values
        HashMap<String, String> values = new HashMap<String, String>();

        // Add keys and values (Country, City)
        values.put("carne", "12345678");
        values.put("nombre", "Tony");
        values.put("apellido", "Stark");
        values.put("curso", "Aleman");
        System.out.println(values);


            String valueaux = "carne";
            if (values.containsKey(valueaux)) {
                // Get the value assigned to a given key in the HashMap
                String aux = values.get(valueaux);
                System.out.println(valueaux + " es " + aux);
            } else {
                System.out.println("City details not found for user " + valueaux);
            }



    }
}
