# [QNABoard_IncludeParts] Include용 Q&A 게시판 소스

관리자 화면&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
회원 화원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
비회원 화면
<br>
<img src="https://user-images.githubusercontent.com/20694370/125551222-949dd0a9-4eee-475c-91de-39ef43c7705c.png" width="300" height="300">
<img src="https://user-images.githubusercontent.com/20694370/125551397-7c685e80-c6e3-41bf-999c-0f112af8c984.png" width="300" height="300">
<img src="https://user-images.githubusercontent.com/20694370/125551275-3be4a2ef-5132-41f8-93e5-5a2f38e2b542.png" width="300" height="300">
<br>
<br>
관리자 화면 : 모두 열람, 모든 게시글 바로 열람, 답변 및 삭제<br>
<br>
회원 화면 : 작성자 게시글 바로 열람, 본문삭제<br>
<br>
비회원 화면 : 비밀번호 체크 후 열람, 열람 시 누구든지 삭제<br>
<br>
# TABLE
<br>
CREATE TABLE qna ( <br>
qQno NUMBER PRIMARY KEY, <br>
qBno NUMBER NOT NULL, <br>
qTitle VARCHAR2(50) NOT NULL, <br>
qWriter VARCHAR2(30) NOT NULL, <br>
qContent VARCHAR2(1000) NOT NULL, <br>
qPassword VARCHAR2(60) NOT NULL, <br>
qRegdate DATE DEFAULT SYSDATE, <br>
qUpdatedate DATE DEFAULT SYSDATE, <br>
qReproot NUMBER DEFAULT 0, <br>
qRepstep NUMBER DEFAULT 0, <br>
qRepindent NUMBER DEFAULT 0, <br>
qUserId VARCHAR2(30), <br>
<br>
CONSTRAINT fk_qna_qBno FOREIGN KEY(qBno) REFERENCES product(prodBno) <br>
ON DELETE CASCADE <br>
)<br>
<br>
CREATE SEQUENCE qna_seq<br>
INCREMENT BY 1<br>
START WITH 1<br>
MAXVALUE 1000000000<br>
NOCYCLE<br>
<br>

# FILES
<br>
[QNA.zip](https://github.com/manco-nineight/EzenTeamProject/files/6829592/QNA.zip)<br>
<br>
<br>
java files - controller, domain, persistence, service 패키지에 각각 추가<br>
<br>
private.png - webapp/resources/img 폴더에 추가<br>
qna.js  - webapp/resources/js 폴더에 추가<br>
<br>
QnaMapper.xml - mappers 폴더에 추가<br>
<br>
qnaPage.jsp - view/qna 폴더에 추가, prodRead 페이지에 include<br>
