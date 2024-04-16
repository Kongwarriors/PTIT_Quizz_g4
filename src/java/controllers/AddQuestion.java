/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ExamDAO;
import dao.ExamQuestionDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Exam;
import models.ExamQuestion;
import models.Question;

/**
 *
 * @author PC
 */
@WebServlet(name="AddQuestion", urlPatterns={"/api-add-question"})
public class AddQuestion extends HttpServlet {
   
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
            out.println("<title>Servlet AddQuestion</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuestion at " + request.getContextPath () + "</h1>");
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(request.getReader(), JsonObject.class);

//        String id_raw = json.get("id").getAsString();
        String exam_id_raw = json.get("exam_id").getAsString();
        String content = json.get("content").getAsString();
        String difficulty = json.get("difficulty").getAsString();
//        String created_at = json.get("created_at").getAsString();

//        int id;
        int exam_id;
        QuestionDAO questionDAO = new QuestionDAO();
        ExamQuestionDAO examQuestionDAO  = new ExamQuestionDAO();
        ExamDAO examDAO = new ExamDAO();
        
        try {
//            id = Integer.parseInt(id_raw);
            exam_id = Integer.parseInt(exam_id_raw);
//            Question question = questionDAO.getQuestionById1(id);
            Exam exam = examDAO.getExamById(exam_id);
            if(exam != null){
//                eQ
                Question newQuestion = new Question();
                newQuestion.setContent(content);
                newQuestion.setDifficulty(difficulty);
                boolean  ck2 = questionDAO.insert(newQuestion);
                ExamQuestion eQ = new ExamQuestion();
                eQ.setExamId(exam_id);
                eQ.setQuestionId(newQuestion.getId());
                
                ExamDAO eDao = new ExamDAO();
                List<ExamQuestion> leQ = eDao.getExamById(exam_id).getExamQuestions();
                leQ.add(eQ);
                exam.setExamQuestions(leQ);
//                String s = String.valueOf(newQuestion.getId());
//                out.println(s);
//                log(s);
                
                boolean ck1 = examQuestionDAO.insert(eQ);
                if(ck2 && ck1){
                    out.println("{\"message\": \"Question added successfully.\"}");
//                    out.println(exam.toJSON());
                }
                    
                else
                    out.println("{\"message\": \"Question hasn't been added.\"}");
                response.setStatus(HttpServletResponse.SC_CREATED);
            }else if(exam == null){
                out.println("{\"message\": \"Exam didn't exist.\"}");
            }
        } catch (NumberFormatException e) {
            out.println("{\"message\": \"ID or Exam ID has wrong format.\"}");
        }
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
