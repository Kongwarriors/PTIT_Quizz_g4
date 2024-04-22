/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Question;

/**
 *
 * @author PC
 */
public class QuestionDAO extends DAO{
    public List<Question> getAll(int exam_id){
        List<Question> list = new ArrayList<>();
        String sql = "select questions.* from questions left join exam_questions \n"
                + "on questions.id = exam_questions.question_id\n"
                + "where exam_questions.exam_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, exam_id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Question c = new Question();
                c.setContent(rs.getString("content"));
                c.setDifficulty(rs.getString("difficulty"));
                c.setId(rs.getInt("id"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Question getQuestionById(int questionId){
        Question question = new Question();
        question.setId(questionId);
        AnswerDAO answerDAO = new AnswerDAO();
        try{
            String selectQuestionsQuery = "SELECT * FROM questions WHERE id = ?";
            PreparedStatement selectQuestionsPs = connection.prepareCall(selectQuestionsQuery);
            selectQuestionsPs.setString(1, String.valueOf(questionId));
            ResultSet selectQuestionsRs = selectQuestionsPs.executeQuery();
            while(selectQuestionsRs.next()){
                question.setContent(selectQuestionsRs.getString("content"));
                question.setDifficulty(selectQuestionsRs.getString("difficulty"));
                question.setAnswers(answerDAO.getAnswesByQuestionId(questionId));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return question;
    }
    
    public boolean insert(Question c) {
        String sql = "INSERT INTO `quizz_system`.`questions`\n"
                + "(`content`,\n"
                + "`difficulty`)\n"
                + "VALUES\n"
                + "(?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, c.getContent());
            st.setString(2, c.getDifficulty());
            st.executeUpdate();
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1); // Lấy ID của câu hỏi vừa thêm
                c.setId(id);
//                return id;
            } else {
                throw new SQLException("Không tìm thấy ID được sinh ra cho câu hỏi.");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Question getQuestionById1(int id) {
        String sql = "select * from questions where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Question question = new Question();
                question.setContent(rs.getString("content"));
                question.setDifficulty(rs.getString("difficulty"));
                question.setId(rs.getInt(id));
//                c.setAnswers(answers);
                return question;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM `quizz_system`.`questions`\n"
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

    public void update(Question question) {
        String sql = "UPDATE `quizz_system`.`questions`\n"
                + "SET\n"
                + "`content` = ?,\n"
                + "`difficulty` = ?\n"
                + "WHERE `id` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, question.getContent());
            st.setString(2, question.getDifficulty());
            st.setInt(3, question.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // Xử lý ngoại lệ
        }
    }
    public static void main(String[] args) {
        QuestionDAO qDao = new QuestionDAO();
        List<Question> lst = qDao.getAll(2);
        System.out.println(lst.get(0).toJSON());
    }
}
