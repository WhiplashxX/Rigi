package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

/**
 * @author HuangQX
 * @create 2023-04-03-21:09
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
