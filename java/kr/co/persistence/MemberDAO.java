package kr.co.persistence;

import java.util.List;
import java.util.Map;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;
import kr.co.domain.ProdStockDTO;
import kr.co.domain.ProductVO;

public interface MemberDAO {
	
	public MemberDTO login(LoginDTO login);

	public void sign(MemberDTO memberDTO);

	public String userIdCheck(String userId);

	public MemberDTO readInfo(String userId);

	public void deleteMyself(String userId);

	public void updateMyself(MemberDTO memberDTO);

	public List<MemberDTO> list();

	public void updateGrade(MemberDTO memberDTO);

	public List<OrderVO> orderList(String userId, int startnum);

	public int orderAmount(String userId);

	public OrderVO readOrder(int orderNum);

	public void updateOrderAddress(OrderVO orderVO);

	public void deleteOrder(int orderNum);

	public int managementlistAmount();

	public List<OrderVO> managementList(int startNum);

	public void updateOrderProdStatus(OrderVO orderVO);

	public String selectEmail(String orderUserId);
	
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


	MemberDTO findId(String userEmail);

	MemberDTO findPw(Map<String, Object> map);

	void updateFindPw(Map<String, Object> map);

	List<ProductVO> test();

	List<CartDTO> cartlist(String userId);

	ProductVO testread(String prodName);

	ProdStockDTO stock(String prodName);

	


}
