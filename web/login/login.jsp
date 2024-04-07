<%-- 
    Document   : login.jsp
    Created on : Apr 1, 2024, 6:17:52 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="login.css">
        <script src="login.js"></script>
    </head>
    <body>
        <div class="login-container">
            <h2 class ="login-page-title">Login</h2>
            <form id="login-form" method = "post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <a href="../html/register.html">Don't you have an account?</a>
                <button onclick="loginUser()">Login</button>
            </form>
            <p id="error-message" class="error-message"></p>
        </div>
    </body>
</html>
