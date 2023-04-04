package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HuangQX
 * @create 2023-03-23-20:05
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}

