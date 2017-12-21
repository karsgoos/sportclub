drop table if exists event;

drop table if exists address;
create table address(
  id int auto-increment not null PRIMARY kay,
  street varchar(100) not null,
  home_number int not null,
  postal_code varchar(100) not null,
  country varchar(100) not null
);
