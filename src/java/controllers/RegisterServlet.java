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
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import models.User;
import org.json.JSONObject;

/**
 *
 * @author PC
 */
public class RegisterServlet extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
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
        String email = jsonObject.getString("email");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String role = jsonObject.getString("role");
        //Get data from form
        log(username);
        log(password);
        log(email);
        //Xu ly dang nhap va tra ket qua
        JSONObject jsonResponse = new JSONObject();
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setRole(role);
        int res = 0;
        UserDAO userDAO = new UserDAO();
        if(u != null){
            res = userDAO.saveNewUser(u);
            jsonResponse.put("res", res);
        }
        switch (res) {
            case 0:
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Email is exist on system");
                break;
            case 1:
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Username is exist on system");
                break;
            case 2:
                jsonResponse.put("success", true);
                jsonResponse.put("messagee", "Register successed");
                break;
            default:
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Register failed");
                break;
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
