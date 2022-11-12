package me.atroxego.pauladdons.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.atroxego.pauladdons.utils.Utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class dateInformation {
    static JsonObject dateData;
    public static void getDateInformation(){
        makeApiRequest();
        Utils.currentDay = dateData.get("day").getAsInt();
        Utils.currentMinute = dateData.get("minute").getAsInt();
        Utils.currentTime = dateData.get("time").getAsString();
        Utils.currentHour = dateData.get("hour").getAsInt();
        if(Utils.currentTime.endsWith("pm") && Utils.currentHour != 12) Utils.currentHour = dateData.get("hour").getAsInt()+12;
    }

    public static void makeApiRequest() {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL("https://api.slothpixel.me/api/skyblock/calendar");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JsonParser parse = new JsonParser();
                JsonArray dataObject = (JsonArray) parse.parse("[" + String.valueOf(informationString) + "]");

                System.out.println(dataObject.get(0));

                dateData = (JsonObject) dataObject.get(0);
//                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
