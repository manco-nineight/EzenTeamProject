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


INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('test1', 1, 'food', 'asd', 'asd', '24500', 'abc.png')
