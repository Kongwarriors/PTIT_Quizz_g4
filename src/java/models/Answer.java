/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.google.gson.Gson;

/**
 *
 * @author PC
 */
public class Answer {
    private int id;
    private int question_id;
    private String content;
    private boolean correct;

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
    
    public int getQuestion_id() {
        return question_id;
    }

    public boolean isCorrect() {
        return correct;
    }
    public Answer() {
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
