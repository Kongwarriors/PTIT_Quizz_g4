/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
  function showResult() {
    var fullName = document.getElementById("fullName").value;
    var resultTable = document.getElementById("resultTable");
    // Kiểm tra xem mã sinh viên đã được nhập hay chưa
    if (!fullName) {
      alert("Vui lòng nhập mã sinh viên.");
      resultTable.style.display = "none";
      exportBtn.style.display = "none";
      return;
    }
    // Tạo đối tượng JSON chứa mã sinh viên
    var data = {
        "fullName": fullName
    };

    // Gửi yêu cầu POST với dữ liệu JSON
    fetch('ketquaServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(results => {
        // Xử lý kết quả từ máy chủ
        if (results.length === 0) {
            alert("Không tìm thấy thông tin cho mã sinh viên: " + fullName);
            resultTable.style.display = "none";
            exportBtn.style.display = "none";
            return;
        }
        
    var rowCount = resultTable.rows.length;

    // Xóa tất cả các hàng từ hàng thứ hai trở đi
    for (var i = rowCount - 1; i > 0; i--) {
        resultTable.deleteRow(i);
    }
    // Hiển thị bảng kết quả và điền thông tin vào bảng
    results.forEach(function(matchedResult) {
      resultTable.style.display = "table";
      exportBtn.style.display = "block";
      var resultBody = document.getElementById("resultBody");
        // Tạo một hàng mới cho bảng kết quả
        var row = resultTable.insertRow();
        var maCell = row.insertCell(0);
        var tenCell = row.insertCell(1);
        var monThiCell = row.insertCell(2);
        var kyThiCell = row.insertCell(3);
        var thoiGianCell = row.insertCell(4);
        var diemCell = row.insertCell(5);
        var tinhTrangCell = row.insertCell(6);
        var chiTietCell = row.insertCell(7);

        maCell.textContent = matchedResult.ma;
        tenCell.textContent = matchedResult.ten;
        monThiCell.textContent = matchedResult.monThi;
        kyThiCell.textContent = matchedResult.kyThi;
        thoiGianCell.textContent = matchedResult.thoiGian;
        diemCell.textContent = matchedResult.diem;
        tinhTrangCell.textContent = matchedResult.tinhTrang;
        chiTietCell.innerHTML = '<button class="chitiet" onclick="viewDetail()">Xem chi tiết</button>';
      
    });
  })
  .catch(error => {
        console.error('Error:', error);
   });
}

  // Hàm hiển thị chi tiết câu hỏi
  function viewDetail() {
    // Gửi yêu cầu POST đến QuestionServlet để lấy danh sách sinh viên
    fetch('questionServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Không cần gửi dữ liệu nào cụ thể về server, chỉ cần yêu cầu danh sách sinh viên
    })
    .then(response => response.json())
    .then(questions => {
        // Xây dựng nội dung cho modal question
        var questionDetail = document.getElementById('questionDetail');
        questionDetail.innerHTML = '';

        questions.forEach(function(question, index) {
            var questionContent = 'Câu hỏi ' + (index + 1) + ': ' + question.question + '<br>';
            question.answers.forEach(function(answer) {
                questionContent += answer + '<br>';
            });
            var studentAnswer = question.studentAnswer;
            var correctAnswer = question.correctAnswer;
            questionDetail.innerHTML += '<br>' + questionContent + '<br>Câu trả lời của thí sinh: ' + studentAnswer + '<br>Đáp án: ' + correctAnswer + '<hr>';
        });

        // Hiển thị modal
        var modal = document.getElementById('detailModal');
        modal.style.display = 'block';
    })
    .catch(error => {
        console.error('Error:', error);
    });

  }

  // Lấy tất cả các nút "Xem chi tiết" trong bảng
  var detailButtons = document.querySelectorAll('.chitiet');

  // Lặp qua từng nút và gán sự kiện click
  detailButtons.forEach(function(button) {
    button.addEventListener('click', viewDetail);
  });

  // Hàm để tắt modal
  function closeModal() {
    var modal = document.getElementById('detailModal');
    modal.style.display = 'none';
  }
 function exportToExcel() {
    // Lấy dữ liệu từ bảng resultTable
    var table = document.getElementById("resultTable");
    var rows = table.rows;
    var jsonData = [];
    // Lặp qua các dòng của bảng và lấy dữ liệu từ các ô
    for (var i = 1; i < rows.length; i++) { // Bắt đầu từ 1 để bỏ qua hàng tiêu đề
        var row = rows[i];
        var rowData = {
            ma: row.cells[0].textContent,
            ten: row.cells[1].textContent,
            monThi: row.cells[2].textContent,
            kyThi: row.cells[3].textContent,
            thoiGian: row.cells[4].textContent,
            diem: row.cells[5].textContent,
            tinhTrang: row.cells[6].textContent
        };
        jsonData.push(rowData);
    }

    // Chuyển đổi mảng JSON thành chuỗi
    var jsonString = JSON.stringify(jsonData);

    // Gửi yêu cầu POST đến Servlet
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "exportToExcelServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.responseType = "blob";

    // Khi yêu cầu được hoàn thành, tạo một URL cho dữ liệu blob và tạo một liên kết để tải về
    xhr.onload = function() {
        if (xhr.status === 200) {
            var blob = xhr.response;
            var url = window.URL.createObjectURL(blob);
            var a = document.createElement("a");
            a.href = url;
            a.download = "ket_qua_thi.xlsx";
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        }
    };

    // Gửi dữ liệu JSON như một tham số
    xhr.send("jsonData=" + encodeURIComponent(jsonString));
}




