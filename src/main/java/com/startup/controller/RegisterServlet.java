package com.startup.controller;
import com.startup.dao.RoleDao;
import com.startup.model.Address;
import com.startup.model.Role;
import com.startup.model.User;
import com.startup.model.UserRole;
import com.startup.service.AddressService;
import com.startup.service.RoleService;
import com.startup.service.UserRoleService;
import com.startup.service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    private AddressService addressService = new AddressService();
    private UserRoleService userRoleService = new UserRoleService();
    private RoleService roleService = new RoleService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            RoleDao roleDao = new RoleDao();
            List<Role> roles = roleDao.getAllRoles();
            request.setAttribute("roles", roles);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }


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
            String result = registerUser(email, nick, name, password, weight, direccionNombre, numeracion, rolId);
            if (result.equals("success")) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("errorMessage", result);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            String errorMessage = "Error al procesar los datos. Verifica que todos los campos numéricos sean válidos.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            String errorMessage = "Se produjo un error inesperado: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private String registerUser(String email, String nick, String name, String password, int weight, String direccionNombre, String numeracion, int rolId) {
        try {
            User existingUser = userService.getUserByEmail(email);
            if (existingUser != null) {
                return "El email ingresado ya está registrado. Por favor, ingresa otro email.";
            }

            User user = new User();
            user.setEmail(email);
            user.setNick(nick);
            user.setName(name);
            user.setPassword(password);
            user.setWeight(weight);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            int userCreatedId = userService.registerUser(user);
            if (userCreatedId == -1) {
                return "Hubo un problema al registrar el usuario. Inténtalo de nuevo más tarde.";
            }

            List<Address> existingAddresses = addressService.getAddressByUserId(userCreatedId);
            boolean addressExists = false;

            for (Address addr : existingAddresses) {
                if (addr.getName().equals(direccionNombre) && addr.getNumber().equals(numeracion)) {
                    addressExists = true;
                    break;
                }
            }

            if (!addressExists) {
                Address address = new Address();
                address.setName(direccionNombre);
                address.setNumber(numeracion);
                address.setUserId(userCreatedId);

                boolean addressCreated = addressService.addAddress(address);
                if (!addressCreated) {
                    userService.deleteUserById(userCreatedId);
                    return "Hubo un problema al registrar la dirección. Inténtalo de nuevo más tarde.";
                }
            }

            UserRole userRole = new UserRole();
            userRole.setUserId(userCreatedId);
            userRole.setRoleId(rolId);

            boolean roleAssigned = userRoleService.addUserRole(userRole);
            if (!roleAssigned) {
                addressService.deleteAddressByUserId(userCreatedId);
                userService.deleteUserById(userCreatedId);
                return "Hubo un problema al asignar el rol. Inténtalo de nuevo más tarde.";
            }

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Se produjo un error inesperado: " + e.getMessage();
        }
    }
}