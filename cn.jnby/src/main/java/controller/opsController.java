package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import entity.entity;
import service.ApplicationException;
import service.opsService;

@Controller
public class opsController {
	    @Resource(name = "entity")
		private entity et ;
	    @Resource(name = "opsService")
        private opsService os ;
		
		//查找Q&A库中所有数据
		@RequestMapping("/main.do")
		public String main(HttpServletRequest request) {
			List<entity> ets=os.find();
			request.setAttribute("find", ets);
			return "index";
		}
		

		@RequestMapping("/find.do")
		public String findByWords(HttpServletRequest request){
			String answer=request.getParameter("answer");
			String question=request.getParameter("question");
			System.out.println(answer+""+question);
			et.setAnswer(answer);
			et.setQuestion(question);
			List<entity> ets=null;
			try{
				ets=os.findByWords(et);
			}catch(Exception e){
				e.printStackTrace();
				if (e instanceof ApplicationException) {
					// 将异常信息绑定到Request请求上
					request.setAttribute("erro", e.getMessage());
					return "erro";
				}
			}
			request.setAttribute("find", ets);
			return "index";
		}
		
		@RequestMapping("/class.do")
		public String findByClassify(HttpServletRequest request){
			String classify=request.getParameter("classify");
			System.out.println(classify);
			et.setClassify(classify);
			System.out.println(111);
			List<entity> ets=null;
			try{
				ets=os.findByClassify(et);
			}catch(Exception e){
				e.printStackTrace();
				if (e instanceof ApplicationException) {
					// 将异常信息绑定到Request请求上
					request.setAttribute("erro", e.getMessage());
					return "erro";
				}
			}
			request.setAttribute("find", ets);
			return "index";
		}

		/**
		 * 依据唯一标示符删除数据库中的数据
		 * 
		 * @param request
		 * @return
		 */
		@RequestMapping("/del.do")
		public String delete(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			os.delete(id);
			return "redirect:main.do";
		}

		/**
		 * 访问WEB-INF文件夹下的jsp文件
		 * 
		 * @return
		 */
		@RequestMapping("/toFind.do")
		public String toFind() {
			return "find";
		}

		/**
		 * 访问WEB-INF文件夹下的jsp文件
		 * 
		 * @return
		 */
		@RequestMapping("/toUpdate.do")
		public String toUpdate() {
			return "update";
		}
		
		
		
		
		
		//添加Q&A库中的数据
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(HttpServletRequest request,
				@RequestParam("file") MultipartFile file)throws Exception{
			if(!file.isEmpty()) { 
			//获取原始文件的名称和后缀
				String originalFilename = file.getOriginalFilename();	
				
			//上传文件路径 
				System.out.println(1111111111);
				String path = request.getSession().getServletContext().getRealPath("/images/"); 
				System.out.println("path:"+path);
			//上传文件名 
				System.out.println(2222222);
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
				//构造url（根目录文件名+后缀），为了方便在数据库中存取
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
				      
				    
		}else{
			String url ="没有文件";
			et.setUrl(url);
		}
			// 获取jsp中名为...的input标签中用户输入的数据
		       String question = request.getParameter("question");
		       String answer=request.getParameter("answer");
		       String classify = request.getParameter("classify");
		       

		// 将从用户哪里获取来的数据存入实体类中
		       et.setQuestion(question);
		       et.setAnswer(answer);
		       et.setClassify(classify);
		       
		       

		// 调用业务层的方法将数据传给业务层
		       os.saveEntity(et);
		
		return "redirect:main.do";// 重定向到main方法
	}
		
		
		
		/*
		 * 文件下载
		 */
		@RequestMapping(value = "/download.do")
		public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename,
				Model model) throws Exception {
			if(!filename.isEmpty()){
				// 下载文件路径
				String path = request.getSession().getServletContext().getRealPath("/images/");
				File file = new File(path + File.separator + filename);
				HttpHeaders headers = new HttpHeaders();
				// 下载显示的文件名，解决中文名称乱码问题
				String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
				// 通知浏览器以attachment（下载方式）打开
				headers.setContentDispositionFormData("attachment", downloadFielName);
				// application/octet-stream ： 二进制流数据（最常见的文件下载）。
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			}else{
				return null;
			}
        }
		
		

}		
		





	
	
