# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  isbn                          varchar(255) not null,
  title                         varchar(255),
  sector                        varchar(255),
  published_date                varchar(255),
  date_time                     varchar(255),
  reader_id                     integer,
  publisher                     varchar(255),
  pages                         integer not null,
  authors                       array,
  constraint pk_book primary key (isbn)
);

create table dvd (
  isbn                          varchar(255) not null,
  title                         varchar(255),
  sector                        varchar(255),
  published_date                varchar(255),
  date_time                     varchar(255),
  reader_id                     integer,
  languages                     array,
  subtitles                     array,
  cast                          array,
  constraint pk_dvd primary key (isbn)
);

create table reader (
  id                            integer auto_increment not null,
  name                          varchar(255),
  number                        varchar(255),
  email                         varchar(255),
  constraint pk_reader primary key (id)
);


# --- !Downs

drop table if exists book;

drop table if exists dvd;

drop table if exists reader;

