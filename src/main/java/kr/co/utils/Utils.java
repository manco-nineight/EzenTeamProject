package kr.co.utils;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


public class Utils {
	
	
	public static String rename(String oriName) {
		String newName = null;
		
		UUID uid = UUID.randomUUID();	//	이름값을 렌덤으로 주어 중복방지
		newName = uid.toString()+"_"+oriName;
		
		return newName;
		
	}
	
	
	
	public static String makeDir(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		
		File f = new File(uploadPath, year+"");	//separator시스템에 맞게 해준다
		//파일 객체
		if(!f.exists())	{ //파일이 존재하냐?
			f.mkdir(); 	//디렉토리를 만들어주라
		}
		
		int month = cal.get(Calendar.MONTH)+1;
		File f_mon = new File(f, month<10? "0"+month:""+month);
		
		if(!f_mon.exists())	{ //파일이 존재하냐?
			f_mon.mkdir(); 	//디렉토리를 만들어주라
		}
		
		
		
		int date = cal.get(Calendar.DATE);
		File f3 = new File(f_mon, date<10? "0"+date:""+date);
		
		if(!f3.exists())	{ //파일이 존재하냐?
			f3.mkdir(); 	//디렉토리를 만들어주라 폴더 정보
		}
		
		return f3.getAbsolutePath();	//	절대패스
		
		//String aPath = f3.getAbsolutePath();
		//int idx = aPath.indexOf("uploads") + "uploads".length();
		//System.out.println(aPath.substring(idx));	
	}



	public static String getPathFileName(String aPath, String newName) {
		int idx = aPath.indexOf("uploads") + "uploads".length();
         String pathFileName = 	aPath.substring(idx)+File.separator+newName;
         pathFileName =	pathFileName.replace(File.separatorChar, '/');	//	역슬래시를 슬래시로 변환

		return pathFileName;
	}

	public static String getExtendName(String oriName) {
		// 확장자가 png, gif, jpeg, jpg인 파일만 이미지 파일로 간주하겠음.
		int idx =	oriName.lastIndexOf(".");	//확장자 찾기
		return oriName.substring(idx+1);
	}

	public static MediaType getMediaType(String extendName) {
		//extendName = getExtendName(getExtendName); 가능하다

		//extendName = Utils.getExtendName(extendName);
		Map<String, MediaType> map = new HashMap<String, MediaType>();
		map.put("JPG", MediaType.IMAGE_JPEG);
		map.put("JPEG", MediaType.IMAGE_JPEG);
		map.put("PNG", MediaType.IMAGE_PNG);
		map.put("GIF", MediaType.IMAGE_GIF);
		
		
		return map.get(extendName.toUpperCase());
	}



	public static String makeThumbnamil(String aPath,String newName) throws Exception {
		//newName = uuid.toString()+_+oriName;
		//thumbnailName = uuid.toString()+_s+oriName;
		int idx = newName.indexOf("_");
		String thumbnailName  = newName.substring(0, idx)+"_s"+newName.substring(idx);
		//uuid.toString()_s+oriName = 썸네임이름
		
		BufferedImage sourceImg = ImageIO.read(new File(aPath, newName));//램에 원본파일
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT,100);
		//크기 조절FIT_EXACT 가로 세로 100으로
		File f =  new File(aPath, thumbnailName);
		
		String extendName = Utils.getExtendName(newName).toUpperCase();
		
		ImageIO.write(destImg, extendName, f);
		
		return getPathFileName(aPath, thumbnailName);
	}



	public static String uploadFile(String oriName, String uploadPath, MultipartFile file) throws Exception {
		String path = Utils.makeDir(uploadPath);//절대패스 파일주소
		String newName= Utils.rename(oriName);
		
		File target = new File(path, newName);
		FileCopyUtils.copy(file.getBytes(), target);//업로드
		
		String extendName = Utils.getExtendName(oriName);
		
		MediaType mType = Utils.getMediaType(extendName);
		
		String pathFileName = Utils.getPathFileName(path, newName);
	      
		   if(mType != null) {
		         return Utils.makeThumbnamil(path, newName);//이미지 파일은 썸내일작업
		      }else {
		         return Utils.getPathFileName(path, newName);//파일저장경로
		      }
    
		
	}
	public static String getSystemFileName(String filename) {
		String name = null;
		int idx = filename.indexOf("_");
		String prefix = filename.substring(0, idx);//파일명의 앞자리부분
		String sffix = filename.substring(idx+2);//파일명의 뒷자리부분
		name = prefix + sffix;//중앙에 _s부분을 제거하여 썸네일파일에서 원본파일명을 구한다.
		
		return name;
	}

}
