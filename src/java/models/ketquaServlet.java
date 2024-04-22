/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package models;

import controllers.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.KetQua;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class ketquaServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ketquaServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ketquaServlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("ketqua.jsp").forward(request, response);
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
        StringBuilder stringBuilder = new StringBuilder(); 
        BufferedReader reader = request.getReader(); 
        String lineString; 
        while((lineString = reader.readLine()) != null){ 
            stringBuilder.append(lineString);
        }
         String jsonString = stringBuilder.toString(); 
        //Xu ly du lieu tu JSON 
        JSONObject jsonObject; 
        String fullName = null;
        try {
            jsonObject = new JSONObject(jsonString);
            fullName = jsonObject.getString("fullName"); 
        } catch (JSONException ex) {
            Logger.getLogger(ketquaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<KetQua> results = new ArrayList<>();
        results.add(new KetQua("001", "Nguyễn Văn A", "Toán 2", "Kỳ thi 1", "10/02/2024", "8.5", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("001", "Nguyễn Văn A", "Toán 2", "Kỳ thi 2", "20/02/2024", "4", "Trượt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 1", "10/02/2024", "9", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 2", "15/02/2024", "7.0", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 3", "25/02/2024", "7.0", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 4", "01/03/2024", "5.0", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 5", "15/03/2024", "7.0", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 6", "15/04/2024", "8.0", "Đạt", "Xem chi tiết"));
        results.add(new KetQua("002", "Trần Thị B", "Toán 2", "Kỳ thi 7", "20/04/2024", "9.0", "Đạt", "Xem chi tiết"));
        
        JSONArray jsonResults = new JSONArray();
        for (KetQua result : results) {
            if (result.getMa().equals(fullName)) {
                JSONObject jsonResult = new JSONObject();
                try {
                    jsonResult.put("ma", result.getMa());
                    jsonResult.put("ten", result.getTen());
                    jsonResult.put("monThi", result.getMonThi());
                    jsonResult.put("kyThi", result.getKyThi());
                    jsonResult.put("thoiGian", result.getThoiGian());
                    jsonResult.put("diem", result.getDiem());
                    jsonResult.put("tinhTrang", result.getTinhTrang());
                    jsonResult.put("chiTiet", result.getChiTiet());
                    jsonResults.put(jsonResult);
                } catch (JSONException ex) {
                    Logger.getLogger(ketquaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println(jsonResults); 
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
