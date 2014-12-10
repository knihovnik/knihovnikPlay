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

create sequence kniha_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists kniha;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists kniha_seq;

