/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author phume
 */
public class Student {
    private String name, studentID, examType, accessType, date;
    private float participation, averageScore;

    public Student(String name, String studentID, String examType, String accessType, String date, float participation, float averageScore) {
        this.name = name;
        this.studentID = studentID;
        this.examType = examType;
        this.accessType = accessType;
        this.date = date;
        this.participation = participation;
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getExamType() {
        return examType;
    }

    public String getAccessType() {
        return accessType;
    }

    public String getDate() {
        return date;
    }

    public float getParticipation() {
        return participation;
    }

    public float getAverageScore() {
        return averageScore;
    }
}
