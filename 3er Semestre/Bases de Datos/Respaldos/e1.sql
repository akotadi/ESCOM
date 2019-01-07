-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: e1
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `asociado`
--

DROP TABLE IF EXISTS `asociado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asociado` (
  `idEmp` int(11) NOT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  `direccion` varchar(350) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  PRIMARY KEY (`idEmp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asociado`
--

LOCK TABLES `asociado` WRITE;
/*!40000 ALTER TABLE `asociado` DISABLE KEYS */;
/*!40000 ALTER TABLE `asociado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartelera`
--

DROP TABLE IF EXISTS `cartelera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartelera` (
  `idCartelera` int(11) NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `clasificacion` varchar(5) DEFAULT NULL,
  `idCinemex` int(11) DEFAULT NULL,
  `nombreCinemex` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idCartelera`),
  KEY `idCinemex` (`idCinemex`,`nombreCinemex`),
  CONSTRAINT `cartelera_ibfk_1` FOREIGN KEY (`idCinemex`, `nombreCinemex`) REFERENCES `cinemex` (`idCinemex`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartelera`
--

LOCK TABLES `cartelera` WRITE;
/*!40000 ALTER TABLE `cartelera` DISABLE KEYS */;
/*!40000 ALTER TABLE `cartelera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinemex`
--

DROP TABLE IF EXISTS `cinemex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cinemex` (
  `idCinemex` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `dir` varchar(300) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idCinemex`,`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinemex`
--

LOCK TABLES `cinemex` WRITE;
/*!40000 ALTER TABLE `cinemex` DISABLE KEYS */;
/*!40000 ALTER TABLE `cinemex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ec`
--

DROP TABLE IF EXISTS `ec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ec` (
  `idEmp` int(11) NOT NULL,
  `idCinemex` int(11) NOT NULL,
  `nombreCinemex` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idEmp`,`idCinemex`),
  KEY `idCinemex` (`idCinemex`,`nombreCinemex`),
  CONSTRAINT `ec_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `asociado` (`idEmp`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ec_ibfk_2` FOREIGN KEY (`idCinemex`, `nombreCinemex`) REFERENCES `cinemex` (`idCinemex`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ec`
--

LOCK TABLES `ec` WRITE;
/*!40000 ALTER TABLE `ec` DISABLE KEYS */;
/*!40000 ALTER TABLE `ec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gerente` (
  `idGerente` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `turno` varchar(20) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  `noCel` varchar(15) DEFAULT NULL,
  `idCinemex` int(11) DEFAULT NULL,
  `nombreCinemex` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idGerente`),
  KEY `idCinemex` (`idCinemex`,`nombreCinemex`),
  CONSTRAINT `gerente_ibfk_1` FOREIGN KEY (`idCinemex`, `nombreCinemex`) REFERENCES `cinemex` (`idCinemex`, `nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-23 13:37:52
