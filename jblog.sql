/**drop**/
drop table users;
drop table blog;
drop table category;
drop table post;
drop table comments;

drop sequence seq_users_no;
drop sequence seq_category_no;
drop sequence seq_post_no;
drop sequence seq_comments_no;
/**create**/
create sequence seq_users_no
INCREMENT BY 1
START WITH 1 
NOCACHE ;

create sequence seq_category_no
INCREMENT BY 1
START WITH 1 
NOCACHE ;

create sequence seq_post_no
INCREMENT BY 1
START WITH 1 
NOCACHE ;

create sequence seq_comments_no
INCREMENT BY 1
START WITH 1 
NOCACHE ;



create table users(
    userNo number,
    id varchar2(50) NOT NULL UNIQUE,
    username varchar2(100) not null,
    password varchar2(50) not null,
    joindate date not null,
    constraint users_no_pk primary key(userno)
);


create table blog(
    id varchar2(50),
    blogTitle varchar2(200) not null,
    logoFile varchar2(200),
    CONSTRAINT blog_id_pk primary key(id),
    CONSTRAINT blog_id_fk FOREIGN KEY(id)
    REFERENCES users(id)
);

create table category(
    cateNo number,
    id varchar2(50),
    cateName varchar2(200) not null,
    description varchar2(200),
    reg_date date not null,
    CONSTRAINT cate_no_pk PRIMARY key(cateNo),
    CONSTRAINT cate_id_fk FOREIGN key(id)
    REFERENCES blog(id)
);

create table post(
    postNo number,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    reg_date date not null,
    CONSTRAINT post_No_pk primary key(postNo),
    CONSTRAINT post_no_fk FOREIGN key(cateNo)
    REFERENCES category(cateNo)
);

create table comments(
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent varchar2(1000) not null,
    regDate date not null,
    CONSTRAINT cmt_no_pk primary key(cmtNo),
    CONSTRAINT cmt_postNo_fk FOREIGN key(postNo) REFERENCES post(postNo),
    CONSTRAINT cmt_userNo_fk FOREIGN KEY(userNo) REFERENCES users(userNo)
);

/**insert**/

insert into users
values(SEQ_USERS_NO.nextval,
       'id',
       'userName',
       1234,
       sysdate
       );
       
insert into blog
values('id',
       '블로그입니다',
       '파일경로');

insert into category
values( SEQ_CATEGORY_NO.nextval,
        '초사이안',
        '첫카테고리',
        '설명',
        sysdate
        );
        
insert into post
values(seq_post_no.nextval,6,'치킨많이먹기','치킨에 대해 얘기해볼게요',sysdate);        
/*user select*/
select userno,
       id,
       username
from users
where password = 1234
and id = 'id';

select *
from users;

select userno,
       id
from users;

select *
from blog;

select *
from category;

delete from blog;
delete from users;

update blog
SET blogtitle = '밥먹었블로그',
logofile = ''
where id = '초사이언';

select seq_category_no.nextval
from dual;

select rownum rnum,
       cateno,
       id,
       catename,
       description,
       regDate
from(select cateno,
            id,
            catename,
            description,
            reg_date as regDate
     from category
     where cateNo = 1)
order by rnum desc;


select cateno,
       count(*)
from post
group by cateNo;

select *
from post;




/*카테고리 리스트*/
select c.catename,
       c.id,
       c.description,
       c.reg_date,
       c.cateNo,
       nvl(b.count,0) as postCnt
from category c left join (select cateno,
                                  count(*) as count
                            from post
                            group by cateno)b
on c.cateno = b.cateno
where id = '치킨맨'
order by c.cateno desc ;
/**카테고리 하나**/
select c.catename,
       c.id,
       c.description,
       c.reg_date,
       c.cateNo,
       nvl(b.count,0) as postCnt
from category c left join (select cateno,
                                  count(*) as count
                            from post
                            group by cateno)b
on c.cateno = b.cateno
where c.cateno = 4;




/***delete***/

delete from category
where cateno = 22;

/***/
select postno,
       cateNo,
       posttitle,
       postContent,
       reg_date as regDate
from post
where cateno = 1;

select count(*) as postCnt
from post
where cateno = 20;