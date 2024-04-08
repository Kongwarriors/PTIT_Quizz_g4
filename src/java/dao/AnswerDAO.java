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
}
