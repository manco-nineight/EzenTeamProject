CREATE TABLE member (

userId VARCHAR2(30) PRIMARY KEY,
userPw VARCHAR2(50) NOT NULL,
userName VARCHAR2(25) NOT NULL,
userEmail VARCHAR2(60) NOT NULL,
userBirthday VARCHAR2(10) NOT NULL,
userAddress VARCHAR2(600) NOT NULL,
userRegdate DATE DEFAULT SYSDATE,
userUpdatedate DATE DEFAULT SYSDATE,
userGrade NUMBER DEFAULT 1

)

SELECT * FROM member

UPDATE member SET userGrade = 2 WHERE userId = 'redic123'

INSERT INTO member (userId, userPw, userName, userEmail, userBirthday, userAddress, userGrade)
VALUES ('redic123', 'asdf1asdf', 'BahMir', 'redic123@naver.com', '1994-01-11', '경기도 양주시', 3)

INSERT INTO member (userId, userPw, userName, userEmail, userBirthday, userAddress, userGrade)
VALUES ('member003', 'asdf1asdf', '사용자001', 'redic123@naver.com', '1994-01-11', '경기도 양주시', 1)

INSERT INTO member (userId, userPw, userName, userEmail, userBirthday, userAddress, userGrade)
VALUES ('admin', 'asdf1asdf', 'Ezen_TeamPrj', 'Ezen_TeamPrj@naver.com', '1994-01-11', '경기도 양주시', 2)