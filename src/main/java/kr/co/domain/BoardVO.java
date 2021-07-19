package kr.co.domain;

import java.io.Serializable;

public class BoardVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int  readcnt;
	private String regdate;
	private String updatedate;
	private String[] files;
	
	
	
	
	
	public BoardVO() {
		// TODO Auto-generated constructor stub
	}





	public BoardVO(int bno, String title, String content, String writer, int readcnt, String regdate,
			String updatedate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.readcnt = readcnt;
		this.regdate = regdate;
		this.updatedate = updatedate;
		
		
	}





	





	public String[] getFiles() {
		return files;
	}





	public void setFiles(String[] files) {
		this.files = files;
	}





	public int getBno() {
		return bno;
	}





	public void setBno(int bno) {
		this.bno = bno;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public String getWriter() {
		return writer;
	}





	public void setWriter(String writer) {
		this.writer = writer;
	}





	public int getReadcnt() {
		return readcnt;
	}





	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}





	public String getRegdate() {
		return regdate;
	}





	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}





	public String getUpdatedate() {
		return updatedate;
	}





	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	
	

}
