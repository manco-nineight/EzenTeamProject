package kr.co.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.ProductVO;
import kr.co.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO prodDAO;

	@Override
	public int getBnoForAttach() {
		return prodDAO.getBnoForAttach();
	}

	@Override
	public void insertProduct(ProductVO prodVO) {
		prodDAO.insertProduct(prodVO);
	}

	@Override
	public int getProdAmount() {
		return prodDAO.getProdAmount();
	}
	
	@Override
	public List<ProductVO> listProd(int startNum, Map<String, Object> map) {
		return prodDAO.listProd(startNum, map);
	}
	

	@Override
	public ProductVO prodRead(String prodName) {
		return prodDAO.prodRead(prodName);
	}
	
	@Transactional
	@Override
	public ProductVO prodUpate(String prodName) {
		prodDAO.readcnt(prodName);
		
		return prodDAO.prodUpdate(prodName);
	}

	@Override
	public void updateProduct(ProductVO prodVO) {

		prodDAO.updateProduct(prodVO);
	}

	@Override
	public String getThumnail(String prodName) {
		// TODO Auto-generated method stub
		return prodDAO.getThumnail(prodName);
	}
	
	@Override
	public List<String> getThumbFilePath(String prodName) {
		return prodDAO.getThumbFilePath(prodName);
	}
	
	@Override
	public void deleteProd(String prodName) {
		prodDAO.deleteProd(prodName);
	}

	@Override
	public String prodNameDupCheck(String prodName) {
		return prodDAO.prodNameDupCheck(prodName);
	}
	
	@Override
	public ProductVO getContent(int prodBno) {
		return prodDAO.getContent(prodBno);
	}

}
