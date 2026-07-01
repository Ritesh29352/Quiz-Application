package org.example;

import org.example.dao.QuizDAO;
import org.example.dao.UserDAO;
import org.example.model.Question;
import org.example.model.User;
import org.example.service.QuizService;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Admin Login Method
    public static boolean adminLogin(Scanner sc) {

        System.out.println("\n========== ADMIN LOGIN ==========");

        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Admin Login Successful!");
            return true;
        }

        System.out.println("Invalid Admin Credentials!");
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        QuizDAO quizDAO = new QuizDAO();
        QuizService quizService = new QuizService();

        while (true) {

            System.out.println("\n==================================");
            System.out.println("        QUIZ APPLICATION");
            System.out.println("==================================");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    User newUser = new User();

                    System.out.print("Enter Name: ");
                    newUser.setName(sc.nextLine());

                    System.out.print("Enter Email: ");
                    newUser.setEmail(sc.nextLine());

                    System.out.print("Enter Password: ");
                    newUser.setPassword(sc.nextLine());

                    if (userDAO.registerUser(newUser)) {
                        System.out.println("Registration Successful!");
                    } else {
                        System.out.println("Registration Failed!");
                    }

                    break;

                case 2:

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    User user = userDAO.login(email, password);

                    if (user != null) {

                        System.out.println("\nWelcome " + user.getName());

                        boolean logout = false;

                        while (!logout) {

                            System.out.println("\n========== STUDENT MENU ==========");
                            System.out.println("1. Take Quiz");
                            System.out.println("2. View Previous Results");
                            System.out.println("3. Logout");
                            System.out.print("Enter Choice: ");

                            int studentChoice = sc.nextInt();

                            switch (studentChoice) {

                                case 1:
                                    quizService.startQuiz(user.getId());
                                    break;

                                case 2:
                                    quizService.viewResults(user.getId());
                                    break;

                                case 3:
                                    logout = true;
                                    System.out.println("Logged Out Successfully!");
                                    break;

                                default:
                                    System.out.println("Invalid Choice!");
                            }
                        }

                    } else {

                        System.out.println("Invalid Email or Password!");

                    }

                    break;

                case 3:

                    if (adminLogin(sc)) {

                        boolean adminLogout = false;

                        while (!adminLogout) {

                            System.out.println("\n========== ADMIN MENU ==========");
                            System.out.println("1. Add Question");
                            System.out.println("2. View Questions");
                            System.out.println("3. Delete Question");
                            System.out.println("4. View All Results");
                            System.out.println("5. Logout");
                            System.out.print("Enter Choice: ");

                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            switch (adminChoice) {

                                case 1:

                                    Question q = new Question();

                                    System.out.print("Question: ");
                                    q.setQuestion(sc.nextLine());

                                    System.out.print("Option 1: ");
                                    q.setOption1(sc.nextLine());

                                    System.out.print("Option 2: ");
                                    q.setOption2(sc.nextLine());

                                    System.out.print("Option 3: ");
                                    q.setOption3(sc.nextLine());

                                    System.out.print("Option 4: ");
                                    q.setOption4(sc.nextLine());

                                    System.out.print("Correct Answer (1-4): ");
                                    q.setCorrectAnswer(sc.nextInt());

                                    quizDAO.addQuestion(q);

                                    break;

                                case 2:

                                    List<Question> questions = quizDAO.getAllQuestions();

                                    for (Question question : questions) {

                                        System.out.println("----------------------------------");
                                        System.out.println("ID : " + question.getId());
                                        System.out.println(question.getQuestion());
                                        System.out.println("1. " + question.getOption1());
                                        System.out.println("2. " + question.getOption2());
                                        System.out.println("3. " + question.getOption3());
                                        System.out.println("4. " + question.getOption4());
                                        System.out.println("Correct Answer : " + question.getCorrectAnswer());

                                    }

                                    break;

                                case 3:

                                    System.out.print("Enter Question ID: ");
                                    int id = sc.nextInt();

                                    quizDAO.deleteQuestion(id);

                                    break;

                                case 4:

                                    quizDAO.viewAllResults();

                                    break;

                                case 5:

                                    adminLogout = true;
                                    System.out.println("Admin Logged Out!");

                                    break;

                                default:

                                    System.out.println("Invalid Choice!");

                            }

                        }

                    }

                    break;

                case 4:

                    System.out.println("Thank You for Using Quiz Application!");
                    sc.close();
                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Choice!");

            }
        }
    }
}