SELECT * FROM quizz_system.exams;

INSERT INTO exams (title, description, status, scheduled_time) VALUES
('Java Basics', 'Test your knowledge of basic Java concepts', 'scheduled', '2024-04-10 10:00:00'),
('SQL Fundamentals', 'Test your knowledge of SQL queries', 'free_access', NULL),
('HTML and CSS', 'Test your knowledge of web development basics', 'scheduled', '2024-04-15 09:00:00');

INSERT INTO questions (content, difficulty) VALUES
('What is the output of 3+4 in Java?', 'easy'),
('What is the primary key in a relational database?', 'medium'),
('What does CSS stand for?', 'easy'),
('What is the default port for HTTP?', 'medium');

INSERT INTO answers (question_id, content, is_correct) VALUES
(1, '7', true),
(1, '6', false),
(2, 'Unique identifier for a record', true),
(2, 'Unique identifier for a table', false),
(3, 'Cascading Style Sheets', true),
(3, 'Hyper Text Markup Language', false),
(4, '80', true),
(4, '8080', false);

INSERT INTO exam_questions (exam_id, question_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 3),
(3, 4);
