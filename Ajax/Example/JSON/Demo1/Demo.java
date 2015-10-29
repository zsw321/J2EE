/*
* 利用JSONObject、JSONArray 操作 JSON
*/

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 演示JSON-LIB小工具　
 *
 */
public class Demo {
	//当Map使用
	@Test
	public void fun1(){
		JSONObject map = new JSONObject();
		map.put("name", "zsw321");
		map.put("age", 32);
		map.put("sex", "male");
		
		String s = map.toString();
		System.out.print(s);          //  {"name":"zsw321","age":32,"sex":"male"}
		
	}
	
	/*
	 * 当你已经有一个Person对象时，可以把Person转换成JSONObject对象
	 */
	@Test
	public void fun2(){
		Person p = new Person("zsw", 25, "male");
		
		// 把对象转换成JSONObject类型
		JSONObject map = JSONObject.fromObject(p);
		System.out.print(map.toString());           // {"age":25,"name":"zsw","sex":"male"}
	}
	
	/**
	 * JSONArray
	 */
	@Test
	public void fun3(){
		Person p1 = new Person("zhangs", 22, "male");
		Person p2 = new Person("lisi", 24, "female");
		
		JSONArray list = new JSONArray();
		list.add(p1);
		list.add(p2);
		
		System.out.print(list.toString());    // [{"age":22,"name":"zhangs","sex":"male"},{"age":24,"name":"lisi","sex":"female"}]  
	}
	
	/**
	 * 原来就有一个List，我们需要把List转换成JSONArray
	 */
	@Test
	public void fun4(){
		Person p1 = new Person("zhangs", 22, "male");
		Person p2 = new Person("lisi", 24, "female");
		Person p3 = new Person("wangw", 23, "male");
		
		List<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		System.out.print(JSONArray.fromObject(list).toString());
		
		// [{"age":22,"name":"zhangs","sex":"male"},{"age":24,"name":"lisi","sex":"female"},{"age":23,"name":"wangw","sex":"male"}]
	}
	
	
}
