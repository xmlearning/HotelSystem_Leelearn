package com.lee.www.controller.filter;

import com.lee.www.controller.constant.Pages;
import com.lee.www.exception.ServiceException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ming
 * @date 2023-01-02 11:24
 */
@WebFilter(
        filterName = "EncodingFilter",
        urlPatterns = {"/*"},
        servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING",value = "UTF-8")
        }
)
public class EncodingFilter implements Filter {

    private String ENCODING = null;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.ENCODING = filterConfig.getInitParameter("ENCODING");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding(ENCODING);

        try {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding(ENCODING);
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (IOException | ServiceException e){
            resp.sendRedirect(Pages.ERROR_JSP.toString());
        }

        System.out.println("====================================");
        System.out.println("编码过滤器：" + "method = " + req.getParameter("method") + " view = " + req.getParameter("view") + " find = " + req.getParameter("find") + " name = " + req.getParameter("name"));
        System.out.println("用户名：" + req.getParameter("name"));
        System.out.println("电话号码"+req.getParameter("phoneNumber"));
        System.out.println("昵称"+req.getParameter("nickName"));
        System.out.println("请求链接：" + req.getQueryString());


    }

    @Override
    public void destroy() {

    }
}
