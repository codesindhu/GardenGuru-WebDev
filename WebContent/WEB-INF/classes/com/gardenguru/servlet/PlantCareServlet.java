package com.gardenguru.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gardenguru.dao.PlantDAO;
import com.gardenguru.model.Plant;
import com.gardenguru.util.DBUtil;

@WebServlet("/plantcare")
public class PlantCareServlet extends HttpServlet {
    private PlantDAO plantDAO;

    public void init() {
        // Initialize the PlantDAO and database connection when the servlet is
        // initialized
        Connection connection = DBUtil.getConnection();
        plantDAO = new PlantDAO(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all plants from the database
            List<Plant> plants = plantDAO.getAllPlants();

            // Set the list of plants as an attribute in the request
            request.setAttribute("plants", plants);

            // Forward the request to the plantcare.jsp page for rendering
            RequestDispatcher dispatcher = request.getRequestDispatcher("plantcare.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception (handle it more appropriately in a production environment)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching plant data");
        }
    }

    public void destroy() {
        // Close the database connection when the servlet is destroyed
        DBUtil.closeConnection();
    }
}
