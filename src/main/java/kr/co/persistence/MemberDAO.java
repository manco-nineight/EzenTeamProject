package kr.co.persistence;

import java.util.List;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;

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

	


}
