package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("thongke.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = new ArrayList<>();

        // Thêm sinh viên vào danh sách
        students.add(new Student("Nguyễn Văn A", "SV001", "practice", "free", "2024-02-01", 10, 9));
        students.add(new Student("Trần Thị B", "SV002", "midterm", "specificTime", "2024-02-15", 8, 7));
        students.add(new Student("Phạm Văn C", "SV003", "final", "free", "2024-03-05", 12, 9));
        students.add(new Student("Lê Thị D", "SV004", "practice", "free", "2024-02-10", 15, 8));
        students.add(new Student("Hoàng Văn E", "SV005", "final", "specificTime", "2024-03-20", 9, 7));
        students.add(new Student("Mai Thị F", "SV006", "practice", "free", "2024-02-01", 11, 8));
        students.add(new Student("Đặng Văn G", "SV007", "midterm", "specificTime", "2024-02-15", 7, 6));
        students.add(new Student("Vũ Thị H", "SV008", "final", "free", "2024-03-05", 13, 9));
        students.add(new Student("Hồ Văn I", "SV009", "practice", "specificTime", "2024-02-10", 14, 8));
        students.add(new Student("Trần Văn K", "SV010", "final", "specificTime", "2024-03-20", 10, 9));

        // Chuyển danh sách sinh viên thành JSON
        JSONArray jsonStudents = new JSONArray();
        for (Student student : students) {
            JSONObject jsonStudent = new JSONObject();
            try {
                jsonStudent.put("name", student.getName());
                jsonStudent.put("studentID", student.getStudentID());
                jsonStudent.put("examType", student.getExamType());
                jsonStudent.put("accessType", student.getAccessType());
                jsonStudent.put("date", student.getDate());
                jsonStudent.put("participation", student.getParticipation());
                jsonStudent.put("averageScore", student.getAverageScore());
                jsonStudents.put(jsonStudent);
            } catch (JSONException ex) {
                ex.printStackTrace(); // In ra thông báo lỗi nếu có
            }
        }

        // Gửi JSON response về client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonStudents.toString());
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
