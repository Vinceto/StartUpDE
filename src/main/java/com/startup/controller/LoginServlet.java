package com.startup.controller;

import com.startup.model.User;
import com.startup.service.UserRoleService;
import com.startup.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    private UserRoleService userRoleService = new UserRoleService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValid = userService.loginUser(email, password);
        User user = userService.getUserByEmail(email);
        String name = userService.loginAndGetUserName(email, password);
        String role = userRoleService.getUserRole(user.getId());

        if (isValid && name != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("email", email);
            session.setAttribute("role", role);

            List<User> users = userService.getAllUsers();
            if (role.equals("Admin")) {
                request.setAttribute("users", users);
            }
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } else {
            String errorMessage;
            if (!isValid) {
                errorMessage = "Credenciales inválidas. Por favor, verifica tu email y contraseña.";
            } else {
                errorMessage = "No existe una cuenta asociada a ese email.";
            }
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}