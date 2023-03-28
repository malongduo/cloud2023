package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.omg.CORBA.TIMEOUT;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {
    public String payment_ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"payment_ok"+id;
    }
    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name="exection.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String payment_timeout(Integer id){
        int timenumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timenumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池"+Thread.currentThread().getName()+"payment_timeout"+id;
    }
    public String payment_timeoutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"payment_timeoutHandler"+id;
    }
}
