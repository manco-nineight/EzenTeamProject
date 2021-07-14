package kr.co.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.ProductStockVO;
import kr.co.domain.ProductVO;
import kr.co.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO mDao;

	@Override
	public void insert(MemberDTO dto) {
		mDao.insert(dto);
	}
	
	@Override
	public int idcheck(String userId) {
		return mDao.idcheck(userId);
	}

	@Override
	public int getAmount() {
		return mDao.getAmount();
	}

	@Override
	public List<MemberDTO> list(int startNum) {
		return mDao.list(startNum);
	}

	@Override
	public int getSearchAmount(Map<String, Object> map) {
		return mDao.getSearchAmount(map);
	}

	@Override
	public List<MemberDTO> search(Map<String, Object> map, int startNum) {
		return mDao.search(map, startNum);
	}

	@Override
	public MemberDTO read(String userId) {
		return mDao.read(userId);
	}

	@Override
	public MemberDTO update(String userId) {
		return mDao.update(userId);
	}

	@Override
	public void update(MemberDTO vo) {
		mDao.update(vo);
	}

	@Override
	public void delete(String userId) {
		mDao.delete(userId);
	}

	@Override
	public MemberDTO login(LoginDTO login) {
		return mDao.login(login);
	}

	@Override
	public MemberDTO findId(String userEmail) {
		return mDao.findId(userEmail);
	}

	@Override
	public MemberDTO findPw(Map<String, Object> map) {
		return mDao.findPw(map);
	}

	@Override
	public void updateFindPw(Map<String, Object> map) {
		mDao.updateFindPw(map);
	}

	@Override
	public List<ProductVO> test() {
		return mDao.test();
	}

	@Override
	public List<CartDTO> cartlist(String userId) {
		return mDao.cartlist(userId);
	}

	@Override
	public ProductVO testread(String prodName) {
		return mDao.testread(prodName);
	}

	@Override
	public ProductStockVO stock(String prodName) {
		return mDao.stock(prodName);
	}


}
