package kr.co.ezen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
<<<<<<< HEAD

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
=======
//
//import javax.annotation.Resource;
//import javax.rmi.CORBA.Util;
import javax.servlet.ServletContext;
//import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
>>>>>>> imwonbin
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
=======
import org.springframework.util.FileCopyUtils;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
>>>>>>> imwonbin

import kr.co.utils.Utils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
<<<<<<< HEAD
	
	@Autowired
	private ServletContext sc;
	
	
	private String uploadPath ="resources"+File.separator+"uploads";
	
	@ResponseBody
	@RequestMapping(value = "/displayFile" , method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String filename) {
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		System.out.println(filename);
		try {
			String extendName = Utils.getExtendName(filename);
			MediaType mType = Utils.getMediaType(extendName);
			HttpHeaders headers = new HttpHeaders();
			
			String uploadPath = sc.getRealPath(this.uploadPath);
			
			in = new FileInputStream(uploadPath+filename);
			
			if (mType != null) {
				headers.setContentType(mType);
			}else {
				filename = filename.substring(filename.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//파일을 다운로드 받게해줌
				headers.add("Content-Disposition", "attachment;filename=\""+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return entity;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//System.out.println(sc.getRealPath(uploadPath)+"::::::::::::::::::::::");
		
		
		return "Test";
	}
	
}
=======
   
   @Autowired
   private ServletContext sc;
      
   private String uploadPath= "resources"+File.separator+"uploads";
   
   
//   @RequestMapping(value = "/testInterceptor1", method =  RequestMethod.GET)
//   public String testInterceptor1(Model model) {
//	   
//	   System.out.println("controller");
//	   model.addAttribute("hello", "world");
//	   
//	   return "home";
//   }
//   
   
   @ResponseBody
   @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
   public String deleteFile(String filename) {
      
      filename = filename.replace('/', File.separatorChar);
      
      String uploadPath = sc.getRealPath(this.uploadPath);
      
      String extendName = Utils.getExtendName(filename);
      MediaType mType = Utils.getMediaType(extendName);
      if(mType != null) {
         String getSystemFileName = Utils.getSystemFileName(filename);
         File f0 = new File(uploadPath, getSystemFileName);
         f0.delete();
      }
      
      File f = new File(uploadPath, filename);
      
      f.delete();
      
      
      return "ok";
   }
   
   
   
   @ResponseBody
   @RequestMapping(value = "/displayFile", method = RequestMethod.GET)
   public ResponseEntity<byte[]> displayFile(String filename) {
      ResponseEntity<byte[]> entity = null;
      
      InputStream in = null;
      
      try {
         String extendName = Utils.getExtendName(filename);
         MediaType mType = Utils.getMediaType(extendName);
         HttpHeaders headers = new HttpHeaders();
         
         String uploadPath = sc.getRealPath(this.uploadPath);
         
         in = new FileInputStream(uploadPath+filename);
         
         if(mType != null) {
            headers.setContentType(mType);
         }else {
            filename = filename.substring(filename.indexOf("_")+1); 
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment;filename=\""+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
         }
         
         entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
         
      } catch (Exception e) {
         e.printStackTrace();
         entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
      } finally {
         if (in != null) {
            try {
               in.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      
      
      return entity;
   }
   
   
   @ResponseBody
   @RequestMapping(value = "/uploadAjax", method=RequestMethod.POST, produces = "text/plain;charset=utf8")
   public String uploadAjax(MultipartHttpServletRequest request) throws Exception {
      MultipartFile  file = request.getFile("file");
      
      String oriName = file.getOriginalFilename();
      
      String uploadPath = sc.getRealPath(this.uploadPath);
      
      String result = Utils.uploadFile(oriName, uploadPath, file);
      
      return result;
   }
   
   
   @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
   public void uploadAjax() {
      
   }
   
   
   
   @RequestMapping(value = "/noajax", method = RequestMethod.POST)
   public String noajax(MultipartHttpServletRequest request, Model model) throws Exception {
      MultipartFile file = request.getFile("file");
      
      String oriName = file.getOriginalFilename();
      
      String uploadPath = sc.getRealPath(this.uploadPath);
      
      String pathFileName = Utils.uploadFile(oriName, uploadPath, file);
      model.addAttribute("pathFileName", pathFileName);
      
      return "/uploadresult";
   }
   
   
   @RequestMapping(value = "/noajaxForm", method = RequestMethod.GET)
   public String noajaxForm() {
      return "/noajax";
   }
   
   
   @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
   public String uploadForm(MultipartHttpServletRequest request, Model model) throws Exception {
//      String id = request.getParameter("id");
      MultipartFile  file = request.getFile("file");
      
      String oriName = file.getOriginalFilename();
      
      String uploadpath= sc.getRealPath(this.uploadPath);
               
      
      String path = Utils.makeDir(uploadpath);
      File target = new File(path, Utils.rename(oriName));
      FileCopyUtils.copy(file.getBytes(), target);
      
   
      
      
      return "/noajax";
      
      
   }
   
   @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
   public void uploadFor() {
      
   }
   @RequestMapping(value = "/home2", method = RequestMethod.GET)
   public void home2() {
      
   }
   
   
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      
      System.out.println(sc.getRealPath(uploadPath)+":::::::::::::::::::::");
      
      
      
      
      
      return "/home";
   }
   
}
>>>>>>> imwonbin
