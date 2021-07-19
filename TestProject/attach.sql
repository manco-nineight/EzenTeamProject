CREATE TABLE prod_attach (
prodAttProdImg VARCHAR2(500),
prodAttBno NUMBER,
prodAttPreBno NUMBER,
prodAttProdThumb VARCHAR2(500),
prodAttRegdate DATE DEFAULT SYSDATE,
CONSTRAINT fk_prod_attach_prodAttBno FOREIGN KEY(prodAttBno) REFERENCES product(prodBno)
ON DELETE CASCADE
)