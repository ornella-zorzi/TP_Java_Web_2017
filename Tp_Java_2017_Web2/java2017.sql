# SQL Manager 2005 for MySQL 3.6.5.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : java2017


SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `java2017`
    CHARACTER SET 'latin1'
    COLLATE 'latin1_swedish_ci';

#
# Structure for the `categoria` table : 
#

CREATE TABLE `categoria` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_cat` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Structure for the `elemento` table : 
#

CREATE TABLE `elemento` (
  `id_el` int(11) NOT NULL AUTO_INCREMENT,
  `id_te` int(11) NOT NULL,
  `nombre_el` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_el`,`id_te`),
  KEY `id_te` (`id_te`),
  KEY `id_el` (`id_el`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

#
# Structure for the `persona` table : 
#

CREATE TABLE `persona` (
  `id_per` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(8) DEFAULT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `habilitado` bit(1) DEFAULT NULL,
  `usuario` varchar(20) DEFAULT NULL,
  `contraseña` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `id_cat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_per`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `usuario` (`usuario`),
  KEY `id_per` (`id_per`),
  KEY `id_cat` (`id_cat`),
  CONSTRAINT `persona_fk` FOREIGN KEY (`id_cat`) REFERENCES `categoria` (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

#
# Structure for the `tipo_elemento` table : 
#

CREATE TABLE `tipo_elemento` (
  `id_te` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_te` varchar(20) DEFAULT NULL,
  `cant_reserva_max` int(11) DEFAULT NULL,
  `tiempo_limite` int(11) DEFAULT NULL,
  `dias_anticipacion` int(11) DEFAULT NULL,
  `encargado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_te`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Structure for the `reserva` table : 
#

CREATE TABLE `reserva` (
  `id_el` int(11) NOT NULL,
  `id_te` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `detalle` varchar(20) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `id_res` int(11) NOT NULL AUTO_INCREMENT,
  `id_per` int(11) NOT NULL,
  `hora_fin` time DEFAULT NULL,
  PRIMARY KEY (`id_el`,`id_te`,`fecha`,`hora_inicio`,`id_res`,`id_per`),
  KEY `id_el` (`id_el`),
  KEY `id_te` (`id_te`),
  KEY `id_res` (`id_res`),
  KEY `id_per` (`id_per`),
  CONSTRAINT `reserva_fk1` FOREIGN KEY (`id_te`) REFERENCES `tipo_elemento` (`id_te`),
  CONSTRAINT `reserva_fk` FOREIGN KEY (`id_el`) REFERENCES `elemento` (`id_el`),
  CONSTRAINT `reserva_fk2` FOREIGN KEY (`id_per`) REFERENCES `persona` (`id_per`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

#
# Data for the `categoria` table  (LIMIT 0,500)
#

INSERT INTO `categoria` (`id_cat`, `nombre_cat`) VALUES 
  (1,'administrador'),
  (2,'encargado'),
  (3,'usuario');

COMMIT;

#
# Data for the `elemento` table  (LIMIT 0,500)
#

INSERT INTO `elemento` (`id_el`, `id_te`, `nombre_el`) VALUES 
  (3,5,'duplex'),
  (4,5,'cabaña'),
  (5,6,'4k');

COMMIT;

#
# Data for the `persona` table  (LIMIT 0,500)
#

INSERT INTO `persona` (`id_per`, `dni`, `nombre`, `apellido`, `habilitado`, `usuario`, `contraseña`, `email`, `id_cat`) VALUES 
  (13,'37815875','Julieta','Chaparro',True,'jchaparro','jchaparro','julietachaparro94@gmail.com',1),
  (14,'37403327','Denise','Negro',True,'denise','denise','gisel-15-08@hotmail.com',2),
  (15,'12345678','Juan','Perez',False,'jperez','jperez','jperez@hotmail.com',2),
  (16,'12121212','maria','gomez',True,'maria','maria','maria@hotmail.com',3);

COMMIT;

#
# Data for the `tipo_elemento` table  (LIMIT 0,500)
#

INSERT INTO `tipo_elemento` (`id_te`, `nombre_te`, `cant_reserva_max`, `tiempo_limite`, `dias_anticipacion`, `encargado`) VALUES 
  (5,'casa',5,5,6,True),
  (6,'televisor',7,8,9,False);

COMMIT;

#
# Data for the `reserva` table  (LIMIT 0,500)
#

INSERT INTO `reserva` (`id_el`, `id_te`, `fecha`, `hora_inicio`, `detalle`, `estado`, `id_res`, `id_per`, `hora_fin`) VALUES 
  (3,5,'2018-02-14','15:00:00','para dos',NULL,32,14,'18:00:00'),
  (5,6,'2018-02-12','12:00:00','philips',NULL,28,13,'15:00:00'),
  (5,6,'2018-02-12','16:00:00','lg',NULL,29,13,'18:00:00'),
  (5,6,'2018-02-13','16:00:00','lg',NULL,33,13,'18:00:00'),
  (5,6,'2018-02-18','10:00:00','samsung',NULL,34,13,'12:00:00');

COMMIT;

