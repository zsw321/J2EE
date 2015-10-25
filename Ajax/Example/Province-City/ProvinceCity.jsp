
//JavaScript
<script type="text/javascript">
// 创建异步对象
function createXMLHttpRequest() {
	try {
		return new XMLHttpRequest();//大多数浏览器
	} catch (e) {
		try {
			return ActvieXObject("Msxml2.XMLHTTP");//IE6.0
		} catch (e) {
			try {
				return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早版本	
			} catch (e) {
				alert("哥们儿，您用的是什么浏览器啊？");
				throw e;
			}
		}
	}
}

/*
 * 1. 在文档加载完毕时发送请求，得到所有省份名称，显示在<select name="province"/>中
 * 2. 在选择了新的省份时，发送请求（参数为省名称），得到xml文档，即<province>元素
 *   解析xml文档，得到其中所有的<city>，再得到每个<city>元素的内容，即市名，使用市名生成<option>，插入到<select name="city">元素中
 */
 
window.onload = function() {//文档加载完毕后执行
	 
	 /*
	ajax四步，请求ProvinceServlet，得到所有省份名称
	使用每个省份名称创建一个<option>元素，添加到<select name="province">中
	*/
	//1. 得到异步对象 
	var xmlHttp = createXMLHttpRequest();
	
	//2. 打开与服务器的连接
	xmlHttp.open("GET", "<c:url value='/ProvinceServlet'/>", true);
	
	//3. 发送请求
	xmlHttp.send(null);//GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
	
	//4. 给异步对象的onreadystatechange事件注册监听器
	xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
		// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			
			// 获取服务器的响应结束
			var text = xmlHttp.responseText;
			
			var arr = text.split(",");
			
			// 循环遍历每个省份名称，每个名称生成一个option对象，添加到<select>中
			for(var i=0; i<arr.length; i++){			
				//<option value="arr[i]">arr[i]</option>
				
				var op = document.createElement("option");  //创建一个指名名称元素
				op.value = arr[i];			//设置op的实际值为当前的省份名称
				
				var textNode = document.createTextNode(arr[i]);  //创建文本节点
				op.appendChild(textNode);			//把文本子节点添加到op元素中，指定其显示值
				
				document.getElementById("p").appendChild(op);
			}
		}
	};
	
	/*
	第二件事情：给<select name="province">添加改变监听
	使用选择的省份名称请求CityServlet，得到<province>元素(xml元素)！！！
	获取<province>元素中所有的<city>元素，遍历之！获取每个<city>的文本内容，即市名称
	使用每个市名称创建<option>元素添加到<select name="city">
	*/
	var proSelect = document.getElementById("p");
	proSelect.onchange = function(){	
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open("POST","<c:url value='/CityServlet'/>",true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		
		xmlHttp.send("pname=" + proSelect.value);
		xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
		// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				/*
				把select中的所有option移除（除了请选择）
				*/
				var citySelect = document.getElementById("c");	
				var optionEleList = citySelect.getElementsByTagName("option");
				
				// 循环遍历每个option元素，然后在citySelect中移除
				while(optionEleList.length > 1){
					citySelect.removeChild(optionEleList[1]);
				}
				
				// 获取服务器的响应结束
				var doc = xmlHttp.responseXML;
				// 得到所有名为city的元素
				var cityEleList = doc.getElementsByTagName("city");
				
				alert(cityEleList);
				
				// 循环遍历每个省份名称，每个名称生成一个option对象，添加到<select>中
				for(var i=0; i<cityEleList.length; i++){			
					//<option value="arr[i]">arr[i]</option>
					var cityEle  = cityEleList[i];
					var cityName;
					
					if(window.addEventListener){
						cityName = cityEle.textContent;	//支持FireFox等浏览器
					}else {
						cityName = cityEle.text;		//支持IE
					}
					
					var op = document.createElement("option");  //创建一个指名名称元素
					op.value = cityName;			//设置op的实际值为当前的省份名称
					
					var textNode = document.createTextNode(cityName);  //创建文本节点
					op.appendChild(textNode);			//把文本子节点添加到op元素中，指定其显示值
					
					document.getElementById("c").appendChild(op);
				}
			}
		};	
	};
};
</script>


<body>
	<h1>省市联动</h1>
		
	<select name="province" id="p">
		<option>======请选择省======</option>
	</select>
							
	<select name="city" id="c">
		<option>======请选择市======</option>
	</select>
	
 </body>
