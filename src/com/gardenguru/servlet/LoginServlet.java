package com.gardenguru.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Your login logic here

        // For example, assuming login is successful
        String redirectURL = "/home.html";

        // Set the response content type
        response.setContentType("text/plain");

        // Write the redirect URL to the response
        try (PrintWriter out = response.getWriter()) {
            out.write(redirectURL);
        }
    }
}
