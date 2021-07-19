package kr.co.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder userPwEncoder;
	
	// 장바구니 목록
	@RequestMapping(value = "/cartlist/{userId}", method = RequestMethod.GET)
	public String cartlist(Model model, @PathVariable("userId") String userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		// 세션이 없거나 다른 상태에서 주소창에 입력하여 비정상 접근 시 접근 제한
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("잘못된 접근입니다.");

			return "/member/errMsg";
			
		} else {
			List<CartDTO> list = mService.cartlist(userId);
			
			model.addAttribute("list", list);
			
			return "/member/cartlist";
		}
	}
	
	
	// 장바구니 insert를 위한 상품 자세히보기 테스트 추후 게시판과 합쳐지면 삭제
	@RequestMapping(value = "/testread/{prodName}", method = RequestMethod.GET)
	public String testread(@PathVariable("prodName") String prodName, Model model) {
		
		ProductVO vo = mService.testread(prodName);
		
		ProductStockVO stock = mService.stock(prodName);
		
		model.addAttribute("vo", vo);
		model.addAttribute("stock", stock);
		
		return "/member/testread";
	}
	
	
	// 장바구니 insert를 위한 상품 목록 테스트 추후 게시판과 합쳐지면 삭제
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(Model model) {
		
		List<ProductVO> test = mService.test();
		
		model.addAttribute("list", test);
	}
	
	
	// 비밀번호 찾기 시 임시 비밀번호를 이메일로 전송
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

	        System.out.println("인증번호 : " + sb.toString()); // 테스트 용

			String setFrom = "korean3359@gmail.com";
	        String toEmail = userEmail;
	        String title = "회원 임시 비밀번호 이메일";
	        String content = "임시 비밀번호는 " + sb.toString() + " 입니다.";
	        
	        // 원할한 테스트를 위한 메일 전송 부분 임시 주석처리 (google에 임시 아이디에서 입력된 메일주소로 전송)
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
	        
	        // 비밀번호 암호화
	        String secureUserPw = userPwEncoder.encode(userPw);

	        map.put("userPw", secureUserPw);
	        
	        mService.updateFindPw(map);
	        
	        return userPw;
	        
		} else {
			return "X";
		}
	}

	// 비밀번호 찾기 UI
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public void findPw() {
		
	}
	
	
	// 아이디 찾기
	@ResponseBody
	@RequestMapping(value = "/findIdResult", method = RequestMethod.POST)
	public String findId(String userEmail, Model model) {
		MemberDTO vo = mService.findId(userEmail);

		model.addAttribute("vo", vo);
		
		String userId = vo.getUserId();
		
		// 보안을 위해 아이디 뒤에서 2글자 삭제 후 **(별표)처리
		String findUserId = userId.substring(0, userId.length()-2) + "**";
		
		System.out.println(findUserId);
		if (vo != null) {
			return findUserId;

		} else {
			return "X";
		}
	}
	
	
	// 아이디 찾기 UI
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() {
		
	}
	
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/member/list";
	}
	
	
	// 로그인
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);
		
		// 비밀번호 입력값과 DB에 암호화된 비밀번호 비교
		boolean isUserPw = userPwEncoder.matches(login.getUserPw(), dto.getUserPw());

		if (isUserPw) {
			model.addAttribute("login", dto);
		}
	}
	
	
	// 로그인 UI
	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void login() {
		
	}
	
	
	// 회원 정보 삭제
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
	public String delete(@PathVariable("userId") String userId, int curPage, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		// 세션이 없거나 다른 상태에서 주소창에 입력하여 비정상 접근 시 접근 제한
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("잘못된 접근입니다.");

			return "/member/errMsg";
			
		} else {
			mService.delete(userId);
			
			return "redirect:/member/list/" + curPage;
		}
	}
	
	
	// 회원 정보 수정
	@RequestMapping(value = "/update/{userId}", method = RequestMethod.POST)
	public String update(@PathVariable("userId") String userId, MemberDTO vo, int curPage) {
		vo.setUserId(userId);
		
		// 비밀번호 암호화
		String secureUserPw = userPwEncoder.encode(vo.getUserPw());
		vo.setUserPw(secureUserPw);
		
		mService.update(vo);
		
		return "redirect:/member/read/" + userId + "?curPage=" + curPage; 
	}
	
	
	// 회원 정보 수정 UI
	@RequestMapping(value = "/update/{userId}", method = RequestMethod.GET)
	public String update(@PathVariable("userId") String userId, @ModelAttribute("curPage") int curPage, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		// 세션이 없거나 세션이 다른 상태에서 주소창에 입력하여 비정상 접근 시 접근 제한
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("잘못된 접근입니다.");

			return "/member/errMsg";
			
		} else {
			MemberDTO vo = mService.update(userId);
			
			model.addAttribute("vo", vo);
			
			return "/member/update";
		}
	}
	
	
	// 회원 정보 자세히 보기
	@RequestMapping(value = "/read/{userId}", method = RequestMethod.GET)
	public String read(@PathVariable("userId") String userId, @ModelAttribute("curPage") int curPage, Model model) {
		MemberDTO vo = mService.read(userId);
		
		model.addAttribute("vo", vo);
		
		return "/member/read";
	}
	
	
	// 회원 목록 검색
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
	
	
	// 회원 목록 페이징
	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(@PathVariable("curPage") int curPage, Model model) {
		int getAmount = mService.getAmount();

		PageTO<MemberDTO> to = new PageTO<MemberDTO>(curPage);
		to.setAmount(getAmount);
		
		List<MemberDTO> list = mService.list(to.getStartNum());
		to.setList(list);
		
		model.addAttribute("to", to);
		
		return "/member/list";
	}
	
	
	// 회원 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		int curPage = 1;
		int getAmount = mService.getAmount();

		PageTO<MemberDTO> to = new PageTO<MemberDTO>(curPage);
		to.setAmount(getAmount);
		
		List<MemberDTO> list = mService.list(to.getStartNum());
		to.setList(list);
		
		model.addAttribute("to", to);
	}
	
	
	// 회원 가입 시 이메일 인증
	@ResponseBody
	@RequestMapping(value = "/emailcheck", method = RequestMethod.GET)
	public String emailCheck(String userEmail) {
		System.out.println("이메일 데이터 전송 확인");
        System.out.println("이메일 : " + userEmail);
        
        // 인증번호(난수) 생성
        Random random = new Random();
        random.setSeed(new Date().getTime()); // 현재 시간을 기반으로 매번 변경되는 시드값을 사용

        int checkNum = random.nextInt(888888) + 111111;
        System.out.println("인증번호 " + checkNum);	// 테스트용
        
        String setFrom = "korean3359@gmail.com";
        String toEmail = userEmail;
        String title = "회원가입 인증 이메일입니다.";
        String content = "인증 번호는 " + checkNum + " 입니다.";
        
        // 원할한 테스트를 위한 메일 전송 임시 주석
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
	
	
	// 회원 userId 중복 체크
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public int idCheck(String userId) {
		int checkResult = mService.idcheck(userId);
		return checkResult;
	}
	
	
	// 회원 가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO vo) {
		// 비밀번호 암호화
		String secureUserPw = userPwEncoder.encode(vo.getUserPw());
		
		vo.setUserPw(secureUserPw);
		
		mService.insert(vo);
		
		return "redirect:/member/list";
	}
	
	// 회원 가입 UI
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
}
