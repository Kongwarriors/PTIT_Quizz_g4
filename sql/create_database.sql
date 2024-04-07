DROP DATABASE IF EXISTS quizz_system;
CREATE DATABASE quizz_system;

USE quizz_system;
DROP TABLE IF EXISTS users;
CREATE TABLE users(
	id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS exams;
CREATE TABLE IF NOT EXISTS exams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    status ENUM('free_access', 'scheduled') NOT NULL,
    scheduled_time DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions(
	id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    difficulty ENUM('easy', 'medium', 'hard') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS answers;
CREATE TABLE answers(
	id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    content TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS exam_questions;
CREATE TABLE IF NOT EXISTS exam_questions (
    exam_id INT,
    question_id INT,
    FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE,
    PRIMARY KEY (exam_id, question_id)
);

DROP TABLE IF EXISTS user_exam;
CREATE TABLE IF NOT EXISTS user_exam (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    exam_id INT,
    start_time DATETIME,
    end_time DATETIME,
    correct_answers INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (exam_id) REFERENCES exams(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS user_answers;
CREATE TABLE IF NOT EXISTS user_answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_exam_id INT,
    question_id INT,
    chosen_answer_id INT,
    FOREIGN KEY (user_exam_id) REFERENCES user_exam(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE,
    FOREIGN KEY (chosen_answer_id) REFERENCES answers(id) ON DELETE CASCADE
);
