package kr.co.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.PageTO;
import kr.co.domain.ProdStockDTO;
import kr.co.domain.ProductVO;
import kr.co.service.ProdAttachService;
import kr.co.service.ProdStockService;
import kr.co.service.ProductService;
import kr.co.utils.Utils;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ServletContext sc;

	private String uploadPath = "resources" + File.separator + "uploads";

	@Autowired
	private ProductService prodService;

	@Autowired
	private ProdStockService prodStockService;

	@Autowired
	private ProdAttachService prodAttachService;

	@RequestMapping(value = "/prodInsert", method = RequestMethod.GET)
	public String insert() {

		return "/product/prodInsert";
	}
	
	/**
	 *  PRODUCT INSERT
	 */
	@RequestMapping(value = "/prodInsert", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String insert(ProductVO prodVO, ProdStockDTO prodStockDTO,
			@RequestParam("prodThumbnailFile") MultipartFile file) throws Exception {
		String oriName = file.getOriginalFilename();
		String uploadPath = sc.getRealPath(this.uploadPath);
		String filename = Utils.uploadFile(oriName, uploadPath, file);
		String filePath = File.separator + "resources" + File.separator + "uploads" + filename;
		prodVO.setProdThumbnail(filePath);
		
		prodService.insertProduct(prodVO);
		prodStockService.insertProdStock(prodStockDTO);
		prodAttachService.updateBno(prodVO.getProdBno());
		
//		prodVO 의 content에서 img 태그로 되어 있는 값들 찾아와서 넣기
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		Matcher getImgName = imgPattern.matcher(prodVO.getProdContent());
		
		
		
//		콘텐츠에 포함되어 있는 이미지와 DB에 저장된 이미지 리스트로 만들
		List<String> contentList = new ArrayList<String>();
		List<String> dbList = prodAttachService.getAttFilePath(prodVO.getProdBno());
		
//		콘텐츠에서 찾은 이미지 명들 리스트에 DB 이름의 양식으로 추
		while (getImgName.find()) {
			String imgName = getImgName.group();
			int srdIdx = imgName.indexOf("src");
			int endIdx = imgName.indexOf(";");
			System.out.println(imgName);
			String imgRealName = imgName.substring(srdIdx + 5, endIdx - 21);
			System.out.println(imgRealName);
			
			int idx = imgRealName.indexOf("_");
			String test = imgRealName.substring(idx+1);
			System.out.println(test);
			
			contentList.add(imgRealName);
		}
		
//		DB에 저장되어 있는 파일명이 contentList에 존재하는지 비교, 만약 존재한다면(true) 건너뛰고 존재하지 않는다면(false) 파일 삭
		for (String dbImg : dbList) {
			boolean deleteUncontainsFile = contentList.contains(dbImg);
			if (!deleteUncontainsFile) {
				int realIdx = dbImg.indexOf("uploads");
				String realName = dbImg.substring(realIdx + 7);
				String realPath = sc.getRealPath(this.uploadPath);
				
				File f = new File(realPath, realName);
				System.out.println(f);
				f.delete();
			}
			
		}

		return "redirect:/product/prodList/";
	}
	@ResponseBody
	@RequestMapping(value = "/prodNameDupCheck", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public int prodNameDupCheck(@RequestParam("prodName")String prodName) {
		
		int result = 0;
		
	 	String prodNameCheck = prodService.prodNameDupCheck(prodName);
	 	
	 	if (prodNameCheck != null) {
	 		result = 1;
	 	} else {
	 		result = 0;
	 	}
		
		return result;
	}
	/**
	 *  ATTACH IMG WHEN INSERT IN TO SUMMERNOTE
	 */
	@ResponseBody
	@RequestMapping(value = "/prodFile", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String uploadfile(MultipartHttpServletRequest request) throws Exception {

		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();

		String uploadPath = sc.getRealPath(this.uploadPath);

		String filename = Utils.uploadFile(oriName, uploadPath, file);

		String filePath = File.separator + "resources" + File.separator + "uploads" + filename;

		int bno = prodService.getBnoForAttach();
		prodAttachService.insertAttachInfo(filePath, bno);

		// return filePath as img src for summernote
		return File.separator + "resources" + File.separator + "uploads" + filename;
	}
	
	/**
	 *  PRODUCT UPDATE
	 */
	@RequestMapping(value = "/prodUpdate" , method = RequestMethod.POST , produces = "text/plain;charset=utf-8")
	public String update(ProductVO prodVO, ProdStockDTO prodStockDTO,
			@RequestParam("prodThumbnailFile") MultipartFile file ) throws Exception {
		
		
		Pattern imgPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
		
		ProductVO vo2 = prodService.getContent(prodVO.getProdBno());
		
		Matcher getImgName = imgPattern.matcher(vo2.getProdContent());
		
		if (vo2.getProdContent() != prodVO.getProdContent()) {
			
			while (getImgName.find()) {
				String imgName = getImgName.group();
				int srdIdx = imgName.indexOf("src");
				int endIdx = imgName.indexOf(";");
				
				String imgRealName = imgName.substring(srdIdx + 5, endIdx - 21);
				int realIdx = imgRealName.indexOf("uploads");
				String realName = imgRealName.substring(realIdx + 7);
				String realPath = sc.getRealPath(this.uploadPath);
				File f = new File(realPath, realName);
				System.out.println(f);
				f.delete();
				prodAttachService.deleteFileImg(imgRealName);
			}
			
		}
		
		 String oriName= file.getOriginalFilename();
		  
		 if (oriName.equals("")) {
				String prodName = prodVO.getProdName();
				
				String thumnail= prodService.getThumnail(prodName);
				
				prodVO.setProdThumbnail(thumnail);
				
				prodService.updateProduct(prodVO);
				
				prodStockService.updateProdStock(prodStockDTO);
				
				return "redirect:/product/prodRead/"+prodName;
				
			}else {
			String uploadPath = sc.getRealPath(this.uploadPath);

			String filename = Utils.uploadFile(oriName, uploadPath, file);

			String filePath = File.separator + "resources" + File.separator + "uploads" + filename;
			prodVO.setProdThumbnail(filePath);

			String prodName = prodVO.getProdName();

			prodService.updateProduct(prodVO);

			prodStockService.updateProdStock(prodStockDTO);
			

			return "redirect:/product/prodRead/"+prodName;

				}
		}
	@RequestMapping(value = "/prodUpdate/{prodName}" , method = RequestMethod.GET , produces = "text/plain;charset=utf-8")
	public String update(@PathVariable("prodName") String prodName , Model model) {

		ProductVO vo = prodService.prodUpate(prodName);
		ProdStockDTO dto = prodStockService.prodStockRead(prodName);
		model.addAttribute("vo" , vo);
		model.addAttribute("dto" , dto);

		return "/product/prodUpdate";
	}
	
	@ResponseBody
	@RequestMapping(value = "/prodFileUpdate/{prodBno}" , method = RequestMethod.POST , produces = "text/plain;charset=utf-8")
	public String updateFile(MultipartHttpServletRequest request ,@PathVariable("prodBno") int bno) throws Exception {
		
		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();
		String uploadPath = sc.getRealPath(this.uploadPath);
		String filename = Utils.uploadFile(oriName, uploadPath, file);
		String filePath = File.separator + "resources" + File.separator + "uploads" + filename;
		
		//prodAttachService.UpdateAttachInfo(filePath, bno);
		//prodVO 의 content에서 img 태그로 되어 있는 값들 찾아와서 넣기
		
		
		return filePath;
	}
	
	/**
	 *  PRODUCT LIST BY CATEGORY AND ORDERED LIST
	 */
	@RequestMapping(value = "/prodList", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public void prodList(@RequestParam(value = "prodCategory", required = false)  String prodCategory, 
			@RequestParam(value = "prodOrder", required = false) String prodOrder,
			String keyword,
			Model model) {
		int curPage = 1;
		
		int amount = prodService.getProdAmount();
		PageTO<ProductVO> to = new PageTO<ProductVO>(curPage);
		to.setAmount(amount);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodCategory", prodCategory);
		map.put("prodOrder", prodOrder);
		map.put("keyword", keyword);
		
		List<ProductVO> list = prodService.listProd(to.getStartNum(), map);
		to.setList(list);

		model.addAttribute("to", to);
		model.addAttribute("prodCategory", prodCategory);
		model.addAttribute("prodOrder", prodOrder);
		model.addAttribute("keyword", keyword);
	}
	@ResponseBody
	@RequestMapping(value = "/prodListScroll", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<ProductVO> prodList(@RequestBody Map<String, Object> param, Model model) {

		int curPage = (int) param.get("curPage");
		
		int amount = prodService.getProdAmount();
		PageTO<ProductVO> to = new PageTO<ProductVO>(curPage);
		to.setAmount(amount);
		
		
		String prodCategory = (String) param.get("prodCategory");
		String prodOrder = (String) param.get("prodOrder");
		String keyword = (String) param.get("keyword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodCategory", prodCategory);
		map.put("prodOrder", prodOrder);
		map.put("keyword", keyword);
		
		List<ProductVO> list = prodService.listProd(to.getStartNum(), map);
	
		to.setList(list);

		return list;
	}

	/**
	 *  PRODUCT READ
	 */
	@RequestMapping(value = "/prodRead/{prodName}", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String prodRead(@PathVariable("prodName") String prodName, Model model) {

		ProductVO vo = prodService.prodRead(prodName);
		ProdStockDTO dto = prodStockService.prodStockRead(prodName);

		model.addAttribute("vo", vo);
		model.addAttribute("dto", dto);

		return "/product/prodRead";
	}
	
	/**
	 *  DELETE FILE
	 */
	@RequestMapping(value = "/prodDelete/{prodName}", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String prodDelete(@PathVariable("prodName") String prodName, @RequestParam("prodBno") int prodBno) {
		List<String> attPathList = prodAttachService.getAttFilePath(prodBno);
		
		String realPath = sc.getRealPath(this.uploadPath);
		
		for (String data : attPathList) {
			
			String pre = data;
			int idx = data.lastIndexOf("uploads/");
			String realName = pre.substring(idx+8);
			
			File attFile = new File(realPath, realName);
			attFile.delete();
		}
		
		List<String> thumbPahtList = prodService.getThumbFilePath(prodName);
		
		for (String data : thumbPahtList) {
			
			String pre = data;
			int idx = data.lastIndexOf("uploads/");
			String realName = pre.substring(idx+8);
			
			File thumbFile = new File(realPath, realName);
			thumbFile.delete();
		}
		
		prodService.deleteProd(prodName);
		
		return "redirect:/product/prodList";
	}


	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public void first() {

	}

}
