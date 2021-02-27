package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            log.info("查询成功");
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);

        }else{
            log.info("-----查询失败----");
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,"插入成功，成功插入条数：",result);
        }else{
            return new CommonResult(500,"插入失败");
        }
    }
    @GetMapping(value = "/payment/timeout")
    public String PaymentTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  serverPort;
    }
}
