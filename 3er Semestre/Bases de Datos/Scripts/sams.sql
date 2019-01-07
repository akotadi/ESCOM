-- MySQL dump 10.13  Distrib 5.1.51, for Win32 (ia32)
--
-- Host: localhost    Database: sams
-- ------------------------------------------------------
-- Server version	5.1.51-community

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
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club` (
  `idClub` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `tel` varchar(60) DEFAULT NULL,
  `idEdo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idClub`),
  KEY `idEdo` (`idEdo`),
  CONSTRAINT `club_ibfk_1` FOREIGN KEY (`idEdo`) REFERENCES `estado` (`idEdo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club`
--

LOCK TABLES `club` WRITE;
/*!40000 ALTER TABLE `club` DISABLE KEYS */;
INSERT INTO `club` VALUES (1,'Aguascalientes','Av. Independencia No. 2351, Col. La Concepci¢n, C.P.20110','Tel.: (449)9 10 70 00',1),(2,'Santa M¢nica','Av. Siglo XXI No. 3980, Col. Rancho Santa M¢nica, C.P.20289, Aguascalientes.','Tel.: (449)918-4009/918-4214',1),(3,'Macro Plaza Tijuana','Blvd. Insurgentes No. 18015, Col. La Mesa c.P.22105','Tel.: (664)9695834',2),(4,'Mexicali','Blvd. L zaro C rdenas 1801, Col. Ex ejido de Zacatecas C.P.21090','Tel.: (686)592-141-48',2),(5,'Tijuana','Blvd. S nchez Taboada 4005, Zona R¡o, c.P.22320','Tel.: (664)6-34-69-79',2),(6,'Los Cabos','Carretera Transpeninsular Km.2, Los Cabos-La Paz Previo La Huerta, C.P.23463, Cabo San Lucas.','Tel.: (624) 144 71 24',3),(7,'Cola de Ballena','Av. Agust¡n Olachea esq. Libramiento Sur s/n. Col. El Zacatal, La Paz.','Tel.: (981)816 6268',3),(8,'Campeche','Mar¡a Lavalle Manzana D Lote 1, Fraccionamiento 2, Col. Sector Fundadores de A-Kim-pech, c.P.24028','Tel.: (981)816 6268',4),(9,'Ciudad del Carmen','Av.Perif‚rico Norte No.52, Col. Asa Poniente, C.P.24118','Tel.: (983)286 1222',4),(10,'Saltillo','Av. Nazario Ort¡z Garza No. 841, col. saltillo 400, C.P.25290','Tel.: (844) 439-18-69',5),(11,'Torre¢n','Blvd. Diagonal Reforma No. 3001 Ote. col. San Marcos, c.P.27040','Tel.: (871) 7-32-11-01',5),(12,'Colima','Felipe Sevilla del R¡o No.49, Col. Vista Hermosa, C.P.28016','Tel.: (312) 323-51-72',6),(13,'Comitl n','Blvd. de las Federaciones Km 120 + 200, col. Chichima. C.P.','Tel.: (963) 632 94 94',7),(14,'San Cristobal','Eje vial No.1 esq. Ni¤o de Atocha, col. El Relicario, C.P.29286, San Crist¢bal de las Casas',NULL,7),(15,'Tapachuca','Carretera a Puerto Madero Km 3.5, col. Valle Dorado II, c.P.30700','Tel.: (962) 628-82880 al 89',7),(16,'Tuxtla Guti‚rrez','Blvd. Belisario Dom¡nguez No.1550, Col. Jardines de Tuxtla, C.P.29020','Tel.: (961) 615 7730',7),(17,'Ciudad Ju rez','Av.Ej‚rcito Nacional No. 7445, Col. Partido Iglesias, C.P.32399','Tel.: (656) 629-09-07',8),(18,'Chihuahua','Perif‚rico de la Juventud No. 2200, Col. Haciendas del Valle, C.P.31238','Tel.: (614) 429 13 00',8),(19,'Homero','Av. Homero No. 200, Col. Real Universidad, c.P. 31125',NULL,8),(20,'Acoxpa','Calzada Acoxpa No. 438, Col. Ex-Hacienda Coapa, C.P.14300','Tel.: (55) 56-79-32-51/56-79-31-97',9),(21,'Plaza Oriente','Av. Canal de Tezontle No. 1520, Col. Alfonso Ortiz Tirado, C.P. 09020','Tel.: (55) 5600 9211',9),(22,'Polanco','Av. Ej‚rcito Nacional No. 559, Col. Granada, C.P.11520','Tel.: (55) 55-45-63-00',9),(23,'San Jer¢nimo','Av. San Jer¢nimo No. 630, Col. La Otra Banda, C.P.011090','Tel.: 5532-78-90',9),(24,'Santa Fe','Av. Tamaulipas No. 3000, Col. La Rosita, c.P.05340, Cuajimalpa','Tel.: (55) 5257 9800',9),(25,'Tepeyac','Av. Ferrocarril Hidalgo No. 431, Col. Guadalupe Tepeyac, C.P.07840','Tel.: (55) 5759 8525',9),(26,'Toreo','Blvd. Manuel µvila Camacho No. 467, Col. Periodistas, C.P.11220','Tel.: (55) 5557 3717/55573474',9),(27,'Universidad DF','Municipio Libre No. 450, Col. Santa Cruz Atoyac, C.P.03310','Tel.: (55) 56-88-87-22',9),(28,'Durango','Blvd. Felipe Pescador No.1401 Ote. Zona Centro, c.P.34000','Tel.: (618) 813-9070',10),(29,'Villa Jardin','Blvd. Miguel Alem n esq. calzada Agustin Castro Col. Villa Jardin, C.P.350150, Lerdo, Dgo.','Tel.: 5532-78-90',10),(30,'Celaya Irrigaci¢n','Av. Irrigaci¢n No. 200, Col. Villas Del Benavente, C.P.38020, Celaya','Tel.: (461) 611-48-46',11),(31,'Irapuato','Av. Paseo Irapuato No.1101, Col. Primera San Gabriel, C.P.36640, Irapuato.','Tel.: (462) 690 00 04 al 13',11),(32,'Le¢n','Av. Manuel J. Clouthier No.102, Fracc. Privada del Moral 2, C.P. 37160, Le¢n.','Tel.: (477) 781 09 01',11),(33,'L¢pez Mateos','Blvd. Adolfo L¢pez Mateos No.3415, Col. Predio Pur¡sima de Jerez, C.P. 37290, Le¢n.','Tel.: 5532-78-90',11),(34,'Acapulco','Av. Farall¢n No. 516, Fracc. Farall¢n del Obispo, C.P. 39690','Tel.: (744) 4-88-32-02',12),(35,'Acapulco Diamante','Blvd. de las Naciones No. 802, Col. Fraccionamiento Granjas del Marqu‚s, C.P.39887','Tel.: (744) 466-25-49',12),(36,'Chilpancingo','Calle Fernando R¡os Neri No.2, Col. Unidada de los Servicios, C.P.39075','Tel.: (747) 116-00-07',12),(37,'Iguala','Perif‚rico Sur S/N entre las calles Garc¡a de la Cadena y Morelos, Col. Valle Escondido, Iguala de la Independencia, Guerrero. C.P.40070',NULL,12),(38,'Pachuca','Av. R¡o de las Avenidas No. 701, Fracc. Las Terrazas, C.P.42098, Pachuca.','Tel.: (771) 713 0921/718 3735',13),(39,'Tulancingo','Blvd. Miguel Hidalgo No. 901, Col. Santa Ana, C.P. 43660','Tel.: (775) 112 1090',13),(40,'La Estancia','Av. Vallarta No. 5455, Col. La Estancia, C.P. 45100, Zapopan.','Tel.: (33) 36-29-81-17/36-29-82-45',14),(41,'Mariano Otero','Av. Mariano Otero No.3450, Col. El Colli, C.P.45050, Zapopan.','Tel.: (33) 36-34-88-52',14),(42,'Palomar','Carretera Guadalajara-Morela No.1501, Col. La Tijera, C.P.45236, Tlacomulco de Zu¤iga.','Tel.: (33) 3612 6580',14),(43,'Puerto Vallarta','Francisco Medina Ascencio No.2880, Col. El Pitillal, C.P.48290','Tel.: (322) 2-25-00-05/22 5 15 54',14),(44,'Tlaquepaque','Av. Ni¤os H‚roes No.720, Col. Hidalgo, C.P. 45550','Tel.: (33) 3657 5706',14),(45,'Cd. Jardin','Av. Victor No. 3 esq. Bordo de Xochiaca, Col. Benito Ju rez, C.P.57000, Netzahualc¢yotl.','Tel.: (55) 57 35 51 80',15),(46,'Coacalco','V¡a Jos‚ L¢pez Portillo No.101-E, Col. Zacautitla, C.P.55700','Tel.: (55) 1039 0404 al 19',15),(47,'Cuatitl n Izcalli','Av. Primero de mayo No. 17, Col. Centro Urbano, C.P.54730','Tel.: (55) 5868 7246 al 52',15),(48,'Ixpaluca','Carretera M‚xico-Cuautla Km. 3, Col. Geovillas Sta. B rbara, C.P.56535','Tel.: ext. 150',15),(49,'Club Centenario','Autopista M‚xico-Quer‚taro No.3895 esq. Gustavo Baz Col. Centro Industrial Tlanepantla, C.P.54030, Tlanepantla de Baz',NULL,15),(50,'Las Am‚ricas','Av. Central esq. Primero de Mayo Mz-4 Lt-1, Col. Las Am‚ricas, C.P. 55075, Ecatepec de Morelos.','Tel.: (55) 24 86 40 33',15),(51,'Lomas Verdes','Av. Lomas Verdes 1200, 3a. y 4a. Secci¢n, Lomas Verdes, C.P.53120','Tel.: (55) 5349 3474',15),(52,'Perif‚rico Cuautitl n','Autopista M‚xico-Quer‚tero Km 36.5 lote 1 s/n, Col. Parque Industrial Cuamatla, C.P.54730','Tel.: (55) 2239 5019',15),(53,'Sat‚lite','Blvd. M‚xico-Quer‚taro No.1900 Col. Viveros de la Loma, C.P.54080, Tlanepantla.','Tel.: (55) 5325 0926',15),(54,'Tec mac','Carretera Federal M‚xico-Pachuca Km. 36.5, Col. San Jos‚ Hueyotenco.','Tel.: (55) 13 83 97 07',15),(55,'Hiperplaza Texcoco','Hidalgo No. 300, entre las calles Camino al Molino de las Flores y Libertad, Col. Santa Cruz de Arriba, C.P.56120, Texcoco',NULL,15),(56,'Toluca','Leona Vicario 502 Pte., La Pur¡sima, C.P.52140, Metepec.','Tel.: (722) 279 9929',15),(57,'Toltecas','Av. Hidalgo s/n esq. Av. Toltecas. Col. Ind. Tlaxcoapan, C.P. 54030, Tlanepantla de Baz.','Tel.: (55) 55-65-61-95',15),(58,'Tultepec','Carretera Cuatitl n-Tultepec No.2, Col. Terremoto. Cuatitl n Estado de M‚xico. C.P.54850',NULL,15),(59,'Zumpango','Carretera Zumpango-Los Reyes s/n, Col. Buenavista, C.P. 55635, Zumpango.','Tel.: (591) 918-5958',15),(60,'Morelia','Av. Enrique Ram¡rez No.420, Col. Chapultepec Oriente, C.P. 58260','Tel.: (433) -15-00-81',16),(61,'Uruapan','Blvd. Industrial No.1241, Col. Villa Uruapan, C.P.60120','Tel.: (452) 528-82-75/528-82-69',16),(62,'Cuatla','Camino Real Tetelcingo Calder¢n No. 23, Col. Tierra Larga, C.P. 62751','Tel.: (735) 122-22-69/122-22-70',17),(63,'Cuernavaca','Av. Vicente Guerrero No. 760, Col. Lomas de la Selva, C.P. 62270, Cuernavaca, Mor.','Tel.: (771) 329-02-00 Ext 125',17),(64,'Tepic','Blvd. Luis Donaldo Colosio No.680, Fracc. Benito Ju rez Oriente, C.P.63175, Tepic, Nay.','Tel.: (311) 1291713 al 20',18),(65,'Cerro de la Silla','Av. Pablo Livas No.2050, Col. La Quinta, C.P.67170, Guadalupe.',NULL,19),(66,'Gonzalitos','Av. J.E. Gonz lez No.400, Mitras Norte, C.P.64320, Monterrey.','Tel.: (81) 83-18-04-06',19),(67,'Las Torres','Av. Eugenio Garza Sada No.4950, Col. Las Brisas, C.P.64780, Monterrey.','Tel.: (81) 83 49 74 36',19),(68,'Miguel Alem n','Av. Miguel Alem n No.7000, Col. Torres de Linda Vista, C.P.67110, Guadalupe.','Tel.: (81) 83-34-68-68',19),(69,'San Nicol s','Avenida Sendero Divisorio No. 200 A, Col. Valle de las Alamedas, San Nicol s de los Garza.',NULL,19),(70,'Universidad Mty','Av. Universidad No.413, Col. El Roble, C.P.66450, San Nicol s de los Gaza.','Tel.: (81) 8332-9632 y 33',19),(71,'Oaxaca','Av. Universidad No. 601, Col. Exhacienda Candiani, C.P.68130','Tel.: (951) 506 0101 al 09',20),(72,'Forjadores','Av. Forjadores de Puebla No. 3401, Col. Cholula de Rivadavia C.P.72760, San Pedro Cholula.','Tel.: (22) 2329-1282',21),(73,'Hospital General','Lateral Sur de Perif‚rico Ecol¢gico No. 701 esq. 9 Sur, Col. Infonavit San Bartolo. C.P.72490, Puebla.',NULL,21),(74,'La Noria','Circuito Interior No. 1920, Col. Exhacienda La Noria, C.P.72410, Puebla','Tel.: (22) 2237 4513',21),(75,'Tehuac n','Calzada Adolfo L¢pez Mateos No.4000, Col. San Lorenzo Teotipilco, C.P.75855','Tel.: 5532-78-90',21),(76,'V¡a Capu','Blvd. H‚roes del 5 de mayo No. 2322, Col. Cleotilde Torres, C.P.72050, Puebla.','Tel.: (22) 2220 6565',21),(77,'Plaza de Toros','Hacienda Vegil No. 101, Col. El Jacal, C.P.76187, Quer‚taro.','Tel.: (442) 242 9148',22),(78,'Quer‚taro','Blvd. Bernardo Quintana No.4115, Col. Parques Industriales, C.P.76130, Quer‚taro.','Tel.: (442) 2-17-70-00',22),(79,'San Juan del R¡o','Av. Central No. 18, Fracc. Valle de Oro, C.P.76802','Tel.: (427) 26-4-81-62',22),(80,'Canc£n','Paseo Kukulc n S.M. 21, Manzana 2, Lote 2, C.P.77500','Tel.: (998) 881 0200',23),(81,'Cumbres','Blvd. Luis Donaldo Colosio S.M. 310, Manzana 1, Lote 4-2, C.P.77560','Tel.: (998) 882 0514',23),(82,'Chetumal','Carmen Ochoa de Merino No. 320, Col. Centro, C.P.77000','Tel.: (983) 129-31-19/129-31-20',23),(83,'Playa del Carmen','Balam Kanche Lote 2 C, 3 y 3 A, Manzana 30, Col. Playacar, C.P.77710','Tel.: (984) 803 5991',23),(84,'Rotonda','Monterrey No. 110 esq. Blvd. M‚xico-Laredo, Col. Tipzen, C.P.79050. Ciudad Valles','Tel.: (481)382-28-44 y 382-51-67',24),(85,'San Luis Potosi','Blvd. Salvador Nava No. 3135, colinas del Parque, C.P.78110','Tel.: (444) 841-53-50',24),(86,'Culiac n','Av. Regional No.1330 Sec. 5, La Etapa Plan Tres R¡os, C.P.80020','Tel.: (667) 7-59-02-00',25),(87,'Los Mochis','Blvd. Jiquilpan No.1112, Col. Jardines de F tima, C.P.81220, Municipio Ahome.','Tel.: (668) 8-12-25-26',25),(88,'Mazatl n','Av. Reforma No.2150, Fracc. Alameda, C.P.82123','Tel.: (669) 989-05-00',25),(89,'M‚xico 68','Av. µlvaro Obreg¢n No.2861, Fracc. Montebello, c.P.80227, Culiac n.','Tel.: (667) 146-03-97 al 404',25),(90,'Ciudad Obreg¢n','Calle 5 de Febrero No. 1074 Norte, Fracc. Comercial Zona Norte. C.P. 85010.','Tel.: (664) 414-71-79',26),(91,'Guaymas','Carr. Int. M‚xico-Nogales Km 1982 No. 1400, Col. Loma Linda, C.P.85420, Guaymas.','Tel.: (622) 2-210-687',26),(92,'Hermosillo','Paseo R¡o Sonora s/n entre Circuito Interior y Reforma, C.P. 83289.','Tel.: (662) 259-03-04',26),(93,'Guayabal','Prol. Paseo Usuamacinta No. 409, Col. Guayabal, C.P.86090.','Tel.: (993) 314 8012 al 21',27),(94,'Villahermosa','Anillo Perif‚rico esq. Ru¡z Cortines, Col. El Carrizal, Municipio.','Tel.: (993) 354-4002',27),(95,'Ciudad Victoria','Blvd. Adolfo L¢pez Mateos No. 150, Local A entre Calle 21 y 24, C.P.87049','Tel.: (834) 305-0713 al 20',28),(96,'Matamoros','Av. Pedro C rdenas No. 4995 b Km 2.5, Col. Amado Nervo, C.P.87396.','Tel.: (868) 8-17-81-41',28),(97,'Reynosa','Blvd. Hidalgo No. 1935, Col. Narciso Mendoza, C.P.88700','Tel.: (899) 180-05-11 al 17',28),(98,'Tampico','Av. Hidalgo No. 6112, Col. Arenal, C.P.89344.','Tel.: (833) 224-50-61',28),(99,'El Molinito','Carretera Apizaco-Puebla No.3 entre Ignacio Zaragoza y Reforma, Col. Tlatempan, C.P.90610, Apetatitlan de Antonio Carvajal.',NULL,29),(100,'Coatzacoalcos','Av. De las Palmas No. 100 esq. Carr. Minatitl n, Fracc. A del Predio R£stico Rancho Alegre, C.P.69558','Tel.: (921) 218 3460',30),(101,'C¢rdoba','Blvd. C¢rdoba-Fort¡n No. 4025, Col. Santa Leticia, C.P.94470, Fort¡n de las Flores.','Tel.: (271) 7-36-17-53 al 59',30),(102,'Poza Rica','Carretera a Cazones Km 50, Col. La Rueda, C.P.93303','Tel.: (782) 782 149 7385',30),(103,'Tuxpan','Carretera Tuxpan-Tampico No. 88 entre calle El Manguito y Camino a Juana Moza, Col. Las Granjas, C.P.92894',NULL,30),(104,'Veracruz','Av. Ej‚rcito Mexicano No. 26, Col. Ejido 1o de Mayo, C.P.91980, Boca del R¡o.','Tel.: (229) 989 0200',30),(105,'Xalapa','Carretera Xalapa-Veracruz No. 191, Fracc. Las Animas, C.P. 91190','Tel.: (228) 813 9454',30),(106,'M‚rida','Prol. Paseo Montejo No. 312, Fracc. Gonzalo Guerrero, C.P.97118','Tel.: (999) 944 9752',31),(107,'M‚rida Oriente','C-Seis No.400 A entre Av. Correa Racho y Calle 13, Col. D¡az Ordaz.','Tel.: (999) 196 2032',31),(108,'Zacatecas','Av. Francisco Garc¡a Salinas No. 1602, Fracc. Tahona, C.P. 98098','Tel.: (492) 8-99-12-03',32);
/*!40000 ALTER TABLE `club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `idEdo` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idEdo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Aguascalientes'),(2,'Baja California Norte'),(3,'Baja California Sur'),(4,'Campeche'),(5,'Coahuila'),(6,'Colima'),(7,'Chiapas'),(8,'chihuahua'),(9,'Distrito Federal'),(10,'Durango'),(11,'Guanajuato'),(12,'Guerrero'),(13,'Hidalgo'),(14,'Jalisco'),(15,'M‚xico'),(16,'Michoac n'),(17,'Morelos'),(18,'Nayarit'),(19,'Nuevo Le¢n'),(20,'Oaxaca'),(21,'Puebla'),(22,'Quer‚taro'),(23,'Quintana Roo'),(24,'San Luis Potos¡'),(25,'Sinaloa'),(26,'Sonora'),(27,'Tabasco'),(28,'Tamaulipas'),(29,'Tlaxcala'),(30,'Veracruz'),(31,'Yucat n'),(32,'Zacatecas');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gerente` (
  `idGerente` int(10) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `idClub` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idGerente`),
  KEY `idClub` (`idClub`),
  CONSTRAINT `gerente_ibfk_1` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
INSERT INTO `gerente` VALUES (101,'AGUIRRE MARTINEZ ISRAEL',1),(102,'BARUCH BALDERRAMA ANGEL',2),(103,'DIAZ GUTIERREZ PEDRO',3),(104,'FERNANDEZ BENITEZ ELIZABETH',4),(105,'GONZALEZ LOPEZ ALAN',5),(106,'LARA CARDENAS URIEL MAURICIO',6),(107,'LOPEZ GARCIA JAVIER',7),(108,'LOPEZ PADILLA DIEGO GERARDO',8),(109,'MORANTE TORRECILLAS JARITH ALY',9),(110,'OCHOA GARCIA HECTOR GABRIEL',10),(111,'RONQUILLO GIL MIGUEL ANGEL',11),(112,'RUIZ VICTORIA HUGO IVAN',12),(113,'VAZQUEZ PEREZ FERMIN TEUCTZINTLI',13),(114,'ZUNIGA SANCHEZ GERARDO',14),(115,'BALLESTEROS HERNANDEZ YOSELIN ANDREA',15),(116,'BARRIENTOS AGUILAR MARCO VINICIO',16),(117,'CANO MARTINEZ ADRIAN',17),(118,'CEVANTES ROMERO DOLORES',18),(119,'CHIA ACOSTA JORGE ALBERTO',19),(120,'DAVALOS GUIZAR HECTOR IVAN',20),(121,'DE LA ROSA MERCADO MARCO ANTONIO',21),(122,'DIMAS PEREZ JUAN RAMON',22),(123,'ESCAMILLA ARROYO ANGEL',23),(124,'ESTRADA GONZALEZ MARIA LUISA',24),(125,'GALVAN HERNANDEZ MARIA ISABEL',25),(126,'GARCIA CRUZ JOSE LUIS',26),(127,'GARCIA REYES NEFTALI',27),(128,'GODINEZ VAZQUEZ ELVIA',28),(129,'GOMEZ GODINEZ  GUADALUPE ALEJANDRA',29),(130,'GOMEZ GODINEZ SALVADOR MANUEL',30),(131,'GONZALEZ ARCEO BELEM IRERI',31),(132,'GONZALEZ HIPOLITO SERGIO ANDRES',32),(133,'GRACIAN HERNANDEZ SERGIO DEMIAN',33),(134,'GUERRERO TELLEZ JORGE',34),(135,'GUZMAN RIVERA MARCELA',35),(136,'HERNANDEZ AGUILAR CARLOS ALBERTO',36),(137,'HERNANDEZ ALAMILLA MIGUEL ANGEL',37),(138,'HERNANDEZ LOPEZ FILEMON',38),(139,'HERRERA HERNANDEZ JENIFFER',39),(140,'LARA HERNANDEZ ADRIANA',40),(141,'LICONA PEREZ NANCY YOLONTZIN',41),(142,'LOPEZ ISLAS MARIANA',42),(143,'LOPEZ LOPEZ MARCIAL',43),(144,'LOPEZ MARTINEZ HUGO',44),(145,'LUNA CRUZ DAVID',45),(146,'MARTINEZ CASTILLO MARIA REYNA',46),(147,'NARVAEZ FLORES MIREYA',47),(148,'NORIEGA GARCIA JORGE',48),(149,'OLGUIN FRIAS ANGEL GERONIMO',49),(150,'PELAEZ GIL RICARDO',50),(151,'PEREZ HUEBE MA. DE LOURDES',51),(152,'RAMIREZ AOKI GERARDO',52),(153,'REYES BELTRAN JUANA',53),(154,'RUIZ ALCANTARA ADA HAZAEL',54),(155,'SANCHEZ TAPIA CARLOS',55),(156,'SAUZA JIMENEZ DAMIAN',56),(157,'SOTO MC.NAUGTH ROCIO EDITH',57),(158,'TAPIA LOPEZ ARIANNA YANET',58),(159,'TELLEZ CORELLA PEDRO IVAN',59),(160,'TOVAR ESCORZA DENISSE',60),(161,'VALDEZ DOMINGUEZ LUIS',61),(162,'VARGAS MARTINEZ BLANCA ELIZABETH',62),(163,'VERTIZ HERNANDEZ CARLOS',63),(164,'VILLAMIL ESCUDERO MIRIAM',64),(165,'VILLEGAS LARA MAGIN',65),(167,'AGUILERA LUNA ORESTES JOB',66),(168,'BRITO CRUZ CARLOS EMMANUEL',67),(169,'CASTILLO GONZALEZ IVAN',68),(170,'CHAVEZ CRUZ DANIEL',69),(171,'COSIO MARTINEZ LUIS EDGAR',70),(172,'CRUZ CARLIN EFREN',71),(173,'ESPARZA GUERRERO LAURA EUNICE',72),(174,'GARCIA TORBELLIN RODRIGO',73),(175,'GOMEZ MARTINEZ MIRIAM AIDE',74),(176,'GONZALEZ ZAVALA HUGO CESAR',75),(177,'HERNANDEZ ORTIZ JESUS',76),(178,'HERRERA MARIN FRANCISCA',77),(179,'ISIDRO RAMIREZ RICARDO',78),(180,'JUAREZ MERCADO JUAN CARLOS',79),(181,'LEYRA LEON JONATHAN JORGE',80),(182,'LOPEZ HERNANDEZ YAZMIN SARAHI',81),(183,'LOPEZ OBREGON ERIKA LISSETTE',82),(184,'MARMOLEJO JIMENEZ ERICK IVAN',83),(185,'NAVARRETE RODRIGUEZ ROSA MARIA',84),(186,'ORTIZ JIMENEZ RAUL',85),(187,'PAZ GARCIA NOE MISAEL',86),(188,'PEDRAZA ALCALA JUAN CARLOS',87),(189,'RAMOS ACEVEDO GERMAN MANUEL',88),(190,'RINCON PEREZ JOSE ANTONIO',89),(191,'RIVERA GUILLEN DIEGO',90),(192,'RUIZ GUTIERREZ MARCO ANTONIO',91),(193,'SAGRERO DE LA CRUZ JONATHAN ISRAEL',92),(194,'SALAZAR NAVARRETE ALEJANDRO',93),(195,'SALAZAR OLGUIN LUCIA ALEJANDRA',94),(196,'SANCHEZ ALVAREZ LUIS',95),(197,'SANCHEZ ELIZALDE LIZBETH YESENIA',96),(198,'SANCHEZ VILLANUEVA JUAN',97),(199,'SANTOS ATENCO ADAN',98),(200,'TIZCAREÑO CERDA ANA BEATRIZ',99),(201,'URBINA JUAN JOSE MIGUEL',100),(202,'ALVARADO GARCIA ALBERTO YUSSEL',101),(203,'ANDRADE OLIVARES FRANK',102),(204,'BAUTISTA SANCHEZ ALBERTO DAVID',103),(205,'CHACON ARENAS LUIS ANTONIO',104),(206,'CHAVEZ LARA ABRAHAM',105),(207,'CORONA PEREZ PALOMA ERENDIRA',106),(208,'CRUZ OROZCO JONATHAN ABIMAEL',107),(209,'GARCIA MAYA EDGAR',108),(210,'GOMEZ CORNEJO IVONNE',1),(211,'GUEVARA HERNANDEZ LUIS AXELL',2),(212,'HUERTA BAEZA CESAR EZEQUIEL',3),(213,'LOPEZ SALVADOR ROBERT',4),(214,'LUNA CHAVERO DANIEL',5),(215,'MACIAS PE¥ALOZA ANA ROSA',6),(216,'MADRID LOPEZ HECTOR EDUARDO',7),(217,'MENDOZA TEJAS MIGUEL ANGEL',8),(218,'NAVARRETE CABALLERO DARIO',9),(219,'NIETO ROMERO XANAT ALEJANDRA',10),(220,'ORDAZ JAIMES CRISTHIAN JONATHAN',11),(221,'ORTIZ ARMENTA CESAR',12),(222,'ORTIZ BADILLO HECTOR MIGUEL',13),(223,'ORTIZ OCHOA IRVIN',14),(224,'PERALTA HERNANDEZ IRVING DAVID',15),(225,'PINEDA REYES GABRIELA IVONNE',16),(226,'PORTILLO PERCASTRE EFREN',17),(227,'RAMIREZ RUBIO ROGELIO',18),(228,'RODRIGUEZ GONZALEZ FERNANDO',19),(229,'SAMANO SANCHEZ EDGAR NIVER',20),(230,'SANCHEZ LOPEZ JESSICA JOSHELIN',21),(231,'SANCHEZ MENDOZA MARTIN RAYMUNDO',22),(232,'TREJO MORENO GERARDO',23),(233,'VALENCIA MARES JAVIER',24),(234,'VELAZQUEZ SANCHEZ CARLOS URIEL',25),(235,'ZARZA CARDIEL DAVID ENRIQUE',26),(236,'MARTINEZ DORANTES ANTONIO ALEJANDRO',27),(237,'HERNANDEZ GUERRERO JAVIER IRVING',28),(238,'ACOSTA CAUDILLO LIDIA',29),(239,'MANZANO CRUZ PEDRO',30),(240,'NAJERA VALDEZ EDGAR ROLANDO',31),(241,'SUAREZ ANDRADE HECTOR',32),(242,'ACEVES RESENDIZ JOSE EDUARDO',33),(243,'ACOSTA GOMEZ ALEJANDRO',34),(244,'BAUTISTA MORA LUIS ALBERTO',35),(245,'DIAZ HERNANDEZ FRANCISCO JAVIER',36),(246,'ESTRADA PAVIA JOSE ANTONIO',37),(247,'FLORES MONDRAGON HUGO MICHELLE',38),(248,'FRAGOSO COLIN JOSE EDUARDO',39),(249,'GALVAN ARCE MIGUEL SHAMID',40),(250,'GARCIA SOTO ELIAS ENRIQUE',41),(251,'GARFIAS QUIROZ GUSTAVO',42),(252,'GAYTAN ARCACIA HAZAEL',43),(253,'GOMEZ HERNANDEZ OMAR JESUS',44),(254,'GOMEZ ROBLES JORGE',45),(255,'GONZALEZ GUTIERREZ JESSICA MONSERRAT',46),(256,'GONZALEZ HERNANDEZ SAMUEL',47),(257,'GONZALEZ NU¥EZ EDGAR',48),(258,'GUERRERO VAZQUEZ FERNANDO',49),(259,'HERNANDEZ OYARZABAL MAURICIO FRANCISCO',50),(260,'JIMENEZ GONZALEZ GERARDO',51),(261,'LIMA RODRIGUEZ FAUSTO NOE',52),(262,'MARTINEZ GUTIERREZ IVAN',53),(263,'MARTINEZ LEDESMA LUIS MIGUEL',54),(264,'MORALES RAMIREZ EDGAR ANUAR',55),(265,'MU¥OZ MENDOZA JESSICA LIZBETH',56),(266,'NAVARRO CERVANTES JOSE ROGELIO',57),(267,'ROSAS ROJAS BRAULIO',58),(268,'RUIZ WENCE LUIS DAVID',59),(269,'TORRES FERNANDEZ YULIANA TERESA',60),(270,'VALLE BERNABE DARIO',61),(271,'VEGA ARELLANO JAIME PAOLO',62),(272,'AGUIRRE ENCISO GANDHI',63),(273,'ANGELES GONZALEZ MARIANA',64),(274,'ARROYO MENDOZA DANIEL',65),(275,'BASURTO ESQUIVEL YAEL',66),(276,'BASURTO SEGOVIA GABRIELA',67),(277,'BERNAL SALGADO ALBERTO',68),(278,'BURGOS MADRIGAL ANDREA',69),(279,'DIAZ CORTES DANIEL',70),(280,'FLORES VEGA ALFREDO',71),(281,'HERRERA PEREZ RODOLFO',72),(282,'LOPEZ MARRON RICARDO NESTOR',73),(283,'LOPEZ MARTINEZ JONATHAN',74),(284,'MARQUEZ MALPICA AURA JESSID',75),(285,'MARTINEZ LOPEZ YOSHIO ALEXIS',76),(286,'MARTINEZ PALMA OMAR',77),(287,'MARTINEZ RODRIGUEZ PABLO ALFONSO',78),(288,'MORALES PEDRAZA MIGUEL ANGEL',79),(289,'MORENO ENRIQUEZ ROBERTO',80),(290,'MORENO SANCHEZ JOSE ALBERTO',81),(291,'OLGUIN ARTEAGA KIRK ALBERTO',82),(292,'RAMIREZ RAMIREZ HUGO ALBERTO',83),(293,'RESENDIZ ORTEGA GUILLERMO',84),(294,'ROA GARCIA JOSE ARTURO',85),(295,'RODRIGUEZ MENDEZ CYNTHIA',86),(296,'SANCHEZ GOMEZ CHRISTIAN',87),(297,'TORRES CARRILLO RICARDO ISRAEL',88),(298,'VARGAS GODINEZ ANGEL',89),(299,'VENCES GUTIERREZ JORGE EDUARDO',90),(300,'VILLALBA VALDEZ JORGE ANTONIO',91),(301,'ZAVALA CORONA MISAEL ANTONIO',92);
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `idProducto` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idProveedor` int(10) unsigned NOT NULL,
  `precioUnitario` double NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FK_producto_1` (`idProveedor`),
  CONSTRAINT `FK_producto_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Galletas Lara Salada',22,30.9),(2,'Galletas Lara Dulce',22,21.9),(3,'Galletas Lara Saludable',22,26.6),(4,'Barcel Chips',22,3.99),(5,'Barcel Hot Nuts',22,2.99),(6,'Marinela Gansito',22,5),(7,'Marinela Pinguino',22,6),(8,'Marinela Submarino',22,6.95),(9,'Ricolino Bubulubu',22,12.95),(10,'Ricolino Paleta Payaso',22,8.45),(11,'Ricolino Kranky',22,6.3),(12,'Coronado Cajeta Vainilla',22,51.5),(13,'Coronado Cajeta Quemada',22,51.5),(14,'Coronado Cajeta Envinada',22,51.5),(15,'Suandy Galletas',22,13.9),(16,'Suandy Pasteles',22,57.35),(17,'Tia Rosa Bigotes',22,4.5),(18,'Tia Rosa Doraditas',22,5),(19,'Tia Rosa Empanadas',22,18.9),(20,'Tia Rosa Conchas',22,7),(21,'Tia Rosa Semitas',22,11.9),(22,'Tia Rosa Orejas',22,6),(23,'Tia Rosa Tortillinas',22,14.9),(24,'Sabritas Poffets',24,4.35),(25,'Sabritas Rancheritos',24,4.32),(26,'Sabritas Sabritones',24,4.55),(27,'Sabritas Sun Chips',24,4.55),(28,'Sabritas Tacos',24,4.35),(29,'Sabritas Tacos',24,4.53),(30,'Sabritas Tradicionales',24,4.55),(31,'Sabritas Churrumais',24,4.53),(32,'Sabritas Crujitos',24,4.55),(33,'Sabritas Fritos',24,4.32),(34,'Sabritas Rufles',24,4.32),(35,'Sabritas Doritos Nacho',24,4.52),(36,'Sabritas Cheetos',24,4.52),(37,'Axion Liquido',25,23.92),(38,'Axion Polvo',25,18.4),(39,'Axion Pasta',25,21.2),(40,'Ajax Bicloro',25,23.82),(41,'Ajax Spray',25,21.2),(42,'Ajax Fibras',25,21.2),(43,'Fabuloso Liquido',25,11.75),(44,'Magitel',25,19.5),(46,'Sony Bravia Full HD',27,17998),(47,'Sony Bravia',27,4699),(48,'Sony Televisor WEGA',27,15498),(49,'Sony PlayStation 3',27,8699),(50,'Sony PSP',27,3799),(51,'Horno Microondas AGE1072',30,1349),(52,'Horno Microondas AGE107W',30,1999),(53,'Refrigerador 4 puertas',30,4490),(54,'Refrigerador Side by Side',30,8999),(55,'Refrigerador Bottom Freezer',30,7299),(56,'Lavadora Carga Frontal WD6122CKC',30,6099),(57,'Lavadora Carga Frontal DV337AGG',30,7599),(58,'Lavadora Automatica WA90U3',30,8599),(59,'LG Celular GSM ME970',32,2322.42),(60,'LG Celular GSM ME230',32,4600),(61,'LG Celular GSM MG160',32,5129),(62,'Pasta Ojito',33,3.79),(63,'Pasta Letra',33,3.79),(64,'Pasta Lenteja',33,3.79),(65,'Pasta Estrella',33,3.79),(66,'Pasta Engrane',33,3.79),(67,'Pasta Monito',33,3.79),(68,'Pasta Fideo 0',33,4.2),(69,'Pasta Fideo 1',33,4.2),(70,'Pasta Fideo 2',33,4.2),(71,'Pasta Corta Codo 1',33,4.29),(72,'Pasta Corta Codo 2',33,4.29),(73,'Pasta Corta Caracol 1',33,4.1),(74,'Pasta Corta Caracol 2',33,4.1),(75,'Pasta Corta Tornillo',33,4.1),(76,'Pasta Larga Spaguetti Italiano',33,4.4),(77,'Pasta Larga Fetuccine',33,4.4),(78,'Sopa Milunch Camaron Picante',33,19.8),(79,'Sopa Milunch Camaron ',33,19.8),(80,'Sopa Milunch Pollo',33,19.8),(81,'Sopa Milunch Res',33,19.8);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `tel` varchar(25) NOT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (22,'Grupo Bimbo S.A de C.V.','01(55)52-68-65-85'),(24,'Sabritas','01(55)52-02-73-73'),(25,'Colgate-Palmolive','01(800)001-1400'),(27,'Sony Mexico','01(55)57-59-85-25'),(30,'Samsung Electronics Mexico','01(55)57-59-85-25'),(32,'LG Electronics México','01(55)57-58-67-56'),(33,'Grupo La Moderna','01(772)279-79-01');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedorsams`
