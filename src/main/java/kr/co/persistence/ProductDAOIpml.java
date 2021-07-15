package kr.co.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.ProductVO;

@Repository
public class ProductDAOIpml implements ProductDAO{
	
	@Autowired
	private SqlSession session;
	
	private final String NAMESPACE ="kr.co.product";
	
	@Override
	public ProductVO review(Integer prodBno) {
		
		return session.selectOne(NAMESPACE+".review", prodBno);
	}

}
