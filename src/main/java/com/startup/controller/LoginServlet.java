package com.startup.controller;
import com.startup.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isValid = userService.loginUser(email, password);
        String name = userService.loginAndGetUserName(email, password);

        if (isValid && name != null) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("email", email);
            response.sendRedirect("home.jsp");
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