/*发起Ajax请求的简单jsp页面，包括Get和Post请求*/

/××××××JavaScript部分×××××××/
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

window.onload = function() {//文档加载完毕后执行
	var btn = document.getElementById("btn");
	btn.onclick = function() {//给按钮的点击事件注册监听
		/*
		ajax四步操作，得到服务器的响应
		把响应结果显示到h1元素中
		*/
		/*
		1. 得到异步对象 
		*/
		var xmlHttp = createXMLHttpRequest();
		/*
		2. 打开与服务器的连接
		  * 指定请求方式
		  * 指定请求的URL
		  * 指定是否为异步请求
		*/
		//xmlHttp.open("GET", "<c:url value='/helloAjax'/>", true);       //GET请求
		
		/×××××××Post请求×××××××/
		xmlHttp.open("POST", "<c:url value='/helloAjax'/>", true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		/×××××××××××××××××××××/
		
		
		3. 发送请求
		*/
		//xmlHttp.send(null);           //GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
		xmlHttp.send("username=张寿文"); //Post请求
		/*
		4. 给异步对象的onreadystatechange事件注册监听器
		*/
		xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取服务器的响应结束
				
				var text = xmlHttp.responseText;
				// 获取h1元素
				var h1 = document.getElementById("h1");
				h1.innerHTML = text;
			}
		};
	};
};
</script>

/××××××××××××××××××××××××××××××××××××××××××××××

/×××××××××××HTML主体部分×××××××××××××××××××××××/
  <body>
		<button id="btn">
			点击这里
		</button>
		<h1 id="h1"></h1>
	</body>
/××××××××××××××××××××××××××××××××××××××××××××××
