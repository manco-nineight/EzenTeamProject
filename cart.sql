CREATE TABLE cart(
    cartNo NUMBER primary key,
    userId VARCHAR2(30) not null,
    prodName VARCHAR2(500) not null,
    prodSize VARCHAR2(10),
    amount NUMBER default 0,
    constraint fk_cart_userid FOREIGN KEY(userId) REFERENCES member(userId),
    FOREIGN KEY(prodName) REFERENCES product(prodName)
    ON DELETE CASCADE
)

ALTER TABLE cart ADD Column prodSize

drop table cart


INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('test1', 1, 'food', '테스트', '내용', '24500', 'abc.png')


INSERT INTO cart (cartNo, userId, prodName, prodSize, amount)
VALUES (1, 'm001', 'test1', 'S', 1)

INSERT INTO cart (cartNo, userId, prodName, prodSize, amount)
VALUES (2, 'm002', 'test2', 'M', 2)

INSERT INTO cart (cartNo, userId, prodName, prodSize, amount)
VALUES (3, 'm002', '테스트2', 'L', 3)

select cartNo, c.userId, p.prodName, p.prodPrice, prodSize, amount, s.prodStockSSize, s.prodStockMSize, s.prodStockLSize
from member m, product p, cart c, prod_stock s
where m.userId=c.userId and p.prodName=c.prodName;

CREATE OR REPLACE VIEW v_cart
AS
select cartNo, c.userId, p.prodName, p.prodPrice, p.prodThumbnail, amount, s.prodStockSSize, s.prodStockMSize, s.prodStockLSize
from member m, product p, cart c
where m.userId=c.userId and p.prodName=c.prodName
WITH READ ONLY

select * from v_cart where userId = 'm001'

select * from cart