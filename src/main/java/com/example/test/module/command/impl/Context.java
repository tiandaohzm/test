package com.example.test.module.command.impl;

import com.example.test.module.command.Command;
import com.example.test.module.entity.Car;
import com.example.test.module.nodes.Node;
import com.example.test.module.receiver.Receiver;
import com.example.test.module.task.Task;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * @decription 核心线程，负责接受任务并调度相应的责任链来执行任务
 * @date 2019/8/22
 * @author 何志铭
 */
@Component
public class Context implements BeanPostProcessor, Runnable, Command {

    /* 阻塞队列，维护所有提交上来的任务 */
    private static final transient Queue<Car> queue =new PriorityBlockingQueue<>();


     /* 定义mapping映射表并初始化 */
    @Resource(name = "map")
    private Map<String, Node> map;

    /* xml的md5，用于标识xml是否有更改 */
    public static String md5;

    /* 命令执行者 */
    @Autowired
    private Receiver receiver;

    /**
     * @decription 核心线程池，用来执行维护任务
     * @date 2019/8/21
     * @author 何志铭
     */
    private static final ExecutorService ec = Executors.newCachedThreadPool();

    /* 记时线程池，用来查看任务是否超时 */
    private static final ExecutorService timer = Executors.newCachedThreadPool();

    /* 调用具体的责任链来执行任务 */
    private void handle(Car car){
        try {
            //根据不同的车辆状态选择不同的维护策略
            Node startNode = map.get(car.getStatus());
            if(startNode == null){
                startNode = map.get("default");
            }
            Task task = new Task(startNode, car);
            receiver.action(task);
            //Future future = ec.submit(task);
//            timer.submit(()->{
//                while (true) {
//                    try {
//                        Object obj = future.get(20, TimeUnit.SECONDS);
//                        if(obj != null){
//                            break;
//                        }
//                    } catch (Exception e) {
//                        while (future.cancel(true)){
//                            break;
//                        }
//                    }
//                }
//            });
        }catch (Exception e){
            /* todo 处理维护异常
             */
        }
    }

    /* 接收任务 */
    @Override
        public void excute(Car car){
            try {
                queue.add(car);
            }catch (Exception e){
                e.printStackTrace();
                /* todo 处理异常 */
            }
    }

    /* 开启守护线程，处理队列中的任务 */
    @Override
    public void run() {
        while (true){
            if(queue.peek() != null){
                Car car = queue.poll();
                handle(car);
            }
        }
    }
}
