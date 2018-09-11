package com.stark.filter;

import com.stark.service.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Configuration
@WebFilter(urlPatterns = "/*")
@Order(2) //越小越前
public class CustomFilter implements Filter {

    @Autowired
    private CustomService service; //测试IOC使用 OK

    private Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CustomFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("CustomFilter.doFilter()");
        service.test();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("CustomFilter.destroy()");
    }
}
