create table myapp_board(
  board_no int not null,
  meal char(1) not null,
  diet int not null,
  writer int not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now()
);

alter table myapp_board
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;
  
create table myapp_member(
  member_no int not null,
  name varchar(20) not null,
  id varchar(50) not null,
  age int not null,
  height int not null,
  weight int not null,
  gender char(1) not null,
  password varchar(100) not null,
  created_date date default (current_date())
);

alter table myapp_member
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;

alter table myapp_member
  add constraint myapp_member_uk unique (id);
  
  
-- 게시판 작성자에 대해 외부키 설정
alter table myapp_board
  add constraint myapp_board_writer_fk foreign key (writer) references myapp_member (member_no);


create table myapp_food(
  food_no int not null,
  food varchar(50) not null,
  calories int not null
);

alter table myapp_food
  add constraint primary key (food_no),
  modify column food_no int not null auto_increment;
  
ALTER TABLE myapp_board
ADD CONSTRAINT myapp_board_food_fk
FOREIGN KEY (diet) REFERENCES myapp_food (food_no);

