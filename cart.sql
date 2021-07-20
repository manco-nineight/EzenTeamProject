CREATE TABLE cart(
    cartNo NUMBER primary key,
    userId VARCHAR2(30) not null,
    prodName VARCHAR2(500) not null,
    prodSize VARCHAR2(10),
    amount NUMBER default 0,
    constraint fk_cart_userid FOREIGN KEY(userId) REFERENCES member(userId) ON DELETE CASCADE,
    FOREIGN KEY(prodName) REFERENCES product(prodName)
    ON DELETE CASCADE
)

ALTER TABLE cart ADD Column prodSize
drop table cart
drop table product


INSERT INTO product (prodName, prodBno, prodCategory, prodTitle, prodContent, prodPrice, prodThumbnail) 
VALUES ('test1', 1, 'food', 'asd', 'asd', '24500', 'abc.png')


INSERT INTO cart (cartNo, userId, prodName, prodSize, amount)
VALUES (1, 'm001', 'test1', 'S', 1)
