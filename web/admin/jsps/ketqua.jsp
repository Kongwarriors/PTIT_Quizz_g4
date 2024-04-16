<%-- 
    Document   : ketqua
    Created on : Apr 3, 2024, 10:26:23 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="../css/ketqua.css"/>
    </head>
    <body>
        <header>
        <div class="head">
          <div class="left_area">
            <a href="#">PTIT QUIZ</a>
          </div>
          <div class="right_area">
            <a href="/logout" class="logout_btn">Logout</a>
          </div>
        </div>
      </header>

      <!--header area end-->
        <!-- Tra cứu mã sinh viên -->
        <div class="ketqua">
          <div class="center">
              <div class="border">
                <div class="container">
                  <div class="timkiem">Tìm kiếm sinh viên</div>
                  <div class="info">
                    <div class="masv">
                      <input type="text" id="fullName" placeholder="Mã sinh viên"><br>
                    </div>
                    <button class="tracuu" onclick="showResult()">Tra cứu</button>
                  </div>
                </div>
              </div>
          </div>
          <!-- Bảng hiển thị kết quả -->
            <table id="resultTable" >
              <thead>
                <tr>
                  <th>Mã</th>
                  <th>Tên</th>
                  <th>Môn thi</th>
                  <th>Kỳ thi</th>
                  <th>Thời gian</th>
                  <th>Điểm</th>
                  <th>Tình trạng</th>
                  <th>Chi tiết</th>
                </tr>
              </thead>
              <tbody id="resultBody">
              </tbody>
            </table>
            <div><button id="exportBtn" onclick="exportToExcel()">Xuất File</button></div>
        </div>  
        <div id="detailModal" class="modal">
          <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Chi tiết câu hỏi và đáp án</h2>
            <div id="questionDetail">
              <!-- Nội dung câu hỏi và đáp án sẽ được thêm vào đây -->
            </div>
          </div>
        </div>
    </body>
    <script src="../javascripts/ketqua.js"></script>
</html>
