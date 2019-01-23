package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DAO.opsDAO;
import entity.entity;
/*
 * 业务层实现类
 */
@Service("opsService")
public class opsServiceImp implements opsService{
	@Resource(name = "entity")
	private entity et ;
	@Resource(name = "opsDAO")
	private opsDAO DAO ;

	public List<entity> find() {
		//查找表中所有词条
		List<entity> ets = DAO.find();
		System.out.println(2);
		if(ets == null){
			throw new ApplicationException("无任何词条");
		}
		
		return ets;
	}
	//添加词条
	public void saveEntity(entity et) {
		DAO.saveEntity(et);
		
	}
	//修改词条
	public void update(entity et) {
		DAO.update(et);
	}
	//删除词条
	public void delete(int id) {
		DAO.delete(id);
	}
	//根据字段名查找词条
	public List<entity> findByWords(entity et) {
		List<entity> ets =DAO.findByWords(et);
		System.out.println(ets);
		if(ets==null){
			throw new ApplicationException("查无对应词条");
		}
		return ets;
	}
	//分类查找
	public List<entity> findByClassify(entity et) {
		List<entity> ets=DAO.findByClassify(et);
		System.out.println("ets="+ets);
		if(ets==null){
			throw new ApplicationException("无此分类");
		}
		return ets;
	}

}