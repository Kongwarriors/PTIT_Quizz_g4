/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class UserExamAnswer {
    private UserExam userExam;
    private Answer answer;

    public UserExamAnswer() {
    }

    public UserExam getUserExam() {
        return userExam;
    }

    public void setUserExam(UserExam userExam) {
        this.userExam = userExam;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    
}
