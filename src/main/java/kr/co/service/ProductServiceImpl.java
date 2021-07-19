package kr.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.ProductVO;
import kr.co.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO pDao;
	
	@Override
	public ProductVO review(Integer prodBno) {
		
		return pDao.review(prodBno);
	}
}
