-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: smsdb
-- ------------------------------------------------------
-- Server version	9.0.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `status` enum('Absent','Late','Present') DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrx58locko31i5sa3goghxssli` (`class_id`),
  KEY `FK7121lveuhtmu9wa6m90ayd5yg` (`student_id`),
  CONSTRAINT `FK7121lveuhtmu9wa6m90ayd5yg` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKrx58locko31i5sa3goghxssli` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  `class_room_number` varchar(255) DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKabwndmekk4vpn9h27nah2xswu` (`subject_id`),
  KEY `FK8td8h5k21lq8jax2h6oobm9l0` (`teacher_id`),
  CONSTRAINT `FK8td8h5k21lq8jax2h6oobm9l0` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`),
  CONSTRAINT `FKabwndmekk4vpn9h27nah2xswu` FOREIGN KEY (`subject_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `credits` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `syllabus_url` varchar(255) DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK468oyt88pgk2a0cxrvxygadqg` (`teacher_id`),
  CONSTRAINT `FK468oyt88pgk2a0cxrvxygadqg` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_results`
--

DROP TABLE IF EXISTS `exam_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_results` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) DEFAULT NULL,
  `marks_obtained` int DEFAULT NULL,
  `exam_id` bigint DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtf85ht7yquiorwjx2xbdx3fxw` (`exam_id`),
  KEY `FKr7qgl670f47u65kkdm8ex5119` (`student_id`),
  CONSTRAINT `FKr7qgl670f47u65kkdm8ex5119` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKtf85ht7yquiorwjx2xbdx3fxw` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_results`
--

LOCK TABLES `exam_results` WRITE;
/*!40000 ALTER TABLE `exam_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkq9895f263nw6qxibikek0t40` (`class_id`),
  KEY `FKsd59hk5y7ji0jckdtxlsbhrr0` (`subject_id`),
  CONSTRAINT `FKkq9895f263nw6qxibikek0t40` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKsd59hk5y7ji0jckdtxlsbhrr0` FOREIGN KEY (`subject_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fees`
--

DROP TABLE IF EXISTS `fees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `paid_status` varchar(255) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh56p3es1h1lt6ge4cl3by4oko` (`student_id`),
  CONSTRAINT `FKh56p3es1h1lt6ge4cl3by4oko` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fees`
--

LOCK TABLES `fees` WRITE;
/*!40000 ALTER TABLE `fees` DISABLE KEYS */;
/*!40000 ALTER TABLE `fees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation_link`
--

DROP TABLE IF EXISTS `invitation_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitation_link` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `expires_at` datetime(6) DEFAULT NULL,
  `is_used` bit(1) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation_link`
--

LOCK TABLES `invitation_link` WRITE;
/*!40000 ALTER TABLE `invitation_link` DISABLE KEYS */;
INSERT INTO `invitation_link` VALUES (1,'2024-11-26 16:18:30.430123','2024-12-03 16:18:30.430151',_binary '\0','stfdgffg','4e3eac53-5a13-4045-8141-ea0692c0bf33'),(2,'2024-11-26 16:18:44.521452','2024-12-03 16:18:44.521475',_binary '','STUDENT','5a581639-6c89-4656-bb00-6163e1fe0b31'),(3,'2024-11-26 16:28:18.470267','2024-12-03 16:28:18.470280',_binary '','TEACHER','674fd686-a1fb-4090-9f54-0a2fb97dc08c'),(4,'2024-11-26 16:48:40.040795','2024-12-03 16:48:40.040808',_binary '\0','TEACHER','007f89c2-cc98-4055-98fa-7f4f9bccf8df'),(5,'2024-11-26 16:48:58.413144','2024-12-03 16:48:58.413169',_binary '\0','Student','4f2b2bed-4fa4-4ed4-902b-688dab55225b'),(6,'2024-11-26 17:09:40.803948','2024-12-03 17:09:40.803962',_binary '\0','Khaleddd','b6bcd948-eb85-4060-a097-72f6c13806c4'),(7,'2024-11-27 16:33:22.330051','2024-12-04 16:33:22.330065',_binary '\0','testrole','f7ef2aa9-11a9-4fc0-aece-9e50578a2e79'),(8,'2024-11-27 16:41:33.378638','2024-12-04 16:41:33.378653',_binary '\0','Student','6a54e066-42b0-4f58-afe8-99bd181eba34'),(9,'2024-11-27 16:57:31.825240','2024-12-04 16:57:31.825296',_binary '\0','TEACHER','9336f90f-bee6-4131-bf89-fb4065b75e5d'),(10,'2024-11-27 17:01:17.764218','2024-12-04 17:01:17.764252',_binary '\0','TEACHER','10efa2ee-b71b-4083-81da-f44cc298c0b4'),(11,'2024-11-27 18:37:52.093766','2024-12-04 18:37:52.093804',_binary '\0','STUDENT','4e74a96a-bef3-4fef-9ec8-d385bf852ab5'),(12,'2024-12-11 07:41:11.155136','2024-12-18 07:41:11.155148',_binary '','PARENT','f7bf1064-eeae-4b2e-8f2c-d90c50d63bc4');
/*!40000 ALTER TABLE `invitation_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grades`
--

DROP TABLE IF EXISTS `student_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_grades` (
  `student_id` bigint NOT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `grading_date` datetime(6) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  KEY `FKe8t3tau7ti61n06siogcuigkq` (`student_id`),
  CONSTRAINT `FKe8t3tau7ti61n06siogcuigkq` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grades`
--

LOCK TABLES `student_grades` WRITE;
/*!40000 ALTER TABLE `student_grades` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enrollment_date` date DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKg4fwvutq09fjdlb4bb0byp7t` (`user_id`),
  KEY `FKhnslh0rm5bthlble8vjunbnwe` (`class_id`),
  KEY `FKbkxvcp63bguiovrenmt5i42ip` (`parent_id`),
  CONSTRAINT `FKbkxvcp63bguiovrenmt5i42ip` FOREIGN KEY (`parent_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdt1cjx5ve5bdabmuuf3ibrwaq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKhnslh0rm5bthlble8vjunbnwe` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'2024-12-15',NULL,5,6),(2,'2024-12-15',NULL,5,7),(3,'2024-12-15',NULL,5,8);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_subjects`
--

DROP TABLE IF EXISTS `teacher_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_subjects` (
  `teacher_id` bigint NOT NULL,
  `subject` varchar(255) DEFAULT NULL,
  KEY `FK6dcl3ihufp4v0j1fuxlw4ksoj` (`teacher_id`),
  CONSTRAINT `FK6dcl3ihufp4v0j1fuxlw4ksoj` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_subjects`
--

LOCK TABLES `teacher_subjects` WRITE;
/*!40000 ALTER TABLE `teacher_subjects` DISABLE KEYS */;
INSERT INTO `teacher_subjects` VALUES (1,'Mathematics');
/*!40000 ALTER TABLE `teacher_subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `qualification` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKcd1k6xwg9jqtiwx9ybnxpmoh9` (`user_id`),
  CONSTRAINT `FKb8dct7w2j1vl1r2bpstw5isc0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'Master of Science',4);
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetable`
--

DROP TABLE IF EXISTS `timetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetable` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `day_of_week` varchar(255) DEFAULT NULL,
  `time_slot` varchar(255) DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfg5q0pab7g44429oo3bfewhno` (`class_id`),
  KEY `FK8f9cm59dfc15ke8rrpd7g651f` (`subject_id`),
  CONSTRAINT `FK8f9cm59dfc15ke8rrpd7g651f` FOREIGN KEY (`subject_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKfg5q0pab7g44429oo3bfewhno` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetable`
--

LOCK TABLES `timetable` WRITE;
/*!40000 ALTER TABLE `timetable` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activated` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','PARENT','PRINCIPAL','STUDENT','TEACHER') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '','Jeddah','+966555555555','2024-11-25 14:57:25.544000','1990-07-23','admin@email.com','Admin','$2a$10$3M5JxFU6aZd2UqDEJ476I.JAAWYC8FxRoMVvChrpR/t1WK6eZTkda','https://png.pngtree.com/png-vector/20220719/ourmid/pngtree-color-icon---businessman-service-chief-intern-vector-png-image_37961988.png','ADMIN','2024-11-25 14:57:25.544000'),(2,_binary '','Yemen','1234554321','2024-11-26 14:53:18.043000','1990-11-26','test@test.com','Test','$2a$10$SB0QlwFR913kp5HAd6QNGeaLlC0Z6MFRVsGtmItFw8lTpsFbJZ4Qi','https://p7.hiclipart.com/preview/759/979/702/computer-icons-user-profile-avatar-avatar.jpg','STUDENT','2024-11-26 14:53:18.043000'),(3,_binary '\0','string','string','2024-11-26 16:18:57.700000','2024-11-26','dgff@fdgdgfgd.com','string','$2a$10$dbmYlq.oAWKyrM6YR2aWEO.BhbStXLeDLJkrcLa4ULf.XmsNzV/Va','string','STUDENT','2024-11-26 16:18:57.700000'),(4,_binary '\0','string','435364565','2024-11-26 16:37:22.286000','1984-11-26','test1@test.com','Test1','$2a$10$90HHmXb4YgrO5//ND/cOoOJugx2mvWFg8Y2PxfNOjfR2QvAU4RUr2','https://p7.hiclipart.com/preview/759/979/702/computer-icons-user-profile-avatar-avatar.jpg','TEACHER','2024-11-26 16:37:22.286000'),(5,_binary '','Mukalla','6564654645','2024-12-11 07:42:17.462000','1987-12-11','parent@test.com','Parent','$2a$10$GPEuB3isQF7N1lAEdKeQfueR43tC2xnzo0TNxFruoIJid1prytR/6','string','PARENT','2024-12-11 07:42:17.462000'),(6,_binary '\0',NULL,NULL,'2024-12-11 07:43:34.254000',NULL,'std@std.com','STD','$2a$10$jBdAjaMbYPBB2Vpmp17Ktu1etNTOanCLCEpiRYG6gWm6Vzvt5rCiu',NULL,'STUDENT','2024-12-11 07:43:34.254000'),(7,_binary '',NULL,NULL,'2024-12-11 07:46:27.718000',NULL,'std1@std.com','STD1','$2a$10$NWzr45fWKNjZf8sTXXrgNeRnJ9Yuo/9PQ6mahmG9WFFmPFOhxiPS6',NULL,'STUDENT','2024-12-11 07:46:27.718000'),(8,_binary '',NULL,NULL,'2024-12-11 07:49:27.945000',NULL,'std2@std.com','STD1','$2a$10$4L1tDkz3yxGVTlTSJlyu0ug0r6xUjjVuq/P5wVkZKlIr6X6gV.ML.',NULL,'STUDENT','2024-12-11 07:49:27.945000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-17 13:03:54
