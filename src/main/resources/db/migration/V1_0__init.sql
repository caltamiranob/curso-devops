CREATE TABLE `empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruc` varchar(11) NOT NULL,
  `razon_social` varchar(100) NOT NULL,
  `razon_social_comercial` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `celular` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
