/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

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

/**
 *
 * @author PC
 */
@WebServlet(name="DeleteQuestion", urlPatterns={"/api-delete-question"})
public class DeleteQuestion extends HttpServlet {
   
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
            out.println("<title>Servlet DeleteQuestion</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteQuestion at " + request.getContextPath () + "</h1>");
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
        String id_raw=request.getParameter("id");
        boolean removed = false;
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
//            log(id_raw);
            QuestionDAO c = new QuestionDAO();
            c.delete(id);
            ExamDAO eDao = new ExamDAO();
            ExamQuestionDAO eQD = new ExamQuestionDAO();
//            ExamQuestion eQ = eQD.getExamQuestionByExamId(id)
            Exam e = eDao.getExamById(id);
            List<ExamQuestion> list = e.getExamQuestions();
//            list.remove();
            e.setExamQuestions(list);
            removed = true;
//            response.sendRedirect("list");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (removed) {
            out.println("{\"message\": \"Question with ID " + id + " has been deleted.\"}");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            out.println("{\"error\": \"Question with ID " + id + " not found.\"}");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
