package com.example.test.module.task;

import com.example.test.module.entity.Car;
import com.example.test.module.nodes.Node;

import java.util.concurrent.Callable;

/**
 * @decription 具体任务，一个任务对应一个车辆的维护
 * @date 2019/8/22
 * @author 何志铭
 */
public class Task implements Callable {

    private Node node;
    private Car car;

    public Task(Node node, Car car){
        this.node = node;
        this.car = car;
    }

    @Override
    public Object call() throws Exception {
        try {
            node.handle(car);
        } catch (Exception e) {
            /* todo 捕获流程中抛出的异常并做相应处理 */
        }
        return "任务完成";
    }

}
