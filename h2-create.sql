create table book (
  id                        bigint not null,
  title                     varchar(64) not null,
  author                    varchar(64) not null,
  constraint uq_book_title unique (title),
  constraint pk_book primary key (id))
;

create sequence book_seq;



