/*这是对Ajax请求进行响应的简单Servlet，包括Get、Post两种类型请求响应*/


//Get请求响应
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print("Ajax");
		response.getWriter().print("Hello Ajax!");

	}

  //Post请求响应
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");   //对响应进行编码设置
		request.setCharacterEncoding("UTF-8");                //对请求进行编码设置
		
		String username = request.getParameter("username");
		
		System.out.print("Post:Ajax" + username);
		response.getWriter().print("(Post:)Hello Ajax!"  + username);

	}
