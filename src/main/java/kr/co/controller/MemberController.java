package kr.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.CartDTO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;
import kr.co.domain.PageTO;
import kr.co.domain.ProductStockVO;
import kr.co.domain.ProductVO;
import kr.co.service.MemberService;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.MimeMessageHelper;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService mService;

	@Autowired
	private JavaMailSender mailSender;
	
//	@Autowired
//	private BCryptPasswordEncoder userPwEncoder;
	
	
	
	
	@RequestMapping(value = "/cartlist/{userId}", method = RequestMethod.GET)
	public String cartlist(Model model, @PathVariable("userId") String userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("로그인이 필요한 작업입니다.");

			return "/member/errMsg";
			
		} else {
			List<CartDTO> list = mService.cartlist(userId);
			
			model.addAttribute("list", list);
			
			return "/member/cartlist";
		}
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

		model.addAttribute("vo", vo);

        StringBuffer sb = new StringBuffer(); 
        SecureRandom sRandom = new SecureRandom();
        
        sRandom.setSeed(new Date().getTime()); 
        int idx = 0;
		if (vo != null) {
			for (int i=0; i<8; i++) { 
				idx = sRandom.nextInt(charSet.length); 
				sb.append(charSet[idx]); 
			}
			System.out.println("이메일 전송 완료");
			System.out.println("이메일 주소 : " + userEmail);
			System.out.println("임시비밀번호 : " + sb.toString()); 
			//---------
			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP 임시비밀번호.";
			String content = userId+"님을 위한 임시비밀번호." + "<br><br>"  + sb + "입니다." + "<br>";
			try {
				
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content, true);
				mailSender.send(message);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//---------
	        



	        
	        String userPw = sb.toString();
	        
//	        String secureUserPw = userPwEncoder.encode(userPw);
	        //패스워드 빈 문제가 해결되면 수정 요망
//	        String secureUserPw = userPw.toString();

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

		model.addAttribute("vo", vo);
		
		String userId = vo.getUserId();
		
	
		
	
		if (vo != null) {
			String findUserId = userId.substring(0, userId.length()-2) + "**";
			return findUserId;

		}else {
			return "X";
		}
			
	}
	
	
	
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//---------------------------------------------------------------
	@RequestMapping(value = "/managementlist/{curPage}", method = RequestMethod.GET)
	public String managementlist(@PathVariable("curPage") int curPage,Model model) {
		
       
		
		
		int managementlistAmount = mService.managementlistAmount();
		PageTO<OrderVO> to = new PageTO<OrderVO>(curPage);
		to.setAmount(managementlistAmount);
		
		
		List<OrderVO> list = mService.managementList(to.getStartNum());
		
		to.setList(list);
		model.addAttribute("list", list);
		model.addAttribute("to", to);
		
		
		return "/member/managementlist";

		
	}
	
	@RequestMapping(value = "/managementlist", method = RequestMethod.GET)
	public void managementlist(Model model) {
		
       
		
		
		int curPage = 1;
		int managementlistAmount = mService.managementlistAmount();
		PageTO<OrderVO> to = new PageTO<OrderVO>(curPage);
		to.setAmount(managementlistAmount);
		
		
		List<OrderVO> list = mService.managementList(to.getStartNum());
		
		to.setList(list);
		model.addAttribute("list", list);
		model.addAttribute("to", to);
		
		
		
	}
	
	@RequestMapping(value = "/orderManagement/{orderNum}", method = RequestMethod.GET)
	public String orderManagement(@PathVariable ("orderNum") int orderNum, Model model) {
		
		OrderVO vo = mService.readOrder(orderNum);
		
		model.addAttribute("vo", vo);
		return "/member/orderManagement";
		
		
		
		
	}
	
	@RequestMapping(value = "/orderManagement", method = RequestMethod.POST)
	@ResponseBody
	public String orderManagement(int orderNum, int orderProdStatus, long orderTrackingNum, String orderUserId) {
		
	    
		
		
		if (orderProdStatus == 2) {
			System.out.println(orderTrackingNum);
			
			mService.updateOrderProdStatus(new OrderVO(orderNum, null, 0, 0, 0, null, null, orderProdStatus, orderTrackingNum));
			
			String userEmail = mService.selectEmail(orderUserId);
			
			System.out.println(userEmail);	
			System.out.println("이메일 전송 완료");
			System.out.println("운송장 번호 : " + orderTrackingNum);
			
			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP에서 주문하신 상품의 배송이 시작되었습니다.";
			String content = "주문하신 상품의 운송장 번호는 " + orderTrackingNum + "입니다.";
			try {

				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content, true);
				mailSender.send(message);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "1";
		}else {
			System.out.println(orderTrackingNum);
			
			mService.updateOrderProdStatus(new OrderVO(orderNum, null, 0, 0, 0, null, null, orderProdStatus, orderTrackingNum));
			
			String userEmail = mService.selectEmail(orderUserId);
			
			System.out.println(userEmail);
			
			return "1";
		}
		
		
		
		
	}
	
	
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOrder(int orderNum) {
		
		
		mService.deleteOrder(orderNum);
		
		
		return "/member/orderTracking";
		
	}
	
	
