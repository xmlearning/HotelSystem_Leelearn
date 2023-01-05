package com.lee.www.controller.listener;

import com.lee.www.service.inter.OrderRoomService;
import com.lee.www.service.inter.RemarkService;
import com.lee.www.service.inter.RoomService;
import com.lee.www.service.inter.UserService;
import com.lee.www.util.BeanFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author ming
 * @date 2023-01-04 10:27
 * @description 负责监听servlet的初始化和销毁事件
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /**
         * 注册服务单例
         */
        ServletContext sc = sce.getServletContext();
        UserService userService = (UserService) BeanFactory.getBean(BeanFactory.ServiceType.UserService);
        sc.setAttribute("userService",userService);
        RoomService roomService = (RoomService) BeanFactory.getBean(BeanFactory.ServiceType.RoomService);
        sc.setAttribute("roomService",roomService);
        OrderRoomService orderRoomService = (OrderRoomService)BeanFactory.getBean(BeanFactory.ServiceType.OrderRoomService);
        sc.setAttribute("orderRoomService",orderRoomService);
        RemarkService remarkService = (RemarkService) BeanFactory.getBean((BeanFactory.ServiceType.RemarkService));
        sc.setAttribute("remarkService",remarkService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
