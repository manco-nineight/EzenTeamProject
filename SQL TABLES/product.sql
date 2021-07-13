CREATE TABLE product (
prodName VARCHAR2(500) PRIMARY KEY,
prodBno NUMBER UNIQUE,
prodCategory VARCHAR2(50) NOT NULL,
prodTitle VARCHAR2(500) NOT NULL,
prodContent VARCHAR2(3000) NOT NULL,
prodReadCnt NUMBER DEFAULT 0,
prodRegdate DATE DEFAULT SYSDATE,
prodUpdatedate DATE DEFAULT SYSDATE,
prodPrice NUMBER NOT NULL,
prodSalesCount NUMBER DEFAULT 0,
prodThumbnail VARCHAR2(500) UNIQUE
)

CREATE OR REPLACE VIEW v_product_s
AS
SELECT * FROM product prod, prodStock prodS
WHERE prod.prodName = prodS.prodStockName
WITH READ ONLY

INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodReadCnt, prodPrice, prodSalesCount, prodContent)
VALUES ('제품이름', 1, '카테고리01', '제목입니다', 0, 10000, 10, '상세 내용 입니다.')

UPDATE product SET prodBno = 1 WHERE prodBno = 0;

SELECT * FROM product

DELETE FROM product

DROP TABLE product

ALTER TABLE product ADD prodContent VARCHAR2(3000) NOT NULL