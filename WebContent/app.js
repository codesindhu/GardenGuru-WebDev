document.addEventListener("DOMContentLoaded", function () {
    
    // Dummy plant data for testing
    const plants = [
        { name: "Rose", careTips: "Water regularly and provide sunlight" },
        { name: "Fern", careTips: "Keep soil moist and place in shade" },
        // Add more plant data as needed
    ];

    // Display plant information dynamically
    const plantList = document.getElementById("plant-list");
    plants.forEach((plant) => {
        const listItem = document.createElement("li");
        listItem.textContent = `${plant.name} - ${plant.careTips}`;
        plantList.appendChild(listItem);
    });

    // Click event for each article in the Latest Articles section
    const latestArticles = document.querySelectorAll(".Articles-col");
    latestArticles.forEach((article) => {
        article.addEventListener("click", function () {
            // Get article title and content
            const title = article.querySelector("h3").innerText;
            const content = article.querySelector("p").innerText;

            // Display full article
            displayFullArticle(title, content);
        });
    });

    // Form submission and local storage
    const articleForm = document.getElementById("article-form");
    const submittedArticlesContainer = document.getElementById("submitted-articles");

    articleForm.addEventListener("submit", function (event) {
        event.preventDefault();

        // Get input values
        const title = document.getElementById("article-title").value;
        const content = document.getElementById("article-content").value;
        const author = document.getElementById("article-author").value;

        // Create a new article element
        const article = document.createElement("div");
        article.className = "Articles-col";
        const titleElement = document.createElement("h3");
        const contentElement = document.createElement("p");
        const authorElement = document.createElement("p");

        titleElement.textContent = title;
        contentElement.textContent = content;
        authorElement.textContent = "Written by: " + author;

        article.appendChild(titleElement);
        article.appendChild(contentElement);
        authorElement.textContent = "Written by: " + author;

        // Append the article to the container
        submittedArticlesContainer.appendChild(article);

        // Save the article to local storage
        saveArticleToLocalStorage({ title, content, author });

        // Clear the form fields
        document.getElementById("article-title").value = "";
        document.getElementById("article-content").value = "";
        document.getElementById("article-author").value = "";

        // Add event listener to display the full article on click
        article.addEventListener("click", function () {
            displayFullArticle(title, content,author);
        });
    });

    // Load existing articles from local storage
    loadArticlesFromLocalStorage();

    function saveArticleToLocalStorage(article) {
        let articles = JSON.parse(localStorage.getItem("articles")) || [];
        articles.push(article);
        localStorage.setItem("articles", JSON.stringify(articles));
    }

    function loadArticlesFromLocalStorage() {
        let articles = JSON.parse(localStorage.getItem("articles")) || [];
        articles.forEach((article) => {
            const newArticle = document.createElement("div");
            newArticle.className = "Articles-col";
            const titleElement = document.createElement("h3");
            const contentElement = document.createElement("p");

            titleElement.textContent = article.title;
            contentElement.textContent = article.content;

            newArticle.appendChild(titleElement);
            newArticle.appendChild(contentElement);

            submittedArticlesContainer.appendChild(newArticle);
        });
    }

    // Function to display the full article
    function displayFullArticle(title, content,author) {
        // Create a new container for the full article
        const fullArticleContainer = document.createElement("div");
        fullArticleContainer.classList.add("Articles-col", "full-article");

        // Add the title and content to the container
        fullArticleContainer.innerHTML = `
            <h2>${title}</h2>
            <p>${content}</p>
            <p>Written by: ${author}</p>
        `;

        // Append the full article container to the main content area
        const mainContent = document.getElementById("main-content");
        mainContent.innerHTML = "";
        mainContent.appendChild(fullArticleContainer);
    }
    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();

        var username = document.getElementById("email").value;
        var password = document.getElementById("password").value;

        // Perform AJAX login request
        $.ajax({
            type: "POST",
            url: "/login",
            contentType: "application/json",
            data: JSON.stringify({ username: username, password: password }),
            success: function (response) {
                if (response.startsWith("/")) {
                    // Redirect to the specified URL
                    window.location.href = response;
                } else {
                    // Display the response (e.g., error message)
                    $("#login-status").text(response);
                }
            },
            error: function (error) {
                $("#login-status").text("Error: " + error.responseText);
            }
        });
    });

    // Login function using jQuery AJAX
    $("#login-button").click(function () {
        login();
    });
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
    
        $.ajax({
            type: "POST",
            url: "/login",
            contentType: "application/json",
            data: JSON.stringify({ username: username, password: password }),
            success: function (response) {
                console.log("Login Response:", response);

                if (response === "Login successful!") {
                    console.log("Redirecting to home page...");
                    window.location.href = "index.html";
                } else {
                    $("#login-status").text(response);
                }
            },
            error: function (error) {
                console.error("Login Error:", error.responseText);
                $("#login-status").text("Error: " + error.responseText);
            }
        });
    }
 });
