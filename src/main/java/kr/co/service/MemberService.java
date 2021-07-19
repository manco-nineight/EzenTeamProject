package kr.co.service;

import java.util.List;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;

public interface MemberService {
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