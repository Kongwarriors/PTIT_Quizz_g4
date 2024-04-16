/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import models.Answer;
import models.Exam;
import models.ExamQuestion;
import models.Question;
import models.User;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args) {
        //Test save new user
//        DAO dao = new DAO();
//        User u = new User();
//        u.setEmail("admin1@gmail.com");
//        u.setUsername("admin1");
//        u.setPassword("123@");
//        u.setRole("admin");
//        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.saveNewUser(u));
        //Test getAllExam()
//        ExamDAO examDAO = new ExamDAO();
//        List<Exam> exams = examDAO.getAllExam();
//        for(Exam exam : exams){
//            System.out.println(exam.getCreatedAt());
//            List<ExamQuestion> examQuestions = exam.getExamQuestions();
//            for(ExamQuestion examQuestion : examQuestions){
//                System.out.println("Question id : ");
//                System.out.println(examQuestion.getQuestionId());
//            }
//        }
        //Test getQuestionByExamId()
//        ExamQuestionDAO examQuestionDAO = new ExamQuestionDAO();
//        List<ExamQuestion> examQuestions = examQuestionDAO.getExamQuestionByExamId(1);
//        for(ExamQuestion examQuestion : examQuestions){
//            System.out.println(examQuestion.getQuestionId());
//        }
        //Test getAnswerByQuestionId()
//        AnswerDAO answerDAO = new AnswerDAO();
//        List<Answer> answers = answerDAO.getAnswesByQuestionId(2);
//        for(Answer answer : answers){
//            System.out.println(answer.getContent());
//        }
        //Test getQuestionById(){
        QuestionDAO questionDAO = new QuestionDAO();
        Question question = questionDAO.getQuestionById(1);
        System.out.println(question.getContent());
        System.out.println(question.getDifficulty());
        List<Answer> answers = question.getAnswers();
        for(Answer answer : answers){
            System.out.println(answer.getContent());
        }
    }
}
