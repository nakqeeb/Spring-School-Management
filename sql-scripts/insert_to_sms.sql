USE `smsdb`;

-- Insert Users
-- INSERT INTO users (activated, address, contact_number, created_at, date_of_birth, email, name, password, profile_picture, role, updated_at) VALUES
-- Admin
-- (1, 'School Headquarters', '+966501234567', '2024-01-01 09:00:00', '1975-05-15', 'admin@schoolsms.com', 'John Davis', '123456', 'admin_pic.jpg', 'ADMIN', '2024-01-01 09:00:00'),

-- Principal
-- (1, 'School Main Building', '+966502345678', '2024-01-01 10:00:00', '1970-03-20', 'principal@schoolsms.com', 'Sarah Johnson', '123456', 'principal_pic.jpg', 'PRINCIPAL', '2024-01-01 10:00:00'),

-- Teachers
-- (1, '123 Teacher Lane', '+966503456789', '2024-01-01 11:00:00', '1985-07-10', 'math.teacher@schoolsms.com', 'Mike Williams', '123456', 'math_teacher_pic.jpg', 'TEACHER', '2024-01-01 11:00:00'),
-- (1, '456 Educator Street', '+966504567890', '2024-01-01 12:00:00', '1980-11-25', 'science.teacher@schoolsms.com', 'Emily Brown', '123456', 'science_teacher_pic.jpg', 'TEACHER', '2024-01-01 12:00:00'),

-- Parents
-- (1, '789 Family Road', '+966505678901', '2024-01-01 13:00:00', '1975-09-05', 'parent1@schoolsms.com', 'Robert Miller', '123456', 'parent1_pic.jpg', 'PARENT', '2024-01-01 13:00:00'),
-- (1, '101 Home Avenue', '+966506789012', '2024-01-01 14:00:00', '1978-12-12', 'parent2@schoolsms.com', 'Lisa Taylor', '123456', 'parent2_pic.jpg', 'PARENT', '2024-01-01 14:00:00'),

-- Students
-- (1, '202 Student Street', '+966507890123', '2024-01-01 15:00:00', '2005-06-15', 'student1@schoolsms.com', 'Alex Thompson', '123456', 'student1_pic.jpg', 'STUDENT', '2024-01-01 15:00:00'),
-- (1, '303 Learning Lane', '+966508901234', '2024-01-01 16:00:00', '2006-08-20', 'student2@schoolsms.com', 'Emma Garcia', '123456', 'student2_pic.jpg', 'STUDENT', '2024-01-01 16:00:00');

-- Insert Teachers
-- INSERT INTO teachers (qualification, user_id) VALUES
-- ('Master of Science in Mathematics', 3),
-- ('Master of Science in Biology', 4);

-- Insert Courses
-- INSERT INTO courses (course_name, credits, description, syllabus_url, teacher_id) VALUES
-- ('Advanced Mathematics', 4, 'Advanced mathematical concepts and problem-solving', 'http://example.com/math_syllabus', 1),
-- ('Biology Fundamentals', 3, 'Introduction to biological sciences', 'http://example.com/biology_syllabus', 2);

-- Insert Teacher Subjects
-- INSERT INTO teacher_subjects (teacher_id, subject) VALUES
-- (1, 'Mathematics'),
-- (2, 'Biology');

-- Insert Classes
-- INSERT INTO classes (class_name, class_room_number, subject_id, teacher_id) VALUES
-- ('Math 101', 'Room A1', 3, 1),
-- ('Biology 101', 'Room B2', 4, 2);

-- Insert Students
-- INSERT INTO students (enrollment_date, class_id, parent_id, user_id) VALUES
-- ('2024-01-15', 7, 5, 7),
-- ('2024-01-15', 8, 6, 8);

-- Insert Attendance
-- INSERT INTO attendance (date, status, class_id, student_id) VALUES
-- ('2024-02-01', 'Present', 7, 3),
-- ('2024-02-01', 'Late', 8, 4);

-- Insert Exams
-- INSERT INTO exams (date, exam_name, total_marks, class_id, subject_id) VALUES
-- ('2024-03-01', 'Midterm Mathematics', 100, 7, 3),
-- ('2024-03-15', 'Midterm Biology', 100, 8, 4);

-- Insert Exam Results
-- INSERT INTO exam_results (grade, marks_obtained, exam_id, student_id) VALUES
-- ('A', 85, 5, 3),
-- ('B+', 78, 6, 4);

-- Insert Fees
-- INSERT INTO fees (amount, due_date, paid_status, payment_date, student_id) VALUES
-- (5000.00, '2024-02-15', 'Paid', '2024-02-10', 3),
-- (5000.00, '2024-02-15', 'Pending', NULL, 4);

-- Insert Timetable
-- INSERT INTO timetable (day_of_week, time_slot, class_id, subject_id) VALUES
-- ('Monday', '09:00-10:30', 7, 3),
-- ('Tuesday', '11:00-12:30', 8, 4);

-- Insert Student Grades
-- INSERT INTO student_grades (student_id, subject_name, grade, grading_date) VALUES
-- (3, 'Mathematics', 'A', '2024-02-15 00:00:00.000000'),
-- (4, 'Biology', 'B+', '2024-02-15 00:00:00.000000');