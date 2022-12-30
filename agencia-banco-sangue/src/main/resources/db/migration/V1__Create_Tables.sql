CREATE TABLE IF NOT EXISTS person (
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    cpf varchar(14) DEFAULT NULL,
    rg varchar(12) DEFAULT NULL,
    birth_date timestamp,
    gender varchar(9) DEFAULT NULL,
    mother varchar(255) DEFAULT NULL,
    father varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    cep varchar(10) DEFAULT NULL,
    street varchar(255) DEFAULT NULL,
    number int(3) DEFAULT NULL,
    neighborhood varchar(255) DEFAULT NULL,
    city varchar(255) DEFAULT NULL,
    state varchar(2) DEFAULT NULL,
    phone varchar(16) DEFAULT NULL,
    cellphone varchar(17) DEFAULT NULL,
    height double DEFAULT NULL,
    weight double DEFAULT NULL,
    blood_type varchar(2) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY UK_cpf (cpf),
    UNIQUE KEY UK_rg (rg)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;