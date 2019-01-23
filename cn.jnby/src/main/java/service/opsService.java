package service;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.entity;

/*
 * 业务层接口
 */
@Service("opsService")
public interface opsService {
	public List<entity> find();
	public void saveEntity(entity et);
	public void update(entity et);
	public void delete(int id);
    public List<entity> findByWords(entity et); 	
    public List<entity> findByClassify(entity et);

	
}
