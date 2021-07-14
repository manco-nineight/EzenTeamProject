package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {
	
	
	
	
	
	public void insert(BoardVO vo);

	public List<BoardVO> list();

	public BoardVO read(Integer bno);

	public BoardVO update(Integer bno);

	public void update(BoardVO vo);

	public void delete(Integer bno);

	public void auto(BoardVO vo);

	public int getAmount();

	public List<BoardVO> list(int startNum);

	public List<String> getAttach(int bno);

	public void deleteFilename(String filename);

	List<BoardVO> writer(String put, int startNum);


	int getSAmount(String put);

	int getTitleAmount(String put);

	List<BoardVO> title(String put, int startNum);

	int getContentAmount(String put);

	List<BoardVO> content(String put, int startNum);

	

}
