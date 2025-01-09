package com.gardenguru.dao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gardenguru.model.Plant;

public class PlantDAO {

    // Modify these variables based on your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/garden_guru";
    private static final String USER = "garden_guru_user";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
}

public class PlantDAO {
    private Connection connection;

    public PlantDAO(Connection connection) {
        this.connection = connection;
    }

    // Create operation
    public void addPlant(Plant plant) throws SQLException {
        String query = "INSERT INTO plants (name, scientific_name, care_tips, image_url, growth_stage, watering_frequency) "
                +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plant.getName());
            preparedStatement.setString(2, plant.getScientificName());
            preparedStatement.setString(3, plant.getCareTips());
            preparedStatement.setString(4, plant.getImageUrl());
            preparedStatement.setString(5, plant.getGrowthStage());
            preparedStatement.setInt(6, plant.getWateringFrequency());

            preparedStatement.executeUpdate();
        }
    }

    // Read operation
    public Plant getPlantById(int plantId) throws SQLException {
        String query = "SELECT * FROM plants WHERE id = ?";
        Plant plant = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, plantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    plant = extractPlantFromResultSet(resultSet);
                }
            }
        }

        return plant;
    }

    // Update operation
    public void updatePlant(Plant plant) throws SQLException {
        String query = "UPDATE plants SET name = ?, scientific_name = ?, care_tips = ?, " +
                "image_url = ?, growth_stage = ?, watering_frequency = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, plant.getName());
            preparedStatement.setString(2, plant.getScientificName());
            preparedStatement.setString(3, plant.getCareTips());
            preparedStatement.setString(4, plant.getImageUrl());
            preparedStatement.setString(5, plant.getGrowthStage());
            preparedStatement.setInt(6, plant.getWateringFrequency());
            preparedStatement.setInt(7, plant.getId());

            preparedStatement.executeUpdate();
        }
    }

    // Delete operation
    public void deletePlant(int plantId) throws SQLException {
        String query = "DELETE FROM plants WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, plantId);

            preparedStatement.executeUpdate();
        }
    }

    // Get all plants
    public List<Plant> getAllPlants() throws SQLException {
        String query = "SELECT * FROM plants";
        List<Plant> plants = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Plant plant = extractPlantFromResultSet(resultSet);
                plants.add(plant);
            }
        }

        return plants;
    }

    private Plant extractPlantFromResultSet(ResultSet resultSet) throws SQLException {
        Plant plant = new Plant();
        plant.setId(resultSet.getInt("id"));
        plant.setName(resultSet.getString("name"));
        plant.setScientificName(resultSet.getString("scientific_name"));
        plant.setCareTips(resultSet.getString("care_tips"));
        plant.setImageUrl(resultSet.getString("image_url"));
        plant.setGrowthStage(resultSet.getString("growth_stage"));
        plant.setWateringFrequency(resultSet.getInt("watering_frequency"));

        return plant;
    }
}
