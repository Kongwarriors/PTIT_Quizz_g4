/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.websocket.Session;
import java.io.BufferedReader;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE;
import models.User;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {
   
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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
////        processRequest(request, response);
//        request.getRequestDispatcher("../login/login.jsp").forward(request, response);
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
        //Doc du lieu tu API
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String lineString;
        while((lineString = reader.readLine()) != null){
            stringBuilder.append(lineString);
        }
        String jsonString = stringBuilder.toString();
        //Xu ly du lieu tu JSON
        JSONObject jsonObject = new JSONObject(jsonString);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        //Get data from form
        log(username);
        log(password);
        //Xu ly dang nhap va tra ket qua
        JSONObject jsonResponse = new JSONObject();
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        boolean res = false;
        UserDAO userDAO = new UserDAO();
        if(u != null){
            res = userDAO.checkLogin(u);
        }
        if (res) {
            jsonResponse.put("success", true);
            jsonResponse.put("role", u.getRole());
        }
        else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Username or password is incorrect");
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        log(jsonResponse.toString());
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
