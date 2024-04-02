-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: idbproject
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `payment_table_1`
--

DROP TABLE IF EXISTS `payment_table_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_table_1` (
  `id` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `month` varchar(45) NOT NULL,
  `Payment_method` varchar(45) DEFAULT NULL,
  `acount_name` varchar(45) DEFAULT NULL,
  `acount_number` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_table_1`
--

LOCK TABLES `payment_table_1` WRITE;
/*!40000 ALTER TABLE `payment_table_1` DISABLE KEYS */;
INSERT INTO `payment_table_1` VALUES (70,'Md. Moshaidul Islam','January','Bkash','Md. Moshaidul Islam','01911-657844','90000');
/*!40000 ALTER TABLE `payment_table_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_chart_1`
--

DROP TABLE IF EXISTS `result_chart_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_chart_1` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  `round` varchar(45) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `midterm_date` date DEFAULT NULL,
  `midterm_evidence` int DEFAULT NULL,
  `midterm_external` int DEFAULT NULL,
  `monthly_date` date DEFAULT NULL,
  `monthly_evidence` int DEFAULT NULL,
  `monthly_external` int DEFAULT NULL,
  `total_marks` int DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_chart_1`
--

LOCK TABLES `result_chart_1` WRITE;
/*!40000 ALTER TABLE `result_chart_1` DISABLE KEYS */;
INSERT INTO `result_chart_1` VALUES (2,'Sabbir Hasan Chopwdhury','JEE','55','January','2024-01-01',44,33,'2024-01-17',48,43,88,'Pass'),(1,'Md. Limon Islam','JEE','55','January','2024-01-01',22,33,'2024-01-18',21,32,53,'Fail'),(3,'Sabit Hasan','JEE','55','January','2024-01-01',44,45,'2024-01-01',47,48,94,'Pass'),(4,'Tushar','JEE','55','January','2024-01-01',22,33,'2024-01-01',35,33,65,'Pass'),(5,'AL Hasan','JEE','55','January','2024-01-01',22,33,'2024-01-01',35,18,53,'Fail'),(11,'Rajib Al Ahmed','Web Design & Devlopment','55','January','2023-10-03',12,22,'2023-10-19',33,44,68,'Pass'),(12,'Robiul Islam Robi','Web Design & Devlopment','55','January','2023-10-07',22,22,'2023-10-06',22,22,44,'Fail'),(21,'Jahir Sheikh','C #','55','January','2023-10-05',22,33,'2023-10-12',44,22,64,'Pass'),(22,'Jahir Sheikh','C #','55','January','2023-10-05',22,33,'2023-10-12',44,22,64,'Pass'),(23,'Jahir Sheikh','C #','55','January','2023-10-05',22,33,'2023-10-12',44,22,64,'Pass'),(80,'AL Hasan','Networking','55','January','2023-10-03',33,44,'2023-10-05',55,55,103,'Pass'),(11,'Rajib Al Ahmed','Web Design & Devlopment','55','February','2023-10-03',33,44,'2023-10-05',55,55,103,'Pass'),(12,'Robiul Islam Robi','Web Design & Devlopment','55','February','2023-10-03',33,44,'2023-10-05',22,22,51,'Fail'),(13,'Al Mamun','Web Design & Devlopment','55','February','2023-10-03',33,44,'2023-10-05',44,44,86,'Pass'),(80,'AL Hasan','Networking','55','February','2023-10-05',33,44,'2023-10-12',50,43,90,'Pass'),(50,'Mahmud Ul Haque','Database Management','55','February','2023-10-05',33,44,'2023-10-12',50,43,90,'Pass'),(14,'Sohab Pranto','Web Design & Devlopment','55','February','2023-10-05',33,44,'2023-10-12',34,43,77,'Pass'),(12,'Robiul Islam Robi','Web Design & Devlopment','55','March','2023-10-12',12,12,'2023-10-05',33,31,56,'Fail'),(30,'Saiyadatun Tumpa','Graphics Design','55','February','2023-10-05',44,33,'2023-10-05',22,22,51,'Fail'),(3,'Sabit Hasan','JEE','55','February','2023-10-06',33,33,'2023-10-04',33,33,66,'Pass'),(99,'Limon Islam Borno','JEE','55','March','2023-10-05',33,33,'2023-10-05',44,44,84,'Pass');
/*!40000 ALTER TABLE `result_chart_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_table_1`
--

DROP TABLE IF EXISTS `student_table_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_table_1` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `fathers_name` varchar(45) DEFAULT NULL,
  `mothers_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  `round` varchar(45) DEFAULT NULL,
  `center` varchar(45) DEFAULT NULL,
  `batch_id` varchar(45) DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_table_1`
--

LOCK TABLES `student_table_1` WRITE;
/*!40000 ALTER TABLE `student_table_1` DISABLE KEYS */;
INSERT INTO `student_table_1` VALUES (1,'Md. Limon Islam','Male','Abul Kalam AAad','Most. Rehana Begum','limonislamborno@gmail.com','Komorpur, Faridpur','2023-10-03','JEE','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\User\\Documents\\NetBeansProjects\\IsdbManagementSystem\\cross.png'),(2,'Sabbir Hasan Chopwdhury','Male','Ohiduzzan Chowshury','Salma Begum','sabir123bd@gmail.com','Komorpur, Faridpur','1998-10-22','JEE','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\User\\Documents\\NetBeansProjects\\IsdbManagementSystem\\IsdbManagementSystem\\assets\\Trainee\\sabbir.jpg'),(3,'Sabit Hasan','Male','Abdul Kholil','Most. Hasna Begum','sabithasanbd@gmail.com','Faridpur, Pabna','1998-10-15','JEE','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\sabit.jpg'),(4,'Tushar','Male','Khalek Molla','Kohinur Begum','tusharahmed@gmail.com','Issordi, Pabna',NULL,'JEE','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\tushar.jpg'),(5,'AL Hasan','Male','Ahosan Habib','Habiba Begum','alhasanbd56@gmail.com','Vola, Barishal','1999-10-14','JEE','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\hasan.jpg'),(11,'Rajib Al Ahmed','Male','Selim Khan','Jahanara Begum','eajibahmedbd420@gmail.com','Jamalpur','1995-10-13','Web Design & Devlopment','55','New Horizon','Web Design & Devlopment/New Horizon-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\rajib.jpg'),(12,'Robiul Islam Robi','Male','ABdul Jobbar','Saima Begum','robiulislamrobi@gmail.com','Nator, Rajshahi','2000-10-05','Web Design & Devlopment','55','New Horizon','Web Design & Devlopment/New Horizon-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\robiul.jpg'),(13,'Al Mamun','Male','Jolil Sheikh','Kona Begum','almamunjdbc@gmail.com','Rajoir, Madaripur','2003-10-09','Web Design & Devlopment','55','New Horizon','Web Design & Devlopment/New Horizon-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\man.png'),(14,'Sohab Pranto','Male','Rahul Khondokar','Rahima Afroz','sohabprantp55@gmail.com','Boropul, Rajbari','2004-10-07','Web Design & Devlopment','55','New Horizon','Web Design & Devlopment/New Horizon-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\paranto.jpg'),(15,'Tonmoy Ahmed','Male','Sohel Mridha','Mim Sultana','tonmoymahmud@gmail.com','Boropul, Razbari','2000-10-05','Web Design & Devlopment','55','New Horizon','Web Design & Devlopment/New Horizon-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\man.png'),(21,'Jahir Sheikh','Male','Goffar Mondol','Jakia Khatun','jahirsheikhbd@gmail.com','Khabaspur, Faridpur','2010-10-09','C #','55','Panthapath Tsp','C #/Panthapath Tsp-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\man.png'),(22,'Masud Rana','Male','Ahmed Sarif','Tashfia Akter','masudranabd@gmail.com','Rakoir, Madaripur','2000-10-12','C #','55','Panthapath Tsp','C #/Panthapath Tsp-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\masud.jpg'),(23,'Tamim Ahsan','Male','Akbor Rahman','Aklima Begum','tamimahsanbf@gmail.com','Patoir, Jhinaidah','2005-10-07','C #','55','Panthapath Tsp','C #/Panthapath Tsp-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\man.png'),(30,'Saiyadatun Tumpa','Female','Asraful Islam','Meghla Sultana','saiyadatuntumpabd@gmail.com','Satghor, Rajshahi','2013-10-10','Graphics Design','55','Shewrapara Tsp','Graphics Design/Shewrapara Tsp-F/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\tumpa.jpg'),(50,'Mahmud Ul Haque','Male','Abul Bashar','Tithi Sultana','mahmudulhaque@gmail.com','Komorpur, Faridpur','2004-10-07','Database Management','55','Polton Tsp','Database Management/Polton Tsp-M/55/01','C:\\Users\\user\\Documents\\NetBeansProjects\\IsdbManagementSystem\\assets\\Trainee\\man.png'),(80,'AL Hasan','Female','Ahosan Habib','Habiba Begum','alhasanbd56@gmail.com','Vola, Barishal','1999-10-14','Networking','55','CCSL','JEE/CCSL-M/55/01','C:\\Users\\User\\Documents\\NetBeansProjects\\IsdbManagementSystem\\IsdbManagementSystem\\assets\\1.jpg'),(99,'Limon Islam Borno','Male','Abul Kalam Azad','Most Rehana Begum','hjhgfjdshfgjdhk@gmail.com','Komorpur, Faridpur','2023-10-02','JEE','55','--Select One--','JEE-3465','C:\\Users\\User\\Documents\\NetBeansProjects\\IsdbManagementSystem\\IsdbManagementSystem\\assets\\Trainee\\14542468_595660663955665_8174983706924017487_o.jpg');
/*!40000 ALTER TABLE `student_table_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuff_table_1`
--

DROP TABLE IF EXISTS `stuff_table_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stuff_table_1` (
  `id` int NOT NULL,
  `name` varchar(55) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `specialist` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuff_table_1`
--

LOCK TABLES `stuff_table_1` WRITE;
/*!40000 ALTER TABLE `stuff_table_1` DISABLE KEYS */;
INSERT INTO `stuff_table_1` VALUES (70,'Md. Moshaidul Islam','Male','Consultant','JEE','mosahidulsirbd@gmail.com','MSC','Keranigionj','01911-657844','C:\\Users\\User\\Documents\\NetBeansProjects\\IsdbManagementSystem\\IsdbManagementSystem\\assets\\Trainee\\consult.jpg');
/*!40000 ALTER TABLE `stuff_table_1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-18 14:41:27
