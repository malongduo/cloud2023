package com.atguigu.springcloud.service;

import org.omg.CORBA.TIMEOUT;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PyamentHystrixService {

    public String payment_Ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"payment_Ok"+id;
    }

    public String payment_TimeOut(Integer id){
        int number = 3;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池"+Thread.currentThread().getName()+"payment_TimeOut"+id;
    }
}
