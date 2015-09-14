/*
*主要思路.获取参数、通过反射找到对应的方法，再调用对应的方法
*/
package cn.zsw;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AServlet extends HttpServlet {

	
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 1.获取参数，用来识别用户想请求的方法
		 * 2. 然后判断是否哪一个方法，是哪一个我们就调用哪一个
		 */
		
		String methodName = req.getParameter("method");
		
		if(methodName == null || methodName.trim().isEmpty()){
			throw new RuntimeException("你没有传递method参数！无法确定要调用的方法!");
		}
		
		/*
		 * 得到方法名称，通过反射来调用方法
		 * 1. 得到方法名，通过方法名再得到Method类的对象！
		 *   * 需要得到Class，然后调用它的方法进行查询！得到Method
		 *   * 我们要查询的是当前类的方法，所以我们需要得到当前类的Class
		 */
		Class c = this.getClass();
		Method method = null;
		
		try {
			method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("您要调用的方法：" + methodName + "(HttpServletRequest,HttpServletResponse)，它不存在！");
		} 
		
		try {
			method.invoke(this, req, res);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("你调用的函数内部有异常!");
		} 
	}

	public void addUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("addUser().....");
	}
	
	public void deleteUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("deleteUser().....");
	}
}