--

DROP TABLE IF EXISTS `proveedorsams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedorsams` (
  `idClub` int(10) unsigned NOT NULL,
  `idProveedor` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idClub`,`idProveedor`),
  KEY `FK_proveedorsams_2` (`idProveedor`),
  KEY `idProveedor` (`idProveedor`),
  CONSTRAINT `proveedorsams_ibfk_1` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `proveedorsams_ibfk_2` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedorsams`
--

LOCK TABLES `proveedorsams` WRITE;
/*!40000 ALTER TABLE `proveedorsams` DISABLE KEYS */;
INSERT INTO `proveedorsams` VALUES (1,22),(8,22),(15,22),(22,22),(29,22),(36,22),(43,22),(50,22),(57,22),(64,22),(71,22),(78,22),(85,22),(92,22),(99,22),(106,22),(2,24),(9,24),(16,24),(23,24),(30,24),(37,24),(44,24),(51,24),(58,24),(65,24),(72,24),(79,24),(86,24),(93,24),(100,24),(107,24),(3,25),(10,25),(17,25),(24,25),(31,25),(38,25),(45,25),(52,25),(59,25),(66,25),(73,25),(80,25),(87,25),(94,25),(101,25),(108,25),(4,27),(11,27),(18,27),(25,27),(32,27),(39,27),(46,27),(53,27),(60,27),(67,27),(74,27),(81,27),(88,27),(95,27),(102,27),(5,30),(12,30),(19,30),(26,30),(33,30),(40,30),(47,30),(54,30),(61,30),(68,30),(75,30),(82,30),(89,30),(96,30),(103,30),(6,32),(13,32),(20,32),(27,32),(34,32),(41,32),(48,32),(55,32),(62,32),(69,32),(76,32),(83,32),(90,32),(97,32),(104,32),(7,33),(14,33),(21,33),(28,33),(35,33),(42,33),(49,33),(56,33),(63,33),(70,33),(77,33),(84,33),(91,33),(98,33),(105,33);
/*!40000 ALTER TABLE `proveedorsams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicio` (
  `idServicio` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Farmacia'),(2,'Foto Centro'),(3,'Joyer¡a'),(4,'¢ptica'),(5,'TMA Centro Llantero'),(6,'Service Deli'),(7,'BWM Banco Walt-Mart'),(8,'Punta del Cielo'),(9,'Servicio a Domicilio'),(10,'Apple shop');
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicioclub`
--

DROP TABLE IF EXISTS `servicioclub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicioclub` (
  `idServicio` int(11) DEFAULT NULL,
  `idClub` int(10) unsigned DEFAULT NULL,
  KEY `idServicio` (`idServicio`),
  KEY `idClub` (`idClub`),
  CONSTRAINT `servicioclub_ibfk_1` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `servicioclub_ibfk_2` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicioclub`
--

LOCK TABLES `servicioclub` WRITE;
/*!40000 ALTER TABLE `servicioclub` DISABLE KEYS */;
INSERT INTO `servicioclub` VALUES (1,1),(2,1),(3,1),(4,1),(9,1),(1,2),(2,2),(9,2),(1,3),(2,3),(3,3),(4,3),(9,3),(1,4),(2,4),(3,4),(4,4),(9,4),(1,5),(2,5),(3,5),(4,5),(9,5),(1,6),(2,6),(3,6),(4,6),(5,6),(6,6),(9,6),(1,7),(2,7),(3,7),(6,7),(9,7),(1,8),(2,8),(9,8),(1,9),(2,9),(5,9),(1,10),(2,10),(3,10),(4,10),(9,10),(1,11),(2,11),(3,11),(4,11),(5,11),(6,11),(9,11),(1,12),(2,12),(3,12),(4,12),(9,12),(1,13),(2,13),(3,13),(6,13),(1,14),(2,14),(6,14),(9,14),(1,15),(2,15),(3,15),(4,15),(5,15),(1,16),(2,16),(3,16),(9,16),(10,16),(1,17),(2,17),(3,17),(4,17),(1,18),(2,18),(3,18),(4,18),(9,18),(1,19),(2,19),(3,19),(4,19),(9,19),(1,20),(2,20),(3,20),(4,20),(5,20),(6,20),(8,20),(9,20),(10,20),(1,21),(2,21),(3,21),(4,21),(6,21),(9,21),(10,21),(1,22),(2,22),(3,22),(4,22),(6,22),(9,22),(1,23),(2,23),(3,23),(4,23),(9,23),(10,23),(1,24),(2,24),(3,24),(4,24),(6,24),(8,24),(9,24),(10,24),(1,25),(2,25),(3,25),(4,25),(9,25),(10,25),(1,26),(2,26),(3,26),(4,26),(6,26),(7,26),(9,26),(1,27),(2,27),(3,27),(4,27),(6,27),(9,27),(10,27),(1,28),(2,28),(3,28),(4,28),(5,28),(9,28),(1,29),(2,29),(3,29),(4,29),(5,29),(6,29),(9,29),(1,30),(2,30),(3,30),(4,30),(6,30),(9,30),(1,31),(5,31),(9,31),(1,32),(2,32),(3,32),(9,32),(1,33),(2,33),(3,33),(4,33),(9,33),(1,34),(2,34),(3,34),(4,34),(5,34),(6,34),(9,34),(1,35),(2,35),(3,35),(4,35),(5,35),(6,35),(9,35),(1,36),(2,36),(1,37),(2,37),(1,38),(2,38),(3,38),(4,38),(9,38),(1,39),(2,39),(7,39),(9,39),(1,40),(2,40),(3,40),(4,40),(9,40),(10,40),(1,41),(2,41),(3,41),(4,41),(7,41),(9,41),(1,42),(2,42),(3,42),(4,42),(6,42),(9,42),(1,43),(2,43),(3,43),(4,43),(6,43),(9,43),(1,44),(2,44),(3,44),(4,44),(7,44),(9,44),(1,45),(2,45),(3,45),(4,45),(9,45),(1,46),(2,46),(3,46),(4,46),(9,46),(1,47),(2,47),(3,47),(4,47),(5,47),(9,47),(1,48),(2,48),(3,48),(4,48),(6,48),(8,48),(9,48),(1,49),(2,49),(3,49),(6,49),(9,49),(1,50),(2,50),(3,50),(4,50),(6,50),(9,50),(1,51),(2,51),(3,51),(4,51),(6,51),(8,51),(9,51),(10,51),(1,52),(2,52),(3,52),(4,52),(6,52),(9,52),(1,53),(2,53),(3,53),(4,53),(6,53),(9,53),(1,54),(2,54),(3,54),(4,54),(6,54),(8,54),(9,54),(1,55),(2,55),(9,55),(1,56),(2,56),(3,56),(4,56),(6,56),(7,56),(9,56),(1,57),(2,57),(3,57),(4,57),(6,57),(9,57),(1,58),(2,58),(4,58),(6,58),(7,58),(9,58),(1,59),(2,59),(4,59),(1,60),(2,60),(3,60),(4,60),(9,60),(1,61),(2,61),(9,61),(1,62),(2,62),(3,62),(4,62),(6,62),(7,62),(8,62),(9,62),(1,63),(2,63),(3,63),(4,63),(6,63),(7,63),(9,63),(10,63),(1,64),(2,64),(3,64),(4,64),(5,64),(6,64),(9,64),(1,65),(2,65),(3,65),(4,65),(9,65),(1,66),(2,66),(3,66),(4,66),(5,66),(9,66),(10,66),(1,67),(2,67),(3,67),(4,67),(6,67),(9,67),(1,68),(2,68),(3,68),(4,68),(9,68),(1,69),(2,69),(3,69),(4,69),(6,69),(9,69),(1,70),(2,70),(3,70),(4,70),(6,70),(9,70),(1,71),(2,71),(3,71),(4,71),(9,71),(10,71),(1,72),(2,72),(9,72),(1,73),(2,73),(3,73),(4,73),(1,74),(2,74),(3,74),(4,74),(6,74),(9,74),(10,74),(1,75),(2,75),(9,75),(1,76),(2,76),(3,76),(4,76),(9,76),(1,77),(2,77),(3,77),(4,77),(6,77),(7,77),(9,77),(1,78),(2,78),(3,78),(4,78),(7,78),(9,78),(10,78),(1,79),(2,79),(6,79),(9,79),(1,80),(2,80),(3,80),(4,80),(9,80),(10,80),(1,81),(2,81),(3,81),(4,81),(6,81),(9,81),(1,82),(2,82),(3,82),(1,83),(2,83),(3,83),(5,83),(9,83),(1,84),(2,84),(7,84),(1,85),(2,85),(3,85),(4,85),(9,85),(1,86),(2,86),(3,86),(4,86),(9,86),(1,87),(2,87),(3,87),(4,87),(5,87),(9,87),(1,88),(2,88),(3,88),(4,88),(5,88),(9,88),(1,89),(2,89),(3,89),(4,89),(5,89),(6,89),(9,89),(1,90),(2,90),(3,90),(4,90),(5,90),(9,90),(1,91),(2,91),(9,91),(1,92),(2,92),(3,92),(4,92),(5,92),(9,92),(1,93),(2,93),(3,93),(4,93),(5,93),(9,93),(1,94),(2,94),(3,94),(4,94),(9,94),(10,94),(1,95),(2,95),(3,95),(4,95),(5,95),(9,95),(1,96),(2,96),(3,96),(4,96),(6,96),(9,96),(1,97),(2,97),(4,97),(6,97),(9,97),(1,98),(2,98),(3,98),(4,98),(6,98),(9,98),(1,99),(2,99),(3,99),(4,99),(5,99),(1,100),(2,100),(3,100),(4,100),(6,100),(9,100),(1,101),(2,101),(3,101),(4,101),(9,101),(1,102),(2,102),(1,104),(2,104),(3,104),(4,104),(6,104),(9,104),(10,104),(1,105),(2,105),(3,105),(4,105),(5,105),(9,105),(1,106),(2,106),(3,106),(4,106),(9,106),(10,106),(1,107),(2,107),(3,107),(4,107),(6,107),(9,107),(1,108),(2,108),(3,108),(4,108),(5,108),(9,108);
/*!40000 ALTER TABLE `servicioclub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socio` (
  `idSocio` int(10) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `tel` varchar(15) NOT NULL,
  PRIMARY KEY (`idSocio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
INSERT INTO `socio` VALUES (1,'ANGELES DE LA CRUZ ANDREE MICHEL IRVING','Av. Juarez. s/n','554-234-5555'),(2,'ARMENDARIZ CRUZ OCTAVIO ARTURO','Av. Juarez. s/n','554-234-5555'),(3,'CARVAJAL MURILLO ANA VICTORIA','Av. Juarez. s/n','554-234-5555'),(4,'CASTAÑEDA GONZALEZ LEONARDO MAURICIO','Av. Juarez. s/n','554-234-5555'),(5,'CASTILLO GONZALEZ IVAN','Av. Juarez. s/n','554-234-5555'),(6,'CRUZ CHAVEZ EDGAR DANIEL','Av. Juarez. s/n','554-234-5555'),(7,'FLORES GALICIA OMAR ISAIAS','Av. Juarez. s/n','554-234-5555'),(8,'GARCIA CERVANTES ANEL','Av. Juarez. s/n','554-234-5555'),(9,'GARCIA MORA OSVALDO','Av. Juarez. s/n','554-234-5555'),(10,'GOMEZ TETLALMATZI JESSICA','Av. Juarez. s/n','554-234-5555'),(11,'GONZALEZ ZAVALA HUGO CESAR','Av. Juarez. s/n','554-234-5555'),(12,'GUZMAN RIOS RAFAEL ANGEL','Av. Juarez. s/n','554-234-5555'),(13,'HERNANDEZ ESTRADA ALAN YESSAHIR','Av. Juarez. s/n','554-234-5555'),(14,'HERNANDEZ ROSAS DANIEL ALEJANDRO','Av. Juarez. s/n','554-234-5555'),(15,'LOPEZ OBREGON ERIKA LISSETTE','Av. Juarez. s/n','554-234-5555'),(16,'MONROY MORAN SERGIO ISRAEL','Av. Juarez. s/n','554-234-5555'),(17,'NAJER GONZALEZ LESSLYE ALY','Av. Juarez. s/n','554-234-5555'),(18,'OVIEDO ESPINOZA JOSAFAT','Av. Juarez. s/n','554-234-5555'),(19,'PEREZ MORALES MARCELA','Av. Juarez. s/n','554-234-5555'),(20,'PIMENTEL MARTINEZ WALDO','Av. Juarez. s/n','554-234-5555'),(21,'RAMIREZ JIMENEZ ALLAN CESAR','Av. Juarez. s/n','554-234-5555'),(22,'RAMIREZ MARTINEZ ERICK DANIEL','Av. Juarez. s/n','554-234-5555'),(23,'RIOS GASPAR IZCHEL NAYELI','Av. Juarez. s/n','554-234-5555'),(24,'RODRIGUEZ MARTINEZ ARTURO','Av. Juarez. s/n','554-234-5555'),(25,'ROMERO GALVAN LUIS ROBERTO','Av. Juarez. s/n','554-234-5555'),(26,'SANCHEZ MONROY OMAR ISRAEL','Av. Juarez. s/n','554-234-5555'),(27,'SORIA HIDALGO MANUEL ALEJANDRO','Av. Juarez. s/n','554-234-5555'),(28,'VAZQUEZ CARAVANTES EDUARDO','Av. Juarez. s/n','554-234-5555'),(29,'VILLA RUSSELL JUAN PABLO','Av. Juarez. s/n','554-234-5555'),(30,'LAGOS CAXNAJOY EDGAR JACOBO','Av. Juarez. s/n','554-234-5555'),(31,'ESPINOZA GIL ADALID','Av. Juarez. s/n','554-234-5555'),(32,'ANGELES RODRIGUEZ EDUARDO NERI','Av. Juarez. s/n','554-234-5555'),(33,'BECERRA ABARCA ALBERTO ISAIAS','Av. Juarez. s/n','554-234-5555'),(34,'BONILLA SANCHEZ MAURICIO EDUARDO','Av. Juarez. s/n','554-234-5555'),(35,'DEGOLLADO HERNANDEZ DANIEL','Av. Juarez. s/n','554-234-5555'),(36,'DEL PUERTO ZAMORA JULIO ALBERTO','Av. Juarez. s/n','554-234-5555'),(37,'DIAZ ARROYO SERGIO','Av. Juarez. s/n','554-234-5555'),(38,'DOMINGUEZ PALACIOS GERSON ISAAC','Av. Juarez. s/n','554-234-5555'),(39,'DURAND GONZALEZ ALDO','Av. Juarez. s/n','554-234-5555'),(40,'ESCARCEGA JAIME ANGEL OMAR','Av. Juarez. s/n','554-234-5555'),(41,'ESCOBAR PEREZ MIGUEL ANGEL','Av. Juarez. s/n','554-234-5555'),(42,'ESPINOSA BOYZO VICTOR','Av. Juarez. s/n','554-234-5555'),(43,'GARCIA MARTINEZ JESE ANDRES','Av. Juarez. s/n','554-234-5555'),(44,'GARCIA TORBELLIN RODRIGO','Av. Juarez. s/n','554-234-5555'),(45,'GIL PEREZ ANGEL ARMANDO','Av. Juarez. s/n','554-234-5555'),(46,'GONZALEZ DIAZ MARTIN AUGUSTO','Av. Juarez. s/n','554-234-5555'),(47,'GONZALEZ SANCHEZ MARS YUREN','Av. Juarez. s/n','554-234-5555'),(48,'GUERRERO ANDONAEGUI GABRIEL','Av. Juarez. s/n','554-234-5555'),(49,'HORTA GASCA KARLA JANIRA','Av. Juarez. s/n','554-234-5555'),(50,'LARA RUBI JULIO CESAR','Av. Juarez. s/n','554-234-5555'),(51,'LINARES ARVIZU JOSE DANIEL','Av. Juarez. s/n','554-234-5555'),(52,'MALDONADO HERNANDEZ JUAN RAFAEL','Av. Juarez. s/n','554-234-5555'),(53,'MENDEZ CANO ANGEL','Av. Juarez. s/n','554-234-5555'),(54,'MUÑOZ GOMEZ EDUARDO','Av. Juarez. s/n','554-234-5555'),(55,'OBISPO VARGAS SAUL','Av. Juarez. s/n','554-234-5555'),(56,'OLALDE SOTO JOSE CARLOS','Av. Juarez. s/n','554-234-5555'),(57,'PALMA GONZALEZ CARLOS EPHRA-IM','Av. Juarez. s/n','554-234-5555'),(58,'PEÑA LOPEZ DAVID ISSAI','Av. Juarez. s/n','554-234-5555'),(59,'ROJO SALAZAR JESUS NESTOR','Av. Juarez. s/n','554-234-5555'),(60,'SERRANO GARCIA JOSUE MARIO RAMON','Av. Juarez. s/n','554-234-5555'),(61,'VALENTIN LECHUGA EDDY','Av. Juarez. s/n','554-234-5555'),(62,'VAZQUEZ LAZCANO OSWALDO GIOVANI','Av. Juarez. s/n','554-234-5555'),(63,'VELAZQUEZ TORRES JAVIER JESUS','Av. Juarez. s/n','554-234-5555'),(64,'AGUILA PEREZ JUAN CARLOS','conocida','55-55-55-55-55'),(65,'AYALA HERNANDEZ EDUARDO','conocida','55-55-55-55-56'),(66,'BAUTISTA EUGENIO CESAR','conocida','55-55-55-55-57'),(67,'BAUTISTA SAN MARTIN SAUL ALEJANDRO','conocida','55-55-55-55-58'),(68,'CERON JAIME LUIS MAURICIO','conocida','55-55-55-55-59'),(69,'DAVILA VAZQUEZ MARIA MERCEDES','conocida','55-55-55-55-60'),(70,'DELGADILLO LORANCA ARTURO','conocida','55-55-55-55-61'),(71,'FLORES NAVA ERICK','conocida','55-55-55-55-62'),(72,'FRAGOSO LARIOS ALBERTO','conocida','55-55-55-55-63'),(73,'GALLARDO TORRES MICHELL IVAN','conocida','55-55-55-55-64'),(74,'GARCIA DEL CARMEN JOSE RAUL','conocida','55-55-55-55-65'),(75,'GARCIA FABIAN OSWALDO','conocida','55-55-55-55-66'),(76,'GARCIA VALERIO ALEJANDRA','conocida','55-55-55-55-67'),(77,'GONZALEZ ESTRADA HILDA','conocida','55-55-55-55-68'),(78,'GONZALEZ GALLEGOS ROSA ISELA','conocida','55-55-55-55-69'),(79,'IBA¥EZ JIMENEZ RODRIGO','conocida','55-55-55-55-70'),(80,'MANRIQUE PLASCENCIA ERIKA ADRIANA','conocida','55-55-55-55-71'),(81,'MARTINEZ DORANTES ANTONIO ALEJANDRO','conocida','55-55-55-55-72'),(82,'MENDOZA ANGELES FRANCISCO JAVIER','conocida','55-55-55-55-73'),(83,'MEZA GONZALEZ MANUEL','conocida','55-55-55-55-74'),(84,'MORENO SANCHEZ JOSE ALBERTO','conocida','55-55-55-55-75'),(85,'ONOFRE DIAZ JORGE ANTONIO','conocida','55-55-55-55-76'),(86,'ORTIZ DE LA CRUZ ROBERTO','conocida','55-55-55-55-77'),(87,'OSORIO FALCON RICARDO','conocida','55-55-55-55-78'),(88,'PADILLA CRISANTOS CARLOS GUADALUPE','conocida','55-55-55-55-79'),(89,'QUIROZ CRUZ EDGAR','conocida','55-55-55-55-80'),(90,'RAMIREZ HERNANDEZ EDGAR SAMUEL','conocida','55-55-55-55-81'),(91,'RIVAS BONILLA JESUS MARIO','conocida','55-55-55-55-82'),(92,'ROJAS CUANDON ALDO ABRAHAM','conocida','55-55-55-55-83'),(93,'SANCHEZ AVILA EDGAR DANIEL','conocida','55-55-55-55-84'),(94,'SANCHEZ CRUZ ERICKA','conocida','55-55-55-55-85'),(95,'SERRANO MONTIEL EDSEL','conocida','55-55-55-55-86'),(96,'TORRES HERNANDEZ OSCAR OSVALDO','conocida','55-55-55-55-87'),(97,'VILLANUEVA SOTO JOSE ANTONIO','conocida','55-55-55-55-88'),(98,'SANCHEZ PINEDA SALVADOR','conocida','55-55-55-55-89'),(99,'REYES DE LOS SANTOS MIGUEL ANGEL','conocida','55-55-55-55-90'),(100,'NAJERA HERNANDEZ ELIZABETH','conocida','55-55-55-55-91'),(101,'ALVARADO BAUTISTA ALBERTO ANGEL','conocida','55-55-55-55-92'),(102,'BOCANEGRA SOSA JOSE DE JESUS','conocida','55-55-55-55-93'),(103,'CORDERO ANTONIO DAVID JESUS','conocida','55-55-55-55-94'),(104,'CRUZ BIBIANO OSCAR','conocida','55-55-55-55-95'),(105,'ESPARZA REYES ALBERTO EFRAIN','conocida','55-55-55-55-96'),(106,'FERNANDEZ BENHUMEA JONATHAN','conocida','55-55-55-55-97'),(107,'FLORES OLVERA JOSE ANTONIO','conocida','55-55-55-55-98'),(108,'GALLARDO VALDOVINOS JESUS EDUARDO','conocida','55-55-55-55-99'),(109,'GARCIA EVANGELISTA SANTA ESMERALDA','conocida','55-55-55-55-100'),(110,'GONZALEZ ANGELES FABIAN GERARDO','conocida','55-55-55-55-101'),(111,'GUTIERREZ MENDOZA IVAN DANIEL','conocida','55-55-55-55-102'),(112,'GUZMAN LUCIANO LUIS ENRIQUE','conocida','55-55-55-55-103'),(113,'HERNANDEZ HERNANDEZ GUSTAVO','conocida','55-55-55-55-104'),(114,'HERNANDEZ SOSA HAZEL ANTONIO','conocida','55-55-55-55-105'),(115,'MARTINEZ GUTIERREZ JOSE ALFONSO','conocida','55-55-55-55-106'),(116,'MASTACHE CABALLERO JESUS','conocida','55-55-55-55-107'),(117,'MENDOZA MARTINEZ ERICK DANIEL','conocida','55-55-55-55-108'),(118,'MU¥OZ PAVIA ISRAEL','conocida','55-55-55-55-109'),(119,'RAMIREZ BARRIENTOS PABLO','conocida','55-55-55-55-110'),(120,'RAMOS REYNA DIEGO ARMANDO','conocida','55-55-55-55-111'),(121,'RANGEL ROBLEDO SAMUEL','conocida','55-55-55-55-112'),(122,'SANCHEZ GARCIA VICTOR ELIHU','conocida','55-55-55-55-113'),(123,'SANTANDER MAYA ARTURO CHRISTIAN','conocida','55-55-55-55-114'),(124,'SANTIAGO ALVAREZ CARLOS ROGELIO','conocida','55-55-55-55-115'),(125,'SOSA QUINTERO LUIS ALBERTO','conocida','55-55-55-55-116'),(126,'TINAJERO SANCHEZ ABRAHAM ALFONSO','conocida','55-55-55-55-117'),(127,'VARGAS NENGUA ROCIO GABRIELA','conocida','55-55-55-55-118'),(128,'VEGA RESENDIZ OSCAR ALEJANDRO','conocida','55-55-55-55-119'),(129,'GONZALEZ HERNANDEZ URIEL','conocida','55-55-55-55-120'),(130,'AVILA COLIN NURIA DEL CARMEN','conocida','55-55-55-55-121'),(131,'BLANCO ALVAREZ BRENDA','conocida','55-55-55-55-122'),(132,'COLIN CRUZ ERICK','conocida','55-55-55-55-123'),(133,'CRUZ CABALLERO PEDRO','conocida','55-55-55-55-124'),(134,'DELGADO GONZALEZ YAMIL OMAR','conocida','55-55-55-55-125'),(135,'FLORES RAMIREZ BLANCA LISET','conocida','55-55-55-55-126'),(136,'FUENTES ANGELES LINDA ISABEL','conocida','55-55-55-55-127'),(137,'GOMEZ GONZALEZ DAVID JORGE','conocida','55-55-55-55-128'),(138,'GOMEZ MENESES FERNANDO','conocida','55-55-55-55-129'),(139,'GUTIERREZ ALVAREZ HECTOR ALBER','conocida','55-55-55-55-130'),(140,'GUTIERREZ ORTINEZ EDUARDO','conocida','55-55-55-55-131'),(141,'HERNANDEZ SANCHEZ ABIGAIN ELISA','conocida','55-55-55-55-132'),(142,'HERNANDEZ SANTOS PEDRO EVERARDO','conocida','55-55-55-55-133'),(143,'JIMENEZ ORTEGA DANIEL','conocida','55-55-55-55-134'),(144,'LEON REYNOSO JOSE ROBERTO','conocida','55-55-55-55-135'),(145,'MEJIA URBINA ELIZABETH DEL CARMEN','conocida','55-55-55-55-136'),(146,'MORALES B·EZ JORGE','conocida','55-55-55-55-137'),(147,'MORENO AGUILLON CRISTHIAN OMAR','conocida','55-55-55-55-138'),(148,'NAVARRO CRUZ JULIO CESAR','conocida','55-55-55-55-139'),(149,'NIETO CASTILLO LIZBETH','conocida','55-55-55-55-140'),(150,'OLVERA PADILLA VICTOR IVAN','conocida','55-55-55-55-141'),(151,'PACHECO ANTONIO ARTURO','conocida','55-55-55-55-142'),(152,'PEREZ NICOLAS RAFAEL','conocida','55-55-55-55-143'),(153,'RAMIREZ AGUILAR JOSE GUADALUPE','conocida','55-55-55-55-144'),(154,'ROQUE MORALES JORGE','conocida','55-55-55-55-145'),(155,'SALAZAR PESQUERA FERNANDO','conocida','55-55-55-55-146'),(156,'SOLORIO BAUTISTA ABRAHAM','conocida','55-55-55-55-147'),(157,'VALDES HERNANDEZ GUSTAVO DANIEL','conocida','55-55-55-55-148'),(158,'VAZQUEZ GUTIERREZ KAREN MARISOL','conocida','55-55-55-55-149'),(159,'VAZQUEZ HERNANDEZ MIGUEL ANGEL','conocida','55-55-55-55-150'),(160,'ZARZA PEREZ ALDO HIGINIO','conocida','55-55-55-55-151'),(161,'ZEPEDA ALVAREZ JULIO CESAR','conocida','55-55-55-55-152'),(162,'ARVIZU MENDOZA AGUSTIN URIEL','conocida','55-55-55-55-153'),(163,'BRAVO ALVAREZ EDGAR','conocida','55-55-55-55-154'),(164,'CRUZ HERNANDEZ JOSE ALEJANDRO','conocida','55-55-55-55-155'),(165,'CRUZ SANCHEZ IVAN FRANCISCO','conocida','55-55-55-55-156'),(166,'DE HARO CASTILLO HECTOR','conocida','55-55-55-55-157'),(167,'FRANCO ROJAS ALEJANDRO','conocida','55-55-55-55-158'),(168,'GIL MALDONADO ANA LAURA','conocida','55-55-55-55-159'),(169,'HERMOSILLO GARCIA KAREN ADRIANA','conocida','55-55-55-55-160'),(170,'HERNANDEZ LOPEZ JOSE ROGELIO','conocida','55-55-55-55-161'),(171,'HERNANDEZ MEJIA JESUS ALEJANDRO','conocida','55-55-55-55-162'),(172,'HERNANDEZ MEJIA LUIS CARLOS','conocida','55-55-55-55-163'),(173,'JIMENEZ GALLEGOS OSCAR','conocida','55-55-55-55-164'),(174,'JOAQUIN SANDOVAL JOSE DOMINGO','conocida','55-55-55-55-165'),(175,'LANDERO REYES MAURICIO','conocida','55-55-55-55-166'),(176,'LINARES CERON JUAN ANTONIO','conocida','55-55-55-55-167'),(177,'MARTINEZ LUNA NICOLAS','conocida','55-55-55-55-168'),(178,'MARTINEZ PE¥A JESUS ADRIAN','conocida','55-55-55-55-169'),(179,'MORA REYES MARIO EDUARDO','conocida','55-55-55-55-170'),(180,'MORALES MACEDA ADRIANA','conocida','55-55-55-55-171'),(181,'ORTEGA GARCIA ABRAHAM','conocida','55-55-55-55-172'),(182,'PACHECO ESPINOSA JAIME','conocida','55-55-55-55-173'),(183,'PALMA BELTRAN FRANCISCO XAVIER','conocida','55-55-55-55-174'),(184,'PEREZ GONZALEZ RODRIGO ISRAEL','conocida','55-55-55-55-175'),(185,'PIEDRAS CARRILLO MIGUEL ANGEL','conocida','55-55-55-55-176'),(186,'REYES HERNANDEZ JUAN ALFREDO','conocida','55-55-55-55-177'),(187,'SALCEDO VAZQUEZ LUIS EDUARDO','conocida','55-55-55-55-178'),(188,'STREVEL PEREZ OLIVER CARLOS','conocida','55-55-55-55-179'),(189,'VALDIVIA LOZANO FERNANDO','conocida','55-55-55-55-180'),(190,'VARGAS BONILLA OMAR','conocida','55-55-55-55-181'),(191,'VAZQUEZ FLORES JORGE AARON','conocida','55-55-55-55-182'),(192,'VAZQUEZ NIEVES DANIEL AUGUSTO','conocida','55-55-55-55-183');
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socioclub`
--

DROP TABLE IF EXISTS `socioclub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socioclub` (
  `idSocio` int(10) unsigned NOT NULL,
  `idClub` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idSocio`,`idClub`),
  KEY `FK_sociosams_1` (`idClub`),
  CONSTRAINT `socioclub_ibfk_1` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idSocio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `socioclub_ibfk_2` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socioclub`
--

LOCK TABLES `socioclub` WRITE;
/*!40000 ALTER TABLE `socioclub` DISABLE KEYS */;
INSERT INTO `socioclub` VALUES (108,1),(109,1),(107,2),(110,2),(106,3),(111,3),(105,4),(112,4),(104,5),(113,5),(103,6),(114,6),(102,7),(115,7),(101,8),(116,8),(100,9),(117,9),(99,10),(118,10),(98,11),(119,11),(97,12),(120,12),(96,13),(121,13),(95,14),(122,14),(94,15),(123,15),(93,16),(124,16),(92,17),(125,17),(91,18),(126,18),(90,19),(127,19),(89,20),(128,20),(88,21),(129,21),(87,22),(130,22),(86,23),(131,23),(85,24),(132,24),(84,25),(133,25),(83,26),(134,26),(82,27),(135,27),(81,28),(136,28),(80,29),(137,29),(79,30),(138,30),(78,31),(139,31),(77,32),(140,32),(76,33),(141,33),(75,34),(142,34),(74,35),(143,35),(73,36),(144,36),(72,37),(145,37),(71,38),(146,38),(70,39),(147,39),(69,40),(148,40),(68,41),(149,41),(67,42),(150,42),(66,43),(151,43),(65,44),(152,44),(64,45),(153,45),(63,46),(154,46),(62,47),(155,47),(61,48),(156,48),(60,49),(157,49),(59,50),(158,50),(58,51),(159,51),(57,52),(160,52),(56,53),(161,53),(55,54),(162,54),(54,55),(163,55),(53,56),(164,56),(52,57),(165,57),(51,58),(166,58),(50,59),(167,59),(49,60),(168,60),(48,61),(169,61),(47,62),(170,62),(46,63),(171,63),(45,64),(172,64),(44,65),(173,65),(43,66),(174,66),(42,67),(175,67),(41,68),(176,68),(40,69),(177,69),(39,70),(178,70),(38,71),(179,71),(37,72),(180,72),(36,73),(181,73),(35,74),(182,74),(34,75),(183,75),(33,76),(184,76),(32,77),(185,77),(31,78),(186,78),(30,79),(187,79),(29,80),(188,80),(28,81),(189,81),(27,82),(190,82),(26,83),(191,83),(25,84),(192,84),(24,85),(23,86),(22,87),(21,88),(20,89),(19,90),(18,91),(17,92),(16,93),(15,94),(14,95),(13,96),(12,97),(11,98),(10,99),(9,100),(8,101),(7,102),(6,103),(5,104),(4,105),(3,106),(2,107),(1,108);
/*!40000 ALTER TABLE `socioclub` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-04 15:30:11
