CREATE TABLE prod_stock (
prodName VARCHAR2(500),
prodStockSSize NUMBER DEFAULT 0,
prodStockMSize NUMBER DEFAULT 0,
prodStockLSize NUMBER DEFAULT 0,
CONSTRAINT fk_prodStock_StockName FOREIGN KEY(prodName) REFERENCES product(prodName)
ON DELETE CASCADE
)



select * from PROD_STOCK

delete from PROD_STOCK