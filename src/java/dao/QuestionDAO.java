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
import models.Question;

/**
 *
 * @author PC
 */
public class QuestionDAO extends DAO{
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
}
