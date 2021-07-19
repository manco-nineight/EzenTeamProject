package kr.co.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
   
   @Autowired
   private SqlSession session;
   
   private final String NAMESPACE = "kr.co.board";
   
   
   @Override
	public List<BoardVO> content(String put, int startNum) {
		put = "%"+put+"%";
		 RowBounds rb = new RowBounds(startNum-1, 10);
		 
		return session.selectList(NAMESPACE+".content",put,rb);
	}
	
	@Override
	public int getContentAmount(String put) {
		put = "%"+put+"%";
		return session.selectOne(NAMESPACE+".getContentAmount",put);
	}
	
	@Override
	public int getTitleAmount(String put) {
		put = "%"+put+"%";
		return session.selectOne(NAMESPACE+".getTitleAmount",put);
	}
	
	@Override
	public List<BoardVO> title(String put, int startNum) {
		put = "%"+put+"%";
		 RowBounds rb = new RowBounds(startNum-1, 10);
		 
		return session.selectList(NAMESPACE+".title",put,rb);
	}
	
	
	@Override
	public int getSAmount(String put) {
		put = "%"+put+"%";
		return session.selectOne(NAMESPACE+".getSAmount",put);
	}
	
	
	@Override
	public List<BoardVO> writer(String put, int startNum) {
		
		put = "%"+put+"%";
		 RowBounds rb = new RowBounds(startNum-1, 10);
		 
		return session.selectList(NAMESPACE+".writer",put,rb);
	}
   
   
   
   @Override
	public void deleteAllFilenameByBno(int bno) {
		// TODO Auto-generated method stub
		session.delete(NAMESPACE+".deleteAllFilenameByBno",bno);
	}
   
   @Override
	public void deleteFilename(String filename) {
		// TODO Auto-generated method stub
	
	   session.delete(NAMESPACE+".deleteFilename",filename);
	}
   
   @Override
	public List<String> getAttach(int bno) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE+".getAttach",bno);
	}
   
   
   @Override
	public List<String> readAttach(Integer bno) {
		// TODO Auto-generated method stub
		return session.selectList(NAMESPACE+".readAttach",bno);
	}
   
   @Override
	public void addAttach(String filename, int bno) {
	   
	   
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("filename", filename);
	   map.put("bno", bno);
		session.insert(NAMESPACE+".addAttach",map);
		
	}
   
   @Override
   public void insert(BoardVO vo) {
      session.insert(NAMESPACE+".insert", vo);      
   }
   
   
   @Override
	public void readcnt(Integer bno) {
		session.update(NAMESPACE+".readcnt",bno);
		
	}
   
   @Override
	public List<BoardVO> list() {
		
		return session.selectList(NAMESPACE+".list");
	}
   
   @Override
	public BoardVO read(Integer bno) {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE+".read", bno);
		
		
	}
   
   @Override
	public BoardVO update(Integer bno) {
		// TODO Auto-generated method stub
		return read(bno);
	}
  
   
   @Override
	public void update(BoardVO vo) {
		session.update(NAMESPACE+".update", vo);
		
	}
   
   @Override
	public void delete(Integer bno) {
		session.delete(NAMESPACE+".delete", bno);
		
	}
   
   @Override
	public void auto(BoardVO vo) {
		session.insert(NAMESPACE+".auto",vo);
		
	}
   
   @Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return session.selectOne(NAMESPACE+".getAmount");
	}
   
   @Override
	public List<BoardVO> list(int startNum) {
		// TODO Auto-generated method stub
	   
	   RowBounds rb = new RowBounds(startNum-1, 10);
	   
		return session.selectList(NAMESPACE+".list",null,rb);
	}

}