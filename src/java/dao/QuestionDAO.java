/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
        try{
            String queryGetQuestions = "SELECT * FROM questions WHERE "
        }
        return question;
    }
}
