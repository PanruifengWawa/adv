create database adv;
use adv;

drop table if exists terminal;
create table terminal(
mac varchar(128) primary key,
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

drop table if exists vote;
create table vote(
id serial primary key,
name varchar(256) not null,
all_can_vote_number int not null default 0,
ecah_item_can_vote_number int not null default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists vote_item;
create table vote_item(
id serial primary key,
name varchar(256) not null,
img_src varchar(512),
result int not null default 0,
vote_id bigint(20) unsigned not null,
foreign key(vote_id) references vote(id) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists vote_record;
create table vote_record(
id serial primary key,
mac varchar(512) not null,
vote_id bigint(20) unsigned not null,
vote_item_id bigint(20) unsigned not null,
vote_number int not null,
foreign key(vote_id) references vote(id) on delete cascade,
foreign key(vote_item_id) references vote_item(id) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table bg_image(
id serial primary key,
name varchar(512) not null,
type int not null unique,
img_src varchar(512) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table message(
id serial primary key,
mac varchar(512) not null,
terminal_name varchar(512),
content text not null,
state int not null default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into bg_image(name,type,img_src) values('微信墙背景图片',0,'');
insert into bg_image(name,type,img_src) values('抢答大屏幕背景图片',1,'');
insert into bg_image(name,type,img_src) values('抢答终端背景图片',2,'');


drop trigger if exists insert_vote_record_trigger;
delimiter || 
create trigger insert_vote_record_trigger after insert on vote_record for each row   
begin 
	update vote_item set result = result + new.vote_number where id = new.vote_item_id;
end||
delimiter ;


drop trigger if exists delete_vote_record_trigger;
delimiter || 
create trigger delete_vote_record_trigger after delete on vote_record for each row   
begin 
	update vote_item set result = result - old.vote_number where id = old.vote_item_id;
end||
delimiter ;


-- v2
alter table terminal add column src varchar(512);


-- v3 
create table version(
id serial primary key,
version_id varchar(256),
src varchar(512) not null,
app_name varchar(128) not null,
description text
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
