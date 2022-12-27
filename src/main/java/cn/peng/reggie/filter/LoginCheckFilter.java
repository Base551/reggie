package cn.peng.reggie.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 名称:LoginCheckFilter
 * 描述:
 *
 * @author:Secret Base
 * @datetime:2022-12-23 10:08:19
 * @version:1.0
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取本次请求的URI
        //判断本次请求是否需要处理
        //如果不需要处理则直接放行
        //判断登录状态，如果已登录则直接放行
        //如果未登录则返回未登录结果

        log.info("拦截到请求: {}",request.getRequestURI() );
        filterChain.doFilter(request,response);
    }
}
