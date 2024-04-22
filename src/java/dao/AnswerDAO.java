/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Answer;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;

/**
 *
 * @author PC
 */
public class AnswerDAO extends DAO{
    public List<Answer> getAll(int q_id) {
        List<Answer> list = new ArrayList<>();
        String sql = "SELECT * FROM answers where question_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, q_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Answer answer = new Answer();
                answer.setContent(rs.getString("content"));
                answer.setCorrect(rs.getBoolean("is_correct"));
                answer.setQuestion_id(q_id);
                answer.setId(rs.getInt("id"));
                list.add(answer);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Answer> getAnswesByQuestionId(int questionId){
        List<Answer> answers = new ArrayList<>();
        String selectAnswerQuery = "SELECT * FROM answers WHERE question_id = ?";
        try{
            PreparedStatement selectAnswerPs = connection.prepareCall(selectAnswerQuery);
            selectAnswerPs.setString(1, String.valueOf(questionId));
            ResultSet selectAnswersRs = selectAnswerPs.executeQuery();
            while(selectAnswersRs.next()){
                Answer answer = new Answer();
                answer.setId(selectAnswersRs.getInt("id"));
                answer.setContent(selectAnswersRs.getString("content"));
                answer.setCorrect(selectAnswersRs.getBoolean("is_correct"));
                answers.add(answer);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return answers;
    }
    
    public Answer getAnswerById(int id){
        String selectAnswerQuery = "SELECT * FROM answers WHERE id = ?";
        Answer answer = new Answer();
        try{
            PreparedStatement selectAnswerPs = connection.prepareCall(selectAnswerQuery);
            selectAnswerPs.setString(1, String.valueOf(id));
            ResultSet selectAnswersRs = selectAnswerPs.executeQuery();           
            if(selectAnswersRs.next()){                
                answer.setId(selectAnswersRs.getInt("id"));
                answer.setContent(selectAnswersRs.getString("content"));
                answer.setCorrect(selectAnswersRs.getBoolean("is_correct"));             
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return answer;
    }
    
    public void insert(Answer c, int question_id) {
        String sql = "INSERT INTO `quizz_system`.`answers`\n"
                + "(`question_id`,\n"
                + "`content`,\n"
                + "`is_correct`)\n"
                + "VALUES\n"
                + "(?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, question_id);
            st.setString(2, c.getContent());
            st.setBoolean(3, c.getCorrect());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Answer getAswerById(int id) {
        String sql = "select * from answers where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Answer answer = new Answer();
                answer.setContent(rs.getString("content"));
                answer.setCorrect(rs.getBoolean("is_correct"));
                answer.setId(id);
                return answer;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public void delete(int id){
        String sql = "DELETE FROM `quizz_system`.`answers`\n"
                + "WHERE id = ?;";
//        int isDelete = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
//            isDelete = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
//        return isDelete;
    }
    public void update(Answer c){
        String sql = "UPDATE `quizz_system`.`answers`\n"
                + "SET\n"
                + "`content` = ?,\n"
                + "`is_correct` = ?\n"
                + "WHERE `id` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getContent());
            st.setBoolean(2, c.getCorrect());
            st.setInt(3, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }
}
