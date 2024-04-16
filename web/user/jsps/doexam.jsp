<%-- 
    Document   : doexam
    Created on : Apr 9, 2024, 12:34:56 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Exam"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Do Exam</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/doexam.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="UserHome.jsp">
                <p>Welcome, <%= ((models.User)session.getAttribute("userLoggedIn")).getUsername()%>!</p>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="UserHome.jsp">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="filterDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Filter Exams
                        </a>
                        <div class="dropdown-menu" aria-labelledby="filterDropdown">
                            <a class="dropdown-item" href="#">All</a>
                            <a class="dropdown-item" href="#">Scheduled</a>
                            <a class="dropdown-item" href="#">In Progress</a>
                            <a class="dropdown-item" href="#">Free access</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ViewResults.jsp">View Results</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Profile.jsp">Profile</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5 exam" style="margin-top: 70px;">
        <h2 id="exam-title">
<!--            Tieu de bai thi-->
        </h2>
        <div id="timer">Thời gian còn lại: <span id="countdown"></span></div>
        <div id="questionContainer">
            <!-- Nội dung của câu hỏi và đáp án sẽ được thêm vào đây -->
        </div>
        <button id="submit-btn" onclick="submitExam()">Nộp bài</button>
    </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="../javascripts/doexam.js"></script>
</body>
</html>

