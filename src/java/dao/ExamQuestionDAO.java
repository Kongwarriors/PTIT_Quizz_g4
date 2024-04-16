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
import models.ExamQuestion;
import models.Question;

/**
 *
 * @author PC
 */
public class ExamQuestionDAO extends DAO{
    public List<ExamQuestion> getExamQuestionByExamId(int examId){
        List<ExamQuestion> examQuestions = new ArrayList<>();
        try{
            String queryGetQuestions = "SELECT * FROM exam_questions WHERE exam_id = ?";
            PreparedStatement getQuestionsPs = connection.prepareCall(queryGetQuestions);
            getQuestionsPs.setString(1, String.valueOf(examId));
            ResultSet questionsRs = getQuestionsPs.executeQuery();
            while(questionsRs.next()){
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setExamId(examId);
                int questionId = questionsRs.getInt("question_id");
                examQuestion.setQuestionId(questionId);
                examQuestions.add(examQuestion);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return examQuestions;
    }
    
    public boolean insert(ExamQuestion eQ){
        String sql = "INSERT INTO `quizz_system`.`exam_questions`\n"
                + "(`exam_id`,\n"
                + "`question_id`)\n"
                + "VALUES\n"
                + "(?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, eQ.getExamId());
            st.setInt(2, eQ.getQuestionId());
//            st.setInt(2, eQ.getQuestionId());
//            st.setTimestamp(5, );
//            st.setString(6, );
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
