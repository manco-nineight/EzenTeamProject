package kr.co.service;

import java.util.List;
import java.util.Map;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.ProductStockVO;
import kr.co.domain.ProductVO;

public interface MemberService {

	void insert(MemberDTO dto);
	
	int idcheck(String userId);

	int getAmount();

	List<MemberDTO> list(int startNum);

	int getSearchAmount(Map<String, Object> map);

	List<MemberDTO> search(Map<String, Object> map, int startNum);

	MemberDTO read(String userId);

	MemberDTO update(String userId);

	void update(MemberDTO vo);
	
	void delete(String userId);

	MemberDTO login(LoginDTO login);

	MemberDTO findId(String userEmail);

	MemberDTO findPw(Map<String, Object> map);

	void updateFindPw(Map<String, Object> map);

	List<ProductVO> test();

	List<CartDTO> cartlist(String userId);

	ProductVO testread(String prodName);

	ProductStockVO stock(String prodName);

	
}
