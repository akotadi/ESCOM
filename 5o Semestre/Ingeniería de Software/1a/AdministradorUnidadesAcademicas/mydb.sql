-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
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
-- Table structure for table `contenido`
--

DROP TABLE IF EXISTS `contenido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contenido` (
  `idContenido` int(11) NOT NULL AUTO_INCREMENT,
  `horas` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `numero` varchar(50) NOT NULL,
  PRIMARY KEY (`idContenido`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contenido`
--

LOCK TABLES `contenido` WRITE;
/*!40000 ALTER TABLE `contenido` DISABLE KEYS */;
/*!40000 ALTER TABLE `contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensajes` (
  `idMensajes` int(11) NOT NULL AUTO_INCREMENT,
  `MS_000` varchar(50) NOT NULL,
  `MS_001` varchar(50) NOT NULL,
  `MS_002` varchar(50) NOT NULL,
  `MS_003` varchar(50) NOT NULL,
  `MS_004` varchar(50) NOT NULL,
  `MS_005` varchar(50) NOT NULL,
  `MS_006` varchar(50) NOT NULL,
  PRIMARY KEY (`idMensajes`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
INSERT INTO `mensajes` VALUES (1,'Por favor llene todos los campos de forma correcta','Registro exitoso','No hay pendientes','Error al cargar la página.','Por favor llene todos los campos.','No se pudo conectar a la base de datos','Materia existente.');
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiempoasignado`
--

DROP TABLE IF EXISTS `tiempoasignado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiempoasignado` (
  `idtiempoAsignado` int(11) NOT NULL AUTO_INCREMENT,
  `horas_practica_semana` varchar(50) NOT NULL,
  `horas_teoria_nivel` varchar(50) NOT NULL,
  `horas_teoria_semana` varchar(50) NOT NULL,
  `horas_total_nivel` varchar(50) NOT NULL,
  PRIMARY KEY (`idtiempoAsignado`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiempoasignado`
--

LOCK TABLES `tiempoasignado` WRITE;
/*!40000 ALTER TABLE `tiempoasignado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiempoasignado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_de_aprendizaje`
--

DROP TABLE IF EXISTS `unidad_de_aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidad_de_aprendizaje` (
  `idUnidad_de_aprendizaje` int(11) NOT NULL AUTO_INCREMENT,
  `CATCC` varchar(50) DEFAULT NULL,
  `CTEPIC` varchar(50) DEFAULT NULL,
  `aprobado_por` varchar(50) NOT NULL,
  `area_de_informacion` varchar(50) NOT NULL,
  `autorizado_por` varchar(50) NOT NULL,
  `modalidad` varchar(50) NOT NULL,
  `nivel` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `objetivo` varchar(50) NOT NULL,
  `orientacion_educativa` varchar(50) NOT NULL,
  `proposito` varchar(50) NOT NULL,
  `revisado_por` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `vigencia` varchar(50) NOT NULL,
  `idContenido` int(11) NOT NULL,
  `idtiempoAsignado` int(11) NOT NULL,
  `idUnidad_Tematica` int(11) NOT NULL,
  PRIMARY KEY (`idUnidad_de_aprendizaje`,`idContenido`,`idtiempoAsignado`,`idUnidad_Tematica`),
  KEY `fk_Unidad_de_aprendizaje_Contenido_idx` (`idContenido`),
  KEY `fk_Unidad_de_aprendizaje_tiempoAsignado1_idx` (`idtiempoAsignado`),
  KEY `fk_Unidad_de_aprendizaje_Unidad_Tematica1_idx` (`idUnidad_Tematica`),
  CONSTRAINT `fk_Unidad_de_aprendizaje_Contenido` FOREIGN KEY (`idContenido`) REFERENCES `contenido` (`idContenido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Unidad_de_aprendizaje_Unidad_Tematica1` FOREIGN KEY (`idUnidad_Tematica`) REFERENCES `unidad_tematica` (`idUnidad_Tematica`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Unidad_de_aprendizaje_tiempoAsignado1` FOREIGN KEY (`idtiempoAsignado`) REFERENCES `tiempoasignado` (`idtiempoAsignado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_de_aprendizaje`
--

LOCK TABLES `unidad_de_aprendizaje` WRITE;
/*!40000 ALTER TABLE `unidad_de_aprendizaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `unidad_de_aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_tematica`
--

DROP TABLE IF EXISTS `unidad_tematica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidad_tematica` (
  `idUnidad_Tematica` int(11) NOT NULL AUTO_INCREMENT,
  `competencia` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `numero` varchar(50) NOT NULL,
  PRIMARY KEY (`idUnidad_Tematica`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_tematica`
--

LOCK TABLES `unidad_tematica` WRITE;
/*!40000 ALTER TABLE `unidad_tematica` DISABLE KEYS */;
/*!40000 ALTER TABLE `unidad_tematica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-01 22:09:23
