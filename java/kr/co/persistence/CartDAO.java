package kr.co.persistence;

import java.util.List;

import kr.co.domain.CartDTO;

public interface CartDAO {
	
	int insert(CartDTO vo);

	int delete(int cartNo);

	List<CartDTO> list(String userId);

	int update(CartDTO vo);
	
}
