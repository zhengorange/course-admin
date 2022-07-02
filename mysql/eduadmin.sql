mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: localhost    Database: eduadmin
-- ------------------------------------------------------
-- Server version	5.7.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `class_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `class_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'计算机技术'),(5,'应用统计');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) DEFAULT NULL,
  `course_time` int(10) unsigned DEFAULT NULL,
  `credits` int(10) unsigned DEFAULT NULL,
  `teacher_id` int(10) unsigned NOT NULL,
  `max_student` int(10) unsigned NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_name` (`course_name`),
  KEY `FK_course_teacher` (`teacher_id`),
  CONSTRAINT `FK_course_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'计算机网络',19,3,1,20),(2,'数据结构',0,3,1,30),(3,'软件工程',10,5,1,10),(4,'NLP入门到放弃',24,2,1,12),(5,'计算机组成原理',0,3,2,20),(6,'操作系统',16,3,2,10),(7,'线性代数',23,1,2,10),(8,'微积分',22,1,2,18),(9,'信息技术教育研究与发展',15,3,3,15),(10,'现代教育技术及应用',17,2,3,10),(11,'仿真建模与MATLAB',0,2,3,10),(12,'知识表示与处理',1,3,3,10),(13,'数字系统集成设计',2,2,3,12),(14,'数字信号处理',3,1,3,10),(15,'计算机网络安全',21,1,3,10),(16,'数据库新技术',5,1,4,10),(17,'数字图像处理',6,2,4,10),(18,'可信软件理论与技术',12,2,4,10),(19,'人工智能数学基础',13,1,4,10),(20,'计算机视觉',24,1,4,10);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary`
--

DROP TABLE IF EXISTS `secretary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secretary` (
  `secretary_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `secretary_name` varchar(50) NOT NULL,
  `secretary_password` varchar(50) NOT NULL,
  PRIMARY KEY (`secretary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary`
--

LOCK TABLES `secretary` WRITE;
/*!40000 ALTER TABLE `secretary` DISABLE KEYS */;
INSERT INTO `secretary` VALUES (4,'小王','202cb962ac59075b964b07152d234b70'),(5,'test','202cb962ac59075b964b07152d234b70');
/*!40000 ALTER TABLE `secretary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `select_course`
--

DROP TABLE IF EXISTS `select_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `select_course` (
  `select_course_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  `grade` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`select_course_id`),
  UNIQUE KEY `student_id_course_id` (`student_id`,`course_id`),
  KEY `FK_select_course_course` (`course_id`),
  CONSTRAINT `FK_select_course_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_select_course_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `select_course`
--

LOCK TABLES `select_course` WRITE;
/*!40000 ALTER TABLE `select_course` DISABLE KEYS */;
INSERT INTO `select_course` VALUES (11,22,7,10),(12,22,8,NULL),(13,22,6,NULL),(14,22,5,NULL),(15,22,3,NULL),(16,22,2,99),(17,22,1,99),(18,23,7,NULL),(19,23,8,NULL),(20,23,6,NULL),(21,23,5,NULL),(22,23,3,NULL),(23,23,2,NULL),(24,23,1,NULL),(25,24,7,NULL),(26,24,8,NULL),(27,24,6,NULL),(28,24,5,NULL),(29,24,3,NULL),(30,24,2,NULL),(31,24,1,NULL),(32,25,7,NULL),(33,25,8,NULL),(34,25,6,NULL),(35,25,5,NULL),(36,25,3,NULL),(37,25,2,NULL),(38,25,1,NULL),(39,26,7,NULL),(40,26,8,NULL),(41,26,6,NULL),(42,26,5,NULL),(43,26,3,NULL),(44,26,2,NULL),(45,26,1,NULL);
/*!40000 ALTER TABLE `select_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_name` varchar(50) NOT NULL,
  `student_gender` int(10) unsigned NOT NULL,
  `class_id` int(10) unsigned NOT NULL,
  `student_password` varchar(50) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FK_student_class` (`class_id`),
  CONSTRAINT `FK_student_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (22,'张三',1,1,'202cb962ac59075b964b07152d234b70'),(23,'李四',1,1,'202cb962ac59075b964b07152d234b70'),(24,'王二',1,1,'202cb962ac59075b964b07152d234b70'),(25,'静静',2,1,'202cb962ac59075b964b07152d234b70'),(26,'晴晴',2,1,'202cb962ac59075b964b07152d234b70');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_gender` int(10) unsigned NOT NULL,
  `teacher_password` varchar(50) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'飞飞',1,'202cb962ac59075b964b07152d234b70'),(2,'张三',2,'202cb962ac59075b964b07152d234b70'),(3,'李四',2,'202cb962ac59075b964b07152d234b70'),(4,'苗苗',2,'202cb962ac59075b964b07152d234b70');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_items`
--

DROP TABLE IF EXISTS `training_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_items` (
  `plan_items_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `training_plan_id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  `required` int(10) unsigned NOT NULL,
  PRIMARY KEY (`plan_items_id`),
  UNIQUE KEY `training_plan_id_course_id` (`training_plan_id`,`course_id`),
  KEY `FK_plan_items_course` (`course_id`),
  CONSTRAINT `FK_plan_items_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_plan_items_training_plan` FOREIGN KEY (`training_plan_id`) REFERENCES `training_plan` (`plan_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_items`
--

LOCK TABLES `training_items` WRITE;
/*!40000 ALTER TABLE `training_items` DISABLE KEYS */;
INSERT INTO `training_items` VALUES (59,6,3,0),(60,6,6,0),(61,6,18,0),(62,6,7,1),(63,6,8,1),(64,6,1,1),(65,1,4,0),(66,1,20,0),(67,1,18,0),(68,1,19,0),(69,1,16,0),(70,1,17,0),(71,1,14,0),(72,1,15,0),(73,1,13,0),(74,1,12,0),(75,1,7,1),(76,1,8,1),(77,1,6,1),(78,1,5,1),(79,1,3,1),(80,1,2,1),(81,1,1,1);
/*!40000 ALTER TABLE `training_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_plan`
--

DROP TABLE IF EXISTS `training_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_plan` (
  `plan_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(50) DEFAULT NULL,
  `class_id` int(10) unsigned NOT NULL,
  `electives` int(10) unsigned NOT NULL,
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `class_id` (`class_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_plan`
--

LOCK TABLES `training_plan` WRITE;
/*!40000 ALTER TABLE `training_plan` DISABLE KEYS */;
INSERT INTO `training_plan` VALUES (1,'计算机技术专业培养方案',1,12),(6,'应用统计学培养方案',5,9);
/*!40000 ALTER TABLE `training_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-02 22:12:57
