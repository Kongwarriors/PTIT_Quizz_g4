document.addEventListener("DOMContentLoaded", function() {
    const applyFiltersButton = document.getElementById("applyFilters");
    const exportExcelButton = document.getElementById("exportExcel");

    applyFiltersButton.addEventListener("click", function() {
        applyFilters();
    });

    exportExcelButton.addEventListener("click", function() {
        exportExcel();
    });

    // Hiển thị danh sách sinh viên khi trang được tải
    updateStatistics("all", "all", "");

    function applyFilters() {
        const examType = document.getElementById("examType").value;
        const accessType = document.getElementById("accessType").value;
        const date = document.getElementById("date").value;

        // Thực hiện logic áp dụng bộ lọc ở đây
        // Sau khi lọc dữ liệu, cập nhật bảng thống kê và biểu đồ
        updateStatistics(examType, accessType, date);
    }

    function updateStatistics(examType, accessType, date) {
        const filterData = {
            examType: examType,
            accessType: accessType,
            date: date
        };

        fetch('StudentServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(filterData)
        })
        .then(response => response.json())
        .then(students => {
            const statsTableBody = document.getElementById("statsBody");

            // Xóa nội dung cũ trong bảng
            statsTableBody.innerHTML = "";

            // Lọc dữ liệu sinh viên dựa trên yêu cầu của bộ lọc
            let filteredStudents = students;

            if (examType !== 'all') {
                filteredStudents = filteredStudents.filter(student => student.examType === examType);
            }

            if (accessType !== 'all') {
                filteredStudents = filteredStudents.filter(student => student.accessType === accessType);
            }

            if (date !== '') {
                filteredStudents = filteredStudents.filter(student => student.date === date);
            }

            // Biến đếm cho số thứ tự sinh viên
            let studentCount = 1;

            // Thêm dữ liệu mới vào bảng
            filteredStudents.forEach(function(student) {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${studentCount}</td>
                    <td>${student.name}</td>
                    <td>${student.studentID}</td>
                    <td>${translateExamType(student.examType)}</td>
                    <td>${translateAccessType(student.accessType)}</td>
                    <td>${student.date}</td>
                    <td>${student.participation}</td>
                    <td>${calculateCompletionRate(student.averageScore)}</td>
                    <td>${student.averageScore}</td>
                `;
                statsTableBody.appendChild(row);

                // Tăng biến đếm
                studentCount++;
            });

            // Sau khi cập nhật bảng thống kê, vẽ biểu đồ
            drawHistogram(filteredStudents);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    }

    // Hàm chuyển đổi loại kỳ thi sang tiếng Việt
    function translateExamType(examType) {
        switch (examType) {
            case "practice":
                return "Luyện tập";
            case "midterm":
                return "Giữa kỳ";
            case "final":
                return "Cuối kỳ";
            default:
                return "Không xác định";
        }
    }

    // Hàm chuyển đổi trạng thái truy cập sang tiếng Việt
    function translateAccessType(accessType) {
        switch (accessType) {
            case "free":
                return "Tự do";
            case "specificTime":
                return "Thời gian cụ thể";
            default:
                return "Không xác định";
        }
    }

    function calculateCompletionRate(averageScore) {
        const maxScore = 10.0;
        const completionRate = (averageScore / maxScore) * 100;
        return completionRate.toFixed(2) + "%";
    }

    function drawHistogram(students) {
        if (!students || students.length === 0) {
            console.log("Không có dữ liệu để vẽ biểu đồ.");
            return;
        }

        const canvas = document.getElementById('myChart');
        const ctx = canvas.getContext('2d');

        // Tạo mảng chứa tần suất của từng điểm số trung bình
        const scoreFrequency = Array.from({ length: 11 }, () => 0);

        // Lấy dữ liệu từ danh sách sinh viên đã được lọc
        students.forEach(student => {
            const averageScore = student.averageScore;
            const score = Math.round(averageScore); // Làm tròn điểm trung bình thành số nguyên
            scoreFrequency[score]++;
        });

        // Kiểm tra xem biểu đồ đã tồn tại chưa
        if (window.myChart instanceof Chart) {
            // Nếu đã tồn tại, hủy biểu đồ cũ trước khi vẽ lại
            window.myChart.destroy();
        }

        // Vẽ biểu đồ histogram
        window.myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: Array.from({ length: 11 }, (_, i) => i), // Nhãn trục x từ 0 đến 10
                datasets: [{
                    label: 'Tần suất',
                    data: scoreFrequency,
                    backgroundColor: 'rgba(54, 162, 235, 0.5)', // Màu nền của cột
                    borderColor: 'rgba(54, 162, 235, 1)', // Màu viền của cột
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Tần suất'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Điểm số trung bình'
                        }
                    }
                }
            }
        });
    }

    // Xuất danh sách thành tệp Excel
    function exportExcel() {
        const table = document.getElementById("statistics");
        const wb = XLSX.utils.table_to_book(table, { sheet: "Sheet JS" });
        XLSX.writeFile(wb, "statistics.xlsx");
    }
});
