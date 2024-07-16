package com.startup.controller;

import com.startup.model.Address;
import com.startup.model.User;
import com.startup.model.UserRole;
import com.startup.service.AddressService;
import com.startup.service.UserRoleService;
import com.startup.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private AddressService addressService = new AddressService();
    private UserRoleService userRoleService = new UserRoleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").trim().toLowerCase();
        String nick = request.getParameter("nick");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int weight = Integer.parseInt(request.getParameter("weight"));
        String direccionNombre = request.getParameter("direccionNombre");
        String numeracion = request.getParameter("numeracion");
        int rolId = Integer.parseInt(request.getParameter("rolId"));

        try {
            // Validar si el email ya está registrado
            User existingUser = userService.getUserByEmail(email);
            if (existingUser != null) {
                String errorMessage = "El email ingresado ya está registrado. Por favor, ingresa otro email.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            User user = new User();
            user.setEmail(email);
            user.setNick(nick);
            user.setName(name);
            user.setPassword(password);
            user.setWeight(weight);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            if (userService.registerUser(user)) {
                Address address = new Address();
                address.setName(direccionNombre);
                address.setNumber(numeracion);
                address.setUserId(user.getId());

                addressService.addAddress(address);

                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(rolId);

                userRoleService.addUserRole(userRole);

                response.sendRedirect("login.jsp");
            } else {
                String errorMessage = "Hubo un problema al registrar el usuario. Inténtalo de nuevo más tarde.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            String errorMessage = "Error al procesar los datos. Verifica que todos los campos numéricos sean válidos.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            String errorMessage = "Se produjo un error inesperado. Por favor, inténtalo de nuevo más tarde.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}