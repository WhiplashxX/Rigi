package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * @author HuangQX
 * @create 2023-03-29-21:01
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
