CREATE DATABASE  IF NOT EXISTS `construccion` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `construccion`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: construccion
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `razon_social` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `usuario_c_idx` (`idUsuario`),
  CONSTRAINT `usuario_c` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Los arboles','3413123456','arboles@mail.com',5),(2,'Complejo Baigorria','3413987456','cb@mail.com',6);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maquinarias`
--

DROP TABLE IF EXISTS `maquinarias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maquinarias` (
  `idmaquina` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `precioHora` float DEFAULT NULL,
  PRIMARY KEY (`idmaquina`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maquinarias`
--

LOCK TABLES `maquinarias` WRITE;
/*!40000 ALTER TABLE `maquinarias` DISABLE KEYS */;
INSERT INTO `maquinarias` VALUES (1,'Excavadora',3000),(2,'Grua',1500);
/*!40000 ALTER TABLE `maquinarias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales` (
  `idmaterial` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `id_provedor` int DEFAULT NULL,
  PRIMARY KEY (`idmaterial`),
  KEY `id_provedor_idx` (`id_provedor`),
  CONSTRAINT `id_provedor` FOREIGN KEY (`id_provedor`) REFERENCES `provedores` (`idprovedor`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales`
--

LOCK TABLES `materiales` WRITE;
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` VALUES (1,'Ladrillo rojo',1),(2,'Bolsa de Porlan',2),(3,'Canilla',3),(4,'Cemento',2);
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materiales_tareas`
--

DROP TABLE IF EXISTS `materiales_tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales_tareas` (
  `id_material_` int NOT NULL,
  `id_tarea_` int NOT NULL,
  `cant_a_usar` varchar(45) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_material_`,`id_tarea_`,`fecha`),
  KEY `id_tarea__idx` (`id_tarea_`),
  CONSTRAINT `id_material_` FOREIGN KEY (`id_material_`) REFERENCES `materiales` (`idmaterial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `id_tarea_` FOREIGN KEY (`id_tarea_`) REFERENCES `tareas` (`idtarea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materiales_tareas`
--

LOCK TABLES `materiales_tareas` WRITE;
/*!40000 ALTER TABLE `materiales_tareas` DISABLE KEYS */;
/*!40000 ALTER TABLE `materiales_tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras`
--

DROP TABLE IF EXISTS `obras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obras` (
  `idobra` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`idobra`),
  KEY `id_cliente_idx` (`id_cliente`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`idCliente`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras`
--

LOCK TABLES `obras` WRITE;
/*!40000 ALTER TABLE `obras` DISABLE KEYS */;
/*!40000 ALTER TABLE `obras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_maquina`
--

DROP TABLE IF EXISTS `precios_maquina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_maquina` (
  `id_maquina` int NOT NULL,
  `fecha_desde` datetime NOT NULL,
  `valor_hora` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id_maquina`,`fecha_desde`),
  CONSTRAINT `id_maquina` FOREIGN KEY (`id_maquina`) REFERENCES `maquinarias` (`idmaquina`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_maquina`
--

LOCK TABLES `precios_maquina` WRITE;
/*!40000 ALTER TABLE `precios_maquina` DISABLE KEYS */;
/*!40000 ALTER TABLE `precios_maquina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_material`
--

DROP TABLE IF EXISTS `precios_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_material` (
  `id_material` int NOT NULL,
  `fecha_desde` datetime NOT NULL,
  `precio` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id_material`,`fecha_desde`),
  CONSTRAINT `id_material` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`idmaterial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_material`
--

LOCK TABLES `precios_material` WRITE;
/*!40000 ALTER TABLE `precios_material` DISABLE KEYS */;
INSERT INTO `precios_material` VALUES (1,'2021-10-12 00:00:00',300.0000),(1,'2021-10-15 00:00:00',310.0000),(3,'2021-11-19 04:34:28',50.0000),(4,'2021-11-20 17:19:47',500.0000);
/*!40000 ALTER TABLE `precios_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_tipo_tarea`
--

DROP TABLE IF EXISTS `precios_tipo_tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_tipo_tarea` (
  `precio_m2` decimal(10,4) DEFAULT NULL,
  `id_tipo_tarea_` int NOT NULL,
  `fecha_desde` datetime NOT NULL,
  PRIMARY KEY (`fecha_desde`,`id_tipo_tarea_`),
  KEY `id_tipo_tarea__idx` (`id_tipo_tarea_`),
  CONSTRAINT `id_tipo_tarea_` FOREIGN KEY (`id_tipo_tarea_`) REFERENCES `tipos_tarea` (`idtipo_tarea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_tipo_tarea`
--

LOCK TABLES `precios_tipo_tarea` WRITE;
/*!40000 ALTER TABLE `precios_tipo_tarea` DISABLE KEYS */;
/*!40000 ALTER TABLE `precios_tipo_tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuestos`
--

DROP TABLE IF EXISTS `presupuestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuestos` (
  `idpresupuesto` int NOT NULL AUTO_INCREMENT,
  `fecha_emision` datetime NOT NULL,
  `fecha_aceptacion` datetime DEFAULT NULL,
  `id_obra` int DEFAULT NULL,
  PRIMARY KEY (`idpresupuesto`),
  KEY `id_obra_idx` (`id_obra`),
  CONSTRAINT `id_obra` FOREIGN KEY (`id_obra`) REFERENCES `obras` (`idobra`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuestos`
--

LOCK TABLES `presupuestos` WRITE;
/*!40000 ALTER TABLE `presupuestos` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuestos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provedores`
--

DROP TABLE IF EXISTS `provedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provedores` (
  `idprovedor` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `razon_social` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idprovedor`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provedores`
--

LOCK TABLES `provedores` WRITE;
/*!40000 ALTER TABLE `provedores` DISABLE KEYS */;
INSERT INTO `provedores` VALUES (1,'Av. San Martin 1531','4700125','Ladrillera los Hornos SRL'),(2,'Av. San Martin 1234','155333123','Cementera Rio Azul'),(3,'Malvinas 123','156666997','La casa del repuesto');
/*!40000 ALTER TABLE `provedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `idtarea` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `cant_m2` decimal(10,2) DEFAULT NULL,
  `id_presupuesto` int DEFAULT NULL,
  `id_tipo_tarea` int DEFAULT NULL,
  PRIMARY KEY (`idtarea`),
  KEY `id_tipo_tarea_idx` (`id_tipo_tarea`),
  KEY `id_presupuesto_idx` (`id_presupuesto`),
  CONSTRAINT `id_presupuesto` FOREIGN KEY (`id_presupuesto`) REFERENCES `presupuestos` (`idpresupuesto`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `id_tipo_tarea` FOREIGN KEY (`id_tipo_tarea`) REFERENCES `tipos_tarea` (`idtipo_tarea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas_maquinas`
--

DROP TABLE IF EXISTS `tareas_maquinas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas_maquinas` (
  `id_maquina__` int NOT NULL,
  `id_tarea__` int NOT NULL,
  `hs_uso` int DEFAULT NULL,
  PRIMARY KEY (`id_maquina__`,`id_tarea__`),
  KEY `id_tarea___idx` (`id_tarea__`),
  CONSTRAINT `id_maquina__` FOREIGN KEY (`id_maquina__`) REFERENCES `maquinarias` (`idmaquina`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `id_tarea__` FOREIGN KEY (`id_tarea__`) REFERENCES `tareas` (`idtarea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas_maquinas`
--

LOCK TABLES `tareas_maquinas` WRITE;
/*!40000 ALTER TABLE `tareas_maquinas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tareas_maquinas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_trabajador`
--

DROP TABLE IF EXISTS `tipo_trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_trabajador` (
  `idtipo_trabajador` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `precioHora` float DEFAULT NULL,
  PRIMARY KEY (`idtipo_trabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_trabajador`
--

LOCK TABLES `tipo_trabajador` WRITE;
/*!40000 ALTER TABLE `tipo_trabajador` DISABLE KEYS */;
INSERT INTO `tipo_trabajador` VALUES (1,'Oficial',400),(2,'Obrero',200);
/*!40000 ALTER TABLE `tipo_trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_usuario` (
  `idtipo_usuario` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idtipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'Administrador'),(2,'Cliente'),(3,'Trabajador');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_tarea`
--

DROP TABLE IF EXISTS `tipos_tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_tarea` (
  `idtipo_tarea` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipo_tarea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_tarea`
--

LOCK TABLES `tipos_tarea` WRITE;
/*!40000 ALTER TABLE `tipos_tarea` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipos_tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador_tarea`
--

DROP TABLE IF EXISTS `trabajador_tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajador_tarea` (
  `cuil_trabajador` bigint NOT NULL,
  `id_tarea_asignada` int NOT NULL,
  `cant_horas_trabajadas` int DEFAULT NULL,
  PRIMARY KEY (`cuil_trabajador`,`id_tarea_asignada`),
  KEY `id_tarea_asignada_idx` (`id_tarea_asignada`),
  CONSTRAINT `id_tarea_asignada` FOREIGN KEY (`id_tarea_asignada`) REFERENCES `tareas` (`idtarea`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador_tarea`
--

LOCK TABLES `trabajador_tarea` WRITE;
/*!40000 ALTER TABLE `trabajador_tarea` DISABLE KEYS */;
/*!40000 ALTER TABLE `trabajador_tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajadores`
--

DROP TABLE IF EXISTS `trabajadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabajadores` (
  `cuil` bigint NOT NULL,
  `tipo_doc` varchar(45) DEFAULT NULL,
  `n_doc` int DEFAULT NULL,
  `nombre_completo` varchar(80) DEFAULT NULL,
  `fecha_nac` datetime DEFAULT NULL,
  `disponoble` tinyint DEFAULT NULL,
  `tipo_trabajador` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`cuil`),
  KEY `usuario_idx` (`id_usuario`),
  KEY `tipo_trabajador` (`tipo_trabajador`),
  CONSTRAINT `tipo_trabajador` FOREIGN KEY (`tipo_trabajador`) REFERENCES `tipo_trabajador` (`idtipo_trabajador`),
  CONSTRAINT `usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajadores`
--

LOCK TABLES `trabajadores` WRITE;
/*!40000 ALTER TABLE `trabajadores` DISABLE KEYS */;
INSERT INTO `trabajadores` VALUES (20000000099,'DNI',9,'Juan Lopez','1988-10-10 00:00:00',1,1,7),(20000000126,'DNI',12,'Miguel Jager','1994-11-11 00:00:00',1,2,8);
/*!40000 ALTER TABLE `trabajadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `cuil` bigint NOT NULL,
  `id_tipo` int NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `cuil_UNIQUE` (`cuil`),
  UNIQUE KEY `idusuario_UNIQUE` (`idusuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `tipo_usuario_idx` (`id_tipo`),
  CONSTRAINT `tipo_usuario` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_usuario` (`idtipo_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Ivan','Biscaldi',20423301024,1,'12345','ivanbisc12@gmail.com'),(2,'Matias','Santolari',20000000000,1,'12345','mati@mail.com'),(3,'Joan','Romero',20000000001,1,'12345','joan@mail.com'),(4,'Franco','Navarro',20000000002,1,'12345','franco@mail.com'),(5,'Adrian','Meca',20000000003,2,'12345','adrian@mail.com'),(6,'Juan','Carlos',20000000008,2,'12345','juancarlos@mail.com'),(7,'Juan','Lopez',20000000099,3,'12345','jlopez@mail.com'),(8,'Miguel','Jager',20000000126,3,'12345','mjager@mail.com');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-27  1:58:53
