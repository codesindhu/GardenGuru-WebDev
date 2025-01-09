package com.gardenguru.servlet;

public // WeatherServlet.java

package com.gardenguru.servlet;

import com.gardenguru.service.WeatherService;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Get the city parameter from the request (you can modify this based on your needs)
        String city = request.getParameter("city");

        // Call the WeatherService to get weather data
        WeatherService weatherService = new WeatherService();
        JSONObject weatherData = weatherService.getWeatherData(city);

        // Send the weather data as JSON response
        response.getWriter().write(weatherData.toString());
    }
}
