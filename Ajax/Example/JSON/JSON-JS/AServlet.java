public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 向客户端发送json串
		 */
		String str = "{\"name\":\"zhangSan\", \"age\":18, \"sex\":\"male\"}";
		response.getWriter().print(str);
		System.out.println(str);
	}
