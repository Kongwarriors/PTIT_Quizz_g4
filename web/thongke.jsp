<%-- 
    Document   : ketqua
    Created on : Apr 3, 2024, 10:26:23 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống kê</title>
    <style>
        /* styles.css */

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 1000px; /* Điều chỉnh kích thước container */
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #d32f2f; /* Đỏ */
            margin-bottom: 70px;
            margin-top: 70px;
        }

        .filters {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px; /* Giảm khoảng cách dưới của phần lọc */
        }

        .filters label,
        .filters select,
        .filters input[type="date"],
        .export-buttons button {
            margin-right: 10px;
        }

        /* Điều chỉnh khoảng cách giữa các phần tử trong phần lọc */
        .filters label,
        .filters select,
        .filters input[type="date"] {
            margin-bottom: 0; /* Loại bỏ khoảng cách dưới */
        }

        /* Điều chỉnh độ rộng và padding để các phần tử nằm gần nhau hơn */
        .filters label,
        .filters select,
        .filters input[type="date"] {
            width: auto; /* Độ rộng tự động */
            padding: 8px; /* Padding để các phần tử nằm gần nhau hơn */
        }

        /* Điều chỉnh các phần tử trong phần lọc khi màn hình thu nhỏ */
        @media (max-width: 768px) {
            .filters label,
            .filters select,
            .filters input[type="date"] {
                margin-bottom: 10px; /* Khoảng cách dưới lớn hơn khi màn hình thu nhỏ */
            }

            .filters label:first-child,
            .filters select:first-child,
            .filters input[type="date"]:first-child {
                margin-bottom: 0; /* Loại bỏ khoảng cách dưới của phần tử đầu tiên */
            }

            .filters select {
                width: calc(50% - 5px); /* Điều chỉnh độ rộng của select để chia đều khi màn hình thu nhỏ */
            }
        }

        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed; /* Tính toán độ rộng cột dựa trên nội dung và chiều rộng đã đặt */
        }

        table th,
        table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
            word-wrap: break-word; /* Ngắt từ dài */
        }

        table th {
            background-color: #f2f2f2;
        }

        .export-buttons {
            text-align: center;
            margin-bottom: 40px;
        }

        button {
            padding: 10px 20px;
            background-color: #d32f2f; /* Đỏ */
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #b71c1c; /* Đỏ tối */
        }

        /* Cài đặt mặc định */
        #index {
            width: auto; /* Thay đổi độ rộng cột STT để nó tự điều chỉnh theo nội dung bên trong */
        }

        .stats-container {
            overflow-x: auto; /* Kéo thanh cuộn ngang nếu danh sách dài */
            width: 100%; /* Đảm bảo bảng thống kê lấp đầy không gian */
        }

        /* Điều chỉnh độ rộng của cột Họ và Tên */
        th:nth-child(2),
        td:nth-child(2) {
            width: 150px; /* Độ rộng mặc định */
        }

        /* Căn giữa nội dung của từng cột */
        th:nth-child(1),
        td:nth-child(1),
        th:nth-child(2),
        td:nth-child(2),
        th:nth-child(3),
        td:nth-child(3),
        th:nth-child(4),
        td:nth-child(4),
        th:nth-child(5),
        td:nth-child(5),
        th:nth-child(6),
        td:nth-child(6),
        th:nth-child(7), /* Thêm cột Loại kỳ thi */
        td:nth-child(7), /* Thêm cột Loại kỳ thi */
        th:nth-child(8), /* Thêm cột Trạng thái truy cập */
        td:nth-child(8), /* Thêm cột Trạng thái truy cập */
        th:nth-child(9), /* Thêm cột Ngày thi */
        td:nth-child(9) { /* Thêm cột Ngày thi */
            text-align: center;
        }

        /* Thêm kích thước cột Họ và Tên khi thu nhỏ màn hình */
        @media (max-width: 768px) {
            th:nth-child(2),
            td:nth-child(2) {
                width: auto; /* Đặt lại độ rộng thành tự động khi thu nhỏ màn hình */
            }
        }

        /* Điều chỉnh cột Họ và Tên để căn giữa */
        th:nth-child(2),
        td:nth-child(2) {
            text-align: center;
        }

        /* Điều chỉnh nội dung của cột Họ và Tên để căn trái */
        td:nth-child(2) {
            text-align: left;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.1/jspdf.umd.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.4/xlsx.full.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container">
        <h1>Thống kê Kết quả thi Sinh viên</h1>
        <div class="filters">
            <label for="examType">Loại kỳ thi:</label>
            <select id="examType">
                <option value="all">Tất cả</option>
                <option value="practice">Luyện tập</option>
                <option value="midterm">Giữa kỳ</option>
                <option value="final">Cuối kỳ</option>
            </select>

            <label for="accessType">Trạng thái truy cập:</label>
            <select id="accessType">
                <option value="all">Tất cả</option>
                <option value="free">Tự do</option>
                <option value="specificTime">Thời gian cụ thể</option>
            </select>

            <label for="date">Ngày:</label>
            <input type="date" id="date">

            <button id="applyFilters">Áp dụng Bộ lọc</button>
        </div>

        <div class="export-buttons">
            <button id="exportExcel">Xuất Excel</button>
        </div>

        <table id="statistics">
            <thead>
                <tr>
                    <th>STT</th> <!-- Số thứ tự(STT) -->
                    <th>Họ và tên</th>
                    <th>Mã sinh viên</th>
                    <th>Loại kỳ thi</th> <!-- Thêm cột Loại kỳ thi -->
                    <th>Trạng thái truy cập</th> <!-- Thêm cột Trạng thái truy cập -->
                    <th>Ngày thi</th> <!-- Thêm cột Ngày thi -->
                    <th>Tổng số lần tham gia</th>
                    <th>Tỉ lệ hoàn thành (%)</th>
                    <th>Điểm trung bình</th>
                </tr>
            </thead>
            <tbody id="statsBody"></tbody>
        </table>
        <!-- Thẻ canvas để vẽ biểu đồ -->
        <canvas id="myChart" width="400" height="200"></canvas>
    </div>
    <script src="thongke.js"></script>
</body>
</html>
