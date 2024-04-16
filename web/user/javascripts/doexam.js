$(document).ready(function() {
    // Load book data when the page is ready
    loadExamData();
});

function loadExamData() {
    var xmlHttpRequest = new XMLHttpRequest();
    var urlParams = new URLSearchParams(window.location.search);
    var examId = urlParams.get('examId');
    var requestURL = "../../doexampage?examId=" + examId;
    xmlHttpRequest.open("GET", requestURL, true);
    xmlHttpRequest.setRequestHeader("Content-Type", "application/json");
    // Lấy tham số examId từ URL
    xmlHttpRequest.onload = function () {
        if (xmlHttpRequest.readyState === 4 && xmlHttpRequest.status === 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            console.log(data);
            // Hiển thị tiêu đề của kỳ thi
            $('#exam-title').text(data.exam.title);
            console.log(data.exam.title);
            // Xóa tất cả các hàng trong bảng
            $('#questionContainer').empty();
            
            // Thêm dữ liệu câu hỏi vào container
            data.questions.forEach(function(question) {
                var questionHTML = `
                    <div class="question">
                        <p>${question.content}</p>
                        <ul class="answers">`;
                
                question.answers.forEach(function(answer) {
                    questionHTML += `
                        <li>
                            <input type="radio" id="answer-${answer.answerId}" name="answer-${question.id}" value="${answer.answerId}">
                            <label for="answer-${answer.answerId}">${answer.content_answer}</label>
                        </li>`;
                });
                
                questionHTML += `
                        </ul>
                    </div>`;
                
                $('#questionContainer').append(questionHTML);
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

function submitExam(){
//    var answers = []; // Mảng để lưu kết quả người dùng chọn
//    var examId = ''; // Biến để lưu trữ examId
//
//    // Lặp qua các câu hỏi để lấy kết quả người dùng đã chọn
//    $('.question').each(function(index) {
//        var questionId = $(this).attr('data-question-id');
//        var selectedAnswerId = $('input[name="answer-' + questionId + '"]:checked').val();
//        if (selectedAnswerId) {
//            answers.push({ questionId: questionId, selectedAnswerId: selectedAnswerId });
//        }
//    });
//    // Tạo JSON object để gửi lên servlet
//    var data = {
//        answers: answers
//    };
//
//    // Gửi dữ liệu lên servlet
//    var xhr = new XMLHttpRequest();
//    xhr.open('POST', '/path/to/servlet', true);
//    xhr.setRequestHeader('Content-Type', 'application/json');
//    xhr.onreadystatechange = function() {
//        if (xhr.readyState === 4) {
//            if (xhr.status === 200) {
//                // Xử lý phản hồi từ servlet nếu cần
//                console.log('Exam submitted successfully');
//            } else {
//                console.error('Error submitting exam:', xhr.statusText);
//            }
//        }
//    };
//    xhr.send(JSON.stringify(data));
// Khai báo một mảng để lưu trữ các id của câu trả lời đã được chọn
    var checkedAnswerIds = [];

    // Lặp qua tất cả các câu hỏi để lấy các câu trả lời đã được chọn
    $('.question').each(function() {
        // Lấy ID của câu hỏi
        var questionId = $(this).attr('data-question-id');

        // Lặp qua tất cả các input radio trong câu hỏi
        $(this).find('input[type="radio"]').each(function() {
            // Kiểm tra xem input radio này có được chọn không
            if ($(this).prop('checked')) {
                // Nếu được chọn, thêm ID của nó vào mảng checkedAnswerIds
                var answerId = $(this).attr('id').split('-')[1]; // Lấy ID của câu trả lời
                checkedAnswerIds.push(answerId);
            }
        });
    });
    // Kiểm tra kết quả đã thu được
    console.log('ID của các câu trả lời đã được chọn:', checkedAnswerIds);
    var data = {
        checkedAnswerIds: checkedAnswerIds
    };
    // Gửi dữ liệu lên servlet
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/SaveUserExamAnswer', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Xử lý phản hồi từ servlet nếu cần
            console.log('Data submitted successfully');
        }
    };
    xhr.send(JSON.stringify(data));
}
