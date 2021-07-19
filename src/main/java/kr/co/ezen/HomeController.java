package kr.co.ezen;

<<<<<<< HEAD
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
=======
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> Nsangwon
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.utils.Utils;
=======
>>>>>>> Nsangwon

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
=======
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
>>>>>>> Nsangwon
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
<<<<<<< HEAD
		
		//System.out.println(sc.getRealPath(uploadPath)+"::::::::::::::::::::::");
		
		
		return "Test";
=======
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home"; 
>>>>>>> Nsangwon
	}
	
}
