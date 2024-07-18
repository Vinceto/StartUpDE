<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <jsp:include page="assets/html/header.jsp"/>
</header>
<main class="flex-grow-1 d-flex flex-column justify-content-center align-items-center my-5 py-5">
    <div class="container text-center">
        <h1 class="mt-5">Welcome to Startup Project</h1>
        <div class="d-grid gap-2 col-6 mx-auto mt-4">
            <a class="btn btn-primary" href="login.jsp">Login</a>
            <a class="btn btn-secondary" href="RegisterServlet?action=register">Register</a>
        </div>
    </div>
</main>
<footer class="mt-auto">
    <jsp:include page="assets/html/footer.html"/>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>
