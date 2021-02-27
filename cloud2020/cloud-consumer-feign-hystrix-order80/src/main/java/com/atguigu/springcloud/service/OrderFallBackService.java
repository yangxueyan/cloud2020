package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class OrderFallBackService implements OrderHystrixService{
    @Override
    public String payment_ok(Integer id) {
        return "我是OrderFallBackService，目前调用的是：payment_ok，服务调用繁忙，稍后重试";
    }

    @Override
    public String payment_timeout(Integer id) {
        return "我是OrderFallBackService，目前调用的是：payment_timeout，服务调用繁忙，稍后重试";
    }
}
