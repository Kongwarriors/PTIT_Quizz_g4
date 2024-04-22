/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Question1;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class questionServlet extends HttpServlet {
   
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
            out.println("<title>Servlet questionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet questionServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        List<Question1> questions = new ArrayList<>();

        // Thêm sinh viên vào danh sách
        questions.add(new Question1("1 + 9 =", Arrays.asList("A: 10", "B: 11", "C: 12", "D: 0"), "A", "A"));
        questions.add(new Question1("1 * 9 =", Arrays.asList("A: 10", "B: 11", "C: 12", "D: 9"), "D", "A"));
        questions.add(new Question1("10 * 9 =", Arrays.asList("A: 100", "B: 110", "C: 12", "D: 90"), "D", "D"));
        questions.add(new Question1("10 * 10 =", Arrays.asList("A: 100", "B: 120", "C: 12", "D: 9"), "A", "A"));
        questions.add(new Question1("2 * 9 =", Arrays.asList("A: 10", "B: 18", "C: 11", "D: 9"), "B", "C"));
        // Thêm các sinh viên khác tương tự ở đây

        // Chuyển danh sách sinh viên thành JSON
        // Sử dụng thư viện JSON, ví dụ: Gson
        // Gson gson = new Gson();
        // String jsonStudents = gson.toJson(students);

        // Hoặc chuyển danh sách sinh viên thành JSONArray thủ công nếu không sử dụng thư viện JSON
        JSONArray jsonQuestions = new JSONArray();
        for (Question1 question : questions) {
            JSONObject jsonQuestion = new JSONObject();
            try {
                jsonQuestion.put("question", question.getQuestion());
                jsonQuestion.put("answers", question.getAnswers());
                jsonQuestion.put("correctAnswer", question.getCorrectAnswer());
                jsonQuestion.put("studentAnswer", question.getStudentAnswer());
                jsonQuestions.put(jsonQuestion);
            } catch (JSONException ex) {
                Logger.getLogger(questionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Gửi JSON response về client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonQuestions.toString());
        out.flush();
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
