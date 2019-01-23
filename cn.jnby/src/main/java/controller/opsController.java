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
		
		//����Q&A������������
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
					// ���쳣��Ϣ�󶨵�Request������
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
					// ���쳣��Ϣ�󶨵�Request������
					request.setAttribute("erro", e.getMessage());
					return "erro";
				}
			}
			request.setAttribute("find", ets);
			return "index";
		}

		/**
		 * ����Ψһ��ʾ��ɾ�����ݿ��е�����
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
		 * ����WEB-INF�ļ����µ�jsp�ļ�
		 * 
		 * @return
		 */
		@RequestMapping("/toFind.do")
		public String toFind() {
			return "find";
		}

		/**
		 * ����WEB-INF�ļ����µ�jsp�ļ�
		 * 
		 * @return
		 */
		@RequestMapping("/toUpdate.do")
		public String toUpdate() {
			return "update";
		}
		
		
		
		
		
		//���Q&A���е�����
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(HttpServletRequest request,
				@RequestParam("file") MultipartFile file)throws Exception{
			if(!file.isEmpty()) { 
			//��ȡԭʼ�ļ������ƺͺ�׺
				String originalFilename = file.getOriginalFilename();	
				
			//�ϴ��ļ�·�� 
				System.out.println(1111111111);
				String path = request.getSession().getServletContext().getRealPath("/images/"); 
				System.out.println("path:"+path);
			//�ϴ��ļ��� 
				System.out.println(2222222);
				File filepath = new File(path); 
			//�ж�·���Ƿ���ڣ���������ھʹ���һ�� 
				if (!filepath.exists()) { 
				    filepath.mkdirs(); 
				} 
				String filename = file.getOriginalFilename();
				
//				  //ʹ��UUID��ǰ׺�����ļ�����ֹ�����ظ�������
//				    String prefix = UUID.randomUUID().toString();
//				    prefix = prefix.replace("-","");
//				    String fileName = prefix+"_"+file.getOriginalFilename();//ʹ��UUID��ǰ׺�����ļ�����ֹ�����ظ�������
				//����url����Ŀ¼�ļ���+��׺����Ϊ�˷��������ݿ��д�ȡ
				String url = filename;
				System.out.println(url);
				
				  //�������������
				    InputStream inBuff= file.getInputStream();
                    BufferedInputStream in=new BufferedInputStream(inBuff); 
				  //ָ���������λ��;
				    OutputStream outBuff=new FileOutputStream(new File(path+File.separator+filename));
				    BufferedOutputStream out=new BufferedOutputStream(outBuff); 
				    
				    
				  //�������ļ����ƣ����ļ��洢������������ͨ�������д�뵽�ϴ�λ��
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
			String url ="û���ļ�";
			et.setUrl(url);
		}
			// ��ȡjsp����Ϊ...��input��ǩ���û����������
		       String question = request.getParameter("question");
		       String answer=request.getParameter("answer");
		       String classify = request.getParameter("classify");
		       

		// �����û������ȡ�������ݴ���ʵ������
		       et.setQuestion(question);
		       et.setAnswer(answer);
		       et.setClassify(classify);
		       
		       

		// ����ҵ���ķ��������ݴ���ҵ���
		       os.saveEntity(et);
		
		return "redirect:main.do";// �ض���main����
	}
		
		
		
		/*
		 * �ļ�����
		 */
		@RequestMapping(value = "/download.do")
		public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename,
				Model model) throws Exception {
			if(!filename.isEmpty()){
				// �����ļ�·��
				String path = request.getSession().getServletContext().getRealPath("/images/");
				File file = new File(path + File.separator + filename);
				HttpHeaders headers = new HttpHeaders();
				// ������ʾ���ļ������������������������
				String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
				// ֪ͨ�������attachment�����ط�ʽ����
				headers.setContentDispositionFormData("attachment", downloadFielName);
				// application/octet-stream �� �����������ݣ�������ļ����أ���
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			}else{
				return null;
			}
        }
		
		

}		
		





	
	
