package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = OrderFallBackService.class)
public interface OrderHystrixService {
    @GetMapping("/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id);


    @GetMapping("/hystrix/payment/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id);
}
