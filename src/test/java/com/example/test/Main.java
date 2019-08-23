package com.example.test;

import org.junit.Test;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Executor;

public class Main {
    static final Executor ec = new SimpleAsyncTaskExecutor();
    static int i = 0;
    @Test
    public void test() throws Exception{

    }
}
