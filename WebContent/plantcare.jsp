<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Garden Guru - Plant Care</title>
    <!-- Add your CSS styles and JavaScript here -->
</head>
<body>
    <h1>Welcome to Garden Guru</h1>
    <h2>Plant Care Tips</h2>
    
    <!-- Display plant information dynamically -->
    <ul>
        <c:forEach var="plant" items="${plants}">
            <li>
                <strong>${plant.name}</strong><br>
                Scientific Name: ${plant.scientificName}<br>
                Care Tips: ${plant.careTips}<br>
                Growth Stage: ${plant.growthStage}<br>
                Watering Frequency: Every ${plant.wateringFrequency} days<br>
                <!-- Add more plant details as needed -->
            </li>
            <br>
        </c:forEach>
    </ul>
    
    <!-- Add your form for tracking growth, setting reminders, etc. -->

    <!-- Example: Add a form to add a new plant -->
    <form action="addPlant" method="post">
        <label for="plantName">Plant Name:</label>
        <input type="text" id="plantName" name="plantName" required>
        
        <!-- Add more input fields for other plant details -->
        
        <button type="submit">Add Plant</button>
    </form>

</body>
</html>
