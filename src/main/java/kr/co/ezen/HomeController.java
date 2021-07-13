package kr.co.ezen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.LoginDTO;
import kr.co.domain.ProductVO;
import kr.co.service.QNABoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private QNABoardService qService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		//LoginDTO login = qService.getNowLogin("admin");
		//LoginDTO login = qService.getNowLogin("redic123");
		LoginDTO login = qService.getNowLogin("member001");
		model.addAttribute("login", login);
		
		//ProductVO vo = qService.getBno(prodName);
		ProductVO vo = new ProductVO();
		vo.setProdBno(1);
		model.addAttribute("vo", vo);
		
		return "home";
	}
}