package kr.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
			System.out.println("諛곗넚�떆�옉 硫붿꽭吏� �쟾�넚 �꽦怨�");
			System.out.println("�씠�슜�옄 �븘�씠�뵒 : " + orderTrackingNum);
			
			String setFrom = "msn6903@naver.com";
			String toMail = userEmail;
			String title = "Ezen TestSHOP�뿉�꽌 二쇰Ц�븳 �긽�뭹�쓽 諛곗넚�씠 �떆�옉�릺�뿀�뒿�땲�떎..";
			String content = "怨좉컼�떂猿섏꽌 二쇰Ц�븯�떊 �긽�뭹�쓽 �슫�넚�옣踰덊샇�뒗" + orderTrackingNum + "�엯�땲�떎.";
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

	/* 占쎌뵠筌롫뗄�뵬 占쎌뵥筌앾옙 */
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		/* �뀎占�(View)嚥≪뮆占쏙옙苑� 占쎄퐜占쎈선占쎌궔 占쎈쑓占쎌뵠占쎄숲 占쎌넇占쎌뵥 */
		System.out.println("�씠硫붿씪 �쟾�넚 �꽦怨�");
		System.out.println("�씠�슜�옄 �븘�씠�뵒 : " + email);
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;

		System.out.println("�씤利앸쾲�샇" + checkNum);

		/* 占쎌뵠筌롫뗄�뵬 癰귣�沅→묾占� */
		String setFrom = "msn6903@naver.com";
		String toMail = email;
		String title = "Ezen TestSHOP �쉶�썝媛��엯 �씤利앸찓�씪�엯�땲�떎..";
		String content = "���씗 �궗�씠�듃瑜� 諛⑸Ц�빐 二쇱뀛�꽌 媛먯궗�빀�땲�떎." + "<br><br>" + "�씤利앸쾲�샇�뒗 " + checkNum + "�엯�땲�떎." + "<br>"
				+ "�쉶�썝媛��엯 李쎌뿉�꽌 �씤利앸쾲�샇瑜� �엯�젰�빐 �씤利앹쓣 �셿猷뚰빐二쇱꽭�슂.";
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

		String subject = "test 筌롫뗄�뵬";
		String content = "筌롫뗄�뵬 占쎈�믭옙�뮞占쎈뱜 占쎄땀占쎌뒠";
		String from = "msn6903@naver.com";
		String to = "msn6903@gmail.com";

		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			// true占쎈뮉 筌롳옙占쎈뼒占쎈솁占쎈뱜 筌롫뗄苑�筌욑옙�몴占� 占쎄텢占쎌뒠占쎈릭野껋쥓�뼄占쎈뮉 占쎌벥沃섓옙

			/*
			 * 占쎈뼊占쎈떄占쎈립 占쎈�볩옙�뮞占쎈뱜 筌롫뗄苑�筌욑옙筌랃옙 占쎄텢占쎌뒠占쎈뻻占쎈퓦 占쎈툡占쎌삋占쎌벥 �굜遺얜굡占쎈즲 占쎄텢占쎌뒠 揶쏉옙占쎈뮟 MimeMessageHelper mailHelper = new
			 * MimeMessageHelper(mail,"UTF-8");
			 */

			mailHelper.setFrom(from);
			// �뜮�뜆肉� 占쎈툡占쎌뵠占쎈탵 占쎄퐬占쎌젟占쎈립 野껉퍔占� 占쎈뼊占쎈떄占쎌뿳 smtp 占쎌뵥筌앹빘�뱽 獄쏆룄由� 占쎌맄占쎈퉸 占쎄텢占쎌뒠 占쎈뎡占쎌뵬占쎄퐣 癰귣�沅∽옙�뮉占쎌뵠(setFrom())獄쏆꼶諭띰옙�뻻 占쎈툡占쎌뒄
			// 癰귣�沅∽옙�뮉占쎌뵠占쏙옙 筌롫뗄�뵬雅뚯눘�꺖�몴占� 占쎈땾占쎈뻿占쎈릭占쎈뮉占쎌뵠揶쏉옙 癰귥눖釉� 筌뤴뫀紐� 占쎈ご疫뀐옙 占쎈┷野껓옙 占쎌뜚占쎈릭占쎈뻿占쎈뼄筌롳옙 占쎈툡占쎌삋占쎌벥 �굜遺얜굡�몴占� 占쎄텢占쎌뒠占쎈릭占쎈뻻筌롳옙 占쎈쭢占쎈빍占쎈뼄.
			// mailHelper.setFrom("癰귣�沅∽옙�뮉占쎌뵠 占쎌뵠�뵳占� <癰귣�沅∽옙�뮉占쎌뵠 占쎈툡占쎌뵠占쎈탵@占쎈즲筌롫뗄�뵥雅뚯눘�꺖>");
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content, true);
			// true占쎈뮉 html占쎌뱽 占쎄텢占쎌뒠占쎈릭野껋쥓�뼄占쎈뮉 占쎌벥沃섎챷�뿯占쎈빍占쎈뼄.

			/*
			 * 占쎈뼊占쎈떄占쎈립 占쎈�볩옙�뮞占쎈뱜筌랃옙 占쎄텢占쎌뒠占쎈릭占쎈뻿占쎈뼄筌롳옙 占쎈뼄占쎌벉占쎌벥 �굜遺얜굡�몴占� 占쎄텢占쎌뒠占쎈릭占쎈�쏉옙猷� 占쎈쭢占쎈빍占쎈뼄. mailHelper.setText(content);
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
