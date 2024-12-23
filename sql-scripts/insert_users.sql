
-- Insert test data into the `users` table
INSERT INTO users (activated, address, contact_number, created_at, date_of_birth, email, name, password, profile_picture, role, updated_at) VALUES
(1, '123 Admin St', '1234567890', '2024-01-01 00:00:00', '1980-01-01', 'admin@example.com', 'Admin User', '123456', NULL, 'ADMIN', '2024-01-01 00:00:00'),
(1, '456 Principal Ln', '2345678901', '2024-01-01 00:00:00', '1975-01-01', 'principal@example.com', 'Principal User', '123456', NULL, 'PRINCIPAL', '2024-01-01 00:00:00'),
(1, '789 Teacher Blvd', '3456789012', '2024-01-01 00:00:00', '1985-01-01', 'teacher@example.com', 'Teacher User', '123456', NULL, 'TEACHER', '2024-01-01 00:00:00'),
(1, '321 Parent Dr', '4567890123', '2024-01-01 00:00:00', '1980-01-01', 'parent@example.com', 'Parent User', '123456', NULL, 'PARENT', '2024-01-01 00:00:00'),
(1, '654 Student Way', '5678901234', '2024-01-01 00:00:00', '2005-01-01', 'student@example.com', 'Student User', '123456', NULL, 'STUDENT', '2024-01-01 00:00:00');
