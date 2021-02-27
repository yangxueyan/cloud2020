package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.FeignPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignPaymentController {

    @Resource
    private FeignPaymentService feignPaymentService;

    @GetMapping(value = "/feign/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return feignPaymentService.getPaymentById(id);
    }

    @GetMapping(value = "/feign/payment/timeout")
    public String PaymentTimeOut(){
        return feignPaymentService.PaymentTimeOut();
    }
}
