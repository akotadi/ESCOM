-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: AplicadorExamen
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Administrador` (
  `idAdministrador` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  PRIMARY KEY (`idAdministrador`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
INSERT INTO `Administrador` VALUES (7,'MANUEL','MANUEL@HOTMAIL.COM','123'),(8,'TOMAS','TOMAS@MICORREO.COM','000');
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EU`
--

DROP TABLE IF EXISTS `EU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EU` (
  `idUsuario` int(11) NOT NULL,
  `idExamen` int(11) NOT NULL,
  `fechaPresentacion` date DEFAULT NULL,
  `ultimaPregunta` int(11) DEFAULT NULL,
  `calificacion` int(11) DEFAULT NULL,
  `Respuesta1` varchar(1) DEFAULT NULL,
  `Respuesta2` varchar(1) DEFAULT NULL,
  `Respuesta3` varchar(1) DEFAULT NULL,
  `Respuesta4` varchar(1) DEFAULT NULL,
  `Respuesta5` varchar(1) DEFAULT NULL,
  `Respuesta6` varchar(1) DEFAULT NULL,
  `Respuesta7` varchar(1) DEFAULT NULL,
  `Respuesta8` varchar(1) DEFAULT NULL,
  `Respuesta9` varchar(1) DEFAULT NULL,
  `Respuesta10` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`,`idExamen`),
  KEY `idExamen` (`idExamen`),
  CONSTRAINT `EU_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `EU_ibfk_2` FOREIGN KEY (`idExamen`) REFERENCES `Examen` (`idExamen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EU`
--

LOCK TABLES `EU` WRITE;
/*!40000 ALTER TABLE `EU` DISABLE KEYS */;
INSERT INTO `EU` VALUES (4,1,'2017-12-06',10,7,'A','A','D','B','A','A','D','C','B','C'),(4,16,'2017-12-07',3,0,'A','A','C',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,18,'2017-12-07',3,1,'C','B','B',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,25,'2017-12-07',3,3,'C','C','A',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `EU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Examen`
--

DROP TABLE IF EXISTS `Examen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Examen` (
  `idExamen` int(11) NOT NULL AUTO_INCREMENT,
  `tituloExamen` varchar(50) DEFAULT NULL,
  `idReactivo1` int(11) NOT NULL,
  `idReactivo2` int(11) NOT NULL,
  `idReactivo3` int(11) NOT NULL,
  `idReactivo4` int(11) NOT NULL,
  `idReactivo5` int(11) NOT NULL,
  `idReactivo6` int(11) NOT NULL,
  `idReactivo7` int(11) NOT NULL,
  `idReactivo8` int(11) NOT NULL,
  `idReactivo9` int(11) NOT NULL,
  `idReactivo10` int(11) NOT NULL,
  `materia` varchar(20) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idExamen`),
  KEY `idReactivo1` (`idReactivo1`),
  KEY `idReactivo2` (`idReactivo2`),
  KEY `idReactivo3` (`idReactivo3`),
  KEY `idReactivo4` (`idReactivo4`),
  KEY `idReactivo5` (`idReactivo5`),
  KEY `idReactivo6` (`idReactivo6`),
  KEY `idReactivo7` (`idReactivo7`),
  KEY `idReactivo8` (`idReactivo8`),
  KEY `idReactivo9` (`idReactivo9`),
  KEY `idReactivo10` (`idReactivo10`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `Examen_ibfk_1` FOREIGN KEY (`idReactivo1`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_10` FOREIGN KEY (`idReactivo10`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_11` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_2` FOREIGN KEY (`idReactivo2`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_3` FOREIGN KEY (`idReactivo3`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_4` FOREIGN KEY (`idReactivo4`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_5` FOREIGN KEY (`idReactivo5`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_6` FOREIGN KEY (`idReactivo6`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_7` FOREIGN KEY (`idReactivo7`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_8` FOREIGN KEY (`idReactivo8`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Examen_ibfk_9` FOREIGN KEY (`idReactivo9`) REFERENCES `Reactivo` (`idReactivo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Examen`
--

LOCK TABLES `Examen` WRITE;
/*!40000 ALTER TABLE `Examen` DISABLE KEYS */;
INSERT INTO `Examen` VALUES (1,'Historia I',1,2,3,4,5,6,7,8,9,10,'Historia',4),(16,'QUIMICA ETS',64,65,66,67,68,69,70,71,72,73,'Quimica',4),(18,'Examen Geografia',54,55,56,57,58,59,60,61,62,63,'Geografia',4),(25,'Examen Geografia',54,55,56,57,58,59,60,61,62,63,'Geografia',11);
/*!40000 ALTER TABLE `Examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Materia`
--

DROP TABLE IF EXISTS `Materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Materia` (
  `materia` varchar(20) NOT NULL,
  PRIMARY KEY (`materia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Materia`
--

LOCK TABLES `Materia` WRITE;
/*!40000 ALTER TABLE `Materia` DISABLE KEYS */;
INSERT INTO `Materia` VALUES ('Geografia'),('Historia'),('Matematicas'),('Quimica');
/*!40000 ALTER TABLE `Materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reactivo`
--

DROP TABLE IF EXISTS `Reactivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reactivo` (
  `idReactivo` int(11) NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(200) DEFAULT NULL,
  `opcionA` varchar(100) DEFAULT NULL,
  `opcionB` varchar(100) DEFAULT NULL,
  `opcionC` varchar(100) DEFAULT NULL,
  `opcionD` varchar(100) DEFAULT NULL,
  `respuesta` varchar(1) DEFAULT NULL,
  `materia` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idReactivo`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reactivo`
--

LOCK TABLES `Reactivo` WRITE;
/*!40000 ALTER TABLE `Reactivo` DISABLE KEYS */;
INSERT INTO `Reactivo` VALUES (1,'Deidad del México antiguo conocida como la Serpiente Emplumada','Coatlicue','Huitzilopochtli','Quetzalcóatl','Tláloc','C','Historia'),(2,'Cultura que se desarrolló en la ciudad de Palenque','Maya','Mexica','Olmeca','Tolteca','A','Historia'),(3,'Las cabezas colosales son creaciones de la cultura:','Mexica','Mixteca','Olmeca','Tolteca','C','Historia'),(4,'Terreno construido sobre las lagunas del valle de México que sirvió de base al sistema productivo del altiplano central. En la actualidad, puede encontrarse en Xochimilco:','Chinampa','Encomienda','Ejido','Hacienda','A','Historia'),(5,'Manuscrito en papel amate o sobre cuero con representaciones pictóricas que relataba asuntos históricos y religiosos del México antiguo:','Jeroglífico','Pictograma','Libro','Códice','D','Historia'),(6,'¿En dónde se encuentran las pirámides del Sol y la Luna?','Palenque','Chichén Itzá','Teotihuacan','Uxmal','C','Historia'),(7,'Al lugar mítico de donde partieron los mexicas e iniciaron su migración se le conoce como:','Aztlán','Tula','Mictlán','Tenochtitlán','A','Historia'),(8,'La base alimenticia de los pueblos mesoamericanos fue:','La papa','El trigo','El arroz','El maíz','D','Historia'),(9,'Chichén Itzá fue una poderosa ciudad:','Tolteca','Maya','Totonaca','Olmeca','B','Historia'),(10,'Año en que los españoles conquistaron México Tenochtitlán:','1521','1555','1650','1689','A','Historia'),(11,'Nombre de México durante el periodo colonial:','México','Nueva España','Tenochtitlán','Nueva Granada','B','Historia'),(12,'Nombre del conquistador que derrotó al Imperio mexica:','Hernán Cortés','Francisco Pizarro','Pánfilo de Narváez','Cristóbal Colón','A','Historia'),(13,'Nombre del último emperador mexica, a quien los conquistadores españoles torturaron quemándole los pies:','Moctezuma','Cuauhtémoc','Cuitláhuac','Tizoc','B','Historia'),(14,'La Malinche fue:','Una mujer indígena que sirvió de intérprete a Cortés','Una heroína en la lucha por la Independencia','Una famosa curandera de Oaxaca','La primera mujer en ocupar una diputación federal','A','Historia'),(15,'Nombre de la batalla en la que se afirma que los españoles perdieron hombres, caballos y cañones en 15Cortés lloró al llegar a la orilla del lago:','De la Calzada de Tacuba','Derrota de Atizapán','De la Noche Triste','Matanza de Cholula','C','Historia'),(16,'La máxima autoridad civil en la Nueva España era el:','Virrey','Gobernador','Presidente','Primer Ministro','A','Historia'),(17,'La encomienda y el visitador fueron instituciones:','Prehispánicas','Coloniales','Del México recién independizado','Del gobierno de Lázaro Cárdenas','B','Historia'),(18,'En México, a partir del virreinato, se denominaba criollos a:','Los hijos de padre español y madre indígena','Los hijos de esclavos negros','Todas las personas que no tuvieran tez blanca','Los hijos de españoles nacidos en la Nueva España','D','Historia'),(19,'Acapulco fue durante los siglos XVII y XVIII un importante puerto porque controlaba la navegación en el:','Océano Pacífico','Golfo de México','Océano Atlántico','Mar Caribe','A','Historia'),(20,'El principal culto religioso de la sociedad novohispana y que perdura en la actualidad es:','La Virgen de los Remedios','La Virgen de Guadalupe','El Cristo Negro de Chalma','San Judas Tadeo','B','Historia'),(21,'El mineral que más se extraía en la Nueva España era:','El níquel','El cobre','El estaño','La plata','D','Historia'),(22,'Nombre de la poetisa más importante de la época novohispana:','Sor Juana Inés de la Cruz','María Anna Agueda de San Ignacio','La Malinche','Tonantzin','A','Historia'),(23,'Fecha en la que se celebra la Independencia de México:','5 de mayo','5 de febrero','16 de septiembre','20 de noviembre','C','Historia'),(24,'La Independencia de México inició en:','1810','1821','1910','1921','A','Historia'),(25,'¿Cuál fue la importancia del sacerdote Miguel Hidalgo y Costilla en la historia de México?','Inició la Guerra de Independencia','Inició la Revolución mexicana','Promulgó una nueva constitución','Encabezó la Guerra de Reforma','A','Historia'),(26,'¿A quién se conoce como El Padre de la Patria?','Francisco I. Madero','Benito Juárez','Miguel Hidalgo y Costilla','George Washington','C','Historia'),(27,'Cadetes militares que defendieron el Castillo de Chapultepec de la invasión norteamericana en 1847:','Insurgentes','Niños héroes','Defensores de la Patria','Liberales','B','Historia'),(28,'En México el 5 de mayo se celebra:','La Batalla de Puebla','La Revolución mexicana','La promulgación de la Constitución','La Independencia de México','A','Historia'),(29,'Presidente mexicano al que se le atribuye la frase Entre los individuos como entre las naciones, el respeto al derecho ajeno es la paz:','Benito Juárez','Francisco I. Madero','Emiliano Zapata','Lázaro Cárdenas','A','Historia'),(30,'México perdió la mitad de su territorio a mediados del siglo XIX, como consecuencia del enfrentamiento con:','Francia','España','Portugal','Estados Unidos','D','Historia'),(31,'La Guerra de Reforma fue entre:','México y Estados Unidos','Liberales y conservadores','El Ejército mexicano y el Ejército francés','México y Francia','B','Historia'),(32,'El presidente que duró más de treinta años en el poder en México fue:','Antonio López de Santa Anna','Porfirio Díaz','Benito Juárez','Francisco I. Madero','B','Historia'),(33,'El Porfiriato se conoce como una época de extensión de:','Las vías férreas','La esclavitud','El ejido','La radio','A','Historia'),(34,'Fecha en que se celebra el aniversario de la Revolución mexicana:','5 de febrero','5 de mayo','16 de septiembre','20 de noviembre','D','Historia'),(35,'Nombre del líder agrario que encabezó la Revolución mexicana en el estado de Morelos:','Venustiano Carranza','Emiliano Zapata','Francisco I. Madero','Francisco Villa','B','Historia'),(36,'El lema Sufragio efectivo, no reelección fue la bandera política de:','Francisco I. Madero','Benito Juárez','Plutarco E. Calles','Francisco Villa','A','Historia'),(37,'¿Quién fue Francisco Villa?','Un famoso bandido del siglo XIX','Un líder de la Revolución mexicana','Un personaje de la novela Sálvese quien pueda','Un cantante de música vernácula','B','Historia'),(38,'Las Adelitas acompañaron a sus hombres al combate en:','La Guerra de los Pasteles','La Guerra de Independencia','La Revolución de 1910','La Guerra de 1847','C','Historia'),(39,'Composición musical característica de la época revolucionaria:','El corrido','El vals','El jarabe tapatío','El mariachi','A','Historia'),(40,'El 18 de marzo de 1938 el presidente Lázaro Cárdenas:','Expropió el petróleo','Redactó las Leyes de Reforma','Nacionalizó la banca','Inició la Revolución mexicana','A','Historia'),(41,'Señale el orden cronológico correcto de los siguientes acontecimientos históricos:','Revolución mexicana, Independencia, Conquista','Conquista, Revolución mexicana, Independencia','Independencia, Conquista, Revolución mexicana','Conquista, Independencia, Revolución mexicana','D','Historia'),(42,'Representante del muralismo mexicano:','Diego Rivera','Octavio Paz','Luis Barragán','Agustín Lara','A','Historia'),(43,'Nombre del partido político que gobernó durante siete décadas en el siglo XX:','Partido Comunista','Partido Socialista Único de México','Partido Revolucionario Institucional','Partido Auténtico de la Revolución Mexicana','C','Historia'),(44,'¿En qué conflicto bélico internacional participó el Escuadrón 201?','Guerra de Texas','Guerra de los Pasteles','Primera Guerra Mundial','Segunda Guerra Mundial','D','Historia'),(45,'El Tratado de Tlatelolco, logro de la diplomacia mexicana, atañe a:','Los derechos sobre el mar patrimonial','Los derechos de los pueblos indígenas','La no proliferación de las armas nucleares','Los intercambios comerciales en América Latina','C','Historia'),(46,'Los símbolos patrios de México son:','El Escudo, la Bandera y el Himno Nacional','La Bandera, la Moneda y el Himno Nacional','El Águila Real, el Escudo y el Palacio Nacional','El Ángel de la Independencia, el Himno Nacional y la Bandera','A','Historia'),(47,'El tema dominante del himno nacional mexicano es:','La celebración de la unidad latinoamericana','Una exhortación a la guerra defensiva','La exaltación del mestizaje en México','Un llamado a la paz internacional','B','Historia'),(48,'En el himno nacional, la expresión Mas si osare un extraño enemigo significa:','Son peores que osos nuestros enemigos','Abundan los huesos de nuestros enemigos','Pero si se atreviera un enemigo extranjero','Con más razón si el enemigo fuera extravagante','C','Historia'),(49,'El águila y la serpiente que aparecen en el escudo nacional mexicano, incluido en la franja blanca de la bandera:','Figuraban en el escudo de armas de un conquistador español','Son una respuesta nacionalista al águila estadounidense','Evocan una leyenda prehispánica','Aparecían en el estandarte de Miguel Hidalgo y Costilla','C','Historia'),(50,'¿Cuántos estados tiene la República Mexicana?','32','31','36','50','A','Historia'),(54,'NOMBRE DE LA CAPITAL DE ARGENTINA','ROSARIO','SANTIAGO','BUENOS AIRES','PLATA','C','GEOGRAFIA'),(55,'Los climas que presentan 4 estaciones diferenciadas (Primavera, verano, otoño e invierno) Son propios de las zonas...','Ecuatoriales','Tropicales','Templadas','Polares','C','Geografia'),(56,'Las líneas que unen puntos de la superficie terrestre que tiene la misma presión atmosférica se denominan','Isobaras','Isotermas','Isoyetas','Isoclinas','A','Geografia'),(57,'Hablando de los anti-ciclones, ¿cuál de las siguientes afirmaciones no sería la correcta si nos referimos a un anti-ciclón situado en el hemisferio norte?','Constituye un centro de altas presiones','Su dinámica horizonr¡tal gira en sentido de las agujas del reloj','Su dinámica vertical origina vientos descendentes y divergentes','Todas son válidas','D','Geografia'),(58,'La meteorología estudia:','El tiempo','El clima','Las temperaturas','Las precipitaciones','A','Geografia'),(59,'¿Cuál de los siguientes no sería un factor del clima?','Latitud','Longitud','Altitud','Continentalidad','B','Geografia'),(60,'Meridiodinal es un sinónimo de...','Norte','Sur','Este','Oeste','B','Geografia'),(61,'Los alicios, en el hemisferio norte son vientos orientados...','De NE a SW','De SW a NE','De NW a SE','De SE a NW','A','Geografia'),(62,'Las diferencias horarias que se producen en los distintos puntos de la tierra (en función de su diferente longitud están ocasionadas por','El movimiento de traslación de la tierra alrededor del sol','Por el movimiento de la tierra sobre su eje','Por la distinta distancia de los puntos considerados respecto al ecuador','Por la desviación del eje de la tierra','B','Geografia'),(63,'A la distancia angular existente entre un punto cualquiera de la superficie terrestre y el ecuador, se le denomina','Latitud','Longitud','Altura respecto al ecuador','Todas las anteriores','A','Geografia'),(64,'Nombre del compuesto CH4','Dietil','Metil','Trietil','Etil','B','Quimica'),(65,'Nombre del compuesto COOH-CH2-CH4','Ácido propílico','Ácido etílico','Ácido hialurónico','Ácido sódico','B','Quimica'),(66,'Nombre de la siguiente estructura del carbono X - C - O - X','Cetona','Sal','Polímero','Aldehído','A','Quimica'),(67,'¿Cuál es el modelo atómico que se usa en la actualidad?','Modelo atómico de Bohr','Modelo atómico plano','Modelo atómico de Rutherford','Modelo atómico de Einstein','C','Quimica'),(68,'¿Cuál es el elemento químico más abundante en la tierra','Mercurio','Hidrógeno','Oro','CO2','A','Quimica'),(69,'¿Cuál de los siguientes objetos es el más resistente según su estructura química?','Polímero','Plástico','Polimetrico','Poliester','A','Quimica'),(70,'Selecciona la nomenclatura IUPAC del ácido trietílico','1 - trietil - 2 etil','1 - trietil','1,3- etil','1,2,3 - trietil','B','Quimica'),(71,'En la nomenclatura IUPAC de los halogenuros, ¿En qué posición va la numeración del halógeno?','tercer','Final','Primera','En medio','C','Quimica'),(72,'Nombre de los electrones de la última capa de un elemento','Valencia','Valedores','Equlibrio','Balanceo','A','Quimica'),(73,'Proceso insdustrial que consiste en energizar un recipiente para obtener una diferencia de potencial y que los iones se reacomoden con algún objeto metálico','Modelado','Biselado','Electrocutado','Chapado','D','Quimica');
/*!40000 ALTER TABLE `Reactivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contraseña` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (4,'RAMON','RAMON@GMAIL.COM','444'),(11,'TOMAS','TOMAS@HOTMAIL.COM','000'),(12,'FILOMENO','MICORREO@GMAIL.COM','999');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-10  8:18:42
