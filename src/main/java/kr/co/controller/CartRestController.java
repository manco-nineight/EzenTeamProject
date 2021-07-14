package kr.co.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.CartDTO;
import kr.co.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartRestController {
	
	@Autowired
	private CartService cService;
	
	
	@RequestMapping(value = "/cartlist/{userId}", method = RequestMethod.GET)
	public List<CartDTO> cartlist(@PathVariable("userId") String userId) {
		return cService.cartlist(userId);
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.DELETE)
	public int delete(@RequestBody Map<String, Object> map) {
		int cartNo = Integer.parseInt(map.get("cartNo").toString());
		int result = cService.delete(cartNo);

		return result;
	}
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.PUT)
	public int update(@RequestBody Map<String, Object> map) {
		int cartNo = Integer.parseInt(map.get("cartNo").toString());
		String userId = map.get("userId").toString();
		String prodName = map.get("prodName").toString();
		String prodSize = map.get("prodSize").toString();
		int amount = Integer.parseInt(map.get("amount").toString());

		CartDTO vo = new CartDTO(cartNo, userId, prodName, prodSize, amount);
		
		int result = cService.update(vo);
		return result;
	}
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public int insert(@RequestBody Map<String, Object> map) {
		String userId = map.get("userId").toString();
		String prodName = map.get("prodName").toString();
		String prodSize = map.get("prodSize").toString();
		int amount = Integer.parseInt(map.get("amount").toString());
		
		CartDTO vo = new CartDTO(-1, userId, prodName, prodSize, amount);
		int result = cService.insert(vo);
		
		return result;
	}
}
