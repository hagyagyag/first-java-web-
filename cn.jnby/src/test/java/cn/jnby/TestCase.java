package cn.jnby;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.opsDAO;
import entity.entity;
import service.opsService;

public class TestCase {
	private ApplicationContext ac;
	private opsDAO dao;
	private opsService os;
	@Before
	public void init(){
		String config ="springmvc.xml";
		ac = new ClassPathXmlApplicationContext(config);
		dao =ac.getBean("opsDAO",opsDAO.class);
		os = ac.getBean("opsService",opsService.class);
	}
//�����Ƿ����������ݿ�
	@Test
	public void test1() throws SQLException{
		DataSource ds = ac.getBean("ds",DataSource.class);
		System.out.println(ds.getConnection());
	}

//�����Ƿ���Բ�ѯ���ű��
	@Test
	public void test3() throws SQLException{
		List<entity> find = dao.find();
		System.out.println(find);
	}
	

	
}
