-- MySQL dump 10.13  Distrib 5.1.31, for Win32 (ia32)
--
-- Host: localhost    Database: elektra
-- ------------------------------------------------------
-- Server version	5.1.31-community

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'TV, Video y Audio'),(2,'Videojuegos'),(3,'Foto y Videocamaras'),(4,'Telefonia'),(5,'Computo y oficina'),(6,'Linea Blanca'),(7,'Electrodomesticos'),(8,'Salud y Belleza'),(9,'Transporte'),(10,'Recamaras'),(11,'Comedor y Atecomedores'),(12,'Salas y Estancias'),(13,'Cocinas'),(14,'Deporte'),(15,'Ninios y Bebes'),(16,'Como lo vio en TV'),(17,'Promociones');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apPaterno` varchar(45) DEFAULT NULL,
  `apMaterno` varchar(45) DEFAULT NULL,
  `sexo` varchar(3) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'NURIA DEL CARMEN','AVILA','COLIN','F','nuria_12314@hotmail.com',3500),(2,'BRENDA','BLANCO','ALVAREZ','F','brendaloka_1990@hotmail.com',5800),(3,'ERICK','COLIN','CRUZ','M','colin_erick_vz@hotmail.com',4600),(4,'PEDRO','CRUZ','CABALLERO','M','perico33@hotmail.com',5000),(5,'YAMIL OMAR','DELGADO','GONZALEZ','M','yamildelgado@hotmail.com',7600),(6,'BLANCA LISET','FLORES','RAMIREZ','F','chitilis77@yahoo.com.mx',6400),(7,'LINDA ISABEL','FUENTES','ANGELES','F','fuenlin@hotmail.com',9220),(8,'DAVID JORGE','GOMEZ','GONZALEZ','M','jimang7@gmail.com',12400),(9,'HECTOR ALBERTO','GUTIERREZ','ALVAREZ','M','haga87@gmail.com',4600),(10,'EDUARDO','GUTIERREZ','ORTINEZ','M','edward.grenader@live.com.mx',7300),(11,'ABIGAIN ELISA','HERNANDEZ','SANCHEZ','F','linkin_park_eli_1@hotmail.com',8500),(12,'PEDRO EVERARDO','HERNANDEZ','SANTOS','M','NULL',8000),(13,'DANIEL','JIMENEZ','ORTEGA','M','exoskeletol@hotmail.com',9300),(14,'JOSE ROBERTO','LEON','REYNOSO','M','yase0209@gmail.com',13000),(15,'ELIZABETH DEL CARMEN','MEJIA','URBINA','F','the_hope1007@msn.com',6400),(16,'CRISTHIAN OMAR','MORENO','AGUILLON','M','bono_861@hotmail.com',5700),(17,'JULIO CESAR','NAVARRO','CRUZ','M','julcer164@hotmail.com',7500),(18,'LIZBETH','NIETO','CASTILLO','F','lizbeth_n15@hotmail.com',8300),(19,'VICTOR IVAN','OLVERA','PADILLA','M','vics_99@hotmail.com',11000),(20,'ARTURO','PACHECO','ANTONIO','M','yohopa@gmail.com',10500),(21,'RAFAEL','PEREZ','NICOLAS','M','NULL',6400),(22,'JOSE GUADALUPE','RAMIREZ','AGUILAR','M','jose_guadalupe_ra@hotmail.com',6570),(23,'JORGE','ROQUE','MORALES','M','jroquem0500@ipn.mx',5670),(24,'FERNANDO','SALAZAR','PESQUERA','M','coolfer88@gmail.com',9300),(25,'ABRAHAM','SOLORIO','BAUTISTA','M','eseoeleoereio@hotmail.com',13400),(26,'GUSTAVO DANIEL','VALDES','HERNANDEZ','M','themasterska@hotmail.com',4700),(27,'KAREN MARISOL','VAZQUEZ','GUTIERREZ','F','karen_princess_1408@hotmail.com',8500),(28,'MIGUEL ANGEL','VAZQUEZ','HERNANDEZ','M','vguajo13@hotmail.com',3800),(29,'ALDO HIGINIO','ZARZA','PEREZ','M','aldo.escom.ipn90@gmail.com',4600),(30,'JULIO CESAR','ZEPEDA','ALVAREZ','M','juliozpal@gmail.com',5400),(31,'FERNANDO','GOMEZ','MENESES','M','gomen83@hotmail.com',6000),(32,'AGUSTIN URIEL','ARVIZU','MENDOZA','M','loon_langley@hotmail.com',7300),(33,'EDGAR','BRAVO','ALVAREZ','M',NULL,10300),(34,'JOSE ALEJANDRO','CRUZ','HERNANDEZ','M','caballo_alex11@hotmail.com',8300),(35,'IVAN FRANCISCO','CRUZ','SANCHEZ','M','ifcs_ipn@yahoo.com.mx',3900),(36,'HECTOR','DE HARO','CASTILLO','M','de.haro.revolver@hotmail.com',6400),(37,'ALEJANDRO','FRANCO','ROJAS','M','chore_jas_mon@hotmail.com',8400),(38,'ANA LAURA','GIL','MALDONADO','F','hannah_bloosom@hotmail.com',12000),(39,'KAREN ADRIANA','HERMOSILLO','GARCIA','F','guerita_khg@hotmail.com',6300),(40,'JOSE ROGELIO','HERNANDEZ','LOPEZ','M','j.rogelio.hdz.lpz@hotmail.com',7200),(41,'JESUS ALEJANDRO','HERNANDEZ','MEJIA','M','j.alejandrohm@gmail.com',8100),(42,'LUIS CARLOS','HERNANDEZ','MEJIA','M','lucas.bond@hotmail.com',9300),(43,'OSCAR','JIMENEZ','GALLEGOS','M','b-goldberg@hotmail.com',13500),(44,'JOSE DOMINGO','JOAQUIN','SANDOVAL','M','joaquinbu@live.com.mx',7300),(45,'MAURICIO','LANDERO','REYES','M','roggers66@hotmail.com',8600),(46,'JUAN ANTONIO','LINARES','CERON','M','cero-jalc@hotmail.com',11800),(47,'NICOLAS','MARTINEZ','LUNA','M','demenstein@hotmail.com',7600),(48,'JESUS ADRIAN','MARTINEZ','PE¥A','M','hakdrian@hotmail.com',6100),(49,'MARIO EDUARDO','MORA','REYES','M','starguitar02@gmail.com',9800),(50,'ADRIANA','MORALES','MACEDA','F','adriux9@hotmail.com',14200),(51,'ABRAHAM','ORTEGA','GARCIA','M','aortega@usitio.com',10400),(52,'JAIME','PACHECO','ESPINOSA','M','james009.7@hotmail.com',5700),(53,'FRANCISCO XAVIER','PALMA','BELTRAN','M','churropalma@hotmail.com',4900),(54,'RODRIGO ISRAEL','PEREZ','GONZALEZ','M','sagitarius_ilusion@hotmail.com',5100),(55,'MIGUEL ANGEL','PIEDRAS','CARRILLO','M','mpiedras90@hotmail.com',8200),(56,'JUAN ALFREDO','REYES','HERNANDEZ','M','alfred_politecnico@hotmail.com',4900),(57,'LUIS EDUARDO','SALCEDO','VAZQUEZ','M','edusv88@hotmail.com',7230),(58,'OLIVER CARLOS','STREVEL','PEREZ','M','carlos56_dal@hotmail.com',9650),(59,'OMAR','VARGAS','BONILLA','M','omarva_16@hotmail.com',9370),(60,'JORGE AARON','VAZQUEZ','FLORES','M','aazher64@hotmail.com',8350),(61,'DANIEL AUGUSTO','VAZQUEZ','NIEVES','M','megadanx4@msn.com',6150),(62,'JOSE EDUARDO','ACEVES','RESENDIZ','M','ne_heeroblackq@hotmail.com',3800),(63,'ALEJANDRO','ACOSTA','GOMEZ','M','alexander_gom_aco@hotmail.com',4780),(64,'LUIS ALBERTO','BAUTISTA','MORA','M','iclirius.kain.d.p.s.v9@gmail.com',4240),(65,'FRANCISCO JAVIER','DIAZ','HERNANDEZ','M','packo_yeracko@hotmail.com',8450),(66,'JOSE ANTONIO','ESTRADA','PAVIA','M','dark_angel_hola@live.com.mx',9310),(67,'HUGO MICHELLE','FLORES','MONDRAGON','M','huguin9127@hotmail.com',5320),(68,'JOSE EDUARDO','FRAGOSO','COLIN','M','lalodj_17@hotmail.com',3730),(69,'MIGUEL SHAMID','GALVAN','ARCE','M','fryjool@hotmail.com',4000),(70,'ELIAS ENRIQUE','GARCIA','SOTO','M','correodeelias@yahoo.com.mx',5000),(71,'GUSTAVO','GARFIAS','QUIROZ','M','mushroom_rps_crew@hotmail.com',6000),(72,'HAZAEL','GAYTAN','ARCACIA','M','gaytan_525@hotmail.com',7000),(73,'OMAR JESUS','GOMEZ','HERNANDEZ','M','omar-1-0-@hotmail.com',8000),(74,'JORGE','GOMEZ','ROBLES','M','gomerudo@gmail.com',9000),(75,'JESSICA MONSERRAT','GONZALEZ','GUTIERREZ','F','blackdoll_rock@hotmail.com',10000),(76,'SAMUEL','GONZALEZ','HERNANDEZ','M','iamsamforyou90_04@hotmail.com',11000),(77,'EDGAR','GONZALEZ','NU¥EZ','M','yo_mero_666@hotmail.com',12000),(78,'FERNANDO','GUERRERO','VAZQUEZ','M','death_1689@hotmail.com',13000),(79,'MAURICIO FRANCISCO','HERNANDEZ','OYARZABAL','M','nightmare_mjj@hotmail.com',8290),(80,'GERARDO','JIMENEZ','GONZALEZ','M','numberone_gerald@hotmail.com',6234),(81,'FAUSTO NOE','LIMA','RODRIGUEZ','M','noe_315@msn.com',3867),(82,'IVAN','MARTINEZ','GUTIERREZ','M','ivanov.i@hotmail.com',7445),(83,'LUIS MIGUEL','MARTINEZ','LEDESMA','M','mike_lbm@hotmail.com',9732),(84,'EDGAR ANUAR','MORALES','RAMIREZ','M','edmora2005@hotmail.com',8363),(85,'JESSICA LIZBETH','MU¥OZ','MENDOZA','F','munozmj@live.com.mx',7255),(86,'JOSE ROGELIO','NAVARRO','CERVANTES','M','beathack@hotmail.com',8346),(87,'BRAULIO','ROSAS','ROJAS','M','brauliorosas@hotmail.com',7327),(88,'LUIS DAVID','RUIZ','WENCE','M','luisdavidruizwence@hotmail.com',6296),(89,'YULIANA TERESA','TORRES','FERNANDEZ','F','yuliana247@hotmail.com',5823),(90,'DARIO','VALLE','BERNABE','M','darisvalle@gmail.com',5239),(91,'JAIME PAOLO','VEGA','ARELLANO','M','pao02esc@gmail.com',5946),(92,'OSCAR','CRUZ','BIBIANO','M','oscar_hunter88@hotmail.com',5985),(93,'GANDHI','AGUIRRE','ENCISO','M','zerphan_0@hotmail.com',7249),(94,'MARIANA','ANGELES','GONZALEZ','F','mariana_sp_1@hotmail.com',8454),(95,'DANIEL','ARROYO','MENDOZA','M','coyote91_2@hotmail.com',8675),(96,'YAEL','BASURTO','ESQUIVEL','M','arctic_tree07@hotmail.com',7345),(97,'GABRIELA','BASURTO','SEGOVIA','F','redlight_tonight@hotmail.com',6934),(98,'ALBERTO','BERNAL','SALGADO','M','beto_chivas001@hotmail.com',6235),(99,'ANDREA','BURGOS','MADRIGAL','F','la_hija_ideal@hotmail.com',7346),(100,'DANIEL','DIAZ','CORTES','M','musca_dd@hotmail.com',5293),(101,'ALFREDO','FLORES','VEGA','M','alexanderluthor_luthorcorp@hotmail.com',7459),(102,'RODOLFO','HERRERA','PEREZ','M','roddy_03tigger@hotmail.com',12954),(103,'RICARDO NESTOR','LOPEZ','MARRON','M','thecuttermagician@hotmail.com',5834),(104,'JONATHAN','LOPEZ','MARTINEZ','M','nip0nb0y@hotmail.com',8563),(105,'AURA JESSID','MARQUEZ','MALPICA','F','electric_jessy@live.com.mx',8234),(106,'YOSHIO ALEXIS','MARTINEZ','LOPEZ','M','NBLCKNGHT@HOTMAIL.COM',7567),(107,'OMAR','MARTINEZ','PALMA','M','omar_exodo@hotmail.com',14545),(108,'PABLO ALFONSO','MARTINEZ','RODRIGUEZ','M','paluchines@hotmail.com',10546),(109,'MIGUEL ANGEL','MORALES','PEDRAZA','M','pedraza.1721@gmail.com',4524),(110,'ROBERTO','MORENO','ENRIQUEZ','M','robert_colagusano@hotmail.com',8234),(111,'JOSE ALBERTO','MORENO','SANCHEZ','M','alberto_muse_music@hotmail.com',9422),(112,'KIRK ALBERTO','OLGUIN','ARTEAGA','M','zithu@hotmail.com',8454),(113,'HUGO ALBERTO','RAMIREZ','RAMIREZ','M','hugo.alberto5000@hotmail.com',8123),(114,'GUILLERMO','RESENDIZ','ORTEGA','M','as_cubi@hotmail.com',8300),(115,'ARTURO','ROA','GARCIA','M','roa-sol_rukawa@hotmail.com',5234),(116,'CYNTHIA','RODRIGUEZ','MENDEZ','F','cyrome_3@hotmail.com',7345),(117,'CHRISTIAN','SANCHEZ','GOMEZ','M','crispian_evox@hotmail.com',7464),(118,'RICARDO ISRAEL','TORRES','CARRILLO','M','so_m_er90@hotmail.com',7356),(119,'ANGEL','VARGAS','GODINEZ','M','vga_angel@hotmail.com',5785),(120,'JORGE EDUARDO','VENCES','GUTIERREZ','M','ulugtun@hotmail.com',5675),(121,'JORGE ANTONIO','VILLALBA','VALDEZ','M','forastero_de_ultratumba@hotmail.com',5735),(122,'MISAEL ANTONIO','ZAVALA','CORONA','M','tony90_90@hotmail.com',7456),(123,'MAURICIO','ALONSO','MARES','M','mau_a_m@hotmail.com',4005),(124,'ARTURO','BLANCO','GONZALEZ','M','camus_58@hotmail.com',4067),(125,'JESUS','BRIZUELA','ALQUICIRA','M','jesus@brizuela.com.mx',7355),(126,'GUSTAVO','CALDERON','JUAREZ','M',NULL,4034),(127,'PASTOR ESTEBAN','CAMARGO','IGLESIAS','M','peci.peci@gmail.com',12343),(128,'IRMA ADAN','CARRILLO','ROSAS','F','irmool_cr_3@hotmail.com',11023),(129,'JUAN CARLOS','CASTRO','MORALES','M','casmore_8805@hotmail.com',3998),(130,'OMAR','CORTES','LANDEROS','M','ocorsnake01@hotmail.com',12988),(131,'FERNANDO','CRUZ','OJEDA','M','fer_0510_cho@hotmail.com',10834),(132,'JOEL','CRUZ','VELASCO','M','joel_8829@hotmail.com',6034),(133,'ANGEL OMAR','ESCARCEGA','JAIME','M','sonyomar@gmail.com',6344),(134,'DANIEL','FIERRO','GUTIERREZ','M','da5figu@hotmail.com',6877),(135,'NADIA LUCERO','FLORES','HERNANDEZ','F','nadia_flores13@hotmail.com',8434),(136,'KARINA JANET','GARCIA','BARRIENTOS','F','janeth_isis@hotmail.com',9664),(137,'FERNANDO SALATIEL','JIMENEZ','VAZQUEZ','M','neonc.16@gmail.com',8453),(138,'KAREN ALEJANDRA','JUAREZ','VAZQUEZ','F','alex_890917@hotmail.com',9633),(139,'RUTH HEIDI','LOPEZ','ALDERETE','F','heidi__170@hotmail.com',8453),(140,'MARCO ANTONIO','MARTINEZ','BEDOLLA','M','vampdant_marck@hotmail.com',6945),(141,'GABRIEL ERNESTO','MARTINEZ','ESCOBAR','M','capitanamerica2099@hotmail.com',6345),(142,'FERNANDO MANUEL','MONTES','OLVERA','M','nandom_mo@hotmail.com',4923),(143,'CHRISTIAN RICARDO','MORALES','CAYETANO','M','caye_ricardo_999@hotmail.com',4966),(144,'BENITO','PERDOMO','VERGARA','M','benito.perdomo.v@gmail.com',5298),(145,'JAHAZIEL','PEREZ','GONZALEZ','M','rokzzo@gmail.com',3866),(146,'PEDRO HAKIM','PEREZ','HERNANDEZ','M','krauser-zero@hotmail.com',12834),(147,'JUAN MANUEL','PEREZ','SANTOS','M','juanmaps@prodigy.net.mx',11834),(148,'DANIEL ARTURO','RAMOS','GARCIA','M','kratas_1202@hotmail.com',5342),(149,'GRISSEL XHARENY','ROCHA','GONZALEZ','F',NULL,5326),(150,'YAVE','RODRIGUEZ','JUAREZ','M','yave_rj89@hotmail.com',5340),(151,'DAVID','SANCHEZ','CORTES','M','DavidSC23@gmail.com',6990),(152,'ALEJANDRO','SERRANO','TAPIA','M','masteralemetal@hotmail.com',8344),(153,'JORGE BENJAMIN','SILVA','GONZALEZ','M','nimajeb@hotmail.com',6444),(154,'MIGUEL ANGEL','TORRES','GOVEA','M',NULL,5334),(155,'ALFONSO','TORRES','SOSA','M',NULL,3987),(156,'JORGE','VARELA','ROSAS','M','jorge_vr3@hotmail.com',6833),(157,'DAYANA MARISOL','VAZQUEZ','RESENDIZ','F','anayad_03@hotmail.com',5347),(158,'CRISTHIAN HECTOR','VAZQUEZ','RODRIGUEZ','M','cristhiantkd@hotmail.com',8234),(159,'RICARDO','VILLANUEVA','MARTINEZ','M','ricardozupra@yahoo.com.mx',8347),(160,'VICTOR ADRIAN','SOSA','HERNANDEZ','M','vzosa777@gmail.com',10284);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credito`
