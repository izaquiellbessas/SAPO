-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.75-0ubuntu10.2


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sapo
--

CREATE DATABASE IF NOT EXISTS sapo;
USE sapo;

--
-- Definition of table `sapo`.`aluno`
--

DROP TABLE IF EXISTS `sapo`.`aluno`;
CREATE TABLE  `sapo`.`aluno` (
  `numMatricula` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `dataNasc` date default NULL,
  `classificacao` varchar(10) NOT NULL,
  `rua` varchar(30) default NULL,
  `numRua` int(11) default NULL,
  `bairro` varchar(20) default NULL,
  `cidade` varchar(25) default NULL,
  `cep` varchar(10) default NULL,
  `telefone` varchar(14) default NULL,
  PRIMARY KEY  (`numMatricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`aluno`
--

/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
LOCK TABLES `aluno` WRITE;
INSERT INTO `sapo`.`aluno` VALUES  (1,'crianca 1','2001-01-01','0','rua 1                         ',111,'bairro 1            ','cidade 1                 ','11.111-111','(11)1111-1111');
UNLOCK TABLES;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


--
-- Definition of table `sapo`.`fases`
--

DROP TABLE IF EXISTS `sapo`.`fases`;
CREATE TABLE  `sapo`.`fases` (
  `Jogos_codigo` int(11) NOT NULL,
  `codigoFase` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `nivelDificuldade` int(11) NOT NULL,
  `tempoLimite` int(11) default NULL,
  PRIMARY KEY  (`codigoFase`,`Jogos_codigo`),
  KEY `fk_Fase_Jogos` (`Jogos_codigo`),
  CONSTRAINT `fk_Fase_Jogos` FOREIGN KEY (`Jogos_codigo`) REFERENCES `jogos` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`fases`
--

/*!40000 ALTER TABLE `fases` DISABLE KEYS */;
LOCK TABLES `fases` WRITE;
INSERT INTO `sapo`.`fases` VALUES  (1,1,'Quadrado Amarelo',3,100),
 (2,1,'Abacaxi',1,50),
 (1,2,'Triangulo Verde',5,200),
 (2,2,'Limao',1,50),
 (1,3,'circulo amarelo',2,123),
 (2,3,'melancia',1,122),
 (1,4,'estrela verde',2,100),
 (2,4,'mamao',1,213),
 (1,5,'quadrado azul',3,90),
 (2,5,'morango',2,432),
 (1,6,'circulo azul',4,98),
 (2,6,'uva',2,46),
 (1,7,'retangulo amarelo',4,65),
 (2,7,'laranja',2,765),
 (1,8,'quadrado verde',4,43),
 (2,8,'maca',3,234),
 (1,9,'circulo verde',5,546),
 (2,9,'pera',3,122),
 (1,10,'retangulo verde',6,32),
 (2,10,'banana',3,344),
 (1,11,'triangulo azul',6,54),
 (1,12,'estrela azul',7,67),
 (1,13,'triangulo amarelo',7,32),
 (1,14,'estrela amarela',7,234),
 (1,15,'retangulo azul',8,546);
UNLOCK TABLES;
/*!40000 ALTER TABLE `fases` ENABLE KEYS */;


--
-- Definition of table `sapo`.`fases_is`
--

DROP TABLE IF EXISTS `sapo`.`fases_is`;
CREATE TABLE  `sapo`.`fases_is` (
  `idFases_IS` int(11) NOT NULL auto_increment,
  `URL_imagem` varchar(300) default NULL,
  `URL_sons` varchar(300) default NULL,
  `nome_is` varchar(45) default NULL,
  PRIMARY KEY  (`idFases_IS`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`fases_is`
--

/*!40000 ALTER TABLE `fases_is` DISABLE KEYS */;
LOCK TABLES `fases_is` WRITE;
INSERT INTO `sapo`.`fases_is` VALUES  (19,'/imagens/abacaxi.png','/src/sons/fc-abacaxi.wav','Abacaxi'),
 (20,'/imagens/limao.png','/src/sons/fc-limao.wav','Limao'),
 (21,'/imagens/melancia.png','/src/sons/fc-melancia.wav','Melancia'),
 (22,'/imagens/mamao.png','/src/sons/fc-mamao.wav','Mamao'),
 (23,'/imagens/morango.png','/src/sons/fc-morango.wav','Morango'),
 (24,'/imagens/uva.png','/src/sons/fc-uva.wav','Uva'),
 (25,'/imagens/circulo_amarelo.png','/src/sons/fg-circuloamarelo.wav','Circulo Amarelo'),
 (26,'/imagens/circulo_azul.png','/src/sons/fg-circuloazul.wav','Circulo Azul'),
 (27,'/imagens/circulo_verde.png','/src/sons/fg-circuloverde.wav','Circulo Verde'),
 (28,'/imagens/estrela_amarelo.png','/src/sons/fg-estrelamarelo.wav','Estrela Amarela'),
 (29,'/imagens/estrela_azul.png','/src/sons/fg-estrelaazul.wav','Estrela Azul'),
 (30,'/imagens/estrela_verde.png','/src/sons/fg-estrelaverde.wav','Estrela Verde'),
 (31,'/imagens/laranja.png','/src/sons/fc-laranja.wav','Laranja'),
 (32,'/imagens/maca.png','/src/sons/fc-maca.wav','Maca'),
 (33,'/imagens/pera.png','/src/sons/fc-pera.wav','Pera');
INSERT INTO `sapo`.`fases_is` VALUES  (34,'/imagens/quadrado_amarelo.png','/src/sons/fg-quadradoamarelo.wav','Quadrado Amarelo'),
 (35,'/imagens/quadrado_azul.png','/src/sons/fg-quadradoazul.wav','Quadrado Azul'),
 (36,'/imagens/quadrado_verde.png','/src/sons/fg-quadradoverde.wav','Quadrado Verde'),
 (37,'/imagens/retangulo_amarelo.png','/src/sons/fg-retanguloamarelo.wav','Retangulo Amarelo'),
 (38,'/imagens/retangulo_azul.png','/src/sons/fg-retanguloazul.wav','Retangulo Azul'),
 (39,'/imagens/retangulo_verde.png','/src/sons/fg-retanguloverde.wav','Retangulo Verde'),
 (40,'/imagens/triangulo_azul.png','/src/sons/fg-trianguloazul.wav','Triangulo Azul'),
 (41,'/imagens/triangulo_amarelo.png','/src/sons/fg-trianguloamarelo.wav','Triangulo Amarelo'),
 (42,'/imagens/triangulo_verde.png','/src/sons/trianguloverde.wav','Triangulo Verde'),
 (43,'/imagens/banana.png','/src/sons/fc-banana.wav','Banana'),
 (44,'/imagens/SAPO_Logomarca_RC.jpg',NULL,'LOGOMARCA_RC'),
 (45,'/imagens/logomarca_SAPO.png',NULL,'LOGOMARCA');
UNLOCK TABLES;
/*!40000 ALTER TABLE `fases_is` ENABLE KEYS */;


--
-- Definition of table `sapo`.`fases_is_has_fases`
--

DROP TABLE IF EXISTS `sapo`.`fases_is_has_fases`;
CREATE TABLE  `sapo`.`fases_is_has_fases` (
  `Fases_IS_idFases_IS` int(11) NOT NULL,
  `Fases_codigoFase` int(11) NOT NULL,
  `Fases_Jogos_codigo` int(11) NOT NULL,
  PRIMARY KEY  (`Fases_IS_idFases_IS`,`Fases_codigoFase`,`Fases_Jogos_codigo`),
  KEY `fk_Fases_IS_has_Fases_Fases_IS1` (`Fases_IS_idFases_IS`),
  KEY `fk_Fases_IS_has_Fases_Fases1` (`Fases_codigoFase`,`Fases_Jogos_codigo`),
  CONSTRAINT `fk_Fases_IS_has_Fases_Fases1` FOREIGN KEY (`Fases_codigoFase`, `Fases_Jogos_codigo`) REFERENCES `fases` (`codigoFase`, `Jogos_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fases_IS_has_Fases_Fases_IS1` FOREIGN KEY (`Fases_IS_idFases_IS`) REFERENCES `fases_is` (`idFases_IS`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`fases_is_has_fases`
--

/*!40000 ALTER TABLE `fases_is_has_fases` DISABLE KEYS */;
LOCK TABLES `fases_is_has_fases` WRITE;
INSERT INTO `sapo`.`fases_is_has_fases` VALUES  (19,1,2),
 (19,2,2),
 (19,5,2),
 (19,6,2),
 (19,7,2),
 (19,9,2),
 (19,10,2),
 (20,1,2),
 (20,2,2),
 (20,5,2),
 (20,7,2),
 (20,8,2),
 (21,1,2),
 (21,2,2),
 (21,3,2),
 (21,8,2),
 (22,1,2),
 (22,2,2),
 (22,4,2),
 (22,5,2),
 (22,8,2),
 (23,1,2),
 (23,2,2),
 (23,4,2),
 (23,5,2),
 (23,6,2),
 (24,3,2),
 (24,4,2),
 (24,6,2),
 (24,7,2),
 (24,9,2),
 (25,3,1),
 (25,7,1),
 (25,8,1),
 (25,10,1),
 (25,11,1),
 (25,15,1),
 (26,5,1),
 (26,6,1),
 (26,10,1),
 (26,11,1),
 (26,12,1),
 (26,15,1),
 (27,3,1),
 (27,6,1),
 (27,7,1),
 (27,8,1),
 (27,9,1),
 (27,13,1),
 (28,4,1),
 (28,13,1),
 (28,14,1),
 (28,15,1),
 (29,3,1),
 (29,6,1),
 (29,8,1),
 (29,12,1),
 (29,14,1),
 (30,3,1),
 (30,4,1),
 (30,6,1),
 (30,9,1),
 (30,10,1),
 (30,11,1),
 (30,14,1),
 (30,15,1),
 (31,3,2),
 (31,6,2),
 (31,7,2),
 (31,8,2),
 (31,10,2),
 (32,4,2),
 (32,5,2),
 (32,8,2),
 (32,9,2),
 (32,10,2),
 (33,3,2),
 (33,4,2),
 (33,6,2),
 (33,8,2),
 (33,9,2),
 (33,10,2),
 (34,1,1),
 (34,2,1),
 (34,4,1),
 (34,5,1);
INSERT INTO `sapo`.`fases_is_has_fases` VALUES  (34,9,1),
 (34,11,1),
 (34,13,1),
 (34,15,1),
 (35,4,1),
 (35,5,1),
 (35,15,1),
 (36,8,1),
 (36,11,1),
 (36,12,1),
 (36,13,1),
 (37,7,1),
 (37,8,1),
 (37,14,1),
 (38,4,1),
 (38,5,1),
 (38,10,1),
 (38,12,1),
 (38,15,1),
 (39,7,1),
 (39,9,1),
 (39,10,1),
 (40,5,1),
 (40,8,1),
 (40,11,1),
 (40,12,1),
 (41,10,1),
 (41,13,1),
 (41,14,1),
 (42,1,1),
 (42,2,1),
 (42,4,1),
 (42,5,1),
 (42,6,1),
 (42,7,1),
 (42,8,1),
 (42,9,1),
 (42,11,1),
 (42,15,1),
 (43,3,2),
 (43,4,2),
 (43,6,2),
 (43,7,2),
 (43,9,2),
 (43,10,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `fases_is_has_fases` ENABLE KEYS */;


--
-- Definition of table `sapo`.`historico`
--

DROP TABLE IF EXISTS `sapo`.`historico`;
CREATE TABLE  `sapo`.`historico` (
  `codHistorico` int(11) NOT NULL auto_increment,
  `Aluno_numMatricula` int(11) NOT NULL,
  `Orientador_codigo` int(11) NOT NULL,
  `Fases_codigoFase` int(11) NOT NULL,
  `Fases_Jogos_codigo` int(11) NOT NULL,
  `horaInicial` varchar(8) NOT NULL,
  `horaFinal` varchar(8) NOT NULL,
  `data` date NOT NULL,
  `nivelDificuldade` int(11) NOT NULL,
  `qntdErros` int(11) NOT NULL,
  PRIMARY KEY  (`codHistorico`),
  KEY `fk_Historico_Aluno1` (`Aluno_numMatricula`),
  KEY `fk_Historico_Orientador1` (`Orientador_codigo`),
  KEY `fk_Historico_Fases1` (`Fases_codigoFase`,`Fases_Jogos_codigo`),
  KEY `fk_Jogo` (`Fases_Jogos_codigo`),
  CONSTRAINT `fk_Fases` FOREIGN KEY (`Fases_codigoFase`) REFERENCES `fases` (`codigoFase`),
  CONSTRAINT `fk_Historico_Aluno1` FOREIGN KEY (`Aluno_numMatricula`) REFERENCES `aluno` (`numMatricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historico_Orientador1` FOREIGN KEY (`Orientador_codigo`) REFERENCES `orientador` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Jogo` FOREIGN KEY (`Fases_Jogos_codigo`) REFERENCES `jogos` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`historico`
--

/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
LOCK TABLES `historico` WRITE;
INSERT INTO `sapo`.`historico` VALUES  (1,1,1,1,1,'08:32:21','08:32:36','2009-11-23',0,1),
 (2,1,1,1,1,'08:32:21','08:32:36','2009-11-23',0,1),
 (3,1,1,2,1,'08:32:36','08:32:56','2009-11-23',100,2),
 (4,1,1,2,1,'08:32:36','08:32:56','2009-11-23',100,2),
 (5,1,1,3,1,'08:32:56','08:33:11','2009-11-23',100,1),
 (6,1,1,3,1,'08:32:56','08:33:11','2009-11-23',100,1),
 (7,1,1,4,1,'08:33:11','08:33:31','2009-11-23',0,1),
 (8,1,1,4,1,'08:33:11','08:33:31','2009-11-23',0,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;


--
-- Definition of table `sapo`.`jogos`
--

DROP TABLE IF EXISTS `sapo`.`jogos`;
CREATE TABLE  `sapo`.`jogos` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`jogos`
--

/*!40000 ALTER TABLE `jogos` DISABLE KEYS */;
LOCK TABLES `jogos` WRITE;
INSERT INTO `sapo`.`jogos` VALUES  (1,'Figuras Geometricas'),
 (2,'Frutas no Cesto');
UNLOCK TABLES;
/*!40000 ALTER TABLE `jogos` ENABLE KEYS */;


--
-- Definition of table `sapo`.`orientador`
--

DROP TABLE IF EXISTS `sapo`.`orientador`;
CREATE TABLE  `sapo`.`orientador` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `rua` varchar(30) default NULL,
  `numRua` int(11) default NULL,
  `bairro` varchar(20) default NULL,
  `cidade` varchar(25) default NULL,
  `cep` varchar(10) default NULL,
  `telefone` varchar(14) default NULL,
  `profissao` varchar(15) NOT NULL,
  `login` varchar(25) default NULL,
  `senha` varchar(10) default NULL,
  PRIMARY KEY  (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sapo`.`orientador`
--

/*!40000 ALTER TABLE `orientador` DISABLE KEYS */;
LOCK TABLES `orientador` WRITE;
INSERT INTO `sapo`.`orientador` VALUES  (1,'sapo admin                              ','sapo admin                    ',0,'sapo admin          ','sapo admin               ','00.000-000','(00)0000-0000','administrador  ','SAPO ADMIN          ','SAPOSAPO');
UNLOCK TABLES;
/*!40000 ALTER TABLE `orientador` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
