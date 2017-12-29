create database adv;
use adv;

drop table if exists terminal;
create table terminal(
mac varchar(512) primary key,
name varchar(512)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists project;
create table project(
id serial primary key,
name varchar(256) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists material;
create table material(
id serial primary key,
name varchar(256) not null,
type int not null,
time int not null default 0,
src varchar(512),
project_id bigint(20) unsigned not null,
foreign key(project_id) references project(id) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;