package kr.co.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.OrderVO;
import kr.co.domain.ProductVO;
import kr.co.persistence.ProdAttachDAO;
import kr.co.persistence.ProdStockDAO;
import kr.co.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProdStockDAO prodStockDAO;
	
	@Autowired
	private ProdAttachDAO prodAttachDAO;
	
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
	@Transactional
	@Override
	public void updateProduct(ProductVO prodVO) {
		
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		
		
		Matcher getImgName = imgPattern.matcher(prodVO.getProdContent());
		
		
			
		while (getImgName.find()) {
				String imgName = getImgName.group();
				int srdIdx = imgName.indexOf("src");
				int endIdx = imgName.indexOf(";");
				String imgRealName = imgName.substring(srdIdx + 5, endIdx - 21);
				
				int bno= prodVO.getProdBno();
				prodAttachDAO.updateAttachInfo(imgRealName, bno);
			
			
		}

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
	public ProductVO getContent(int bno) {
		// TODO Auto-generated method stub
		return prodDAO.getContent(bno);
	}

	@Override
	public String prodNameDupCheck(String prodName) {
		// TODO Auto-generated method stub
		return prodDAO.prodNameDupCheck(prodName);
	}
	
	@Override
	public ProductVO review(Integer prodBno) {
		
		return prodDAO.review(prodBno);
	}
	@Transactional
	@Override
	public void order(OrderVO vo) {
		
		prodDAO.order(vo);
		
		String prodName = vo.getOrderProdName();
		String prodSize = vo.getOrderProdSize();
		int prodQuantity = vo.getOrderProdQuantity();
		System.out.println(prodSize);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodName", prodName);
		map.put("prodSize", prodSize);
		map.put("prodQuantity", prodQuantity);
		prodStockDAO.quantity(map);
	}

}
