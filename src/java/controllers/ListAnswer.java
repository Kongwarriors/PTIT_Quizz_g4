/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.AnswerDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Answer;
import models.Question;

/**
 *
 * @author PC
 */
@WebServlet(name="ListAnswer", urlPatterns={"/api-list-answer"})
public class ListAnswer extends HttpServlet {
   
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
            out.println("<title>Servlet ListAnswer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAnswer at " + request.getContextPath () + "</h1>");
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String q_id_raw = request.getParameter("id");
        int q_id;
//        log(exam_id_raw);
        out.println(q_id_raw);
        try {
            q_id = Integer.parseInt(q_id_raw);
            AnswerDAO u = new AnswerDAO();
            List<Answer> ds = u.getAll(q_id);
            out.println("[");
            for (int i = 0; i < ds.size(); i++) {
                out.println(ds.get(i).toJSON());
                if (i < ds.size() - 1) {
                    out.println(",");
                }
            }
            out.println("]");
        } catch (NumberFormatException e) {
            out.println("{\"message\": \"Question ID has wrong format or unexisted.\"}");
        }
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
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        String q_id_raw = request.getParameter("id");
//        int q_id;
////        log(exam_id_raw);
//        try {
//            q_id = Integer.parseInt(q_id_raw);
//            AnswerDAO u = new AnswerDAO();
//            List<Answer> ds = u.getAll(q_id);
//            out.println("[");
//            for (int i = 0; i < ds.size(); i++) {
//                out.println(ds.get(i).toJSON());
//                if (i < ds.size() - 1) {
//                    out.println(",");
//                }
//            }
//            out.println("]");
//        } catch (NumberFormatException e) {
//            out.println("{\"message\": \"Question ID has wrong format.\"}");
//        }
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
