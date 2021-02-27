package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "global_fallback")
public class OrderHystrixController {

    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("/consumer/hystrix/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String result=orderHystrixService.payment_ok(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/payment/timeout/{id}")
//    @HystrixCommand(fallbackMethod ="payment_timeoutHandel",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
//    })
    public String payment_timeout(@PathVariable("id") Integer id){
        //int age=10/0;
        String result=orderHystrixService.payment_timeout(id);
        return result;
    }

    public String payment_timeoutHandel(Integer id){
        return "我是order，调用payment超时或运行错误，稍后重试。";
    }

    /**
     * 全局降级方法
     * @param id
     * @return
     */
    // @HystrixCommand
    @GetMapping("/consumer/hystrix/payment/global/{id}")
    public String global_payment_timeout(@PathVariable("id") Integer id){
        //int age=10/0;
        String result=orderHystrixService.payment_timeout(id);
        return result;
    }

    //global fallback--------不能加参数
   public String global_fallback(){
        return "我是payment--全局通用降级方法，系统繁忙或运行错误，请稍后重试。";
    }
}
