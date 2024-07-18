<%@ page import="java.util.List" %>
<%@ page import="com.startup.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Startup Project</title>
    <link rel="stylesheet" href="assets/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <%@ include file="assets/html/header.jsp" %>
</header>
<main class="flex-grow-1 my-5 py-5">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-3">
                <div class="list-group">
                    <a href="#" class="list-group-item list-group-item-action active">Dashboard</a>
                    <a href="#" class="list-group-item list-group-item-action">Profile</a>
                    <a href="#" class="list-group-item list-group-item-action">Settings</a>
                    <a href="#" class="list-group-item list-group-item-action">Help</a>
                </div>
            </div>
            <div class="col-md-9">
                <h1>Dashboard</h1>
                <p>Welcome to your dashboard, <%= session.getAttribute("name") %>. Here you can manage your account and settings.</p>
                <div class="row">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </div>
                </div>

                <% if (session.getAttribute("role") != null && session.getAttribute("role").equals("Admin")) { %>
                <div class="mt-5">
                    <h2>Users List</h2>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nick</th>
                            <th>Email</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% List<User> users = (List<User>) request.getAttribute("users"); %>
                        <% for (User user : users) { %>
                        <tr>
                            <td><%= user.getId() %></td>
                            <td><%= user.getNick() %></td>
                            <td><%= user.getEmail() %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <% } else { %>
                <div class="alert alert-danger mt-5" role="alert">
                    Insufficient permissions to view user data.
                </div>
                <% } %>
            </div>
        </div>
    </div>
</main>
<footer class="mt-auto">
    <%@ include file="assets/html/footer.html" %>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>