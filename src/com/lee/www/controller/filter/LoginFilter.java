package com.lee.www.controller.filter;

import com.lee.www.controller.constant.Methods;
import com.lee.www.controller.constant.Pages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.lee.www.controller.constant.CacheConst.ADMIN;
import static com.lee.www.controller.constant.CacheConst.USER;
import static com.lee.www.util.ControllerUtils.redirect;

/**
 * @author ming
 * @date 2023-01-02 11:26
 */
@WebFilter(
        filterName = "LoginFilter",
        urlPatterns = {"/*"},
        servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING",value = "UTF-8")
        }
)
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String method = req.getParameter("method");
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        if (
                !(("/"+Pages.LOGIN_JSP.toString()).equalsIgnoreCase(path)
                        || ("/"+Pages.REGIST_JSP.toString()).equalsIgnoreCase(path)
                        || (Methods.LOGIN_DO.toString()).equalsIgnoreCase(method)
                        || (Methods.REGIST_DO.toString()).equalsIgnoreCase(method)
                        || uri.endsWith("jpg"))
        ){
            HttpSession session = req.getSession(false);
            if (session == null ){
                // 如果有记住登陆状态的cookie,则给session添加'user'属性
                Cookie[] cookies = req.getCookies();
                if (cookies != null){
                    for (Cookie cookie : cookies) {
                        String name = cookie.getName();
                        if (USER.toString().equalsIgnoreCase(name)){
                            session = req.getSession(true);
                            session.setAttribute(USER.toString(),cookie.getValue());
                        }
                        if (ADMIN.toString().equalsIgnoreCase(name)){
                            session = req.getSession(true);
                            session.setAttribute(ADMIN.toString(),cookie.getValue());
                        }
                    }

                }
            }

            // 检查session是否有'user',没有则重定向到登陆界面
            if (session == null || session.getAttribute(USER.toString()) == null){
                redirect(resp, Pages.LOGIN_JSP.toString());
                return;
            }

        }
        filterChain.doFilter(req,resp);

    }

    @Override
    public void destroy() {

    }
}