--

DROP TABLE IF EXISTS `credito`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `credito` (
  `idcredito` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  PRIMARY KEY (`idcredito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `credito`
--

LOCK TABLES `credito` WRITE;
/*!40000 ALTER TABLE `credito` DISABLE KEYS */;
INSERT INTO `credito` VALUES (1,'Basico',5000),(2,'Preferente',9000),(3,'Consentido',13000),(4,'Dorado',18000);
/*!40000 ALTER TABLE `credito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `pago` (
  `idPago` int(11) NOT NULL,
  `idcredito` int(11) DEFAULT NULL,
  `idtienda` int(11) DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `fechaPago` date DEFAULT NULL,
  `idproducto` int(11) NOT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idcredito` (`idcredito`),
  KEY `idtienda` (`idtienda`),
  KEY `idcliente` (`idcliente`),
  KEY `idproducto` (`idproducto`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`idcredito`) REFERENCES `credito` (`idcredito`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pago_ibfk_2` FOREIGN KEY (`idtienda`) REFERENCES `tienda` (`idtienda`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pago_ibfk_3` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pago_ibfk_4` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (1,1,120,2,'2010-03-01',1),(2,2,127,4,'2010-03-02',2),(3,3,131,6,'2010-03-03',3),(4,4,175,8,'2010-03-04',4),(5,1,196,10,'2010-03-05',5),(6,2,197,12,'2010-03-06',6),(7,3,770,14,'2010-03-07',7),(8,4,822,16,'2010-03-08',8),(9,1,1037,18,'2010-03-09',9),(10,2,1229,20,'2010-03-10',10),(11,3,1342,22,'2010-03-11',11),(12,4,1397,24,'2010-03-12',12),(13,1,1449,26,'2010-03-13',13),(14,2,1504,28,'2010-03-14',14),(15,3,1514,30,'2010-03-15',15),(16,4,1531,32,'2010-03-16',16),(17,1,1656,34,'2010-03-17',17),(18,2,1768,36,'2010-03-18',18),(19,3,1769,38,'2010-03-19',19),(20,4,2005,40,'2010-03-20',20),(21,1,2006,42,'2010-03-21',21),(22,2,2046,46,'2010-03-22',22),(23,3,2048,48,'2010-03-23',23),(24,4,2090,50,'2010-03-24',24),(25,1,2150,52,'2010-03-25',25),(26,2,2209,54,'2010-03-26',26),(27,3,2212,56,'2010-03-27',27),(28,4,2230,58,'2010-03-29',28),(29,1,2273,60,'2010-03-30',29),(30,2,2367,62,'2010-03-31',30),(31,3,2383,64,'2010-03-01',1),(32,4,2394,66,'2010-03-02',2),(33,1,2516,68,'2010-03-03',3),(34,2,3010,70,'2010-03-04',4),(35,3,3049,72,'2010-03-05',5),(36,4,3052,74,'2010-03-06',6),(37,1,3117,76,'2010-03-07',7),(38,2,3120,78,'2010-03-08',8),(39,3,3450,80,'2010-03-09',9),(40,4,3748,82,'2010-03-10',10),(41,1,3751,84,'2010-03-11',11),(42,2,4607,86,'2010-03-12',12),(43,3,4680,88,'2010-03-13',13),(44,4,4830,90,'2010-03-14',14),(45,1,5076,92,'2010-03-15',15),(46,2,5116,94,'2010-03-16',16),(47,3,5212,96,'2010-03-17',17),(48,4,5429,98,'2010-03-18',18),(49,1,5445,100,'2010-03-19',19),(50,2,5568,103,'2010-03-20',20),(51,3,5830,105,'2010-03-21',21),(52,4,5843,107,'2010-03-22',22),(53,1,5940,109,'2010-03-23',23),(54,2,6012,110,'2010-03-24',24),(55,3,6014,113,'2010-03-25',25),(56,4,6099,115,'2010-03-26',26),(57,1,6103,117,'2010-03-27',27),(58,2,6117,119,'2010-03-28',28),(59,3,6544,120,'2010-03-29',29),(60,4,6622,123,'2010-03-30',30),(61,1,6761,125,'2010-03-31',1),(62,2,7391,127,'2010-03-01',2),(63,3,7579,129,'2010-03-02',3),(64,4,7582,130,'2010-03-03',4),(65,1,7689,133,'2010-03-04',5),(66,2,7977,135,'2010-03-05',6),(67,3,8332,137,'2010-03-06',7),(68,4,9380,139,'2010-03-07',8),(69,1,9637,140,'2010-03-08',9),(70,2,9843,143,'2010-03-09',10),(71,3,120,3,'2010-03-01',1),(72,4,127,5,'2010-03-02',2),(73,1,131,7,'2010-03-03',3),(74,2,175,9,'2010-03-04',4),(75,3,196,11,'2010-03-05',5),(76,4,197,13,'2010-03-06',6),(77,1,770,15,'2010-03-07',7),(78,2,822,17,'2010-03-08',8),(79,3,1037,19,'2010-03-09',9),(80,4,1229,21,'2010-03-10',10),(81,1,1342,23,'2010-03-11',11),(82,2,1397,25,'2010-03-12',12),(83,3,1449,27,'2010-03-13',13),(84,4,1504,29,'2010-03-14',14),(85,1,1514,31,'2010-03-15',15),(86,2,1531,33,'2010-03-16',16),(87,3,1656,35,'2010-03-17',17),(88,4,1768,37,'2010-03-18',18),(89,1,1769,39,'2010-03-19',19),(90,2,2005,41,'2010-03-20',20),(91,3,2006,43,'2010-03-21',21),(92,4,2046,45,'2010-03-22',22),(93,1,2048,47,'2010-03-23',23),(94,2,2090,49,'2010-03-24',24),(95,3,2150,51,'2010-03-25',25),(96,4,2209,53,'2010-03-26',26),(97,1,2212,55,'2010-03-27',27),(98,2,2230,57,'2010-03-29',28),(99,3,2273,59,'2010-03-30',29),(100,4,2367,61,'2010-03-31',30),(101,1,2383,63,'2010-03-01',1),(102,2,2394,65,'2010-03-02',2),(103,3,2516,67,'2010-03-03',3),(104,4,3010,69,'2010-03-04',4),(105,1,3049,71,'2010-03-05',5),(106,2,3052,73,'2010-03-06',6),(107,3,3117,75,'2010-03-07',7),(108,4,3120,77,'2010-03-08',8),(109,1,3450,79,'2010-03-09',9),(110,2,3748,81,'2010-03-10',10),(111,3,3751,83,'2010-03-11',11),(112,4,4607,85,'2010-03-12',12),(113,1,4680,87,'2010-03-13',13),(114,2,4830,89,'2010-03-14',14),(115,3,5076,91,'2010-03-15',15),(116,4,5116,93,'2010-03-16',16),(117,1,5212,95,'2010-03-17',17),(118,2,5429,97,'2010-03-18',18),(119,3,5445,99,'2010-03-19',19),(120,4,5568,101,'2010-03-20',20),(121,1,5830,102,'2010-03-21',21),(122,2,5843,104,'2010-03-22',22),(123,3,5940,106,'2010-03-23',23),(124,4,6012,108,'2010-03-24',24),(125,1,6014,111,'2010-03-25',25),(126,2,6099,112,'2010-03-26',26),(127,3,6103,114,'2010-03-27',27),(128,4,6117,116,'2010-03-28',28),(129,1,6544,118,'2010-03-29',29),(130,2,6622,121,'2010-03-30',30),(131,3,6761,122,'2010-03-31',1),(132,4,7391,124,'2010-03-01',2),(133,1,7579,126,'2010-03-02',3),(134,2,7582,128,'2010-03-03',4),(135,3,7689,131,'2010-03-04',5),(136,4,7977,132,'2010-03-05',6),(137,1,8332,134,'2010-03-06',7),(138,2,9380,136,'2010-03-07',8),(139,3,9637,138,'2010-03-08',9),(140,4,9843,141,'2010-03-09',10);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(400) DEFAULT NULL,
  `precioUnitario` double DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `idsubcategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `idsubcategoria` (`idsubcategoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idsubcategoria`) REFERENCES `subcategoria` (`idsubcategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'TV LCD LG 22\"','Esta tecnolog¡a optimiza el brillo en im genes en movimiento permitiendo una imagen clara en fondo oscuro. El monitor analiza la entrada del contenido y autom ticamente ajusta el contraste.',4199,'LG',1),(2,'TV LCD Full HD SONY 32\"','Un televisor con la mejor calidad de imagen Full HD. Es ideal para conectarla a tu Blu-Ray o consola PS3. Nunca antes hab¡as experimentado vivir los contrastes de una forma tan real.',8999,'SONY',1),(3,'TV LCD SONY 32\"','Pantalla de alta definici¢n que te dar  excelente contraste de colores. Aprov‚chala al m ximo conect ndo un DVD escalador con cable HDMi.',7499,'SONY',1),(4,'TV LCD SAMSUNG 32\"','En una LCD podr s encontrar los colores m s n¡tidos y los contrastes m s definidos para darte toda una experiencia de video.',6999,'SAMSUNG',1),(5,'TV LCD Full HD PANASONIC 42\"','Disfruta los incre¡bles colores y la nitidez de la imagen que te brinda este televisor LCD, acabado tipo piano, resoluci¢n 720 pixeles, sensor de luz, equipado con sintonizador de alta definici¢n y un magn¡fico sonido digital.',13999,'PANASONIC',1),(6,'DVD Escalador LG','Excelente reproductor multiformato. Reproducci¢n Full HD 1080 con cable HDMI. Cuenta con USB Recording para grabaci¢n directa de CD a USB. Funci¢n Karaoke para que cantes con tus amigos y familia, podr s calificar su participaci¢n con fanfarrias.',899,'LG',2),(7,'DVD Escalador PANASONIC','Reproductor DVD escalador con alta calidad en imagen con convertidor 1080 p¡xeles y reproducci¢n multi formato, cuenta con entradas HDMI y control de consumo el‚ctrico adem s podr s visualizar tus im genes favoritas en formato JPEG con m£sica MP3.',799,'PANASONIc',2),(8,'DVD Reproductor SONY','Una excelente opci¢n para los exigentes en calidad. Puedes ver tus videos y fotos, escuchar Mp3, todo en un solo reproductor. Cuenta con sonido digital.',589,'SONY',2),(9,'Radiograbadora con MP3 SONY','Podr s escuchar Mp3, Cassette y radio. Incluye entrada para micr¢fono y auxiliar.Con pantalla LCD y control remoto. Disfrutar s al m ximo tu m£sica con potencia de sonido de 200W.Graba desde el disco al USB o Mp3.',2999,'SONY',3),(10,'Reproductor Mp4 8GB EKT','Un dise¤o port til de audio y video. Escucha tus canciones preferidas a donde tu quieras y a la hora que tu quieras.',499,'EKT',3),(11,'Reproductor Mp3 4GB SONY','Nuevo reproductor SONY MP3 con capacidad en memoria de 4 GB, reproduce tus formatos preferidos (MP3, WMA, AAC) con una excelente calidad. Equipado con pantalla LCD en 3 l¡neas con grabador de voz y FM. Pasa horas de m£sica continua.',999,'SONY',3),(12,'Minicomponente 13000 watts LG','Con base para Ipod que te permite escuchar tu m£sica preferida a todo volumen, ideal para grandes dimensiones. Crea las mejores fiestas con este minicomponente.',7399,'LG',4),(13,'Minicomponente 5100 watts SONY','Minicomponente con 3 bocinas SONY de 5100 watts de potencia compatible con CD, MP3, CD RW, WMA y USB. Cuenta con un excelente sistema de sonido gracias a un subwoofer. Capacidad en bandeja de 3 discos, incluye pr ctico control remoto.',3799,'SONY',4),(14,'Teatro en casa LG','Vive la m xima experiencia del cine en casa, con la mayor calidad de imagen y sonido est‚reo. Dise¤o elegante color negro, cuenta con escalador a FULL HD (1080 pixeles) con conectividad HDMI. Deja de ver pel¡culas y empieza a vivirlas.',3799,'LG',4),(15,'Consola XBOX 360 Edicion de coleccion HALO REACH','Consola de videojuegos Xbox 360. Inspirada en el videojuego HALO REACH ahora con un novedoso dise¤o m s delgado, equipada con 250 GB de disco duro y Wi-Fi para una conexi¢n m s f cil al mundo de Xbox LIVE. Edici¢n Limitada.',6699,'XBOX',6),(16,'Consola portatil PSP GO','Desc£bre un mundo lleno de aventura con el control en tu mano. PSP va m s all  de un videojuego port til, accesa a fotograf¡as, m£sica y videos con s¢lo un bot¢n. Conectividad Bluetooth y acceso a skype.',4700,'SONY',6),(17,'Camara Digital 12 Megapixeles KODAK','C mara digital KODAK ,12 megapixeles. Gracias a su funci¢n share podr s subir tus fotos a tu computadora y a internet, pantalla 2.7 pulgadas, zoom ¢ptico 3X, funci¢n de captura inteligente, reconocimiento de rostros, con un nuevo dise¤o y color novedoso.',1799,'KODAK',8),(18,'Camara Digital 14 Megapixeles SONY','C mara Digital SONY con pantalla LCD de 3 pulgadas, entre sus principales caracter¡sticas, cuenta con memoria interna y memoria externa, face motion detection, touch panel, correcci¢n de ojos rojos y smile shutter. Puedes obtener fotos panor micas.',5499,'SONY',8),(19,'Videocamara Disco Duro SONY','Una videoc mara con memoria interna y entrada para tarjeta, te ayuda a tener los mejores momentos de tu vida siempre guardados.',5999,'SONY',9),(20,'Refrigerador 25 pies WHIRLPOOL','Elegante color Natural Silver con Gabinete negro, despachador mec nico de agua y hielo, caj¢n de frutas y verduras con cubiertas de cristal, parrillas Spillguard, canastilla en congelador y contrapuertas con trivets blancos. ',10999,'WHIRLPOOL',12),(21,'Frigobar 3.7 pies GE','Creado para los solteros o para la oficina, este frigobar te proporcionar  la practicidad que siempre buscaste. Cuenta con chapa de seguridad, 3 multiniveles en parrillas, 2 hieleras y, por si fuera poco, su funcionamiento es completamente ecol¢gico.',2899,'GE',12),(22,'Congelador horizontal 7 pies FRIGIDAIRE','Gracias a sus 7 pies c£bicos de capacidad resulta perfecto para espacios reducidos, luz de encendido y canastillas para organizar tus alimentos congelados. ­Creado para el emprendedor que eres!',4299,'FRIGIDAIRE',12),(23,'Centro de lavado 14 kilos FRIGIDAIRE','Suspensi¢n Sure-Spin, motor de 3/4 HP, 2 combinaciones de velocidad de agitaci¢n y/o centrifugado, sistema de secado balanceado, ciclos de secado autom tico y por tiempo, temporizador de 100 minutos, opci¢n de enfriamiento y de ahorro de planchado.',11999,'FRIGIDAIRE',13),(24,'Recamara minimalista modelo ZENNY','Rec mara estilo minimalista tama¤o king size fabricada en madera de pino. Cuenta con mueble de televisi¢n en acabado de laca semi mate que le dar  un toque moderno a tu habitaci¢n. Ideal para espacios amplios.',6599,'SIN MARCA',30),(25,'Recamara contemporanea modelo NIZA','Rec mara estilo contempor neo tama¤o king size, fabricada con madera industrializada color chocolate. Consta de cabecera, bur¢s, c¢moda y luna. Ideal para espacios medianos. NO incluye base, colch¢n ni art¡culos de ambientaci¢n.',4799,'KAPESI',30),(26,'Colchon matrimonial AMERICA modelo QUALITY','Colch¢n matrimonial, tapizado en jacquard importado, tecnolog¡a Perfect-Side y Pillow-Top, acojinamiento en suaves placas de poliuretano. Tratado con sistema antibacterial que impide de forma duradera el crecimiento de bacterias y hongos.',1999,'AMERICA',32),(27,'Colchon Matrimonial SPRING AIR modelo SUPREME','Colch¢n matrimonial con sistema de no vuelta, tapizado en jacquard de importaci¢n. Cuenta con zonas de confort, soporte y durabilidad para un mejor descanso. Construido en su interior con 325 resortes de acero.',2249,'SPRING AIR',32),(28,'Colchon matrimonial RESTONIC modelo MOSQUITO FREE','Colch¢n tama¤o matrimonial tapizado en jacquard nacional color lila con colchoneta tipo pillow top. Gracias a su sistema anti-mosquito podr s disfrutar de un mayor descanso con la comodidad que tu cuerpo se merece liber ndote de los molestos mosquitos.',1949,'RESTONIC',32),(29,'Eliptica B.H. BRISA','Ejercita todo tu cuerpo con un suave y agradable movimiento el¡ptico, que no lastima tus articulaciones. Fija objetivos claros y obt‚n resultados con la prueba de grasa corporal y la lectura de pulsaciones, que permite medir las calor¡as consumidas.',4999,'BH',43),(30,'Caminadora BH modelo PIONEER CLASSIC','Caminadora con amplio espacio para correr o caminar, gracias a su banda ancha. Ideal para ejercitarse en la comodidad de su hogar. Potente motor de 2.5 CV para un suave andar. Programas de entrenamiento en amigable monitor.',7999,'BH',43);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategoria`
--

DROP TABLE IF EXISTS `subcategoria`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `subcategoria` (
  `idsubcategoria` int(11) NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `idcategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsubcategoria`),
  KEY `idcategoria` (`idcategoria`),
  CONSTRAINT `subcategoria_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `subcategoria`
--

LOCK TABLES `subcategoria` WRITE;
/*!40000 ALTER TABLE `subcategoria` DISABLE KEYS */;
INSERT INTO `subcategoria` VALUES (1,'Televisores',1),(2,'DVD y Blu-Ray',1),(3,'Audio Portatil',1),(4,'Audio en Casa',1),(5,'Audio y Video en el Auto',1),(6,'Consolas',2),(7,'Videojuegos',2),(8,'Camaras Digitales',3),(9,'Videocamaras',3),(10,'Celulares',4),(11,'Equipos de Computo',5),(12,'Refrigeradores',6),(13,'Lavadoras y Centros de Lavado',6),(14,'Secadoras de Ropa',6),(15,'Estufas y Parrillas',6),(16,'Campanas',6),(17,'Hornos',6),(18,'Climas y Calefaccion',6),(19,'Lavavajillas',6),(20,'Despachadores y Purificadores de Agua',6),(21,'Planchado y Costura',7),(22,'Preparacion de Alimentos',7),(23,'Preparacion de Bebidas',7),(24,'Utensilios de Cocina',7),(25,'Equipos de Limpieza',7),(26,'Cuidado Personal',8),(27,'Italika',9),(28,'Bicicletas',9),(29,'Accesorios Autos',9),(30,'Recamaras',10),(31,'Bases de Camas y Literas',10),(32,'Colchones y Boxes',10),(33,'Roperos y Comodas',10),(34,'Comedores',11),(35,'Antecomedores',11),(36,'Buffet y Vitrinas',11),(37,'Salas',12),(38,'Salas Piezas Sueltas',12),(39,'Libreros y Centros de Entretenimiento',12),(40,'Sofa, Cama y Sillon Reclinable',12),(41,'Cocina',13),(42,'Complementos para Cocina',13),(43,'Aparatos de Ejercicio',14),(44,'Entretenimiento',15),(45,'Novedades',16),(46,'Audio y Video',17);
/*!40000 ALTER TABLE `subcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tienda` (
  `idtienda` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(300) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idtienda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tienda`
--

LOCK TABLES `tienda` WRITE;
/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
INSERT INTO `tienda` VALUES (120,'MEGA TIJUANA OTAY','BLVD INDUSTRIAL 18380  MAESTROS UNIVERSITARIOS','Baja California',22457,'6641379908'),(127,'MEGA TIJUANA LAS TORRES','MONTEALBAN S/N EJIDO FRANCISCO VILLA SUR','Baja California',22234,NULL),(131,'MEGA CD CONSTITUCION','BLVD AGUSTIN OLACHEA S/N INVI Olivos Juan Dominguez Cot','Baja California Sur',23600,NULL),(175,'MEGA MANZANILLO SALA','BLVD MIGUEL DE LA MADRID 11500 OBISPO SERGIO MENDEZ A','Colima',28869,'3143332222'),(196,'EKT CAMPECHE ALAMEDA','AV REPUBLICA 175  LA PAZ','Campeche',24050,NULL),(197,'EKT CIUDAD DEL CARMEN CENTRO','CALLE 22 99  CIUDAD DEL CARMEN CENTRO','Campeche',24100,NULL),(770,'MEGA MUZQUIZ','PRESIDENTE JUAREZ S/N Melchor Muzquiz Centro','Coahuila de Zaragoza',26340,'8646163046'),(822,'MEGA CD ACUNA','GUERRERO Y MUZQUIZ S/N  CIUDAD ACU¥A CENTRO','Coahuila de Zaragoza',26200,'8777726821'),(1037,'EKT MEXICALI BLVD LAZARO CARDENAS','BLVD LAZARO CARDENAS S/N, NUEVO MEXICALI','Baja California',22932,'6865803928'),(1229,'EKT CAMPECHE','CALLE 10 289  San Francisco de Campeche  Centro','Campeche',24000,'98165126'),(1342,'MINI EKT AGUASCALIENTES 2 ASUN','CENTRO SOCIAL VILLASUNCION S/N, PILAR BLANCO INFONAVIT','Aguascalientes',20289,'4499011929'),(1397,'EKT TUXTLA GTZ 3 CENTRO','9 AV SUR OTE 783 LOMAS DEL VENADO','Chiapas',29080,'9611125905'),(1449,'EKT CD DEL CARMEN','CALLE 22 156-A  CIUDAD DEL CARMEN CENTRO','Campeche',24100,'9381030904'),(1504,'EKT AGUASCALIENTES 1 ALLENDE','ALLENDE 117,  Zona Centro','Aguascalientes',20000,'4499011913'),(1514,'EKT TIJUANA EL RUBI','BLVD FUNDADORES 6420 El Rubi 5a Seccion','Baja California',22626,'6646370649'),(1531,'APA HECELCHAKAN','CALLE 23 S/N  HECELCHAKAN','Campeche',24800,'9968270732'),(1656,'EKT SAN CRISTOBAL DE LAS CASAS','AV GENERAL URTILLA S/N  SAN CRISTOBAL DE LAS CASAS CEN','Chiapas',29200,'96780989'),(1768,'APA QUESERIA','20 DE NOVIEMBRE 16 CUAUHTEMOC','Colima',28500,'3123952189'),(1769,'APA SAN FRANCISCO DE LOS ROMO','DECRETO 30 DE ENERO 201, VI¥EDOS SAN JOSE','Aguascalientes',20303,'4659674255'),(2005,'EKT TIJUANA CUCAPAH','BLVD CUCAPAH 22365  UNIVERSITARIO','Baja California',21180,'66998301139830755'),(2006,'EKT MEXICALI STA BARBARA','AV ING LUIS G ALCERREGA S/N MEXICALI','Baja California',21396,'6861770914'),(2046,'EKT MEXICALI VALLE DEL PEDREGAL','CALZ INDUSTRIAL PALACO S/N  EL CONDOR','Baja California',21395,'6861770933'),(2048,'EKT ENSENADA BLVD REFORMA','BLVD REFORMA 265, VALLE DORADO','Baja California',22890,'6461450928'),(2090,'EKT AGUASCALIENTES','5 DE MAYO 615, Zona Centro','Aguascalientes',20000,'4499011911'),(2150,'EKT TIJUANA 3 CONSTITUCION','AV CONSTITUCION 634  ZONA ESTE','Baja California',22000,'-871157'),(2209,'EKT ESCARCEGA','AV JUSTO SIERRA MENDEZ 50 Morelos','Campeche',24350,'9828241813'),(2212,'EKT AGS  RINCON DE ROMOS','MORELOS NORTE 413, RINCON DE ROMOS CENTRO','Aguascalientes',20400,'4658514627'),(2230,'EKT MEXICALI LAGUNA','CALZADA LAGUNA XOCHIMILCO S/N, Centro Civico','Baja California',21000,'6861770940'),(2273,'EKT TECOMAN','MEDELLIN 336 TECOMAN CENTRO','Colima',28100,'3133240178'),(2367,'EKT TECATE','AV JUAREZ 61 A  TECATE CENTRO','Baja California',21400,'6656541321'),(2383,'EKT MEXICALI','LERDO S/N, Centro Civico','Baja California',21000,'6861770920'),(2394,'MINI EKT MATAMOROS LA LAGUNA','CUAUHTEMOC 712  LA ESPERANZA','Coahuila de Zaragoza',27440,'8711828966'),(2516,'MEGA CAMPECHE BLVD49','AV BOULEVARD 49 56  San Francisco de Campeche  Centro','Campeche',24000,'1981109995'),(3010,'MEGA AGS CIRCUNVALACION PTE','AV CONVENCION 1914, COLINAS DEL RIO','Aguascalientes',20010,'4499011937'),(3049,'MEGA VILLAFLORES','1A AVENIDA SUR 31 LA CONCEPCION','Chiapas',30470,'9656520284'),(3052,'TT COLIMA','MADERO 365 COLIMA CENTRO','Colima',28000,'3123146360'),(3117,'EKT TIJUANA 4 NI¥OS HEROES','CALLE 2DA 1626 B  ZONA ESTE','Baja California',22000,'6642717476'),(3120,'TT TIJUANA 5 DIAZ ORDAZ','BLVD DIAZ ORDAZ S/N  ZONA ESTE','Baja California',22000,'-207479'),(3450,'EKT TIJUANA 1 LUNA PARK','BLVD DIAZ ORDAZ 3600  PINOS DE NAREZ','Baja California',22127,'6641379983'),(3748,'MEGA TIJUANA CENTRO','AV TERCERA 173  ZONA ESTE','Baja California',22000,'-842004'),(3751,'MEGA TIJUANA LAS BRISAS','BLVD DIAZ ORDAZ 1200  BENTON','Baja California',22115,'6641379933'),(4607,'APA CALVILLO',' ZARAGOZA 434, CALVILLO CENTRO','Aguascalientes',20800,'4959567245'),(4680,'PRESTA PRENDA JESUS MARIA 2','JESUS MARIA CENTRO','Aguascalientes',20920,NULL),(4830,'PRESTA PRENDA PALENQUE','Benito Ju rez No. 156 Palenque Centro','Chiapas',29960,'9163452871'),(5076,'MEGA MEXICALI FCO I MADERO','MADERO 550, Centro Civico','Baja California',21000,'6861770901'),(5116,'EKT LA PAZ FORJADORES','BLVD FORJADORES S/N  LOS OLIVOS','Baja California Sur',23040,NULL),(5212,'MEGA MONCLOVA','HIDALGO 202  MONCLOVA CENTRO','Coahuila de Zaragoza',25700,'8666332266'),(5429,'MEGA LA PAZ OCAMPO','REVOLUCION DE 1910 S/N Zona Central','Baja California Sur',23000,'6121602239'),(5445,'MEGA ENSENADA','AV GASTELUM 454B, ENSENADA CENTRO','Baja California',22800,'6461450920'),(5568,'MEGA TIJUANA EL FLORIDO','RUTA INDEPENDENCIA MZ 33  EJIDO FRANCISCO VILLA SUR','Baja California',22234,'6641379988'),(5830,'MEGA TIJUANA SOLER','AV BRAULIO MALDONADO MZ 617 COLINA DEL MEDITERRANEO','Baja California',22530,'6641379937'),(5843,'MEGA MEXICALI','HEROICO COL MILITAR 11 1054 RIO NUEVO','Baja California',21120,'6861770905'),(5940,'MEGA ENSENADA JUAREZ','AV OBREGON 499, ENSENADA CENTRO','Baja California',22800,'6461450905'),(6012,'EKT TIJUANA PLAZA GIRASOL','ORIENTE 7002 LOMAS DEL MAR','Baja California',22564,'6646878205'),(6014,'EKT  LOS PINOS TIJUANA','PROLG GUSTAVO DIAZ ORDAZ 1500  JARDINES DE LA MESA','Baja California',22126,NULL),(6099,'EKT TIJUANA LOMA BONITA','CAMINO ALTURA SALVATIERRA 7200 ZONA ESTE','Baja California',22000,'6649002901'),(6103,'MICROEKT CAMPECHE CALKINI','AV 20 92  Calkini Centro','Campeche',24900,NULL),(6117,'EKT COLIMA VILLA DE ALVAREZ','AV MARIA AHUMADA S/N  VILLA DE ALVAREZ CENTRO','Colima',28970,'3215504590'),(6544,'EKT AGUASCALIENTES CONVENCION','AV CONVENCION PTE 915,  SANTA ANITA','Aguascalientes',20169,'4499011918'),(6622,'MEGA SAN JOSE DEL CABO','CARR TRANSPENINSULAR MZ53 LT52 SAN JOSE DEL CABO CENTRO','Baja California Sur',23400,'6241389508'),(6761,'MEGA CD DEL CARMEN','CALLE 33 15  CIUDAD DEL CARMEN CENTRO','Campeche',24100,'9383799901'),(7391,'MEGA TIJUANA DIAZ ORDAZ','BLVD DIAZ ORDAZ 420 CORTES LA MESA','Baja California',22106,NULL),(7579,'MEGA PALENQUE','AV JUAREZ S/N   Balluntle 1a Seccion','Chiapas',29960,'9163450390'),(7582,'MEGA CABO SAN LUCAS','AV MORELOS S/N CABO SAN LUCAS CENTRO','Baja California Sur',23450,'6241389511'),(7689,'MEGA COLIMA','NIGROMANTE 29  COLIMA CENTRO','Colima',28000,'3123304093'),(7977,'EKT FRONTERA COAHUILA','FRANCISCO I MADERO 203  Frontera Centro','Coahuila de Zaragoza',25600,'8666353911'),(8332,'EKT EL ROSARITO','ROSARITO CENTRO','Baja California',22700,'6611090904'),(9380,'EKT AGUASCALIENTES 3ER ANILLLO','AV SIGLO XXI Centro Distribuidor de Basicos  5202','Aguascalientes',20135,'4499011908'),(9637,'EKT SAN JOSE DEL CABO','MANUEL DOBLADO S/N SAN JOSE DEL CABO CENTRO','Baja California Sur',23400,'6241389504'),(9843,'EKT CABO SAN LUCAS','MORELOS MZ14 LT2  CABO SAN LUCAS CENTRO','Baja California Sur',23450,'6241393164');
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiendaproducto`
--

DROP TABLE IF EXISTS `tiendaproducto`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `tiendaproducto` (
  `idtienda` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `noExistencias` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtienda`,`idproducto`),
  KEY `idproducto` (`idproducto`),
  CONSTRAINT `tiendaproducto_ibfk_1` FOREIGN KEY (`idtienda`) REFERENCES `tienda` (`idtienda`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tiendaproducto_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `tiendaproducto`
--

LOCK TABLES `tiendaproducto` WRITE;
/*!40000 ALTER TABLE `tiendaproducto` DISABLE KEYS */;
INSERT INTO `tiendaproducto` VALUES (120,1,12),(120,11,12),(120,12,9),(120,21,12),(127,2,9),(127,12,9),(127,13,8),(127,22,9),(131,3,23),(131,13,23),(131,14,7),(131,23,23),(175,4,5),(175,14,5),(175,15,6),(175,24,5),(196,5,11),(196,15,11),(196,16,5),(196,25,11),(197,6,13),(197,16,13),(197,17,4),(197,26,13),(770,7,1),(770,17,1),(770,18,5),(770,27,1),(822,8,2),(822,18,2),(822,19,6),(822,28,2),(1037,9,3),(1037,19,3),(1037,20,7),(1037,29,3),(1229,10,5),(1229,20,5),(1229,21,8),(1229,30,5),(1342,1,11),(1342,11,11),(1342,21,11),(1342,22,9),(1397,2,10),(1397,12,10),(1397,22,10),(1397,23,9),(1449,3,9),(1449,13,9),(1449,23,9),(1449,24,8),(1504,4,8),(1504,14,8),(1504,24,8),(1504,25,7),(1514,5,7),(1514,15,7),(1514,25,7),(1514,26,6),(1531,6,6),(1531,16,6),(1531,26,6),(1531,27,5),(1656,7,5),(1656,17,5),(1656,27,5),(1656,28,4),(1768,8,12),(1768,18,12),(1768,28,12),(1768,29,3),(1769,9,10),(1769,19,10),(1769,29,10),(1769,30,5),(2005,1,6),(2005,10,9),(2005,20,9),(2005,30,9),(2006,1,8),(2006,2,7),(2006,11,8),(2006,21,8),(2046,2,7),(2046,3,8),(2046,12,7),(2046,22,7),(2048,3,6),(2048,4,9),(2048,13,6),(2048,23,6),(2090,4,5),(2090,5,9),(2090,14,5),(2090,24,5),(2150,5,4),(2150,6,8),(2150,15,4),(2150,25,4),(2209,6,3),(2209,7,7),(2209,16,3),(2209,26,3),(2212,7,2),(2212,8,6),(2212,17,2),(2212,27,2),(2230,8,1),(2230,9,5),(2230,18,1),(2230,28,1),(2273,9,14),(2273,10,5),(2273,19,14),(2273,29,14),(2367,10,13),(2367,11,6),(2367,20,13),(2367,30,13),(2383,1,12),(2383,11,12),(2383,12,7),(2383,21,12),(2394,2,11),(2394,12,11),(2394,13,8),(2394,22,11),(2516,3,10),(2516,13,10),(2516,14,9),(2516,23,10),(3010,4,9),(3010,14,9),(3010,15,10),(3010,24,9),(3049,5,8),(3049,15,8),(3049,16,9),(3049,25,8),(3052,6,7),(3052,16,7),(3052,17,8),(3052,26,7),(3117,7,5),(3117,17,5),(3117,18,7),(3117,27,5),(3120,8,9),(3120,18,9),(3120,19,6),(3120,28,9),(3450,9,10),(3450,19,10),(3450,20,4),(3450,29,10),(3748,10,11),(3748,20,11),(3748,21,4),(3748,30,11),(3751,1,12),(3751,11,12),(3751,21,12),(3751,22,6),(4607,2,13),(4607,12,13),(4607,22,13),(4607,23,7),(4680,3,4),(4680,13,4),(4680,23,4),(4680,24,8),(4830,4,5),(4830,14,5),(4830,24,5),(4830,25,9),(5076,5,6),(5076,15,6),(5076,25,6),(5076,26,10),(5116,6,7),(5116,16,7),(5116,26,7),(5116,27,10),(5212,7,8),(5212,17,8),(5212,27,8),(5212,28,9),(5429,8,9),(5429,18,9),(5429,28,9),(5429,29,8),(5445,9,10),(5445,19,10),(5445,29,10),(5445,30,7),(5568,1,6),(5568,10,11),(5568,20,11),(5568,30,11),(5830,1,5),(5830,2,9),(5830,11,5),(5830,21,5),(5843,2,4),(5843,3,8),(5843,12,4),(5843,22,4),(5940,3,3),(5940,4,7),(5940,13,3),(5940,23,3),(6012,4,8),(6012,5,6),(6012,14,8),(6012,24,8),(6014,5,9),(6014,6,5),(6014,15,9),(6014,25,9),(6099,6,10),(6099,7,5),(6099,16,10),(6099,26,10),(6103,7,11),(6103,8,6),(6103,17,11),(6103,27,11),(6117,8,12),(6117,9,7),(6117,18,12),(6117,28,12),(6544,9,13),(6544,10,8),(6544,19,13),(6544,29,13),(6622,10,14),(6622,11,9),(6622,20,14),(6622,30,14),(6761,1,8),(6761,11,8),(6761,12,10),(6761,21,8),(7391,2,7),(7391,12,7),(7391,13,10),(7391,22,7),(7579,3,6),(7579,13,6),(7579,14,9),(7579,23,6),(7582,4,5),(7582,14,5),(7582,15,8),(7582,24,5),(7689,5,3),(7689,15,3),(7689,16,7),(7689,25,3),(7977,6,5),(7977,16,5),(7977,17,6),(7977,26,5),(8332,7,3),(8332,17,3),(8332,18,5),(8332,27,3),(9380,8,10),(9380,18,10),(9380,19,5),(9380,28,10),(9637,9,12),(9637,19,12),(9637,20,6),(9637,29,12),(9843,10,13),(9843,20,13),(9843,21,7),(9843,30,13);
/*!40000 ALTER TABLE `tiendaproducto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-09-08  3:56:56
