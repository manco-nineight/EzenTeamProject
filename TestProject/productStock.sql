CREATE TABLE prod_stock (
prodName VARCHAR2(500),
prodStockSSize NUMBER DEFAULT 0,
prodStockMSize NUMBER DEFAULT 0,
prodStockLSize NUMBER DEFAULT 0,
CONSTRAINT fk_prodStock_prodStockName FOREIGN KEY(prodName) REFERENCES product(prodName)
ON DELETE CASCADE
)


INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('test1', 1, 'food', '테스트', '내용', '24500', 'abc.png')

INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('test2', 2, 'jean', '스트', '용내', '39900', 'abcd.png')

INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('테스트2', 3, '상의', '세일', '내용', '29800', 'a1b.jpg')

INSERT INTO prod_stock (prodName, prodStockSSize, prodStockMSize, prodStockLSize) 
VALUES ('test1', 9, 8, 7)

INSERT INTO prod_stock (prodName, prodStockSSize, prodStockMSize, prodStockLSize) 
VALUES ('test2', 10, 11, 12)

INSERT INTO prod_stock (prodName, prodStockSSize, prodStockMSize, prodStockLSize) 
VALUES ('테스트2', 14, 16, 15)

INSERT INTO prod_stock (prodName, prodStockSSize, prodStockMSize, prodStockLSize) 
VALUES ('테스트2', 13, 14, 15)

select * from PROD_STOCK

delete from PROD_STOCK