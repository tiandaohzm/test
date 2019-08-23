package com.example.test.module.retry;

import com.example.test.module.entity.Car;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.Timer;
import java.util.TimerTask;

@Data
public abstract class RetryTemplate {

    private static final int DEFAULT_RETRY_TIME = 1;

    private int retryTime = DEFAULT_RETRY_TIME;

    private int maxTime; //最长可执行时间

    // 重试的睡眠时间
    private int sleepTime = 0;

    public int getSleepTime() {
        return sleepTime;
    }

    public RetryTemplate setSleepTime(int sleepTime) {
        if(sleepTime < 0) {
            throw new IllegalArgumentException("sleepTime should equal or bigger than 0");
        }

        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public RetryTemplate setRetryTime(int retryTime) {
        if (retryTime <= 0) {
            throw new IllegalArgumentException("retryTime should bigger than 0");
        }

        this.retryTime = retryTime;
        return this;
    }

    /**
     * 重试的业务执行代码
     * 失败时请抛出一个异常
     *
     * todo 确定返回的封装类，根据返回结果的状态来判定是否需要重试
     *
     * @return
     */
    protected abstract void doBiz() throws Exception;


    public void execute() throws Exception {
        for (int i = 0; i < retryTime; i++) {
            try {
                doBiz();
                break;
            } catch (Exception e) {
                System.out.println("————重试————");
                if(i ==  retryTime){ //已经是最后一次重试
                    throw e;
                }
            }
        }
    }



}