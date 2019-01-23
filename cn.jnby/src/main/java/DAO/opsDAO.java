
package DAO;

import java.util.List;

import entity.entity;

/*
 * �־ò�ӿ�
 */
public interface opsDAO {
	public List<entity> find();
	public void saveEntity(entity et);
	public void update(entity et);
	public void delete(int id);
    public List<entity> findByWords(entity et); 	
    public List<entity> findByClassify(entity et);
	
}