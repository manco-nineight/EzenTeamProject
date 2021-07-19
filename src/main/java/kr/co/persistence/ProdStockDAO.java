package kr.co.persistence;

import kr.co.domain.ProdStockDTO;

public interface ProdStockDAO {

	void insertProdStock(ProdStockDTO prodStockDTO);

	ProdStockDTO prodStockRead(String prodName);
	
	void updateProdStock(ProdStockDTO prodStockDTO);

}
