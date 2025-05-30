package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task2 {

    public static String fetch() {
        String apiUrl = "https://api.ipify.org/?format=json";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(sb.toString());
            return (String) obj.get("ip");

        } catch (Exception e) {
            System.err.println("Failed to fetch IP: " + e.toString());
            return null;
        }
    }

    public static void getIP() {
        String ip = fetch();
        if (ip != null) {
            System.out.println("IP: " + ip);
        } else {
            System.out.println("Could not retrieve public IP.");
        }
    }

}
