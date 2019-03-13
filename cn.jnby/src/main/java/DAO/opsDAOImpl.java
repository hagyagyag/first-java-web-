package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/*
 * 持久层实现类
 */
import org.springframework.stereotype.Repository;

import entity.entity;

@Repository("opsDAO")
public class opsDAOImpl implements opsDAO{

	@Autowired
	@Qualifier("jt")
	private JdbcTemplate jt;
	
	//增加数据库词条
	public void saveEntity(entity et) {
		
			String sql="INSERT INTO jnby_bj (answer,question,classify,url) VALUES(?,?,?,?)";
			
			Object[] args={et.getAnswer(),et.getQuestion(),et.getClassify(),et.getUrl()};
			jt.update(sql,args);
		
		
	}
	//修改数据库词条
	public void update(entity et) {
		String sql="UPDATE jnby_bj SET answer=?,question=?,classify=?,url=? WHERE id=? ";
		Object[] args={et.getAnswer(),et.getQuestion(),et.getId(),et.getClassify(),et.getUrl()};
		jt.update(sql,args);
	}
	
	
	class qaRowMapper implements RowMapper<entity>{
		public entity mapRow(ResultSet rs, int rowNum) throws SQLException{
			entity et = new entity();
		    et.setId(rs.getInt("id"));
		    et.setAnswer(rs.getString("answer"));
		    et.setQuestion(rs.getString("question"));
		    et.setClassify(rs.getString("classify"));
		    et.setUrl(rs.getString("url"));
			return et;
		}
		
	}
	//查找数据库所有词条
	public List<entity> find() {
			String sql="SELECT * FROM jnby_bj order by classify";
			List<entity> find =jt.query(sql, new qaRowMapper());
			return find;
	}
	
	//根据唯一标识符删除数据库词条
	public void delete(int id) {
		    String sql="DELETE FROM jnby_bj WHERE id=?";
		    Object[] args = {id};
		    jt.update(sql, args);
		
	}

	//根据关键词 查询数据库词条
	public List<entity> findByWords(entity et){
		
		List<entity> ets=null;
		String sql =null;
		try{
			if(!et.getAnswer().equals("")){
				sql = "SELECT * FROM jnby_bj WHERE  question LIKE \"%\"?\"%\" AND answer LIKE \"%\"?\"%\" ";
				Object[] args={et.getQuestion(),et.getAnswer()};
				ets=jt.query(sql, args, new qaRowMapper());
			    }else{
			    sql="SELECT * FROM jnby_bj WHERE  question LIKE \"%\"?\"%\" ";
			    Object[] args={et.getQuestion()};	
			    ets=jt.query(sql, args, new qaRowMapper());
				}
		}catch(Exception e){
			return null;
		}
		
		return ets;
	}
	//分类查找
	
	public List<entity> findByClassify(entity et) {
		List<entity> ets = null;
		String sql="SELECT * FROM jnby_bj WHERE classify = ? ";
		Object[] args={et.getClassify()};
		try{
			ets=jt.query(sql, args,new qaRowMapper());
			
		}catch(Exception e){
			return null;
		}
		return ets;
	}
	

	
}
