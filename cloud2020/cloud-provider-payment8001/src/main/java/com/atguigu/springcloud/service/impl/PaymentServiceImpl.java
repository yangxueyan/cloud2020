package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;
    public int create(Payment payment){
        int result=paymentDao.create(payment);
        return result;

    }

    public Payment getPaymentById(Long id){
        Payment payment=paymentDao.getPaymentById(id);
        return payment;
    }
}
