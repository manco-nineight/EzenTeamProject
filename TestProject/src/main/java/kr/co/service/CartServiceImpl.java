package kr.co.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.ProductVO;
import kr.co.persistence.CartDAO;
import kr.co.persistence.MemberDAO;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDAO cDao;
	
	@Override
	public int insert(CartDTO vo) {
		return cDao.insert(vo);
	}

	@Override
	public int delete(int cartNo) {
		return cDao.delete(cartNo);
	}

	@Override
	public List<CartDTO> cartlist(String userId) {
		return cDao.list(userId);
	}

	@Override
	public int update(CartDTO vo) {
		return cDao.update(vo);
	}

	
}
