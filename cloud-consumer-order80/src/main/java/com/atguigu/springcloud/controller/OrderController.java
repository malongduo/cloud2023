package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        log.info("********插入数据，结果为",commonResult);
        return commonResult;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        CommonResult commonResult = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        log.info("*******查询数据，结果为",commonResult);
        return commonResult;
    }
}
