/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Answer;
import models.Exam;
import models.ExamQuestion;
import models.Question;
import models.User;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
public class DoExamPage extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DoExamPage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoExamPage at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLoggedIn");
        if (user == null) {
        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("/login/login.jsp");
            return;
        }
        List<Exam> exams = (List<Exam>)session.getAttribute("exams");
        String examId = request.getParameter("examId");
        if (examId == null || examId.isEmpty()) {
            // Nếu không có tham số "examId", chuyển hướng về trang trước đó hoặc xử lý theo yêu cầu của bạn
            // Ví dụ: response.sendRedirect("/somePage.jsp");
            // Hoặc bạn có thể gửi lại thông báo lỗi
            response.setContentType("text/plain");
            response.getWriter().write("Tham số 'examId' không hợp lệ");
            return;
        }
        log(examId);
        Exam selectedExam = null;
        List<Question> questions = new ArrayList<>();
        List<ExamQuestion> examQuestions = new ArrayList<>();
        for(Exam exam : exams){
            if(exam.getId() == Integer.parseInt(examId)){
                selectedExam = exam;
                session.setAttribute("examSelect", exam);
                examQuestions = exam.getExamQuestions();
                break;
            }
        }
//        if (selectedExam == null) {
//            // Nếu không tìm thấy kỳ thi, gửi lại thông báo lỗi
//            response.setContentType("text/plain");
//            response.getWriter().write("Không tìm thấy kỳ thi có id=" + examId);
//            return;
//        }
        QuestionDAO questionDAO = new QuestionDAO();
        for(ExamQuestion examQuestion : examQuestions){
            questions.add(questionDAO.getQuestionById(examQuestion.getQuestionId()));
        }
        session.setAttribute("questions", questions);
        JSONArray jsonQuestions = new JSONArray();
        for(Question question : questions){
            log(question.getContent());
            JSONObject jsonQuestion = new JSONObject();
            jsonQuestion.put("id", question.getId());
            jsonQuestion.put("content", question.getContent());
            jsonQuestion.put("difficulty", question.getDifficulty());
            JSONArray jsonAnswers = new JSONArray();
            for (Answer answer : question.getAnswers()) {
                JSONObject jsonAnswer = new JSONObject();
                jsonAnswer.put("answerId", answer.getId());
                jsonAnswer.put("content_answer", answer.getContent());
                jsonAnswer.put("iscorrect", answer.getCorrect());
                // Thêm các thông tin khác của câu hỏi nếu cần
                // Ví dụ: jsonQuestion.put("questionContent", examQuestion.getQuestion().getContent());
                jsonAnswers.put(jsonAnswer);
            }
            jsonQuestion.put("answers", jsonAnswers);
            jsonQuestions.put(jsonQuestion);
        }
        JSONObject jsonData = new JSONObject();
        jsonData.put("exam", new JSONObject(selectedExam));
        jsonData.put("questions", jsonQuestions);
        session.setAttribute("startTime", LocalDateTime.now());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonData.toString());
    }


    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
