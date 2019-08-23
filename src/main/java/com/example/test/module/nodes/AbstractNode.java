package com.example.test.module.nodes;

import com.example.test.module.entity.Car;
import lombok.Data;

@Data
public abstract class AbstractNode implements Node{
    protected Node next;
    ;


    @Override
    public abstract void handle(Car car) throws Exception;

    @Override
    public Node getNext() {
        return next;
    }


}
