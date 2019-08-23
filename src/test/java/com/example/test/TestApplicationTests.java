package com.example.test;

import com.example.test.module.command.impl.Context;
import com.example.test.module.data.Fake;
import com.example.test.module.entity.Car;
import com.example.test.module.nodes.Node;
import com.example.test.module.nodes.impl.Check;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	Context context;

	@Autowired
	Check check;
	@Test
	public void main() throws Exception{
		//开启核心线程用于处理任务
		Thread thread = new Thread(context);
		thread.start();

		//模拟车辆提交维护
		List<Car> carList = Fake.cars;
		for(Car car : carList) {
			if(!"0".equals(car.getStatus())){ //如果需要维护
				context.excute(car);
			}
		}

		//防止用户进程结束而导致jvm挂掉
		thread.join();
	}

	@Test
	public void test(){
		String a = "55";
//		InputStream inputStream = Context.class.getClassLoader().getResourceAsStream("mapping2.xml");
//		XStream xStream = new XStream(new DomDriver());
//		Map<String, List<String>> map = (Map)xStream.fromXML(inputStream);
//		Map<String, Node> result = new HashMap<>();
//		for(Map.Entry<String, List<String>> entry : map.entrySet()){
//			Node head = null;
//			Node pre = null;
//			int i = 0;
//			for(String beanName : entry.getValue()){
//				if(i == 0) {
//					head = pre = (Node)beanFactory.getBean(beanName);
//				}else {
//					pre.setNext((Node)beanFactory.getBean(beanName));
//					pre = pre.getNext();
//				}
//				i++;
//			}
//			result.put(entry.getKey(), head);
//		}
	}

}
