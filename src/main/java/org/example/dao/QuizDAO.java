package org.example.dao;

import org.example.db.DBConnection;
import org.example.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    // Add Question
    public boolean addQuestion(Question question) {

        String sql = "INSERT INTO questions(question, option1, option2, option3, option4, correctAnswer) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getOption1());
            ps.setString(3, question.getOption2());
            ps.setString(4, question.getOption3());
            ps.setString(5, question.getOption4());
            ps.setInt(6, question.getCorrectAnswer());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Question Added Successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    // View All Questions
    public List<Question> getAllQuestions() {

        List<Question> questionList = new ArrayList<>();

        String sql = "SELECT * FROM questions";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Question q = new Question();

                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setCorrectAnswer(rs.getInt("correctAnswer"));

                questionList.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }// Delete Question
    public boolean deleteQuestion(int id) {

        String sql = "DELETE FROM questions WHERE id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Question Deleted Successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }// Save Quiz Result
    public boolean saveResult(int userId, int score, int totalQuestions) {

        String sql = "INSERT INTO results(userId, score, totalQuestions) VALUES (?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, score);
            ps.setInt(3, totalQuestions);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Result Saved Successfully!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    // View Results of a User
    public void viewResults(int userId) {

        String sql = "SELECT * FROM results WHERE userId = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== YOUR RESULTS ==========");

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("Score : " + rs.getInt("score"));
                System.out.println("Total Questions : " + rs.getInt("totalQuestions"));
                System.out.println("Quiz Date : " + rs.getTimestamp("quizDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewAllResults() {

        String sql = """
            SELECT users.name, score, totalQuestions, quizDate
            FROM results
            JOIN users ON users.id = results.userId
            """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                System.out.println("--------------------------");

                System.out.println("Student : " + rs.getString("name"));
                System.out.println("Score : " + rs.getInt("score"));
                System.out.println("Total : " + rs.getInt("totalQuestions"));
                System.out.println("Date : " + rs.getTimestamp("quizDate"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}