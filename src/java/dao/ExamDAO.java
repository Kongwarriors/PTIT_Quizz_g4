/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import models.Exam;

/**
 *
 * @author PC
 */
public class ExamDAO extends DAO{
    public List<Exam> getAllExam(){
        List<Exam> exams = new ArrayList<>();
        String querySelect = "SELECT * FROM Exams";
        try{
            PreparedStatement querySelectPs = connection.prepareCall(querySelect);
            ResultSet rs = querySelectPs.executeQuery();
            while(rs.next()){
                Exam exam = new Exam();
                int id = rs.getInt("id");
                exam.setId(id);
                String title = rs.getString("title");
                exam.setTitle(title);
                String description = rs.getString("description");
                exam.setDescription(description);
                String status = rs.getString("status");
                exam.setStatus(status);
                Timestamp scheduledTime = rs.getTimestamp("scheduled_time");
                if(scheduledTime != null ){
                    exam.setScheduleTime(scheduledTime.toLocalDateTime());
                }
                Timestamp createAt = rs.getTimestamp("created_at");
                if(createAt != null){
                    exam.setCreatedAt(createAt.toLocalDateTime());
                }       
                exams.add(exam);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exams;
    }
}
