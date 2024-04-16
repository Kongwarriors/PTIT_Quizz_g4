/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.AnswerDAO;
import dao.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Answer;
import models.Question;

/**
 *
 * @author PC
 */
@WebServlet(name="UpdateAnswer", urlPatterns={"/api-update-answer"})
public class UpdateAnswer extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateAnswer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAnswer at " + request.getContextPath () + "</h1>");
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
        String id_raw =json.get("id").getAsString();
    //    log(id_raw);
        String content = json.get("content").getAsString();
        boolean is_correct =json.get("correct").getAsBoolean();
        int id;
        AnswerDAO qdb = new AnswerDAO();
        try {
            id = Integer.parseInt(id_raw);
            if(qdb.getAswerById(id) != null){
                Answer newAnswer = new Answer();
                newAnswer.setId(id);
                newAnswer.setContent(content);
                newAnswer.setCorrect(is_correct);
                qdb.update(newAnswer);
                out.println(newAnswer.toJSON());
                out.println("{\"message\": \"answer updated successfully.\"}");
            }else{
                out.println("{\"message\": \"answer hasn't been updated.\"}");
            }
            
        } catch (NumberFormatException e) {
            out.println("{\"message\": \"ID has wrong format.\"}");
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
