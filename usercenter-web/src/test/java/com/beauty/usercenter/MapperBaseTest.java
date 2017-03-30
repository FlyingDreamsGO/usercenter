package com.beauty.usercenter;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * 类描述：junit测试基础类，引入Spring配置文件，以便注入。
 * 
 * @author: zouwei@pajk.cn
 * @version: 2014年5月6日 下午5:30:00
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
})
public class MapperBaseTest {
	//
	//@Resource
	//这里注入mapper

	@org.junit.Test
	public void test() {

	}

	public void printResult(Object result, String method) {
		String json = JSON.toJSONString(result);
		System.err.println("**************************   " + method + " start");
		System.err.println(json);
		System.err.println("**************************   " + method + " end\n\n");
	}

}
