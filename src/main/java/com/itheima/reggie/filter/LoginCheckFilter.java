package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebFault;
import java.io.IOException;

/**
 * @author HuangQX
 * @create 2023-03-24-15:53
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        String[] Urls = new String[]{//定义不需要被处理的路径
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**",
            "/common/**",
            "/user/sendMsg",
            "/user/login"
        };
        //2、判断本次请求是否需要处理
        boolean check = check(Urls,requestURI);
        //3、如果不需要处理，则直接放行
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        //4-1、判断登灵状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee")!=null){
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4-2、判断登灵状态，如果已登录，则直接放行——移动端
        if(request.getSession().getAttribute("user")!=null){
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }
        //5、如果未登录则返回未登录结果,通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }
    public boolean check(String[] Urls, String requestURI){
        for (String url:Urls){
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if(match)return true;
        }
        return false;
    }
}
