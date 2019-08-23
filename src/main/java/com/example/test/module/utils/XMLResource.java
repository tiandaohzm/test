package com.example.test.module.utils;

import com.example.test.module.command.impl.Context;
import com.example.test.module.nodes.Node;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("map")
@Scope("prototype")
public class XMLResource implements BeanFactoryAware, FactoryBean<Map<String, Node>> {
    private static BeanFactory beanFactory;
    /* xml工具类 */
    private static final XStream xStream = new XStream(new DomDriver());

    @Override
    public Map<String, Node> getObject() throws Exception {
        InputStream inputStream = Context.class.getClassLoader().getResourceAsStream("mapping.xml");
        Map<String, List<String>> map = (Map)xStream.fromXML(inputStream);
        Map<String, Node> result = new HashMap<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            Node head = null;
            Node pre = null;
            int i = 0;
            for(String beanName : entry.getValue()){
                if(i == 0) {
                    head = pre = (Node)beanFactory.getBean(beanName);
                }else {
                    pre.setNext((Node)beanFactory.getBean(beanName));
                    pre = pre.getNext();
                }
                i++;
            }
            result.put(entry.getKey(), head);
        }
        return result;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


}
