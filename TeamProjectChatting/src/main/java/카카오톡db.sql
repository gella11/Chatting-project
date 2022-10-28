drop database if exists cacao;
create database cacao;
use cacao;

drop table if exists user;
create table user(
user_num int auto_increment primary key not null, -- 회원번호
user_name varchar(10),                       -- 회원이름
user_pw varchar(30),                       -- 비밀번호
user_email varchar(30),                     -- 이메일
user_phone varchar(15),                     -- 폰번호
user_profile longtext,                       -- 프로필사진
user_msg varchar(20)                        -- 상태메시지
);
select*from user;

insert user value( null , '이름가', 2, 3, 4, 5, 6);
insert user value( null , '이름나', 12, 13, 14, 15, 16);
insert user value( null , '이름다', 22, 23, 24, 25, 26);
insert user value( null , '이름라', 32, 33, 34, 35, 36);
insert user value( null , '이름마', 42, 43, 44, 45, 46);
insert user value( null , '이름바', 52, 53, 54, 55, 56);
insert user value( null , '이름사', 62, 63, 64, 65, 66);

select user_num , user_name , user_profile , user_msg from user where user_num=1;
drop table if exists friend;
create table friend(
user_num int ,                              -- 본인
friend_num int ,                            -- 친구들
foreign key(user_num) references user(user_num) on delete cascade  ,
foreign key(friend_num) references user(user_num) 
);
select * from friend;
insert friend value(8 , 2);
insert friend value(8 , 3);
insert friend value(8 , 4);
insert friend value(8 , 5);
insert friend value(8 , 6);

drop table if exists chattingroom;
create table chattingroom( 
cr_num int auto_increment primary key not null,
c_num int ,                               -- 채팅방 식별번호 중복가능
user_num int ,                                  -- 채팅방에 들어와있는 사람들
foreign key(user_num) references user(user_num) on delete cascade
);
select*from chattingroom;
insert into chattingroom (c_num)  value(0);

drop table if exists chattingname;
create table chattingname(
c_num int ,                                -- 채팅방 식별번호
c_name varchar(30)                            -- 채팅방 이름
);
select*from chattingname;

drop table if exists usechat;
create table usechat( 
c_num int,                                    -- 채팅방 식별번호
from_num int,                               -- 누가보냈는지
c_content longtext,                             -- 채팅내용
c_date datetime default now() ,                -- 채팅시간
foreign key(from_num) references user(user_num) on delete cascade
);
select*from usechat;
