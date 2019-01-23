package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DAO.opsDAO;
import entity.entity;
/*
 * ҵ���ʵ����
 */
@Service("opsService")
public class opsServiceImp implements opsService{
	@Resource(name = "entity")
	private entity et ;
	@Resource(name = "opsDAO")
	private opsDAO DAO ;

	public List<entity> find() {
		//���ұ������д���
		List<entity> ets = DAO.find();
		System.out.println(2);
		if(ets == null){
			throw new ApplicationException("���κδ���");
		}
		
		return ets;
	}
	//��Ӵ���
	public void saveEntity(entity et) {
		DAO.saveEntity(et);
		
	}
	//�޸Ĵ���
	public void update(entity et) {
		DAO.update(et);
	}
	//ɾ������
	public void delete(int id) {
		DAO.delete(id);
	}
	//�����ֶ������Ҵ���
	public List<entity> findByWords(entity et) {
		List<entity> ets =DAO.findByWords(et);
		System.out.println(ets);
		if(ets==null){
			throw new ApplicationException("���޶�Ӧ����");
		}
		return ets;
	}
	//�������
	public List<entity> findByClassify(entity et) {
		List<entity> ets=DAO.findByClassify(et);
		System.out.println("ets="+ets);
		if(ets==null){
			throw new ApplicationException("�޴˷���");
		}
		return ets;
	}

}