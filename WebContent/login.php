<?php
// login.php

// Check if the form is submitted
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Get the username and password from the form
    $username = $_POST["username"];
    $password = $_POST["password"];

    // You should replace this with your actual authentication logic
    // For simplicity, let's assume the login is successful
    $loginSuccessful = true;

    if ($loginSuccessful) {
        // Set a session variable to store the logged-in user
        session_start();
        $_SESSION["username"] = $username;

        // Return a JSON response indicating success
        echo json_encode(["success" => true, "message" => "Login successful"]);
        exit;
    } else {
        // Return a JSON response indicating failure
        echo json_encode(["success" => false, "message" => "Login failed"]);
        exit;
    }
} else {
    // If the form is not submitted, return an error response
    echo json_encode(["success" => false, "message" => "Invalid request"]);
    exit;
}
?>
