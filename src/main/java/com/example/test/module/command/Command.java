package com.example.test.module.command;

import com.example.test.module.entity.Car;

/**
 * @decription 命令模式，充当中介
 * @date 2019/8/22
 * @author 何志铭
 */
public interface Command {

    void excute(Car car);
}
