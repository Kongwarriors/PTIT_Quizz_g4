/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ADMIN
 */
public class exportToExcelServlet extends HttpServlet {
   
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
            out.println("<title>Servlet exportToExcelServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet exportToExcelServlet at " + request.getContextPath () + "</h1>");
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
        // Lấy dữ liệu JSON từ request
        String jsonString = request.getParameter("jsonData");

        // Chuyển đổi JSON thành mảng dữ liệu
        JSONArray jsonResults = null;
        try {
            jsonResults = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Tạo Workbook Excel và điền dữ liệu
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("KetQua");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Mã");
        row0.createCell(1).setCellValue("Tên");
        row0.createCell(2).setCellValue("Môn Thi");
        row0.createCell(3).setCellValue("Kỳ Thi");
        row0.createCell(4).setCellValue("Thời gian");
        row0.createCell(5).setCellValue("Điểm");
        row0.createCell(6).setCellValue("Tình trạng");
        int rowIndex = 1;
        for (int i = 0; i < jsonResults.length(); i++) {
            JSONObject rowData;
            try {
                rowData = jsonResults.getJSONObject(i);
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(rowData.getString("ma"));
                row.createCell(1).setCellValue(rowData.getString("ten"));
                row.createCell(2).setCellValue(rowData.getString("monThi"));
                row.createCell(3).setCellValue(rowData.getString("kyThi"));
                row.createCell(4).setCellValue(rowData.getString("thoiGian"));
                row.createCell(5).setCellValue(rowData.getString("diem"));
                row.createCell(6).setCellValue(rowData.getString("tinhTrang"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Gửi Workbook dưới dạng file Excel
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ket_qua_thi.xlsx");

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
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
