
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	8.0.21

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


DROP TABLE IF EXISTS `requestcourse`;
DROP TABLE IF EXISTS `result`;
DROP TABLE IF EXISTS `stdansscript`;
DROP TABLE IF EXISTS `studentmark`;
DROP TABLE IF EXISTS `questionans`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `exampaper`;
DROP TABLE IF EXISTS `courses`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `department`;
DROP TABLE IF EXISTS `teacher`;
DROP TABLE IF EXISTS `baseuser`;

--
-- Table structure for table `baseuser`
--

DROP TABLE IF EXISTS `baseuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baseuser` (
  `nid` varchar(20) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `fatherName` varchar(50) NOT NULL,
  `motherName` varchar(50) NOT NULL,
  `gender` tinyint NOT NULL,
  `contactNo` varchar(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dob` date NOT NULL,
  `address` varchar(200) NOT NULL,
  `adminApproval` tinyint DEFAULT '0',
  `role` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`nid`),
  CONSTRAINT `baseuser_chk_1` CHECK (((`role` = _utf8mb4'ADMIN') or (`role` = _utf8mb4'TEACHER') or (`role` = _utf8mb4'STUDENT'))),
  CONSTRAINT `baseuser_chk_2` CHECK (((`adminApproval` = 0) or (`adminApproval` = 1))),
  CONSTRAINT `baseuser_chk_3` CHECK ((`email` like _utf8mb4'%@%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baseuser`
--

LOCK TABLES `baseuser` WRITE;
/*!40000 ALTER TABLE `baseuser` DISABLE KEYS */;
INSERT INTO `baseuser` VALUES ('15454625','Tawhid mahamud','Abdul Bari','Halima khatun',1,'01521254682','pritom@gmail.com','1998-02-05','phulpur',0,'STUDENT','1234'),('15678625','Tawhid mahamud','Abdul Bari','Halima khatun',1,'01521254682','pritom@gmail.com','1998-02-05','phulpur',0,'STUDENT','1234'),('1567862511','Tawhid mahamud','Abdul Bari','Halima khatun',1,'01521254682','pritom@gmail.com','1998-02-05','phulpur',0,'TEACHER','1234'),('6118194022','Liza','Rony rahman','Rina Akter',2,'1983848293','liza@yahoo.com','1993-01-09','New elephant road, (2nd floor), katabon more, 1205',0,'STUDENT','7291'),('6118194051','Rohan','Kobir Hossain','Rohima',1,'19857629','rohan@yahoo.com','1997-10-12','5/15 D.I.T.Market (Ist. Floor ), Dhaka',1,'STUDENT','2345'),('6118194052','Riyad','Rohomot','Saleha',1,'19957628','riyad@yahoo.com','1995-11-01','145, Motijheel c/a (3rd floor), Motijheel, 1000',1,'STUDENT','2346'),('6118194053','Asad','Akbor','Afroza',1,'17057167','asad@yahoo.com','1998-10-12','28 Dilkusha C/a (8th Floor), Dhaka',1,'STUDENT','2347'),('6118194054','Saidul','Mobarok','Nahar',1,'18125766','saidul@yahoo.com','1998-10-11','1/36, south mugdapara bank colony, (1st floor), 1214',1,'STUDENT','2194'),('6118194055','Emon','Abdul Quddus','Nasima',1,'18332833','emon@yahoo.com','1994-11-01','Road #10, baridhara, 1212',1,'STUDENT','2344'),('6118194056','Rashed','Abu Raihan','Taslim',2,'16117278','rashed@yahoo.com','1993-01-09','119, New Elephant Road, Dhaka',1,'STUDENT','5473'),('6118194057','Laila','ABu Naser','Ayesha',2,'13728190','laila@yahoo.com','1992-09-18','a-1/16, sonali bank colony, motijheel, 1000',1,'STUDENT','8237'),('6118194058','Huraira','Abdus Salam','shamima',2,'18372818','huraira@yahoo.com','2000-03-28','bhai Bhai Plaza,(9th Floor),khilkhet, 1229',1,'STUDENT','8737'),('6118194059','Sourov','Nagesh','Nisha',1,'19113928','sourov@yahoo.com','2001-12-28','House # 134, Lane # 1, Baridhara DOHS',1,'STUDENT','7217'),('6118194060','Hizbul','Saiful Islam','Kaniz Kobita',1,'17917391','hizbul@yahoo.com','2003-02-10','128,motijheel C/a, Dhaka',1,'STUDENT','6372'),('6118194061','Eng. Nafis Kamal','Asif Iqbal','Nasrin Jahan',1,'1711909293','nafis@yahoo@.com','1987-12-31','14-15 Motijheel C/a, Ispahani Building, 5th Flr',1,'TEACHER','7231'),('6118194062','Abu Siddik','Abu Zafor','Amena',1,'1891839322','siddik@yahoo@.com','1979-01-23','Puraton Kasba, Jessore',1,'TEACHER','6378'),('6118194063','Afroza Jahan','Raiyan','Shirina Akter',2,'1633827194','afroza@yahoo@.com','1990-11-13','patenga, chittagong, chittagong, 4204',1,'TEACHER','6238'),('6118194064','Mukhesh','Raton Das','Sonali',1,'1478920347','mukhesh@yahoo@.com','1985-09-05','29/h, Sonatongar, Hazaribagh',1,'TEACHER','7392'),('6118194065','Mr. Areng','Jisan Areng','Moleng Areng',1,'1873992294','areng@yahoo@.com','1991-10-25','(ground floor), west nakhalpara, tejgaon, 1215',1,'TEACHER','7238'),('6118194066','Helal Uddin','Faser Uddin','Salma Akter',1,'1622812993','helal@yahoo@.com','1989-12-19','61, suklal das lane, sutrapur, 1100',1,'Teacher','7382'),('6118194067','Kaniz Fatima','Fokir Shah','Airin Akter',2,'192684723','kaniz@yahoo@.com','1994-09-12','Soydana (hajir Pukur), Board Bazar, Gazipur',1,'TEACHER','8394'),('6118194068','Ashraful Hoque','Moinul Islam','Dilsana',1,'1712232344','ashraf@yahoo@.com','1985-03-22','Block-K, Rupnagar, Sialbari Scetion # 2, Mirpu',1,'TEACHER','7829'),('6118194069','Roksana Akter','Selim AKter','Maimuna Akter',2,'197382837','roksana@yahoo@.com','1990-04-29','1/1-e, (1st Fr.) Block-c, Lalmatia',1,'ADMIN','7382'),('6118194070','Abdul Mueez','Ahsan Ali','Poly Akter',1,'192793749','mueez@yahoo@.com','1992-07-03','Suite#704, 55-A, Purana Pltan, P.O. Box: 1000',1,'ADMIN','8293'),('6118194076','Rohima Akter','Faisal','Forida',2,'178229183','rohima@yahoo.com','1997-06-17','House#8, Road # 2 Dhanmondi R/a, Dhaka',0,'STUDENT','3727'),('6118194084','Romiz','Alim','Shelina',1,'1782910846','romiz@yahoo.com','1996-01-09','shewrapara, rokeya sharani, mirpur, 1216',0,'STUDENT','7383'),('6118194090','Nilima Akter','Waris Karim','Falguni Akter',2,'1673947294','nilima@yahoo@.com','1993-02-16','Bikrampur, Bhaban, Tengra',0,'TEACHER','9303'),('Not Assigned','Not Assigned','Not Assigned','Not Assigned',5,'Not Assigned','Not @ Assigned','2222-12-12','Not Assigned',1,'TEACHER','123123');
/*!40000 ALTER TABLE `baseuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courseCode` varchar(10) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  `courseName` varchar(50) NOT NULL,
  `teacherId` varchar(20) DEFAULT 'Not assigned',
  `courseCurrSession` int DEFAULT NULL,
  PRIMARY KEY (`courseCode`,`deptId`),
  KEY `deptId` (`deptId`),
  KEY `teacherId` (`teacherId`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`deptId`) REFERENCES `department` (`deptId`),
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('BE212','2022','biochemistry ','Not assigned',-9999),('CSE315','2019','Computer Architceture','6118194062',NULL),('CSE341','2019','Theory of computing','6118194068',NULL),('CSE344','2019','Mathmatical analysis for computer science','6118194069',NULL),('CSE364','2019','Microprocessor design','6118194069',NULL),('CSE382','2019','Image processing','6118194069',NULL),('CSE441','2019','Database design','6118194069',NULL),('CSE442','2019','Database design lab','6118194069',NULL),('EEE255','2020','Electrical Circuit and design','6118194063',NULL),('EEE281','2020','Electical circuit','6118194066',NULL),('EEE285','2020','Servo Motor and its applyance','6118194066',NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `deptId` varchar(10) NOT NULL,
  `deptName` varchar(60) NOT NULL,
  `currentBatch` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('2019','CSE',5),('2020','EEE',5),('2022','Biocamical Engineearing',5),('3019','ME',5),('3021','CAE',5),('4011','CE',5),('4012','SWE',5);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exampaper`
--

DROP TABLE IF EXISTS `exampaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exampaper` (
  `examId` int NOT NULL,
  `courseCode` varchar(10) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  `teacherId` varchar(20) NOT NULL,
  `percentageValue` decimal(5,2) NOT NULL,
  `startingDateTime` datetime NOT NULL,
  `endingDateTime` datetime NOT NULL,
  `courseSession` int NOT NULL,
  `total` decimal(5,2) NOT NULL,
  `published` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`examId`),
  KEY `courseCode` (`courseCode`,`deptId`),
  KEY `teacherId` (`teacherId`),
  CONSTRAINT `exampaper_ibfk_1` FOREIGN KEY (`courseCode`, `deptId`) REFERENCES `courses` (`courseCode`, `deptId`),
  CONSTRAINT `exampaper_ibfk_2` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exampaper`
--

LOCK TABLES `exampaper` WRITE;
/*!40000 ALTER TABLE `exampaper` DISABLE KEYS */;
INSERT INTO `exampaper` VALUES (1,'CSE315','2019','6118194062',0.05,'2022-02-26 10:30:00','2022-02-26 12:30:00',2,20.00,0),(2,'EEE255','2020','6118194063',0.03,'2022-02-21 11:00:00','2022-02-21 13:00:00',1,25.00,0),(3,'CSE341','2019','6118194068',0.04,'2022-02-27 14:30:00','2022-02-27 16:30:00',3,30.00,0),(4,'CSE344','2019','6118194069',0.06,'2022-03-10 09:00:00','2022-03-10 11:00:00',5,15.00,0),(5,'EEE281','2020','6118194066',0.09,'2022-05-11 08:00:00','2022-05-11 10:00:00',4,40.00,0),(6,'EEE285','2020','6118194066',0.05,'2022-05-11 11:00:00','2022-05-11 13:00:00',2,40.00,0),(7,'CSE382','2019','6118194069',0.03,'2022-05-13 16:00:00','2022-05-11 18:00:00',1,40.00,0),(8,'CSE364','2019','6118194069',0.02,'2022-05-14 10:00:00','2022-05-14 12:00:00',3,40.00,0),(9,'CSE441','2019','6118194069',0.03,'2022-05-14 13:00:00','2022-05-14 15:00:00',4,40.00,0),(10,'EEE285','2020','6118194069',0.04,'2022-05-15 10:30:00','2022-05-15 12:30:00',5,40.00,0),(11,'CSE315','2019','6118194062',0.50,'2022-07-18 18:22:30','2022-07-18 20:22:30',1,5.00,0);
/*!40000 ALTER TABLE `exampaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `examId` int NOT NULL,
  `questionNo` int NOT NULL,
  `question` varchar(200) NOT NULL,
  `questionImage` blob,
  `mark` decimal(5,2) NOT NULL,
  PRIMARY KEY (`examId`,`questionNo`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`examId`) REFERENCES `exampaper` (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,'which is assemble language keyword',NULL,2.00),(1,2,'Name of the screen that recognizes touch input is',NULL,2.00),(1,3,'Identify the device through which data and instructions are entered into a computer',NULL,4.00),(1,4,'Eight Bits make up a',NULL,4.00),(1,5,'Which one of these also known as read/write memory ?',NULL,6.00),(2,1,'The printed output from a computer is called ?',NULL,3.00),(2,2,'WWW stands for ?',NULL,2.00),(2,3,'Which of the following are components of Central Processing Unit (CPU) ?',NULL,3.00),(2,4,'What is full form CMOS ?',NULL,4.00),(2,5,'Second generation of computers consist of which of following ?',NULL,6.00),(3,1,'The maximum length of the char columns is',NULL,2.00),(3,2,'Which data type is more suitable for storing ?',NULL,3.00),(3,3,'Which Clause is used to select a particular row from the set of row in an existing table?',NULL,3.00),(3,4,'INSERT is same as UPDATE ?',NULL,4.00),(3,5,'Which command is used for the table definition in Mysql?',NULL,4.00),(4,1,'What is the meaning of Temporary Tables in Mysql?',NULL,1.00),(4,2,'What will be the output of the following statement',NULL,2.00),(4,3,'COUNT keyword belongs to which categories in Mysql?',NULL,4.00),(4,4,'Which clause is used with an aggregate functions?',NULL,4.00),(4,5,'Fill the blanks with suitable options? BETWEEN ______ AND ______',NULL,3.00),(5,1,'The pop() method of the array does which of the following task ?',NULL,2.00),(5,2,'What is the fundamental rule of lexical scoping?',NULL,4.00),(5,3,'What will happen if a return statement does not have an associated expression?',NULL,5.00),(5,4,'JavaScript Code can be called by using',NULL,2.00),(5,5,'What is the purpose of the parameter $name ',NULL,1.00),(11,1,'what is the pring statement in c++',NULL,2.00),(11,2,'How maney operand are there',NULL,3.00);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionans`
--

DROP TABLE IF EXISTS `questionans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionans` (
  `examId` int NOT NULL,
  `questionNo` int NOT NULL,
  `optionNo` tinyint NOT NULL,
  `optionValue` varchar(200) NOT NULL,
  `ansStatus` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`examId`,`questionNo`,`optionNo`),
  CONSTRAINT `questionans_ibfk_1` FOREIGN KEY (`examId`, `questionNo`) REFERENCES `question` (`examId`, `questionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionans`
--

LOCK TABLES `questionans` WRITE;
/*!40000 ALTER TABLE `questionans` DISABLE KEYS */;
INSERT INTO `questionans` VALUES (1,1,1,'ADD',1),(1,1,2,'System.out',0),(1,1,3,'AVEG',0),(1,1,4,'MID',0),(1,2,1,'Recog screen',0),(1,2,2,'Point Screen',0),(1,2,3,'Touch Screen',1),(1,2,4,'Android Screen',0),(1,3,1,'Software',0),(1,3,2,'Output divice',0),(1,3,3,'Input device',1),(1,3,4,'Memory',0),(1,4,1,'byte',1),(1,4,2,'megabyte',0),(1,4,3,'kilobyte',0),(1,4,4,'None',0),(1,5,1,'ROM',0),(1,5,2,'RAM',1),(1,5,3,'DVD',0),(1,5,4,'Hard Disk',0),(2,1,1,'Copy',0),(2,1,2,'Soft copy',0),(2,1,3,'Hard copy',1),(2,1,4,'Paper',0),(2,2,1,'World Whole Web',0),(2,2,2,'Wide World Web',0),(2,2,3,'Web World Wide',0),(2,2,4,'World Wide Web',1),(2,3,1,'Arithmetic logic unit, Mouse',0),(2,3,2,'Arithmetic logic unit, Control unit',1),(2,3,3,'Arithmetic logic unit, Integrated Circuits',0),(2,3,4,'Control Unit, Monitor',0),(2,4,1,'Content Metal Oxide Semiconductor',0),(2,4,2,'Complementary Metal Oxide Semiconductor',1),(2,4,3,'Complementary Metal Oxygen Semiconductor',0),(2,4,4,'Complementary Metal Oscilator Semiconductor',0),(2,5,1,'Vaccum Tubes',0),(2,5,2,'Diodes',0),(2,5,3,'VLSI Microprocessor',0),(2,5,4,'Transistors',1),(3,1,1,'255 bytes',1),(3,1,2,'65, 535 bytes',0),(3,1,3,'256 bytes',0),(3,1,4,'None of the mentioned',0),(3,2,1,'Varchar',0),(3,2,2,'Longtext',1),(3,2,3,'Mediumtext',0),(3,2,4,'Either a or b',0),(3,3,1,'WHERE',1),(3,3,2,'FROM',0),(3,3,3,'ALTER',0),(3,3,4,'None of the mentioned',0),(3,4,1,'NO',1),(3,4,2,'YES',0),(3,4,3,'Maybe',0),(3,4,4,'None of the mentioned',0),(3,5,1,'DESC table_name;',1),(3,5,2,'DESC table_name',0),(3,5,3,'DESC',0),(3,5,4,'None of the mentioned',0),(4,1,1,'Rows returned by sub query',1),(4,1,2,'Permanent tables',0),(4,1,3,'Virtual tables',0),(4,1,4,'All of the mentioned',0),(4,2,1,'false',0),(4,2,2,'Null',1),(4,2,3,'Depend',0),(4,2,4,'None of the mentioned',0),(4,3,1,'All of the mentioned',0),(4,3,2,'Operators',0),(4,3,3,'Clauses',0),(4,3,4,'Aggregate functions',1),(4,4,1,'SELECT',0),(4,4,2,'GROUP BY',1),(4,4,3,'WHERE',0),(4,4,4,'Both a and c',0),(4,5,1,'Upper and lower limit;',0),(4,5,2,'Lower and upper limit',1),(4,5,3,'Both a and b',0),(4,5,4,'None of the mentioned',0),(5,1,1,'decrements the total length by 1',1),(5,1,2,'increments the total length by 1',0),(5,1,3,'prints the first element but no effect on the length',0),(5,1,4,'None of the above',0),(5,2,1,'Functions are declared in the scope',0),(5,2,2,'Functions are executed using scope chain',1),(5,2,3,'prints the first element but no effect on the length',0),(5,2,4,'None of the above',0),(5,3,1,'It returns the value 0',0),(5,3,2,'It will throw an exception',0),(5,3,3,'It returns the undefined value',1),(5,3,4,'None of the mentioned',0),(5,4,1,'RMI',0),(5,4,2,'Triggering Event',0),(5,4,3,' Preprocessor',0),(5,4,4,'Function/Method',1),(5,5,1,'Document Name',0),(5,5,2,'Input Name',1),(5,5,3,'Output name',0),(5,5,4,'ID',0),(11,1,1,'wrdc',0),(11,1,2,'cdrc',0),(11,1,3,'cout',1),(11,1,4,'cin',0),(11,2,1,'2',0),(11,2,2,'4',0),(11,2,3,'jhkljh',1);
/*!40000 ALTER TABLE `questionans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestcourse`
--

DROP TABLE IF EXISTS `requestcourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requestcourse` (
  `stdId` varchar(20) NOT NULL,
  `courseCode` varchar(10) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  PRIMARY KEY (`stdId`,`courseCode`,`deptId`),
  KEY `courseCode` (`courseCode`,`deptId`),
  CONSTRAINT `requestcourse_ibfk_1` FOREIGN KEY (`stdId`) REFERENCES `student` (`stdId`),
  CONSTRAINT `requestcourse_ibfk_2` FOREIGN KEY (`courseCode`, `deptId`) REFERENCES `courses` (`courseCode`, `deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestcourse`
--

LOCK TABLES `requestcourse` WRITE;
/*!40000 ALTER TABLE `requestcourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestcourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `stdId` varchar(20) NOT NULL,
  `courseCode` varchar(10) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  `courseSession` int NOT NULL,
  `cgpa` decimal(3,2) DEFAULT '-1.00',
  `grade` varchar(3) DEFAULT 'X',
  `semester` int NOT NULL,
  `previousSemCrs` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`stdId`,`courseCode`,`deptId`),
  KEY `courseCode` (`courseCode`,`deptId`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`stdId`) REFERENCES `student` (`stdId`),
  CONSTRAINT `result_ibfk_2` FOREIGN KEY (`courseCode`, `deptId`) REFERENCES `courses` (`courseCode`, `deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES ('6118194051','CSE315','2019',1,3.95,'A',2,0),('6118194052','EEE255','2020',1,3.95,'A',2,0),('6118194053','CSE341','2019',1,3.00,'B',3,0),('6118194054','CSE344','2019',2,2.50,'C+',3,0),('6118194055','EEE281','2020',3,4.00,'A+',4,0);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stdansscript`
--

DROP TABLE IF EXISTS `stdansscript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stdansscript` (
  `stdId` varchar(20) NOT NULL,
  `examId` int NOT NULL,
  `questionNo` int NOT NULL,
  `optionNo` tinyint NOT NULL DEFAULT '0',
  `ansStatus` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`stdId`,`examId`,`questionNo`,`optionNo`),
  KEY `examId` (`examId`,`questionNo`),
  CONSTRAINT `stdansscript_ibfk_1` FOREIGN KEY (`stdId`) REFERENCES `student` (`stdId`),
  CONSTRAINT `stdansscript_ibfk_2` FOREIGN KEY (`examId`, `questionNo`) REFERENCES `question` (`examId`, `questionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stdansscript`
--

LOCK TABLES `stdansscript` WRITE;
/*!40000 ALTER TABLE `stdansscript` DISABLE KEYS */;
INSERT INTO `stdansscript` VALUES ('6118194051',1,1,3,0),('6118194051',1,2,2,0),('6118194051',1,3,3,0),('6118194051',1,4,1,0),('6118194052',2,1,1,0),('6118194052',2,2,4,0),('6118194052',2,3,2,0),('6118194052',2,4,2,0),('6118194053',3,1,4,0),('6118194053',3,2,2,0),('6118194053',3,3,1,0),('6118194053',3,4,1,0),('6118194054',4,1,1,0),('6118194054',4,2,2,0),('6118194054',4,3,2,0),('6118194054',4,4,2,0),('6118194055',5,1,1,0),('6118194055',5,2,2,0),('6118194055',5,3,3,0),('6118194055',5,4,4,0);
/*!40000 ALTER TABLE `stdansscript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `stdId` varchar(20) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  `batch` int NOT NULL,
  `semester` int NOT NULL,
  PRIMARY KEY (`stdId`),
  KEY `deptId` (`deptId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`stdId`) REFERENCES `baseuser` (`nid`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`deptId`) REFERENCES `department` (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('15454625','2019',1,5),('15678625','2019',1,5),('6118194051','2019',2,5),('6118194052','2020',2,6),('6118194053','2019',3,7),('6118194054','2019',3,7),('6118194055','2020',4,8),('6118194056','4012',4,8),('6118194057','2019',5,9),('6118194058','2019',5,9),('6118194059','2020',6,10),('6118194060','2020',6,10);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentmark`
--

DROP TABLE IF EXISTS `studentmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentmark` (
  `stdId` varchar(20) NOT NULL,
  `courseCode` varchar(10) NOT NULL,
  `deptId` varchar(10) NOT NULL,
  `examId` int NOT NULL,
  `gotTotalMarks` decimal(5,2) DEFAULT '0.00',
  `review` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`stdId`,`courseCode`,`deptId`,`examId`),
  KEY `courseCode` (`courseCode`,`deptId`),
  KEY `examId` (`examId`),
  CONSTRAINT `studentmark_ibfk_1` FOREIGN KEY (`stdId`) REFERENCES `student` (`stdId`),
  CONSTRAINT `studentmark_ibfk_2` FOREIGN KEY (`courseCode`, `deptId`) REFERENCES `courses` (`courseCode`, `deptId`),
  CONSTRAINT `studentmark_ibfk_3` FOREIGN KEY (`examId`) REFERENCES `exampaper` (`examId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentmark`
--

LOCK TABLES `studentmark` WRITE;
/*!40000 ALTER TABLE `studentmark` DISABLE KEYS */;
INSERT INTO `studentmark` VALUES ('6118194051','CSE315','2019',1,10.00,0),('6118194052','EEE255','2020',2,15.00,0),('6118194053','CSE341','2019',3,15.00,0),('6118194054','CSE344','2019',4,15.00,0),('6118194055','EEE281','2020',5,20.00,0);
/*!40000 ALTER TABLE `studentmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacherId` varchar(20) NOT NULL,
  `eduQualification` varchar(200) DEFAULT 'Did not give yet',
  `expertise` varchar(50) NOT NULL,
  PRIMARY KEY (`teacherId`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`teacherId`) REFERENCES `baseuser` (`nid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('1567862511','Bsc in CSE','Machine learning'),('6118194061','MSC in EEE','Machine'),('6118194062','BSC in CSE','Programming'),('6118194063','MSC in EEE','Ac current'),('6118194065','MSC in CIVIL','CAD'),('6118194066','BSC in EEE','Dc current'),('6118194067','BSC in EEE','Survay Motor'),('6118194068','MSC in CSE','Networking'),('6118194069','MSC in CSE','Database'),('6118194070','MSC in CSE','Database'),('Not Assigned','Not Assigned','Not Assigned');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-27 12:10:46
