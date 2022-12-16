package com.ls.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author lingsheng
 * @description
 * @date 2022/11/12 18:52
 **/
@WebFilter(filterName = "ValiteFilter",urlPatterns = "/order/takeOrder")
public class ValiteFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String s = servletRequest.getAttributeNames().toString();
        System.out.println(s);
    }
}
