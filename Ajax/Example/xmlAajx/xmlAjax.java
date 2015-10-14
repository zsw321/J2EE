#### 对应的Servlet ####

public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String xml = "<students>" +
					"<student number='zsw321'>" +
					"<name>zsw</name>" + 
					"<age>18</age>" + 
					"<male>Boy</male>" +
					"</student>" +
					"</students>";
		
		response.setContentType("text/xml;charset=utf-8");
		response.getWriter().print(xml);
	}
