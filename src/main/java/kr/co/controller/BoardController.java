package kr.co.controller;

import java.io.File;
//import java.net.Authenticator.RequestorType;
//import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;
import kr.co.utils.Utils;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@Autowired
	private ServletContext sc;
	
	private String uploadPath = "resources"+File.separator+"uploads";
	
	
	

	@RequestMapping(value = "/search/{curPage}", method = RequestMethod.GET)
	public String search(@PathVariable("curPage") int curPage,Model model){
		
		
		
		Integer searchVal = 1;
		String put = "a";
		
	
		
		
	
		if (searchVal.equals(1)) {
			
			
		
			int samount = bService.getSAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(samount);
			
			List<BoardVO> list = bService.writer(put,to.getStartNum());
			to.setList(list);
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
		}
		if (searchVal.equals(2)) {
		
			int tamount = bService.getTitleAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(tamount);
			
			List<BoardVO> list = bService.title(put,to.getStartNum());
			to.setList(list);
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
		}
		if (searchVal.equals(3)) {
		
			int tamount = bService.getContentAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(tamount);
			
			List<BoardVO> list = bService.content(put,to.getStartNum());
			to.setList(list);
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
		}
		
		
		return "/board/search";
		
	}
	
	
	
//	@RequestMapping(value = "/search", method = RequestMethod.POST)
//	public void search(String put, Integer searchVal,Model model,HttpServletRequest request, HttpServletResponse response) {
//		 
//	   
//		
//		if (searchVal.equals(1)) {
//			int curPage = 1;
//			int samount = bService.getSAmount(put);
//			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
//			to.setAmount(samount);
//			
//			List<BoardVO> list = bService.writer(put,to.getStartNum());
//			to.setList(list);
//			
//			
//			HttpSession session =  request.getSession();
//			session.setAttribute("put", put);
//			session.setAttribute("searchVal", searchVal);
//			model.addAttribute("list", list);
//			model.addAttribute("to", to);
//			
//		
//		}
//		if (searchVal.equals(2)) {
//			int curPage = 1;
//			int tamount = bService.getTitleAmount(put);
//			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
//			to.setAmount(tamount);
//			
//			List<BoardVO> list = bService.title(put,to.getStartNum());
//			to.setList(list);
//			HttpSession session =  request.getSession();
//			session.setAttribute("put", put);
//			session.setAttribute("searchVal", searchVal);
//			model.addAttribute("list", list);
//			model.addAttribute("to", to);
//			
//		
//		}
//		if (searchVal.equals(3)) {
//			int curPage = 1;
//			int tamount = bService.getContentAmount(put);
//			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
//			to.setAmount(tamount);
//			
//			List<BoardVO> list = bService.content(put,to.getStartNum());
//			to.setList(list);
//			HttpSession session =  request.getSession();
//			session.setAttribute("put", put);
//			session.setAttribute("searchVal", searchVal);
//			model.addAttribute("list", list);
//			model.addAttribute("to", to);
//			
//	
//		}
//		
//		
//		
//	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void search(String put, Integer searchVal,Model model) {
		 

		model.addAttribute("put",put);
		model.addAttribute("searchVal",searchVal);
		 
		if (searchVal.equals(1)) {
			int curPage = 1;
			int samount = bService.getSAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(samount);
			
			List<BoardVO> list = bService.writer(put,to.getStartNum());
			to.setList(list);
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
			
		
		}
		if (searchVal.equals(2)) {
			int curPage = 1;
			int tamount = bService.getTitleAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(tamount);
			
			List<BoardVO> list = bService.title(put,to.getStartNum());
			to.setList(list);
			
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
			
		
		}
		if (searchVal.equals(3)) {
			int curPage = 1;
			int tamount = bService.getContentAmount(put);
			PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
			to.setAmount(tamount);
			
			List<BoardVO> list = bService.content(put,to.getStartNum());
			to.setList(list);
			
			model.addAttribute("list", list);
			model.addAttribute("to", to);
			
	
		}
		
		
		
	}
	
	
	
	
	
	
	 @ResponseBody
	   @RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
	   public List<String> getAttach(@PathVariable("bno") int bno){
	      return bService.getAttach(bno);
	   }
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	   public String deleteFile(String filename) {
		
		
		bService.deleteFilename(filename);
	      
	      filename = filename.replace('/', File.separatorChar);
	      
	      String uploadPath = sc.getRealPath(this.uploadPath);
	      
	      String extendName = Utils.getExtendName(filename);
	      MediaType mType = Utils.getMediaType(extendName);
	      if(mType != null) {
	         String systemFileName = Utils.getSystemFileName(filename);
	         File f0 = new File(uploadPath, systemFileName);
	         f0.delete();
	      }
	      
	      File f = new File(uploadPath, filename);
	      
	      f.delete();
	      
	      
	      return filename;
	   }
	   
	

	@ResponseBody
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String uploadfile(MultipartHttpServletRequest request) throws Exception {
		 
		
		  MultipartFile file = request.getFile("file");
		  
		  String oriName = file.getOriginalFilename();
		  
		  String uploadPath = sc.getRealPath(this.uploadPath);
		  
		  String filename = Utils.uploadFile(oriName, uploadPath, file);
		  
		  return filename;
		 


	}

	@RequestMapping(value = "/auto", method = RequestMethod.GET)
	public String Auto(BoardVO vo) {

		for (int i = 0; i < 10; i++) {

			vo = new BoardVO(0, "auto" + i, "auto" + i, "auto" + i, 0, null, null);

			bService.auto(vo);

		}

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	public String delete(@PathVariable("bno") Integer bno, int curPage) {

		bService.delete(bno);

		return "redirect:/board/list/" + curPage;
	}

	@RequestMapping(value = "/update/{bno}", method = RequestMethod.POST)
	public String update(@PathVariable("bno") Integer bno, BoardVO vo, int curPage) {

		vo.setBno(bno);
		bService.update(vo);

		return "redirect:/board/read/" + bno + "?curPage=" + curPage;
	}

	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String update(@PathVariable("bno") Integer bno, @ModelAttribute("curPage") int curPage, Model model) {

		BoardVO vo = bService.update(bno);

		model.addAttribute("vo", vo);

		return "/board/update";

	}

	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String Read(@PathVariable("bno") Integer bno, @ModelAttribute("curPage") int curPage, Model model) {

		BoardVO vo = bService.read(bno);
		
		

		model.addAttribute("vo", vo);

		return "/board/read";

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {

		int curPage = 1;

		int amount = bService.getAmount();
		PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
		to.setAmount(amount);

		List<BoardVO> list = bService.list(to.getStartNum());

		// List<BoardVO> list = bService.list();

		to.setList(list);

		model.addAttribute("list", list);
		model.addAttribute("to", to);

	}

	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(@PathVariable("curPage") int curPage, Model model) {

		int amount = bService.getAmount();
		PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
		to.setAmount(amount);

		List<BoardVO> list = bService.list(to.getStartNum());

		
		to.setList(list);

		model.addAttribute("list", list);
		model.addAttribute("to", to);

		return "/board/list";

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		
		
		bService.insert(vo);

		return "redirect:/board/read/" + vo.getBno() + "?curPage=1";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {

	}

}
