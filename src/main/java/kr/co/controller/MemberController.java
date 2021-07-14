package kr.co.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.PageTO;
import kr.co.domain.ProductStockVO;
import kr.co.domain.ProductVO;
import kr.co.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private JavaMailSender mailSender;	
	
	@RequestMapping(value = "/cartlist/{userId}", method = RequestMethod.GET)
	public String cartlist(Model model, @PathVariable("userId") String userId) {
		
		List<CartDTO> list = mService.cartlist(userId);
		
		model.addAttribute("list", list);
		
		return "/member/cartlist";

	}
	
	
	@RequestMapping(value = "/testread/{prodName}", method = RequestMethod.GET)
	public String testread(@PathVariable("prodName") String prodName, Model model) {
		
		ProductVO vo = mService.testread(prodName);
		
		ProductStockVO stock = mService.stock(prodName);
		
		model.addAttribute("vo", vo);
		model.addAttribute("stock", stock);
		
		return "/member/testread";
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(Model model) {
		
		List<ProductVO> test = mService.test();
		
		model.addAttribute("list", test);

	}

	@ResponseBody
	@RequestMapping(value = "/findPwResult", method = RequestMethod.POST)
	public String findPw(String userId, String userEmail, Model model) {
		char[] charSet = new char[] { 
        		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
        		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
        		'!', '@', '#', '$', '%', '^', '&' };
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userEmail", userEmail);
		
		MemberDTO vo = mService.findPw(map);
		
		System.out.println(vo);

		model.addAttribute("vo", vo);

        StringBuffer sb = new StringBuffer(); 
        SecureRandom sRandom = new SecureRandom();	// 보안 결정을 위한 SecureRandom 생성
        
        sRandom.setSeed(new Date().getTime()); // 현재 시간을 기반으로 매번 변경되는 시드값을 사용
        
        int idx = 0;
		
		if (vo != null) {
			System.out.println("이메일 데이터 전송 확인");
	        System.out.println("이메일 : " + userEmail);
	        
	        // 인증번호(난수) 생성
	        for (int i=0; i<8; i++) { 
	        	// idx = (int) (charSet.length * Math.random()); 	// 보안적으로 예측 가능한 난수를 발생시켜서 취약
	        	idx = sRandom.nextInt(charSet.length); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
	        	sb.append(charSet[idx]); 
	        }

	        System.out.println("인증번호 : " + sb.toString());

			String setFrom = "korean3359@gmail.com";
	        String toEmail = userEmail;
	        String title = "회원 임시 비밀번호 이메일";
	        String content = "임시 비밀번호는 " + sb.toString() + " 입니다.";
	        
//			try {
//				MimeMessage msg = mailSender.createMimeMessage();
//				MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
//				helper.setFrom(setFrom);
//				helper.setTo(toEmail);
//				helper.setSubject(title);
//				helper.setText(content);
//				mailSender.send(msg);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	        
	        String userPw = sb.toString();
	        
	        map.put("userPw", userPw);
	        
	        mService.updateFindPw(map);
	        
	        return userPw;
	        
		} else {
			return "X";
		}
	}

	
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public void findPw() {
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findIdResult", method = RequestMethod.POST)
	public String findId(String userEmail, Model model) {
		MemberDTO vo = mService.findId(userEmail);
		
		System.out.println(vo);

		model.addAttribute("vo", vo);
		
		String userId = vo.getUserId();
		
		// 보안을 위해 아이디 뒤에서 2자 삭제 후 **처리
		String findUserId = userId.substring(0, userId.length()-2) + "**";
		
		System.out.println(findUserId);
		if (vo != null) {
			return findUserId;

		} else {
			return "X";
		}
	}
	
	
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() {
		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/member/list";
	}
	
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);
		model.addAttribute("login", dto);
	}
	
	
	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void login() {
		
	}
	
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
	public String delete(@PathVariable("userId") String userId, int curPage) {
		
		mService.delete(userId);
		
		return "redirect:/member/list/" + curPage;
	}
	
	
	@RequestMapping(value = "/update/{userId}", method = RequestMethod.POST)
	public String update(@PathVariable("userId") String userId, MemberDTO vo, int curPage) {
		
		vo.setUserId(userId);
		
		mService.update(vo);
		
		return "redirect:/member/read/" + userId + "?curPage=" + curPage; 
	}
	
	
	@RequestMapping(value = "/update/{userId}", method = RequestMethod.GET)
	public String update(@PathVariable("userId") String userId, @ModelAttribute("curPage") int curPage, Model model) {
		MemberDTO vo = mService.update(userId);
		
		model.addAttribute("vo", vo);
		
		return "/member/update";
	}
	
	
	@RequestMapping(value = "/read/{userId}", method = RequestMethod.GET)
	public String read(@PathVariable("userId") String userId, @ModelAttribute("curPage") int curPage, Model model) {
		MemberDTO vo = mService.read(userId);
		
		model.addAttribute("vo", vo);
		
		return "/member/read";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void search(String selector, String keyword, int curPage, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selector", selector);
		map.put("keyword", keyword);
		
		int getSearchAmount = mService.getSearchAmount(map);
		
		PageTO<MemberDTO> to = new PageTO<MemberDTO>(curPage);
		to.setAmount(getSearchAmount);

		List<MemberDTO> list = mService.search(map, to.getStartNum());
		to.setList(list);
		
		model.addAttribute("to", to);
	}
	
	
	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(@PathVariable("curPage") int curPage, Model model) {
		// 회원 목록 페이징
		int getAmount = mService.getAmount();

		PageTO<MemberDTO> to = new PageTO<MemberDTO>(curPage);
		to.setAmount(getAmount);
		
		List<MemberDTO> list = mService.list(to.getStartNum());
		to.setList(list);
		
		model.addAttribute("to", to);
		
		return "/member/list";
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		// 회원 목록
		int curPage = 1;
		int getAmount = mService.getAmount();

		PageTO<MemberDTO> to = new PageTO<MemberDTO>(curPage);
		to.setAmount(getAmount);
		
		List<MemberDTO> list = mService.list(to.getStartNum());
		to.setList(list);
		
		model.addAttribute("to", to);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/emailcheck", method = RequestMethod.GET)
	public String emailCheck(String userEmail) {
		System.out.println("이메일 데이터 전송 확인");
        System.out.println("이메일 : " + userEmail);
        
        // 인증번호(난수) 생성
        Random random = new Random();
        random.setSeed(new Date().getTime()); // 현재 시간을 기반으로 매번 변경되는 시드값을 사용

        int checkNum = random.nextInt(888888) + 111111;
        System.out.println("인증번호 " + checkNum);
        
        String setFrom = "korean3359@gmail.com";
        String toEmail = userEmail;
        String title = "회원가입 인증 이메일입니다.";
        String content = "인증 번호는 " + checkNum + " 입니다.";
        

//		try {
//			MimeMessage msg = mailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
//			helper.setFrom(setFrom);
//			helper.setTo(toEmail);
//			helper.setSubject(title);
//			helper.setText(content);
//			mailSender.send(msg);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

        
        String num = Integer.toString(checkNum);
        
        return num;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public int idCheck(String userId) {
		// 회원 userId 중복 체크
		int checkResult = mService.idcheck(userId);
		return checkResult;
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO dto) {
		// 회원 가입
		mService.insert(dto);
		
		return "redirect:/member/list";
	}
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		// insert UI
	}
	
}
