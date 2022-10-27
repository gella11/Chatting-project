drop database cacao;
create database cacao;
use cacao;

drop table if exists user;
create table user(
user_num int auto_increment primary key not null, -- 회원번호
user_name varchar(10),							  -- 회원이름
user_pw varchar(30),							  -- 비밀번호
user_email varchar(30), 						  -- 이메일
user_phone varchar(15), 						  -- 폰번호
user_profile longtext,							  -- 프로필사진
user_msg varchar(20) 							  -- 상태메시지
);
select*from user;

drop table if exists friend;
create table friend(
user_num int ,										-- 본인
friend_num int , 									-- 친구들
foreign key(user_num) references user(user_num) on delete cascade  ,
foreign key(friend_num) references user(user_num) 
);

drop table if exists chattingnumber;
create table chattingnumber(
c_num int auto_increment primary key not null,	  -- 채팅방 식별번호
c_name varchar(30)	 						 	  -- 채팅방 이름
);
select*from chattingnumber;

drop table if exists chattingroom;
create table chattingroom( 
c_num int , 									   -- 채팅방 식별번호 중복가능
user_num int , 						               -- 채팅방에 들어와있는 사람들
foreign key(c_num) references chattingnumber(c_num) on delete cascade ,
foreign key(user_num) references user(user_num) on delete cascade
);
select*from chattingroom;

drop table if exists usechat;
create table usechat( 
c_num int,					  					    -- 채팅방 식별번호
from_num int,									    -- 누가보냈는지
c_content longtext, 	      					    -- 채팅내용
c_date datetime default now() , 					-- 채팅시간
foreign key(c_num) references chattingnumber(c_num) on delete cascade ,
foreign key(from_num) references user(user_num) on delete cascade
);
select*from usechat;
