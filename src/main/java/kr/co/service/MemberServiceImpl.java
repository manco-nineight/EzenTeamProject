package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;
import kr.co.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mDao;

	@Override
	public void updateOrderProdStatus(OrderVO orderVO) {
		mDao.updateOrderProdStatus(orderVO);

	}

	@Override
	public String selectEmail(String orderUserId) {
		// TODO Auto-generated method stub
		return mDao.selectEmail(orderUserId);
	}

	@Override
	public List<OrderVO> managementList(int startNum) {
		// TODO Auto-generated method stub
		return mDao.managementList(startNum);
	}

	@Override
	public int managementlistAmount() {
		// TODO Auto-generated method stub
		return mDao.managementlistAmount();
	}

	@Override
	public void deleteOrder(int orderNum) {
		// TODO Auto-generated method stub
		mDao.deleteOrder(orderNum);
	}

	@Override
	public void updateOrderAddress(OrderVO orderVO) {
		mDao.updateOrderAddress(orderVO);

	}

	@Override
	public OrderVO readOrder(int orderNum) {
		// TODO Auto-generated method stub
		return mDao.readOrder(orderNum);
	}

	@Override
	public int orderAmount(String userId) {
		// TODO Auto-generated method stub
		return mDao.orderAmount(userId);
	}

	@Override
	public List<OrderVO> orderList(String userId, int startnum) {
		// TODO Auto-generated method stub
		return mDao.orderList(userId, startnum);
	}

	@Override
	public void updateGrade(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		mDao.updateGrade(memberDTO);
	}

	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return mDao.list();
	}

	@Override
	public void updateMyself(MemberDTO memberDTO) {
		mDao.updateMyself(memberDTO);

	}

	@Override
	public void deleteMyself(String userId) {
		// TODO Auto-generated method stub
		mDao.deleteMyself(userId);
	}

	@Override
	public MemberDTO readInfo(String userId) {
		// TODO Auto-generated method stub
		return mDao.readInfo(userId);
	}

	@Override
	public String userIdCheck(String userId) {
		// TODO Auto-generated method stub
		return mDao.userIdCheck(userId);
	}

	@Override
	public void sign(MemberDTO memberDTO) {
		mDao.sign(memberDTO);

	}

	@Override
	public MemberDTO login(LoginDTO login) {
		// TODO Auto-generated method stub
		return mDao.login(login);
	}

}
