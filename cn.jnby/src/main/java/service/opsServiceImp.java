package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	//上传文件
	public void fileUpload(String path,MultipartFile file) throws IOException {
		
			//获取原始文件的名称和后缀
		//		String originalFilename = file.getOriginalFilename();	
				
			//上传文件路径 
				System.out.println("path:"+path);
			//上传文件名 
				File filepath = new File(path); 
			//判断路径是否存在，如果不存在就创建一个 
				if (!filepath.exists()) { 
				    filepath.mkdirs(); 
				} 
				String filename = file.getOriginalFilename();
				
//				  //使用UUID加前缀命名文件，防止名字重复被覆盖
//				    String prefix = UUID.randomUUID().toString();
//				    prefix = prefix.replace("-","");
//				    String fileName = prefix+"_"+file.getOriginalFilename();//使用UUID加前缀命名文件，防止名字重复被覆盖
				//构造url，为了方便在数据库中存取
				String url = filename;
				System.out.println(url);
				
				  //声明输入输出流
				    InputStream inBuff= file.getInputStream();
                    BufferedInputStream in=new BufferedInputStream(inBuff); 
				  //指定输出流的位置;
				    OutputStream outBuff=new FileOutputStream(new File(path+File.separator+filename));
				    BufferedOutputStream out=new BufferedOutputStream(outBuff); 
				    
				    
				  //类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
				    byte []buffer =new byte[1024];
				    int len=0;
				    while((len=in.read(buffer))!=-1){
				            out.write(buffer, 0, len);
				            out.flush();                
				        }                              
				      
				      outBuff.close();
				      out.close();
				      inBuff.close();
				      in.close();
				      et.setUrl(url);

	}
	

}
