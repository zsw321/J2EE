#### 通过Get方式获取XML内容，以下为script ####

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
		xmlHttp.open("GET", "<c:url value='/xmlAjax'/>", true);
		/*
		3. 发送请求
		*/
		xmlHttp.send(null);//GET请求没有请求体，但也要给出null，不然FireFox可能会不能发送！
		/*
		4. 给异步对象的onreadystatechange事件注册监听器
		*/
		xmlHttp.onreadystatechange = function() {//当xmlHttp的状态发生变化时执行
			// 双重判断：xmlHttp的状态为4（服务器响应结束），以及服务器响应的状态码为200（响应成功）
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				// 获取服务器的响应结束
				var doc = xmlHttp.responseXML;
				
				//查询文档下student的所有元素，取下表0元素
				var ele = doc.getElementsByTagName("student")[0];
				var number = ele.getAttribute("number");
				var name;			
				var age;
				var male;
				
				if(window.addEventListener)    //其他浏览器
				{
					name = ele.getElementsByTagName("name")[0].textContent;
					age = ele.getElementsByTagName("age")[0].textContent;		
					male = ele.getElementsByTagName("male")[0].textContent;
				} else {                      //IE支持
					name = ele.getElementsByTagName("name")[0].text;
					age = ele.getElementsByTagName("age")[0].text;		
					male = ele.getElementsByTagName("male")[0].text;
				}
				
					
				// 获取h1元素
				var h1 = document.getElementById("h1");
				h1.innerHTML = number + "," + name + "," + age + ", " + male;
			}
		};
	};
};
</script>
