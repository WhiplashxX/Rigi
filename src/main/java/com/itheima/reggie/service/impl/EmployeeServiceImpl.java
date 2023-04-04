package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author HuangQX
 * @create 2023-03-23-20:07
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>  implements EmployeeService{

}
