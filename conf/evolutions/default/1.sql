# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table kniha (
  id                        varchar(255) not null,
  nazev                     varchar(255),
  autor                     varchar(255),
  rok_vydani                varchar(255),
  nakladatelstvi            varchar(255),
  constraint pk_kniha primary key (id))
;

create table uzivatel (
  id                        varchar(255) not null,
  jmeno                     varchar(255),
  heslo                     varchar(255),
  constraint pk_uzivatel primary key (id))
;

create sequence kniha_seq;

create sequence uzivatel_seq;




# --- !Downs

drop table if exists kniha cascade;

drop table if exists uzivatel cascade;

drop sequence if exists kniha_seq;

drop sequence if exists uzivatel_seq;

