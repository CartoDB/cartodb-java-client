package com.cartodb;


import org.scribe.builder.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * CartoDB Client
 * @author javi
 *
 */
public class CartoDBClient {

    private String user;
    private String password;
    private String consumerKey;
    private String consumerSecret;

    private Token accessToken;
    private OAuthService service;

    private static CartoDBClient instance = null;

    public static String SQL_API_V1 = "v1";

    private CartoDBClient() {
        Properties cartodbConf = new Properties();
        try {
            cartodbConf.load(CartoDBClient.class.getResourceAsStream("/com/cartodb/cartodb.properties"));
            user = cartodbConf.getProperty("cartodb.user");
            password = cartodbConf.getProperty("cartodb.password");
            consumerKey = cartodbConf.getProperty("cartodb.consumerKey");
            consumerSecret = cartodbConf.getProperty("cartodb.consumerSecret");

            service = new ServiceBuilder()
                .provider(new CartoDBAPI.SSL(user, password))
                .apiKey(consumerKey)
                .apiSecret(consumerSecret)
                .build();

            accessToken = service.getAccessToken(null, null);
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

    public static CartoDBClient getInstance() {
        if (instance == null) {
            instance = new CartoDBClient();
        }
        return instance;
    }

    public String query(String sql, String version, Map<String,String> params) {
        try {
            String url = "https://"+user+".cartodb.com/api/"+version+"/sql?q=" + URLEncoder.encode(sql, "UTF-8");
            for (String key : params.keySet()) {
                url += "&"+key+"="+params.get(key);
            }
            OAuthRequest request = new OAuthRequest(Verb.GET, url);
            service.signRequest(accessToken, request);
            Response response = request.send();
            return (response.getBody());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error:" + e);
            return "{}";
        }
    }

    public String query(String sql, String version) {
        return query(sql, version, new HashMap<String,String>());
    }

}