package com.itheima.reggie.common;

/**
 * @author HuangQX
 * @create 2023-03-30-9:39
 * 自定义业务异常
 */
public class CustonException extends RuntimeException{
    public CustonException(String msg){
        super(msg);
    }
}
