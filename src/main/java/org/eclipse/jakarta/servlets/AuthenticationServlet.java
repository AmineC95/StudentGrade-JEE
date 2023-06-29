package org.eclipse.jakarta.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jakarta.service.AuthService;

@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {

    private AuthService authService = new AuthService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String token = authService.login(username, password);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Invalid username or password");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Token: " + token);
        }
    }
}
