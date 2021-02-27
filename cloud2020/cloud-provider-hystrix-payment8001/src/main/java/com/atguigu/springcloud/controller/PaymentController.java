package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @GetMapping("/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String result=paymentService.payment_ok(id);
        return result;
    }

    @GetMapping("/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String result=paymentService.payment_timeout(id);
        return result;
    }
    @GetMapping("/hystrix/payment/break/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        return result;
    }

}
