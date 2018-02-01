package com.cj.tangtuan.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zsl on 2017/9/3.
 */
@Configuration
public class ConfigurationFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean UserFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter());//添加过滤器
        registration.addUrlPatterns("/user/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("UserFilter");//设置优先级
        registration.setOrder(Integer.MAX_VALUE);//设置优先级
        return registration;
    }

    @Bean
    public FilterRegistrationBean AdminFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter());//添加过滤器
        registration.addUrlPatterns("/admin/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("AdminFilter");//设置优先级
        registration.setOrder(Integer.MAX_VALUE-1);//设置优先级
        return registration;
    }





    public class UserFilter implements Filter {
        @Override
        public void destroy() {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//            HttpServletRequest request = (HttpServletRequest) srequest;
//            //打印请求Url
//            System.out.println("this is MyFilter,url :" + request.getRequestURI());
//            filterChain.doFilter(srequest, sresponse);

            // 将请求转换成HttpServletRequest 请求
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            String path = req.getContextPath();
            String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
            HttpSession session = req.getSession(true);

            String username = (String) session.getAttribute("username");
            if (username == null || "".equals(username)) {
                resp.setHeader("Cache-Control", "no-store");
                resp.setDateHeader("Expires", 0);
                resp.setHeader("Prama", "no-cache");
                //此处设置了访问静态资源类
                resp.sendRedirect(basePath+"/index.html");
            } else {
                // Filter 只是链式处理，请求依然转发到目的地址。
                filterChain.doFilter(req, resp);
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }
    }

    public class AdminFilter implements Filter{

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        }

        @Override
        public void destroy() {

        }
    }
}