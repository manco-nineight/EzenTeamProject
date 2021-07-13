CREATE TABLE prodStock (
prodStockName VARCHAR2(500),
prodStockSSzie NUMBER DEFAULT 0,
prodStockMSize NUMBER DEFAULT 0,
prodStockLSize NUMBER DEFAULT 0,
CONSTRAINT fk_prodStock_prodStockName FOREIGN KEY(prodStockName) REFERENCES product(prodName)
ON DELETE CASCADE
)

SELECT * FROM prodStock