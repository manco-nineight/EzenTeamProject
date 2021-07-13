CREATE TABLE attach (

attachFilename VARCHAR(200) NOT NULL,
attachBno NUMBER,
attachRno NUMBER,
attachRegdate DATE DEFAULT SYSDATE

)

SELECT * FROM attach