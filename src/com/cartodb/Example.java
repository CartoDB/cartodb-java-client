package com.cartodb;


import java.util.HashMap;
import java.util.Map;

/**
 * sample code to access cartodb
 *
 * @author mmtr
 */
public class Example {

    public static void main(String[] args) {
        CartoDBClient client = CartoDBClient.getInstance();

        System.out.println("--------- RESPONSE WITHOUT PARAMS  ------------");
        System.out.println(client.query("select * from table", CartoDBClient.SQL_API_V1));

        Map<String, String> params = new HashMap<String, String>();
        params.put("format", "geojson");
        System.out.println("--------- RESPONSE WIT PARAMS  ------------");
        System.out.println(client.query("select * from table", CartoDBClient.SQL_API_V1, params));
    }

}