-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: camp_xadrez
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.1

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `id_aluno` int NOT NULL AUTO_INCREMENT,
  `id_turma` int NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `pontos_total` int NOT NULL DEFAULT '0',
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `data_criacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_aluno`),
  KEY `idx_aluno_turma` (`id_turma`),
  CONSTRAINT `fk_aluno_turma` FOREIGN KEY (`id_turma`) REFERENCES `turma` (`id_turma`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,1,'João Silva',10,1,'2025-08-13 09:53:26','2025-08-13 09:53:26'),(2,1,'Maria Souza',15,1,'2025-08-13 09:53:26','2025-08-13 09:53:26'),(3,1,'Pedro Lima',7,1,'2025-08-13 09:53:26','2025-08-13 09:53:26'),(4,1,'Rau Vidal',66,1,'2024-02-23 00:05:45','2024-02-24 17:22:15'),(5,1,'Davi Cope',52,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(6,1,'Ana Clara',51,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(7,1,'Thiago de Souza',42,1,'2024-06-13 22:08:14','2024-06-13 22:08:14'),(8,1,'João Pedro',33,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(9,1,'Rebecca Lagais',32,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(10,1,'Sara do Amaral',31,1,'2024-02-23 00:05:45','2025-08-25 16:01:29'),(11,1,'Valentina Cardoso',26,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(12,1,'Davi Matheus',14,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(13,1,'Ana iwani',13,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(14,1,'Laura Cristina',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(15,1,'Rafaela lopis',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(16,1,'Vitoria Abreu',8,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(17,1,'Henrique Franzilli',1,1,'2024-02-25 14:43:30','2024-02-25 14:43:30'),(18,1,'Caio teste',0,1,'2024-02-25 14:50:22','2025-08-20 09:16:10'),(19,1,'teste',0,1,'2024-06-14 01:19:40','2024-06-14 01:19:40'),(20,1,'Novo aluno atualizado',0,0,'2025-08-20 09:40:38','2025-08-20 10:17:47'),(21,1,'Rau Vidal',66,1,'2024-02-23 00:05:45','2024-02-24 17:22:15'),(22,1,'Davi Cope',52,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(23,1,'Ana Clara',51,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(24,1,'Thiago de Souza',42,1,'2024-06-13 22:08:14','2024-06-13 22:08:14'),(25,1,'João Pedro',33,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(26,1,'Rebecca Lagais',32,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(27,1,'Sara do Amaral',30,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(28,1,'Valentina Cardoso',26,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(29,1,'Davi Matheus',14,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(30,1,'Ana iwani',13,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(31,1,'Laura Cristina',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(32,1,'Rafaela lopis',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(33,1,'Vitoria Abreu',8,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(34,1,'Henrique Franzilli',1,1,'2024-02-25 14:43:30','2024-02-25 14:43:30'),(35,1,'Caio Viana',0,1,'2024-02-25 14:50:22','2024-02-28 02:55:50'),(36,1,'teste',0,1,'2024-06-14 01:19:40','2024-06-14 01:19:40'),(37,1,'Rau Vidal',66,1,'2024-02-23 00:05:45','2024-02-24 17:22:15'),(38,1,'Davi Cope',52,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(39,1,'Ana Clara',51,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(40,1,'Thiago de Souza',42,1,'2024-06-13 22:08:14','2024-06-13 22:08:14'),(41,1,'João Pedro',33,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(42,1,'Rebecca Lagais',32,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(43,1,'Sara do Amaral',30,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(44,1,'Valentina Cardoso',26,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(45,1,'Davi Matheus',14,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(46,1,'Ana iwani',13,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(47,1,'Laura Cristina',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(48,1,'Rafaela lopis',12,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(49,1,'Vitoria Abreu',8,1,'2024-02-23 00:05:45','2024-02-23 00:05:45'),(50,1,'Henrique Franzilli',1,1,'2024-02-25 14:43:30','2024-02-25 14:43:30'),(51,1,'Caio Viana',0,1,'2024-02-25 14:50:22','2024-02-28 02:55:50'),(52,1,'teste',0,1,'2024-06-14 01:19:40','2024-06-14 01:19:40');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `script` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2025-08-13 13:44:07',0,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `id_professor` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `senha_hash` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `data_criacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_professor`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'Prof. Caio','caio@gmail.com','$2a$10$e8bF0q/zgdIa/OXSMbo8A.iKl301LNSer.geo7nVUI8vFKmxacJb2',1,'2025-08-13 09:53:26','2025-09-06 12:57:35'),(2,'fernando','fernando@gmial.com','123',0,'2025-08-15 20:45:03','2025-08-15 20:45:03'),(3,'Prof. Caio','caio.viana.39@gmail.com','caio',1,'2025-08-29 23:21:56','2025-08-29 23:21:56'),(4,'novo','novo@gmail.com','novo',1,'2025-09-06 11:54:17','2025-09-06 11:54:17'),(5,'teste','teste@gmail.com','$2a$10$GTAfPpaVb9SscBXxyoDtUeQ8f0sYX935oTphpBCPL9aarS5PPMaMi',1,'2025-09-06 12:05:09','2025-09-06 12:05:09');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turma` (
  `id_turma` int NOT NULL AUTO_INCREMENT,
  `id_professor` int NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` text COLLATE utf8mb4_general_ci,
  `ano_letivo` int NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `data_criacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ultima_atualizacao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_turma`),
  KEY `idx_turma_professor` (`id_professor`),
  CONSTRAINT `fk_turma_professor` FOREIGN KEY (`id_professor`) REFERENCES `professor` (`id_professor`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,1,'Integral jequitiba','Turma de xadrez da unidade jequitiba',2025,1,'2025-08-13 09:53:26','2025-09-08 17:17:22'),(2,1,'string','string',0,0,'2025-08-18 21:29:33','2025-08-18 21:29:33'),(3,1,'string','string',2025,0,'2025-08-18 21:29:59','2025-08-18 21:29:59'),(4,1,'string','string',2025,1,'2025-08-18 21:37:08','2025-08-18 21:37:08'),(5,1,'Integral JEQUITIBÁ Novo','Turma de xadrez do colégio ideal',2025,1,'2025-08-19 10:45:07','2025-08-19 11:01:10');
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-10  0:17:46
