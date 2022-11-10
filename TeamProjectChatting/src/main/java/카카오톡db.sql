drop database if exists cacao;
create database cacao;
use cacao;

drop table if exists user;
create table user(

   user_num int auto_increment not null,           -- 회원번호
   user_name varchar(10) unique not null,           -- 회원이름
   user_pw varchar(30),                       -- 비밀번호
   user_email varchar(30),                     -- 이메일
   user_phone varchar(15),                     -- 폰번호
   user_profile longtext,                       -- 프로필사진
   user_msg varchar(20),                       -- 상태메시지
   user_department varchar(20) ,                 -- 부서명
   user_birth varchar(13),                       -- 생년월일            
   user_date datetime default now(),              -- 입사날짜
      
   constraint primary key (user_num)
        
);

select*from user;


insert user value( null , '이름나', 12, 13, 14, null, 16,'경리부','19931011',null);
insert user value( null , '이름다', 22, 23, 24, null, 26,'경제부','19911215',null);
insert user value( null , '이름라', 32, 33, 34, null, 36,'관리부','19870313',null);
insert user value( null , '이름마', 42, 43, 44, null, 46,'회계부','19550501',null);
insert user value( null , '이름바', 52, 53, 54, null, 56,'개발부','19670617',null);
insert user value( null , '이름사', 62, 63, 64, null, 66,'시설부','19800811',null);

drop table if exists friend;
create table friend(
   
    user_num int ,                              -- 본인
   friend_num int ,                            -- 친구들
    
   foreign key(user_num) references user(user_num) on delete cascade  ,
   foreign key(friend_num) references user(user_num) 
    
);
select * from friend;
insert friend value(1 , 2);
insert friend value(1 , 3);
insert friend value(1 , 4);
insert friend value(1 , 5);
insert friend value(1 , 6);
insert friend value(2 , 1);
insert friend value(2 , 3);
insert friend value(2 , 4);
insert friend value(2 , 5);
insert friend value(2 , 6);






drop table if exists chattingname;
create table chattingname(

   c_num int,                                  -- 채팅방 식별번호
   c_name varchar(30)                            -- 채팅방 이름
    
);
select*from chattingname;
-- select * from chattingroom order by c_num desc limit 1;


select c_num from chattingname where c_name ='이름나,이름가';

drop table if exists chattingroom;
create table chattingroom( 

   c_num int ,                               -- 채팅방 식별번호 중복가능
   user_num int                                  -- 채팅방에 들어와있는 사람들
   -- foreign key(c_num) references chattingnumber(c_num) on delete cascade ,
   -- foreign key(user_num) references user(user_num) on delete cascade

);
select*from chattingroom;


-- select count(c_num) from chattingname where c_name = '이름가,이름나' or c_name = '이름나,이름가';

drop table if exists usechat;
create table usechat( 

   c_num int,                                    -- 채팅방 식별번호
   from_num int,                               -- 누가보냈는지
    from_name varchar(10),
   c_content longtext,                             -- 채팅내용
   c_date datetime default now() ,                -- 채팅시간
    user_profile longtext,
   -- foreign key(c_num) references chattingnumber(c_num) on delete cascade ,
   foreign key(from_num) references user(user_num) on delete cascade

);
select*from usechat;


drop table if exists category;
create table category( 

   c_no   int AUTO_INCREMENT primary key,         -- 카테고리번호
    c_name   varchar(100)                     -- 카테고리이름
    
);
select*from category;

insert into category(c_name) value('전체');
insert into category(c_name) value('인사');
insert into category(c_name) value('마케팅');
insert into category(c_name) value('행정');
insert into category(c_name) value('시설');

drop table if exists board;
CREATE TABLE board(

   b_no      int auto_increment primary key,  -- 번호     
    b_title      varchar(1000),                -- 제목       
    b_content   longtext ,                  -- 내용
    b_file      longtext ,                  -- 첨부파일 [ 게시물 1개당 첨부파일 1개 ]
    b_date       datetime default now()   ,      -- 작성일 : 기본값 현재 DB서버 시스템 날짜 
    b_view      int default 0 ,               -- 조회수 : 기본값 0 
    c_no      int ,                     -- 카테고리번호 FK 
    user_name   varchar(10)   ,               -- 작성자 번호
    
    constraint c_no foreign key (c_no) references category(c_no) ,
    constraint user_name foreign key (user_name) references user(user_name) 
    
);
select * from board;
 insert into board values(null, 'b제목', 'b내용', 'b첨부파일', null, 1, 1, '이름나');
 insert into board values(null, '다제목', '다내용', '다첨부파일', null, 1, 1, '이름다');
 insert into board values(null, '라제목', '라내용', '라첨부파일', null, 1, 1, '이름라');
 insert into board values(null, '마제목', '마내용', '마첨부파일', null, 1, 1, '이름마');

drop table if exists reply;
create table reply(

   r_no      int auto_increment,
    r_content    varchar(1000) not null,
    r_date       datetime default now(),
    r_index      int default 0,            -- [ 상위댓글의 번호  들여쓰기 정도 ]
    b_no      int not null,
    user_name   varchar(15),
    constraint rno_pk primary key(r_no),
    constraint foreign key(user_name) references user(user_name) on delete cascade,          -- 회원 탈퇴시, 댓글도 같이 삭제
    constraint b_no foreign key(b_no) references  board(b_no) on delete cascade                -- 게시물 삭제시 댓글도 같이 삭제
    
);
select*from user;