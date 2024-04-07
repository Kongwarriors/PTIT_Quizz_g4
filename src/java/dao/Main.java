/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import models.Exam;
import models.ExamQuestion;
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
//        }
        //Test getQuestionByExamId()
            ExamQuestionDAO examQuestionDAO = new ExamQuestionDAO();
            List<ExamQuestion> examQuestions = examQuestionDAO.geExamQuestionByExamId(1);
            for(ExamQuestion examQuestion : examQuestions){
                System.out.println(examQuestion.getQuestionId());
            }
    }
}
