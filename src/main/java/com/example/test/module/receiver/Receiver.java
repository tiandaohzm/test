package com.example.test.module.receiver;

import java.util.concurrent.Callable;

/**
 * @decription 命令的接收者，负责执行命令
 * @date 2019/8/22
 * @author 何志铭
 */
public interface Receiver {

    /* 执行具体任务 */
    void action(Callable callable);
}
