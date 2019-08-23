package com.example.test.module.data;

import com.example.test.module.entity.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fake {
    /* 模拟车辆信息 状态 0-正常， 1-轻微损耗， 2-损耗严重 */
    public static List<Car> cars = Arrays.asList(
            new Car(1L,"0"),
            new Car(2L, "1"),
            new Car(3L, "2"),
            new Car(4L, "1"),
            new Car(5L,"1"),
            new Car(6L, "2", 1)
    );
}
