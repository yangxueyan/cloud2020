package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class PaymentService {

    public String payment_ok(Integer id){
        String thread_name=Thread.currentThread().getName();
        return "我是payment，访问成功：当前线程池是："+thread_name+"。传入的id为："+id;
    }

    //服务降级
    @HystrixCommand(fallbackMethod ="payment_timeoutHandel",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String payment_timeout(Integer id){
        String thread_name=Thread.currentThread().getName();
        //int age=10/0;
        int timeout=3;
        try{
            TimeUnit.SECONDS.sleep(timeout);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "我是payment，访问超时：当前线程池是："+thread_name+"。传入的id为："+id;
    }

    public String payment_timeoutHandel(Integer id){
        return "我是payment，系统繁忙或运行错误，请稍后重试。";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("------输入参数不能为负数");
        }
        String random_id= IdUtil.simpleUUID();//等价于UUID.randomUUID().toString()
        return "我是8001断路器，输入参数正确："+id+"，当前线程池是："+Thread.currentThread().getName();
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "我是payment断路器，id输入不能为负数。";
    }
}
