document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector(".login");
    const signupForm = document.querySelector(".signup");
    const loginLink = document.querySelector(".login-link");
    const signupLink = document.querySelector(".signup-link");

    loginLink.addEventListener("click", function () {
        loginForm.style.display = "block";
        signupForm.style.display = "none";
    });

    signupLink.addEventListener("click", function () {
        loginForm.style.display = "none";
        signupForm.style.display = "block";
    });

    // Example signup function
    function signup() {
        // Replace this with your actual signup logic
        const email = document.getElementById("signup-email").value;
        const password = document.getElementById("signup-password").value;

        // Perform signup validation (dummy check, replace with your actual logic)
        if (email && password) {
            // Signup successful, redirect to login.html
            window.location.href = "login.html";
        } else {
            // Display an error message (replace with your actual error handling)
            alert("Signup failed. Please fill in all fields.");
        }
    }

    // Attach the signup function to the signup button click event
    document.getElementById("signup-button").addEventListener("click", function (event) {
        event.preventDefault();
        signup();
    });
});

// Save user details to localStorage
function saveUserDetails(username, password) {
    const userDetails = {
        username,
        password,
    };
    localStorage.setItem('userDetails', JSON.stringify(userDetails));
}

// Retrieve user details from localStorage
function getUserDetails() {
    const storedDetails = localStorage.getItem('userDetails');
    return storedDetails ? JSON.parse(storedDetails) : null;
}
