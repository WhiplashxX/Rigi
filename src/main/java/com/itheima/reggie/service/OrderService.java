package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Orders;
import org.springframework.core.annotation.Order;

/**
 * @author HuangQX
 * @create 2023-04-03-21:09
 */
public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
