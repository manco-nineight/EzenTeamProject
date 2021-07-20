package kr.co.service;

import java.util.List;
import java.util.Map;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;
import kr.co.domain.ProdStockDTO;
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



	MemberDTO findId(String userEmail);

	MemberDTO findPw(Map<String, Object> map);

	void updateFindPw(Map<String, Object> map);

	List<ProductVO> test();

	List<CartDTO> cartlist(String userId);

	ProductVO testread(String prodName);

	ProdStockDTO stock(String prodName);
	
	//-------------------------------
   MemberDTO login(LoginDTO login);

void sign(MemberDTO memberDTO);

String userIdCheck(String userId);

MemberDTO readInfo(String userId);

void deleteMyself(String userId);

void updateMyself(MemberDTO memberDTO);

List<MemberDTO> memberList();

void updateGrade(MemberDTO memberDTO);

List<OrderVO> orderList(String userId, int startNum);

int orderAmount(String userId);

OrderVO readOrder(int orderNum);

void updateOrderAddress(OrderVO orderVO);

void deleteOrder(int orderNum);

int managementlistAmount();

List<OrderVO> managementList(int startNum);

void updateOrderProdStatus(OrderVO orderVO);

String selectEmail(String orderUserId);



}