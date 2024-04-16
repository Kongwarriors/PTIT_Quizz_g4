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
        ExamQuestionDAO examQuestionDAO = new ExamQuestionDAO();
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
                exam.setExamQuestions(examQuestionDAO.getExamQuestionByExamId(id));
                exams.add(exam);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exams;
    }
    
    public List<Exam> filterExam(String statusString){
        List<Exam> exams = new ArrayList<>();
        String querySelect = "SELECT * FROM Exams WHERE status = ?";
        ExamQuestionDAO examQuestionDAO = new ExamQuestionDAO();
        try {
            PreparedStatement selectFilterExamPs = connection.prepareCall(querySelect);
            selectFilterExamPs.setString(1, statusString);
            ResultSet rs = selectFilterExamPs.executeQuery();
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
                exam.setExamQuestions(examQuestionDAO.getExamQuestionByExamId(id));
                exams.add(exam);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return exams;
    }
    
    public boolean insert(Exam c) {
        String sql = "INSERT INTO `quizz_system`.`exams`\n"
//                + "(`id`,\n"
                + "(`title`,\n"
                + "`description`,\n"
                + "`status`,\n"
                + "`scheduled_time`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, c.getId());
            st.setString(1, c.getTitle());
            st.setString(2, c.getDescription());
            st.setString(3, c.getStatus());
            st.setObject(4, c.getScheduleTime());
//            st.setTimestamp(5, );
//            st.setString(6, );
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public Exam getExamById(int id) {
        String sql = "select * from exams where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Exam exam = new Exam();
                exam.setTitle(rs.getString("title"));
                exam.setStatus(rs.getString("status"));
                exam.setScheduleTime(rs.getTimestamp("scheduled_time").toLocalDateTime());
                exam.setDescription(rs.getString("description"));
                exam.setId(rs.getInt("id"));
                return exam;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
//    
    public void delete(int id){
        String sql = "DELETE FROM `quizz_system`.`exams`\n"
                + "WHERE id = ?;";
//        int isDelete = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
//            isDelete = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
//        return isDelete;
    }
    public boolean update(Exam c){
        String sql = "UPDATE `quizz_system`.`exams`\n"
                + "SET\n"
                + "`title` = ?,\n"
                + "`description` = ?,\n"
                + "`status` = ?,\n"
                + "`scheduled_time` = ?\n"
//                + "`created_at` = ?\n"
                + "WHERE `id` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getTitle()); // Chỉ số bắt đầu từ 1
            st.setString(2, c.getDescription());
            st.setString(3, c.getStatus());
            st.setObject(4, c.getScheduleTime());
//            return true;
//            st.setString(5, c.getCreatedAt());
            st.setInt(5, c.getId()); // Chỉ số bắt đầu từ 1
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
