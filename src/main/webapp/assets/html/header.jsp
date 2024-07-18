<%@ page import="javax.servlet.http.HttpSession" %>
<% String email = (request.getSession(false) != null) ? (String) session.getAttribute("email") : null; %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">StartUp</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <% if (email != null) { %>
                <li class="nav-item">
                    <a class="nav-link" href="#">Welcome, <%= email %></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Logout</a>
                </li>
                <% } else { %>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="RegisterServlet?action=register">Registrarse</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>