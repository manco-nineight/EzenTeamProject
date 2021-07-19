

리뷰 테이블에 별점 추가 
ALTER TABLE review ADD reStar NUMBER DEFAULT '임의의값' NOT NULL;

테이블 삭제시 사용
drop table  product
drop table  prodStock
drop table  review
drop table  qna

회원 + 관리자
.
SELECT * from product
SELECT * from review

CREATE TABLE member(
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

userAddress - 회원 주소
userGrade - 관리자(권한) 부여 

상품(게시판)
.
CREATE TABLE product (
prodName VARCHAR2(500) PRIMARY KEY,
prodBno NUMBER  UNIQUE,
prodCategory NUMBER NOT NULL,
prodTitle VARCHAR2(500) NOT NULL,
prodContent VARCHAR2(3000) NOT NULL,
prodReadCnt NUMBER DEFAULT 0,
prodRegdate DATE DEFAULT SYSDATE,
prodUpdatedate DATE DEFAULT SYSDATE,
prodPrice NUMBER NOT NULL,
prodSalesCount NUMBER DEFAULT 0
)

게시물 테스트 용으로 제작했음.
insert into product(prodName,prodBno, prodCategory , prodTitle,prodContent, prodPrice)
values('aaa',1,1,'asad','asafsddsfsd',1)




INSERT INTO product
(prodName, prodBno,prodCategory, prodTitle, prodPrice)
VALUES
('m002',(SELECT NVL2(MAX(prodBno), MAX(prodBno)+1, 1) FROM board,'saddsa','asdds', 1)


유니크
UNIQUE - 중복 방지

CREATE OR REPLACE VIEW v_product_s
AS
SELECT * FROM product prod, prodStock prodS
WHERE prod.prodName = prodS.prodStockName
WITH READ ONLY

(SELECT ONLY)	

-	읽기 용 가상 테이블 




prodCategory NUMBER NOT NULL,	-	상품 종류 번호	1~4	-리스트용 썸네일
prodPrice NUMBER NOT NULL,		-	상품 가격
prodSales NUMBER 				-	판매량

bno2	prodCategory


PRODUCT STOCK	-	사이즈 수량 체크
.
CREATE TABLE prodStock (
prodStockName VARCHAR2(500),
prodStockSSzie NUMBER DEFAULT 0,
prodStockMSize NUMBER DEFAULT 0,
prodStockLSize NUMBER DEFAULT 0,
CONSTRAINT fk_prodStock_prodStockName FOREIGN KEY(prodStockName) REFERENCES product(prodName)
ON DELETE CASCADE
)

prodBoardBno - 게시물이 등록 될 때 마다 증가하는 형태이다

리뷰
.
CREATE TABLE review (
reRno NUMBER PRIMARY KEY,
reBno NUMBER NOT NULL ,
reWriter VARCHAR2(30) NOT NULL ,
reContent VARCHAR2(2000) NOT NULL ,
reRegdate DATE DEFAULT SYSDATE ,
reUpdatedate DATE DEFAULT SYSDATE ,
reStar NUMBER NOT NULL,
CONSTRAINT review_fk_reBno FOREIGN KEY(reBno) REFERENCES product(prodBno)
ON DELETE CASCADE
)


reRno NUMBER PRIMARY KEY,
reBno NUMBER NOT NULL ,
reWriter VARCHAR2(30) NOT NULL ,
reContent VARCHAR2(2000) NOT NULL ,

Q&A
.
CREATE TABLE qna (
qQno NUMBER PRIMARY KEY,
qBno NUMBER NOT NULL,
qTitle VARCHAR2(50) NOT NULL,
qWriter VARCHAR2(30) NOT NULL,
qContent VARCHAR2(1000) NOT NULL,
qPassword VARCHAR2(60) NOT NULL,
qRegdate DATE DEFAULT SYSDATE,
qUpdatedate DATE DEFAULT SYSDATE,
qReproot NUMBER,
qRepstep NUMBER,
qRepindent NUMBER,
CONSTRAINT fk_qna_qBno FOREIGN KEY(qBno) REFERENCES product(prodBno)
ON DELETE CASCADE
)

07-19 추가
CREATE SEQUENCE qna_seq
INCREMENT BY 1
START WITH 1
MAXVALUE 1000000000
NOCYCLE


파일 업로드	-	현재 외래키 등록이 안돼어 있어서 될까?
.
CREATE TABLE attach (
attachFilename VARCHAR2(200) NOT NULL,
attachBno NUMBER,
attachRno NUMBER,
attachRegdate DATE DEFAULT SYSDATE
)

attachBno NUMBER,	-	해당 게시물 번호
attachRno NUMBER,	-	해당 리뷰 댓글 번호

주문
.
CREATE TABLE prod_oder (
orderNum NUMBER PRIMARY KEY,
orderProdName VARCHAR2(100) NOT NULL,
orderProdPrice NUMBER NOT NULL,
orderProdQuantity NUMBER NOT NULL,
orderProdSize NUMBER,
orderUserId VARCHAR2 (30) NOT NULL,
orderUserAddress VARCHAR2 (600) NOT NULL,
CONSTRAINT fk_order_oderUserId FOREIGN KEY(orderUserId) REFERENCES member(userId)
)










