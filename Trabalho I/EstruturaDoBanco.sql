create database if not exists Agenda default charset utf8;

use Agenda;

create table if not exists Contatos(

codigo int unsigned not null auto_increment,
	nome varchar(45) not null,
    data_nascimento date,
    data_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    primary key(codigo)

);

create table if not exists Telefones(

codigo int unsigned not null auto_increment,
    ddd varchar(2) not null,
	numero varchar(11) not null,
    tipo ENUM('CELULAR','RESIDENCIAL','COMERCIAL') not null,
	principal boolean not null,
    contatos_codigo int unsigned not null,
    primary key(codigo),
    foreign key(contatos_codigo) references contatos(codigo)

);

