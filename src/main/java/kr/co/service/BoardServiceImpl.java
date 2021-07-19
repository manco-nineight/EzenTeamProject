package kr.co.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;

@Service
public class BoardServiceImpl  implements BoardService{
	
	@Autowired
	private BoardDAO bDao;
	

	
	
	
	
	@Override
	public List<BoardVO> content(String put, int startNum) {
		// TODO Auto-generated method stub
		return bDao.content(put,startNum);
	}
	@Override
	public int getContentAmount(String put) {
		// TODO Auto-generated method stub
		return bDao.getContentAmount(put);
	}
	@Override
	public int getTitleAmount(String put) {
		// TODO Auto-generated method stub
		return bDao.getTitleAmount(put);
	}
	@Override
	public List<BoardVO> title(String put, int startNum) {
		// TODO Auto-generated method stub
		return bDao.title(put,startNum);
	}
	
	
	@Override
	public int getSAmount(String put) {
		
		return bDao.getSAmount(put);
	}
	
	@Override
	public List<BoardVO> writer(String put, int startNum) {
		
		return bDao.writer(put,startNum);
	}
	
	
	
	@Override
	public void deleteFilename(String filename) {
		// TODO Auto-generated method stub
	
		bDao.deleteFilename(filename);
		
	}
	
	
	@Override
	public List<BoardVO> list() {
		
		return bDao.list();
	}
	
	@Transactional
	@Override
	public void insert(BoardVO vo) {
		
		
		bDao.insert(vo);
		String[] files = vo.getFiles();
		if(files == null) {
			
			return;
		}
		
		for(String filename : files ) {
			bDao.addAttach(filename,vo.getBno());
			
		}
		
	}
	
	@Override
	@Transactional
	public BoardVO read(Integer bno) {
		// TODO Auto-generated method stub
		bDao.readcnt(bno);
		
		BoardVO vo = bDao.read(bno);
	
		
		
		return vo;
	}
	
	
	public List<String> getAttach(int bno){
		
		
		return bDao.getAttach(bno);
	}
	

	@Override
	public BoardVO update(Integer bno) {
		// TODO Auto-generated method stub
		return bDao.update(bno);
	}
	
	@Transactional
	@Override
	public void update(BoardVO vo) {
		bDao.update(vo);
		
	
		
		bDao.deleteAllFilenameByBno(vo.getBno());
		if(vo.getFiles() == null) {
			return;
		}
		for(String filename : vo.getFiles()) {
			bDao.addAttach(filename, vo.getBno());
			
		}
		
	}
	
	@Override
	@Transactional
	public void delete(Integer bno) {
		

		
		bDao.deleteAllFilenameByBno(bno);
		
		bDao.delete(bno);
	}
	
	@Override
	public void auto(BoardVO vo) {
		// TODO Auto-generated method stub
		bDao.auto(vo);
	}
	
	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return bDao.getAmount();
	}
	
	@Override
	public List<BoardVO> list(int startNum) {
		// TODO Auto-generated method stub
		return bDao.list(startNum);
	}
}
