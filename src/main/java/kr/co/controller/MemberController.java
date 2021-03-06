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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService mService;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder userPwEncoder;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/emailcheck", method = RequestMethod.POST)
	public int emailCheck(String userEmail) {
		int checkResult = mService.emailcheck(userEmail);
		return checkResult;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = RequestMethod.POST)
	public int idCheck(String userId) {
		int checkResult = mService.idcheck(userId);
		return checkResult;
	}
	
	
	@RequestMapping(value = "/cartlist/{userId}", method = RequestMethod.GET)
	public String cartlist(Model model, @PathVariable("userId") String userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		if (loginSession ==  null) {
			System.out.println("???????????? ????????? ???????????????.");

			return "/member/wrong_login_info";
			
		} else if (!loginSession.getUserId().equals(userId)) {
			System.out.println("????????? ???????????????.");

			return "/member/warning";
			
		} else {
			List<CartDTO> list = mService.cartlist(userId);
			
			model.addAttribute("list", list);
			
			return "/member/cartlist";
		}
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
			System.out.println("????????? ?????? ??????");
			System.out.println("????????? ?????? : " + userEmail);
			System.out.println("?????????????????? : " + sb.toString()); 
			//---------
			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP ??????????????????.";
			String content = userId+"?????? ?????? ??????????????????." + "<br><br>"  + sb + "?????????." + "<br><br>" + "?????? ??? ??????????????? ??????????????????";

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
			
	        String userPw = sb.toString();
	        
	        String secureUserPw = userPwEncoder.encode(userPw);

	        map.put("userPw", secureUserPw);
	        
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
		
		if (vo != null) {
			String userId = vo.getUserId();
			String findUserId = userId.substring(0, userId.length()-2) + "**";
			return findUserId;
			
		} else {
			return "X";
		}	
			
	}
	
	
	
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() {
		
	}
	
	
	// ?????? ?????? ??????
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
			
			
	// ?????? ?????? ?????????
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
			
			
	// ?????? ??????
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

			mService.updateOrderProdStatus(
					new OrderVO(orderNum, null, 0, 0, null, null, null, orderProdStatus, orderTrackingNum));

			String userEmail = mService.selectEmail(orderUserId);

			System.out.println(userEmail);
			System.out.println("?????? ?????? ??????");
			System.out.println("??????????????? : " + orderTrackingNum);

			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP?????? ???????????? ???????????? ????????? ????????? ?????????????????????.";
			String content = "??????????????? ???????????? ????????? ?????????????????? " + orderTrackingNum + "?????????.";
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

		} else if (orderProdStatus == 3) {
			System.out.println(orderTrackingNum);

			mService.updateOrderProdStatus(
					new OrderVO(orderNum, null, 0, 0, null, null, null, orderProdStatus, orderTrackingNum));

			String userEmail = mService.selectEmail(orderUserId);

			System.out.println(userEmail);
			System.out.println("?????? ?????? ??????");
			System.out.println("??????????????? : " + orderTrackingNum);

			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP ?????? ?????? ??????.";
			String content = "??????????????? ???????????? ????????? ????????? ?????????????????????. ??????????????? " + orderTrackingNum + "????????? ?????? ?????? ????????? ??????????????? ?????? ???????????????.";
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
		}

		else {
			System.out.println(orderTrackingNum);

			mService.updateOrderProdStatus(new OrderVO(orderNum, null, 0, 0, null, null, null, orderProdStatus, 0));

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
		String orderUserAddress  = "(" + sample4_postcode + ") " + sample4_roadAddress + " " + sample4_detailAddress;

		mService.updateOrderAddress(new OrderVO(orderNum, null, 0, 0, null, null, orderUserAddress, 0,0));
		
		return "1";
		
	}
	
	
	@RequestMapping(value = "/readOrder/{orderNum}", method = RequestMethod.GET)
	public String readOrder(@PathVariable("orderNum") int orderNum, Model model) {
		OrderVO vo = mService.readOrder(orderNum);
		
		model.addAttribute("vo", vo);
		return "/member/readOrder";
	}

	
	@RequestMapping(value = "/orderTracking/{curPage}/{userId}", method = RequestMethod.GET)
	public String orderTracking(@PathVariable("curPage") int curPage, @PathVariable("userId") String userId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("????????? ???????????????.");

			return "/member/warning";
		} 
		
		int orderAmount = mService.orderAmount(userId);
		PageTO<OrderVO> to = new PageTO<OrderVO>(curPage);
		to.setAmount(orderAmount);

		List<OrderVO> list = mService.orderList(userId, to.getStartNum());
		
		to.setList(list);
		model.addAttribute("list", list);
		model.addAttribute("to", to);

		return "/member/orderTracking";
	}
	
	
	
	
	@RequestMapping(value = "/orderTracking/{orderUserId}", method = RequestMethod.GET)
	public String orderTracking(@PathVariable("orderUserId") String orderUserId, Model model, HttpServletRequest request) {
		
		String userId = orderUserId;
		
		HttpSession session = request.getSession();
		MemberDTO loginSession = (MemberDTO) session.getAttribute("login");
		
		if (!loginSession.getUserId().equals(userId)) {
			System.out.println("????????? ???????????????.");

			return "/member/warning";
		} 
		
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
	public String updateGrade(MemberDTO dto, String userId, int userGrade) {
		dto.setUserId(userId);
		dto.setUserGrade(userGrade);
		
		mService.updateGrade(dto);

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

	
	@RequestMapping(value = "/updateMyself", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String updateMyself(String userId, String userPw, String userName, String userEmail, String userBirthday,
			String userAddress, String sample4_postcode,String sample4_roadAddress, String sample4_detailAddress) {
		
		String secureUserPw = userPwEncoder.encode(userPw);
		
		if (sample4_postcode == "") {
			String userAddressTrue = userAddress;
			
			mService.updateMyself(
					new MemberDTO(userId, secureUserPw, userName, userEmail, userBirthday, userAddressTrue, null, null, 0));

			return userAddressTrue;
			
		}else {
			String userAddressTrue = "(" + sample4_postcode + ") " + sample4_roadAddress + " " + sample4_detailAddress;
			mService.updateMyself(
					new MemberDTO(userId, secureUserPw, userName, userEmail, userBirthday, userAddressTrue, null, null, 0));

			return userAddressTrue;
		}

	}

	
	// ???????????? ?????? ??????
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String userId) {
		
		mService.deleteMember(userId);
		
		// userGrade??? ???????????? ??? ??????, ?????? ???????????? loginGet.jsp??? ??????
		return "/member/list";	
	}
	

	@RequestMapping(value = "/readSelf", method = RequestMethod.POST)
	public void read(String readuserId, Model model) {
		String userId = readuserId;

		MemberDTO dto = mService.readInfo(userId);

		model.addAttribute("dto", dto);

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/readInfoCheck", method = RequestMethod.GET)
	public int readInfoCheck(String userId, String userPw) {

		MemberDTO dto = mService.readInfo(userId);
		boolean isUserPw = userPwEncoder.matches(userPw, dto.getUserPw());

		if (isUserPw) {
			return 1;
			
		} else {
			return 0;
		}
	}



	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String sign(MemberDTO dto, int userGrade, String sample4_postcode, String sample4_roadAddress, String sample4_detailAddress) {
		
		String secureUserPw = userPwEncoder.encode(dto.getUserPw());
		String userAddress = "(" + sample4_postcode + ") " + sample4_roadAddress + " " + sample4_detailAddress;
		
		dto.setUserPw(secureUserPw);
		dto.setUserAddress(userAddress);
		dto.setUserGrade(userGrade);

		mService.insert(dto);
		
		return "redirect:/member/loginGet";
	}

	@ResponseBody
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	public String mailCheckGET(String userEmail){

		System.out.println("????????? ????????? ?????? ??????");
		System.out.println("????????? ?????? : " + userEmail);
		
		Random random = new Random();
		random.setSeed(new Date().getTime()); // ?????? ????????? ???????????? ?????? ???????????? ???????????? ??????
		
		int checkNum = random.nextInt(888888) + 111111;
		System.out.println("???????????? : " + checkNum);

	
		String setFrom = "msn6903@naver.com";
		String toMail = userEmail;
		String title = "Ezen TestSHOP ???????????? ?????????????????????..";
		String content = "??????????????? ?????? ????????????." + "<br><br>" + "6?????? ?????? " + checkNum + "?????????." + "<br>"
				+ "??????????????? ????????? ??????????????? ??????????????????.";
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


	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void login() {
	
 
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);
		
		if (dto != null) {
			boolean isUserPw = userPwEncoder.matches(login.getUserPw(), dto.getUserPw());

			if (isUserPw) {
				model.addAttribute("login", dto);
			}
			
		} else {
			System.out.println("????????? ?????? ??????????????? ?????? ?????????????????? ???????????? ?????? ??????????????????.");
		}
	}

}
