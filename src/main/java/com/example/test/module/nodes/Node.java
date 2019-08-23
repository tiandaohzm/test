package com.example.test.module.nodes;

import com.example.test.module.entity.Car;

/**
 * @decription 责任链节点
 * @date 2019/8/22
 * @author 何志铭
 */
public interface Node {

    /* 处理任务 */
    void handle(Car car) throws Exception;

    /* 设置下一个节点 */
    void setNext(Node next);
    /* 获取下一节点 */
    Node getNext();
}
