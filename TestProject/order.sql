CREATE TABLE prod_order (

orderNum NUMBER PRIMARY KEY,
orderProdName VARCHAR2(100) NOT NULL,
orderProdPrice NUMBER NOT NULL,
orderProdQuantity NUMBER NOT NULL,
orderProdSize NUMBER,
orderUserId VARCHAR2 (30) NOT NULL,
orderUserAddress VARCHAR2 (600) NOT NULL,
CONSTRAINT fk_order_oderUserId FOREIGN KEY(orderUserId) REFERENCES member(userId)

)

ALTER TABLE prod_order ADD orderProdStatus NUMBER;
주문상태 / ex) 입금확인중, 배송준비중, 배송진행중,배송완료 

ALTER TABLE prod_order ADD orderTrackingNum NUMBER;
택배 운송장 번호