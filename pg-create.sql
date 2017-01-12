create table antecedent (
  id                        bigint not null,
  patient_id                bigint,
  disease_type_id           bigint,
  antecedent_date           timestamp not null,
  detail                    text not null,
  constraint uq_antecedent_detail unique (detail),
  constraint pk_antecedent primary key (id))
;

create table book (
  id                        bigint not null,
  title                     varchar(64) not null,
  author                    varchar(64) not null,
  constraint uq_book_title unique (title),
  constraint pk_book primary key (id))
;

create table condition (
  id                        bigint not null,
  type                      varchar(64) not null,
  value                     integer not null,
  consultation_id           bigint,
  constraint pk_condition primary key (id))
;

create table consultation (
  id                        bigint not null,
  patient_id                bigint,
  doctor_id                 bigint,
  consultation_date         timestamp not null,
  price                     float not null,
  prescription              text not null,
  diagnostic                varchar(255) not null,
  constraint pk_consultation primary key (id))
;

create table disease_type (
  id                        bigint not null,
  name                      varchar(255) not null,
  description               varchar(256),
  constraint pk_disease_type primary key (id))
;

create table doctor (
  id                        bigint not null,
  password                  varchar(255) not null,
  last_name                 varchar(255) not null,
  first_name                varchar(255) not null,
  address                   varchar(255) not null,
  sex                       varchar(255) not null,
  fac                       varchar(255) not null,
  telephone                 bigint not null,
  email                     varchar(255) not null,
  photo                     varchar(255) not null,
  pic                       bytea,
  constraint uq_doctor_telephone unique (telephone),
  constraint uq_doctor_email unique (email),
  constraint pk_doctor primary key (id))
;

create table patient (
  id                        bigint not null,
  password                  varchar(255) not null,
  last_name                 varchar(255) not null,
  first_name                varchar(255) not null,
  sex                       varchar(255) not null,
  telephone                 bigint not null,
  email                     varchar(255) not null,
  address                   varchar(255) not null,
  constraint uq_patient_telephone unique (telephone),
  constraint uq_patient_email unique (email),
  constraint pk_patient primary key (id))
;

create sequence antecedent_seq;

create sequence book_seq;

create sequence condition_seq;

create sequence consultation_seq;

create sequence disease_type_seq;

create sequence doctor_seq;

create sequence patient_seq;

alter table antecedent add constraint fk_antecedent_patient_1 foreign key (patient_id) references patient (id);
create index ix_antecedent_patient_1 on antecedent (patient_id);
alter table antecedent add constraint fk_antecedent_diseaseType_2 foreign key (disease_type_id) references disease_type (id);
create index ix_antecedent_diseaseType_2 on antecedent (disease_type_id);
alter table condition add constraint fk_condition_consultation_3 foreign key (consultation_id) references consultation (id);
create index ix_condition_consultation_3 on condition (consultation_id);
alter table consultation add constraint fk_consultation_patient_4 foreign key (patient_id) references patient (id);
create index ix_consultation_patient_4 on consultation (patient_id);
alter table consultation add constraint fk_consultation_doctor_5 foreign key (doctor_id) references doctor (id);
create index ix_consultation_doctor_5 on consultation (doctor_id);


