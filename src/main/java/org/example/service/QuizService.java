package org.example.service;

import org.example.dao.QuizDAO;
import org.example.model.Question;

import java.util.List;
import java.util.Scanner;

public class QuizService {

    private QuizDAO quizDAO;

    public QuizService() {
        quizDAO = new QuizDAO();
    }

    public void startQuiz(int userId) {

        Scanner sc = new Scanner(System.in);

        List<Question> questions = quizDAO.getAllQuestions();

        if (questions.isEmpty()) {
            System.out.println("No Questions Found!");
            return;
        }

        int score = 0;

        System.out.println("\n==================================");
        System.out.println("          QUIZ START");
        System.out.println("==================================");

        for (Question q : questions) {

            System.out.println("\nQuestion " + q.getId());
            System.out.println(q.getQuestion());

            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());

            System.out.print("Enter Answer (1-4): ");
            int answer = sc.nextInt();

            if (answer == q.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("\n==================================");
        System.out.println("         QUIZ COMPLETED");
        System.out.println("==================================");
        System.out.println("Correct Answers : " + score);
        System.out.println("Wrong Answers   : " + (questions.size() - score));
        System.out.println("Total Questions : " + questions.size());

        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Percentage      : %.2f%%\n", percentage);

        quizDAO.saveResult(userId, score, questions.size());

        System.out.println("\nResult Saved Successfully!");


    }

    public void viewResults(int userId) {

        quizDAO.viewResults(userId);

    }
}