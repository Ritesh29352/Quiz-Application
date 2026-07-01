# Quiz Application (Java + MySQL)

A console-based Quiz Management System developed using **Java**, **JDBC**, and **MySQL**. The application allows students to register, log in, take quizzes, and view their results. It also provides an admin panel to manage quiz questions and monitor student performance.

---

## Features

### Student Module
- Student Registration
- Student Login
- Take Quiz
- View Previous Quiz Results
- Logout

### Admin Module
- Secure Admin Login
- Add New Questions
- View All Questions
- Delete Questions
- View All Student Results

---

## Technologies Used

- Java
- JDBC
- MySQL
- Maven
- IntelliJ IDEA

---

## Project Structure

```
QuizApplication/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── dao/
│   │   │       │   ├── UserDAO.java
│   │   │       │   └── QuizDAO.java
│   │   │       │
│   │   │       ├── db/
│   │   │       │   └── DBConnection.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── User.java
│   │   │       │   ├── Question.java
│   │   │       │   └── Result.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   └── QuizService.java
│   │   │       │
│   │   │       └── Main.java
│   │
│   └── resources/
│
├── pom.xml
└── README.md
```

---

## 🗄️ Database Schema

### Users Table

```sql
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);
```

### Questions Table

```sql
CREATE TABLE questions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    question VARCHAR(255),
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    option4 VARCHAR(100),
    correctAnswer INT
);
```

### Results Table

```sql
CREATE TABLE results(
    id INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    score INT,
    totalQuestions INT,
    quizDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(userId) REFERENCES users(id)
);
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/yourusername/QuizApplication.git
```

---

### 2. Open Project

Open the project in IntelliJ IDEA.

---

### 3. Create Database

```sql
CREATE DATABASE quiz_db;
USE quiz_db;
```

Create the required tables using the SQL statements above.

---

### 4. Configure Database

Update `DBConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/quiz_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

### 5. Install Maven Dependencies

Reload Maven Project in IntelliJ IDEA.

---

### 6. Run Application

Run

```
Main.java
```

---

## Default Admin Credentials

```
Username : admin
Password : admin123
```

---

## Application Workflow

```
Main Menu
   │
   ├── Student Registration
   │
   ├── Student Login
   │       │
   │       ├── Take Quiz
   │       ├── View Results
   │       └── Logout
   │
   ├── Admin Login
   │       │
   │       ├── Add Question
   │       ├── View Questions
   │       ├── Delete Question
   │       ├── View Results
   │       └── Logout
   │
   └── Exit
```

---

## Future Improvements

- GUI using JavaFX or Swing
- Timer for each quiz
- Multiple quiz categories
- Difficulty levels
- Randomized questions
- Password encryption
- Leaderboard
- Export results to PDF/Excel

---

## Learning Outcomes

This project demonstrates practical knowledge of:

- Object-Oriented Programming (OOP)
- JDBC
- MySQL Database Operations
- CRUD Operations
- Maven Project Management
- Exception Handling
- Layered Architecture (DAO, Model, Service)

---

## Author

**Ritesh Kumar**

B.Tech Computer Science Student

---
