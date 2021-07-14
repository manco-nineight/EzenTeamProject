
CREATE TABLE attach(
   filename VARCHAR2(300) NOT NULL,
   bno NUMBER NOT NULL,
   regdate DATE DEFAULT SYSDATE,
   CONSTRAINT fk_attach_bno FOREIGN KEY(bno) REFERENCES board(bno)
)

select * from attach

delete from attach


CREATE TABLE attach(
	filename varchar2(300) not null.
	bno number not null,
	regdate DATE default sysdate,
	constraint fk_attach_bno foreign key(bno) references board(bno)
)


오라클에 view 는 가상테이블이다.
TABLE 과 view 
view 는 조인과 관련되 있다.

뷰 생성 기준의 테이블 값을 그래도 생성한다.
뷰에 컬럼값을 생성하면 해당 테이블에도 생성이된다.
CREATE OR REPLACE VIEW v_member
AS
SELECT * FROM member

SELECT * FROM v_member

insert into v_member(userId,userPw,userName,email)
values ('m006','m006','봉식이','m@m.com')

create or REPLACE view v_member_2_5
as
select * from member where userId >='m002' and userId <='m005'

SELECT * FROM v_member_2_5

update v_member_2_5 set userId = 'M002' where userId = 'm002'

update member set userId = 'm002' where userId = 'M002'

drop view v_member_2_5

create or replace view v_member_2_5
as
select * from member where userId >='m002' and userId <='m005'
with check option

with check option- 체크하는 옵션

update v_member_2_5 set userId = 'm002' where userId = 'm005'
update v_member_2_5 set userId = 'm005' where userId = 'm002'

update v_member_2_5 set userPw = 'm009' where userId = 'm005'
delete from v_member_2_5 where userId = 'm005'

create or replace view v_member_2_5
as
select * from member where userId >='m002' and userId <='m005'
with read only - 읽기만 가능한 조건

delete from v_member_2_5 where userId = 'm003'


조인 학습
.

create table aaa(
id number primary key,
mid number,
name varchar2(12)
)

create table bbb(
mid number primary key,
mname varchar2(12)
)

insert into aaa values (1,100,'kim')
insert into aaa values (2,200,'lim')
insert into aaa values (3,300,'park')
insert into aaa values (4,null,'kang')
insert into aaa values (5,500,'nam')


insert into bbb values (100,'김')
insert into bbb values (200,'이')
insert into bbb values (300,'박')
insert into bbb values (400,'강')
insert into bbb values (500,'남')

select *from aaa a,bbb b
이피조인	-	실무 -이걸 사용하는거을 추천
select a.id, a.name, b.mid, b.mname  from aaa a,bbb b

select a.id, a.name, b.mid, b.mname  from aaa a,bbb b where a.mid = b.mid



인너조인	-	시험에서 많이 나온다.
select a.id, a.name, b.mid, b.mname  from aaa a inner join bbb b
on a.mid = b.mid

네튜럴 조인 제약 - 조인에 참여하는 테이블의 컬럼명,해당 컬럼의 자료형,그 자료형 크기까지 
같아야 한다. 
select * from aaa a natural join bbb b

아웃터 조인 - 값이 일치 해지 않아도 값이 나온다.
레프터 -왼쪽기준 - 왼쪽 값만 나온다
/라우터 -오른쪽기준 - 오른쪽 값만 나온다
/풀 -전체 - 왼쪽 값과 오른쪽 값이 따로 나온다.

레프터
select a.id, a.name, b.mid, b.mname from aaa a left outer join bbb b
on a.mid = b.mid

select a.id, a.name, b.mid, b.mname from bbb b left outer join aaa a
on a.mid = b.mid

라이트
select a.id, a.name, b.mid, b.mname from aaa a right outer join bbb b
on a.mid = b.mid

풀
select a.id, a.name, b.mid, b.mname from aaa a full outer join bbb b
on a.mid = b.mid

조인을 뷰로 만들어 본다.
create or replace view v_AAA_BBB
as
select a.id 아이디, a.name 영문이름, b.mid 한글아이디, b.mname 한글이름
from aaa a, bbb b
where a.mid = b.mid

별칭을 넣어준다.- 컴럼명으로 이용된다

select * from v_AAA_BBB

트리거 - 필기 시험에 필수로 나온다. 방아쇠가 나온다면 무조건 트리거 
-이벤트와 헨들러와 비슷하다.

쌍둥이 테이블

create table aaa_back(
id number primary key,
mid number,
name varchar2(12)
)

delete from aaa

인덱스 학습 - 색인역할을 한다. -  책으로 따지면 목차 색인
주키 = 기본키 = pk = primary key
기본키로 설정이 되어 있으면,
그 컬럼으로 만들어진 index가 자동으로 생성...
index는 최후의 수단 - 데이터가 많아 질수록 속도가 느려진다.
인덱스는 가능하면 사용하지 않는다.

CREATE INDEX idx_aaa_id_mid on aaa(id, mid)



팀단위 프로젝트로 의논


오픈마켓이 

단품마켓 쇼핑물

권한을 1개 추가를 하면
오픈마켓이 된다.
















