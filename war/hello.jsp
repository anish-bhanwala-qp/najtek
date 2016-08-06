<html>
  <head><title>Hello :: Spring Application</title></head>
  <body>
    <h1>Hello - Spring Application</h1>
    <p>Greetings.</p>
  </body>
</html>

create table user (
	id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(64) NOT NULL,
	password varchar(64) NOT NULL,
	
	PRIMARY_KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Employee` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Employee` (`id`, `name`, `role`)
VALUES
	(1, 'Pankaj', 'CEO'),
	(2, 'David', 'Manager');