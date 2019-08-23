package com.example.test.module.entity;

import lombok.Data;

@Data
public class Car implements Comparable<Car>{
    private Long id;
    private String status;
    private int priority = 0; //默认最低优先级

    public Car(){

    }

    public Car(Long id, String status){
        this.id = id;
        this.status = status;
    }

    public Car(Long id, String status, int priority){
        this.id = id;
        this.status = status;
        this.priority = priority;
    }

    @Override
    public int compareTo(Car o) {
        return this.priority - o.getPriority();
    }
}
