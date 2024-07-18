<%@ page import="java.util.List" %>
<%@ page import="com.startup.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="assets/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <jsp:include page="assets/html/header.jsp"/>
</header>
<main class="flex-grow-1 d-flex flex-column justify-content-center align-items-center my-5 py-5">
    <div class="container">
        <h2 class="text-center">Register</h2>
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <% } %>

        <form action="RegisterServlet" method="post" class="mt-4">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="nick" class="form-label">Nick</label>
                <input type="text" class="form-control" id="nick" name="nick" required>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="weight" class="form-label">Weight</label>
                <input type="number" class="form-control" id="weight" name="weight" required>
            </div>
            <div class="mb-3">
                <label for="direccionNombre" class="form-label">Address Name</label>
                <input type="text" class="form-control" id="direccionNombre" name="direccionNombre" required>
            </div>
            <div class="mb-3">
                <label for="numeracion" class="form-label">Numbering</label>
                <input type="text" class="form-control" id="numeracion" name="numeracion" required>
            </div>
            <div class="mb-3">
                <label for="rolId" class="form-label">Role</label>
                <select class="form-select" id="rolId" name="rolId" required>
                    <option value="">Seleccione Rol</option>
                    <% List<Role> roles = (List<Role>) request.getAttribute("roles");
                        if (roles != null) {
                            for (Role role : roles) { %>
                    <option value="<%= role.getId() %>"><%= role.getName() %></option>
                    <%      }
                    } %>
                </select>
            </div>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Register</button>
            </div>
        </form>
    </div>
</main>
<footer class="mt-auto">
    <jsp:include page="assets/html/footer.html"/>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>