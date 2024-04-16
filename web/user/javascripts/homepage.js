$(document).ready(function() {
    // Load book data when the page is ready
    loadExamData();
    $(document).on('click', '.start-exam-btn', function() {
        var examId = $(this).data('exam-id');
        var examStatus = $(this).closest('tr').find('td:nth-child(3)').text(); // Lấy trạng thái của kỳ thi từ cột thứ 3
        if (examStatus === 'scheduled') {
            // Hiển thị thông báo rằng chưa đến giờ thi
            alert('Chưa đến giờ thi');
        } else {
            // Chuyển hướng người dùng đến trang làm bài kiểm tra với id của kỳ thi
            window.location.href = 'doexam.jsp?examId=' + examId;
        }
    });
});

function loadExamData() {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open("GET", "../../userhomepage", true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/json");
    xmlHttpRequest.onload = function () {
        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            console.log(data);
            // Xóa tất cả các hàng trong bảng
            $('#examTable tbody').empty();

            // Thêm dữ liệu kỳ thi mới vào bảng
            data.forEach(function(exam) {
                var scheduleTime = '';
                if (exam.status === "scheduled" && isScheduledExamPassed(exam.scheduleTime)) {
                    exam.scheduleTime = formatDate(exam.scheduleTime);
                    exam.status = "In progress";
                    console.log(scheduleTime);
                }
                $('#examTableBody').append(`
                    <tr>
                        <td>${exam.id}</td>
                        <td>${exam.title}</td>
                        <td>${exam.status}</td>
                        ${exam.status === "scheduled" ? `<td>${exam.scheduleTime}</td>` : '<td></td>'}
                        <td>
                            <button class="btn btn-primary start-exam-btn" data-exam-id="${exam.id}">Start Exam</button>
                        </td>
                    </tr>
                `);
            });
        } else {
            console.error('Error loading exam data:', xmlHttpRequest.statusText);
        }
    };

    xmlHttpRequest.onerror = function () {
        console.error('Network error while loading exam data');
    };

    xmlHttpRequest.send();
}

function isScheduledExamPassed(scheduleTime) {
    var examDateTime = new Date(scheduleTime);
    var currentDateTime = new Date();
    return examDateTime <= currentDateTime;
}

function formatDate(dateTimeString) {
    var dateTime = new Date(dateTimeString);
    var hours = dateTime.getHours();
    var minutes = dateTime.getMinutes();
    var day = dateTime.getDate();
    var month = dateTime.getMonth() + 1;
    var year = dateTime.getFullYear();

    // Thêm số 0 vào trước nếu giờ hoặc phút chỉ có một chữ số
    hours = hours < 10 ? '0' + hours : hours;
    minutes = minutes < 10 ? '0' + minutes : minutes;
    day = day < 10 ? '0' + day : day;
    month = month < 10 ? '0' + month : month;

    return hours + ':' + minutes + ' ' + day + '/' + month + '/' + year;
}