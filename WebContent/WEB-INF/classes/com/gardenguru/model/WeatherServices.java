package com.gardenguru.model;

public // WeatherService.java

package com.gardenguru.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "bd3bbcc7a309282ae483dafe761de996";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    public JSONObject getWeatherData(String city) throws IOException {
        // Create the API URL with the city and API key
        String apiUrl = String.format("%s?q=%s&appid=%s", API_URL, city, API_KEY);

        // Make an HTTP GET request to the API
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the API
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        // Parse the JSON response
        return new JSONObject(response.toString());
    }
}