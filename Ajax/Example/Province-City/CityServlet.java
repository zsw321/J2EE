import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");//注意：发送xml这里要修改！！！
		
		/*
		 * 获取省份名称，加载该省对应的<province>元素！
		 * 把元素转换成字符串发送给客户端
		 */
		/*
		 * 1. 获取省份的名称
		 * 2. 使用省份名称查找到对应的<province>元素
		 * 3. 把<province>元素转换成字符串，发送！
		 */
		try {
			/*
			 * 得到Document
			 */
			SAXReader reader = new SAXReader();
			InputStream input = this.getClass().getResourceAsStream("/china.xml");
			Document doc = reader.read(input);
			
			/*
			 * 获取参数
			 */
			String pname = request.getParameter("pname");//获取省份名称
			System.out.print(pname);
			Element proEle = (Element) doc.selectSingleNode("//province[@name='" + pname + "']");
			String xmlStr = proEle.asXML();//把元素转换成字符串
			response.getWriter().print(xmlStr);
			
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		
		System.out.print("D");
		
		
		 * 1. 获取省份的名称
		 * 2. 使用省份名称查找到对应的<province>元素
		 * 3. 把<province>元素转换成字符串，发送！
		 
		try{
			SAXReader reader = new SAXReader();      //依赖于库dom4j-1.6.1.jar、jaxen-1.1-beta-6.jar
			InputStream input = this.getClass().getResourceAsStream("/china.xml");
			Document doc = reader.read(input);
			
			//获取参数
			String pname = request.getParameter("pname");
			Element proEle = (Element) doc.selectSingleNode("//province[@name='" + pname + "']");
			
			String xmlStr = proEle.asXML();
			response.getWriter().print(xmlStr);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}*/
		
		
	}
