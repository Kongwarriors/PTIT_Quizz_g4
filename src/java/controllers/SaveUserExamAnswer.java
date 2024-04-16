/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import com.google.gson.Gson;
import dao.AnswerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.List;
import models.Exam;
import models.User;

/**
 *
 * @author PC
 */
public class SaveUserExamAnswer extends HttpServlet {
   
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
            out.println("<title>Servlet SaveUserExamAnswer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveUserExamAnswer at " + request.getContextPath () + "</h1>");
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
        User user = (User) session.getAttribute("userLoggedIn");
        if (user == null) {
        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("/login/login.jsp");
            return;
        }
        // Đọc dữ liệu JSON từ request
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();
        session.setAttribute("endTime", LocalDateTime.now());
        LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
        LocalDateTime endTime = (LocalDateTime) session.getAttribute("endTime");
        User userLoggedIn = (User) session.getAttribute("userLoggedIn");
        Exam examSelected = (Exam) session.getAttribute("exam");
        // Chuyển đổi dữ liệu JSON thành một đối tượng Java sử dụng thư viện Gson
        Gson gson = new Gson();
        String[] checkedAnswerIds = gson.fromJson(jsonBuilder.toString(), String[].class);
        int point = 0;
        // Tạo một danh sách để lưu trữ các câu trả lời tương ứng với các id
        // Duyệt qua mỗi id và trích xuất câu trả lời từ id đó
        for (String answerId : checkedAnswerIds) {
            
        }

        // Thực hiện xử lý dữ liệu (nếu cần)
        // ...

        // Gửi danh sách câu trả lời về client
        String jsonResponse = gson.toJson(answers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
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
