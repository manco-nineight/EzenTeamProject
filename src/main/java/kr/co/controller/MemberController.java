package kr.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.domain.BoardVO;
import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.domain.OrderVO;
import kr.co.domain.PageTO;
import kr.co.service.MemberService;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.springframework.mail.javamail.MimeMessageHelper;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService mService;

	@Autowired
	private JavaMailSender mailSender;
	
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
			System.out.println("배송시작 메세지 전송 성공");
			System.out.println("이용자 아이디 : " + orderTrackingNum);
			
			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP에서 주문한 상품의 배송이 시작되었습니다..";
			String content = "고객님께서 주문하신 상품의 운송장번호는" + orderTrackingNum + "입니다.";
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

	/* �씠硫붿씪 �씤利� */
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		/* 酉�(View)濡쒕��꽣 �꽆�뼱�삩 �뜲�씠�꽣 �솗�씤 */
		System.out.println("이메일 전송 성공");
		System.out.println("이용자 아이디 : " + email);
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		System.out.println("인증번호" + checkNum);

		/* �씠硫붿씪 蹂대궡湲� */
		String setFrom = "msn6903@naver.com";
		String toMail = email;
		String title = "Ezen TestSHOP 회원가입 인증메일입니다..";
		String content = "저희 사이트를 방문해 주셔서 감사합니다." + "<br><br>" + "인증번호는 " + checkNum + "입니다." + "<br>"
				+ "회원가입 창에서 인증번호를 입력해 인증을 완료해주세요.";
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
	
    //mail test
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public void sendMailTest() throws Exception {

		String subject = "test 硫붿씪";
		String content = "硫붿씪 �뀒�뒪�듃 �궡�슜";
		String from = "msn6903@naver.com";
		String to = "msn6903@gmail.com";

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			// true�뒗 硫��떚�뙆�듃 硫붿꽭吏�瑜� �궗�슜�븯寃좊떎�뒗 �쓽誘�

			/*
			 * �떒�닚�븳 �뀓�뒪�듃 硫붿꽭吏�留� �궗�슜�떆�뿏 �븘�옒�쓽 肄붾뱶�룄 �궗�슜 媛��뒫 MimeMessageHelper mailHelper = new
			 * MimeMessageHelper(mail,"UTF-8");
			 */

			mailHelper.setFrom(from);
			// 鍮덉뿉 �븘�씠�뵒 �꽕�젙�븳 寃껋� �떒�닚�엳 smtp �씤利앹쓣 諛쏄린 �쐞�빐 �궗�슜 �뵲�씪�꽌 蹂대궡�뒗�씠(setFrom())諛섎뱶�떆 �븘�슂
			// 蹂대궡�뒗�씠�� 硫붿씪二쇱냼瑜� �닔�떊�븯�뒗�씠媛� 蹂쇰븣 紐⑤몢 �몴湲� �릺寃� �썝�븯�떊�떎硫� �븘�옒�쓽 肄붾뱶瑜� �궗�슜�븯�떆硫� �맗�땲�떎.
			// mailHelper.setFrom("蹂대궡�뒗�씠 �씠由� <蹂대궡�뒗�씠 �븘�씠�뵒@�룄硫붿씤二쇱냼>");
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);
			// true�뒗 html�쓣 �궗�슜�븯寃좊떎�뒗 �쓽誘몄엯�땲�떎.

			/*
			 * �떒�닚�븳 �뀓�뒪�듃留� �궗�슜�븯�떊�떎硫� �떎�쓬�쓽 肄붾뱶瑜� �궗�슜�븯�뀛�룄 �맗�땲�떎. mailHelper.setText(content);
			 */

			mailSender.send(mail);

		} catch (Exception e) {
			e.printStackTrace();
		}

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

	// �씤�꽣�뀎�꽣瑜� 援ы쁽�븷 �븣 ui�� 湲곕뒫�쓽 留듯븨 二쇱냼瑜� �떎瑜닿쾶 �꽕�젙�빐�빞�븳�떎.
	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void login() {
	
 
	}
	


	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);

		model.addAttribute("login", dto);
	}

}
