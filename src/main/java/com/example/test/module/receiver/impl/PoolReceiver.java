package com.example.test.module.receiver.impl;

import com.example.test.module.entity.Car;
import com.example.test.module.receiver.Receiver;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @decription 以线程池的方式接受并执行任务
 * @date 2019/8/22
 * @author 何志铭
 */
@Component
public class PoolReceiver implements Receiver {


    /**
     * @decription 核心线程池，用来执行车辆维护任务
     * @date 2019/8/21
     * @author 何志铭
     */
    private static final ExecutorService ec = Executors.newCachedThreadPool();

    @Override
    public void action(Callable callable) {
        ec.submit(callable);
    }
}
