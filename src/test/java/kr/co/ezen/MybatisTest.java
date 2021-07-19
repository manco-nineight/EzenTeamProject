package kr.co.ezen;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

=======
import javax.inject.Inject;
>>>>>>> Nsangwon
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> Nsangwon
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
<<<<<<< HEAD
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MybatisTest {
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
=======
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MybatisTest {
	@Inject
	private SqlSessionFactory sqlFactory;

>>>>>>> Nsangwon
	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}
<<<<<<< HEAD
	
	@Test
	public void testSession() {
		SqlSession session = null;
		
=======

	@Test
	public void testSession() {
		SqlSession session = null;
>>>>>>> Nsangwon
		try {
			session = sqlFactory.openSession();
			
			System.out.println(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
<<<<<<< HEAD

}
=======
}
>>>>>>> Nsangwon
