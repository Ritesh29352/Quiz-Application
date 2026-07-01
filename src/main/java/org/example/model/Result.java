package org.example.model;

import java.sql.Timestamp;

public class Result {

    private int id;
    private int userId;
    private int score;
    private int totalQuestions;
    private Timestamp quizDate;

    public Result() {
    }

    public Result(int id, int userId, int score, int totalQuestions, Timestamp quizDate) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.quizDate = quizDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Timestamp getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(Timestamp quizDate) {
        this.quizDate = quizDate;
    }
}