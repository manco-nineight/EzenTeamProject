create table member (
	userId VARCHAR2(30) PRIMARY KEY,
	userPw CHAR(60) NOT NULL,
	userName VARCHAR2(25) NOT NULL,
	userEmail VARCHAR2(60) NOT NULL,
	userBirthday VARCHAR2(10) NOT NULL,
	userAddress VARCHAR2(600) NOT NULL,
	userRegdate DATE DEFAULT SYSDATE,
	userUpdatedate DATE DEFAULT SYSDATE,
	userGrade NUMBER DEFAULT 1
)

SELECT * FROM member

SELECT userId FROM member 

SELECT userAddress FROM member 

ALTER TABLE member MODIFY(userPw CHAR(60), NOT NULL);

delete from member

drop table member CASCADE CONSTRAINTS

UPDATE member SET userGrade = 0 WHERE userId='m001'