//	/member/updateOrderAddress
	@RequestMapping(value = "/updateOrderAddress", method = RequestMethod.POST)
	@ResponseBody
	public String updateOrderAddress(int orderNum,String sample4_postcode, String sample4_roadAddress, String sample4_detailAddress) {
		
		String orderUserAddress  = "(" + sample4_postcode + ")" + sample4_roadAddress + "/" + sample4_detailAddress;
		
	
		
		
		mService.updateOrderAddress(new OrderVO(orderNum, null, 0, 0, 0, null, orderUserAddress, 0,0));
		
		return "1";
		
	}
	
	
	@RequestMapping(value = "/readOrder/{orderNum}", method = RequestMethod.GET)
	public String readOrder(@PathVariable("orderNum") int orderNum, Model model) {
		
		OrderVO vo = mService.readOrder(orderNum);
		
		model.addAttribute("vo", vo);
		return "/member/readOrder";
	}

	@RequestMapping(value = "/orderTracking/{curPage}/{userId}", method = RequestMethod.GET)
	public String orderTracking(@PathVariable("curPage") int curPage,@PathVariable("userId") String userId, Model model) {
		
	 
		
		
		int orderAmount = mService.orderAmount(userId);
		PageTO<OrderVO> to = new PageTO<OrderVO>(curPage);
		to.setAmount(orderAmount);
		
		
		List<OrderVO> list = mService.orderList(userId,to.getStartNum());
		
		to.setList(list);
		model.addAttribute("list", list);
		model.addAttribute("to", to);
		
	
		return "/member/orderTracking";
	}
	
	
	
	
	@RequestMapping(value = "/orderTracking/{orderUserId}", method = RequestMethod.GET)
	public String orderTracking(@PathVariable("orderUserId") String orderUserId,Model model) {
		
		
		
		
		String userId =  orderUserId;
		
		int curPage = 1;
		int orderAmount = mService.orderAmount(userId);
		PageTO<OrderVO> to = new PageTO<OrderVO>(curPage);
		to.setAmount(orderAmount);
		
		
		List<OrderVO> list = mService.orderList(userId,to.getStartNum());
		
		to.setList(list);
		model.addAttribute("list", list);
		model.addAttribute("to", to);
		
		return "/member/orderTracking";
		
	

	}
	

	
	
	@RequestMapping(value = "/updateGrade", method = RequestMethod.POST)
	public String updateGrade(String userId, Integer userGrade) {

		mService.updateGrade(
				new MemberDTO(userId, null, null, null, null, null, null, null, userGrade));

		return "redirect:/member/list";

	}
	
	
	

	@RequestMapping(value = "/readByAdmin/{userId}", method = RequestMethod.GET)
	public String readByAdmin(@PathVariable("userId") String userId, Model model) {
		MemberDTO dto = mService.readInfo(userId);

		model.addAttribute("dto", dto);

		return "/member/readByAdmin";

	}

	@RequestMapping(value = "/noadmin", method = RequestMethod.GET)
	public void noadmin() {

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {

		List<MemberDTO> to = mService.memberList();

		model.addAttribute("to", to);

		return "/member/list";
	}
	
	@RequestMapping(value = "/updateMyself", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String updateMyself(String userId, String userPw, String userName, String userEmail, String userBirthday,
			String userAddress, String sample4_postcode,String sample4_roadAddress, String sample4_detailAddress) {
		
		if (sample4_postcode == "") {
			String userAddressTrue = userAddress;
			
			mService.updateMyself(
					new MemberDTO(userId, userPw, userName, userEmail, userBirthday, userAddressTrue, null, null, 0));

			return userAddressTrue;
		}else {
			String userAddressTrue = "(" + sample4_postcode + ")" + sample4_roadAddress + "/" + sample4_detailAddress;
			mService.updateMyself(
					new MemberDTO(userId, userPw, userName, userEmail, userBirthday, userAddressTrue, null, null, 0));

			return userAddressTrue;
		}
		
 
		

	}
	
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(String userId) {

		mService.deleteMyself(userId);

		return "/member/list";

	}

	
	@RequestMapping(value = "/deleteMyself", method = RequestMethod.POST)
	public String deleteMyself(String userId) {

		mService.deleteMyself(userId);

		return "redirect:/member/loginGet";

	}
	

	@RequestMapping(value = "/readSelf", method = RequestMethod.POST)
	public void read(String readuserId, Model model) {
		
		
		String userId =readuserId;

		MemberDTO dto = mService.readInfo(userId);

		model.addAttribute("dto", dto);
		
	

		
	}

	@RequestMapping(value = "/userIdCheck", method = RequestMethod.GET)
	@ResponseBody
	public String userIdCheck(String userId) {

		String ckrs = mService.userIdCheck(userId);

		return ckrs;

	}

	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String name(String userId, String userPw, String userName, String userEmail, String userBirthday,
			String sample4_postcode, String sample4_roadAddress, String sample4_detailAddress, int userGrade) {

		String userAddress = "(" + sample4_postcode + ")" + sample4_roadAddress + "/" + sample4_detailAddress;

		mService.sign(
				new MemberDTO(userId, userPw, userName, userEmail, userBirthday, userAddress, null, null, userGrade));

		return "redirect:/member/loginGet";
	}

	
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

	
		System.out.println("이메일 전송 완료");
		System.out.println("이메일 주소 : " + email);
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		System.out.println("인증번호" + checkNum);

	
		String setFrom = "msn6903@naver.com";
		String toMail = email;
		String title = "Ezen TestSHOP 회원가입 인증메일입니다..";
		String content = "회원가입을 위한 인증번호." + "<br><br>" + "6자리 숫자 " + checkNum + "입니다." + "<br>"
				+ "인증번호를 입력해 회원가입을 완료해주세요.";
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);

		return num;
	}
	

	@RequestMapping(value = "/adress", method = RequestMethod.GET)
	public void adress() {

	}

	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public void sign() {

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		return "redirect:/";

	}

	// 占쎌뵥占쎄숲占쎈�롳옙苑ｇ몴占� �뤃�뗭겱占쎈막 占쎈르 ui占쏙옙 疫꿸퀡�뮟占쎌벥 筌띾벏釉� 雅뚯눘�꺖�몴占� 占쎈뼄�몴�떯苡� 占쎄퐬占쎌젟占쎈퉸占쎈튊占쎈립占쎈뼄.
	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void login() {
	
 
	}
	


	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);

		model.addAttribute("login", dto);
	}

}
