package com.lee.www.controller.servlet;

import com.lee.www.controller.constant.Methods;
import com.lee.www.controller.constant.Pages;
import com.lee.www.service.Result;
import com.lee.www.service.constant.Status;
import com.lee.www.service.inter.RemarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lee.www.util.ControllerUtils.*;

/**
 * @author ming
 * @date 2023-01-04 10:29
 * @description 负责评论的请求转发
 */
@WebServlet("/remark")
public class RemarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Methods method = getMethod(req, resp);

        switch (method) {
            case ADD_DO:
                add(req, resp);
                return;
            case FIND_DO:
                find(req, resp);
                return;
            default:
                redirect(resp, Pages.INDEX_JSP.toString());
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RemarkService serv = (RemarkService) getServletContext().getAttribute("remarkService");
        Result result = serv.add(req, resp);
        Status status = result.getStatus();

        switch (status) {
            case DATA_TOO_MUCH:
                forward(req, resp, result.getData(), "您的留言次数已达上限！如果您是测试所需，请联系作者", Pages.REMARK_JSP);
                return;
            case DATA_ILLEGAL:
                forward(req, resp, result.getData(), "数据不合法！（不可为空）", Pages.REMARK_JSP);
                return;
            case SUCCESS:
                redirect(resp,Pages.REMARK_JSP.toString());
                return;
            case ERROR:
                forward(req, resp, result.getData(), "添加留言失败！", Pages.REMARK_JSP);
                return;
            default:
        }
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RemarkService serv = (RemarkService) getServletContext().getAttribute("remarkService");
        Result result = serv.listAll(req, resp);
        Status status = result.getStatus();

        switch (status) {
            case SUCCESS:
                forward(req, resp, result.getData(), null, Pages.REMARK_JSP);
                return;
            case ERROR:
                forward(req, resp, result.getData(), "查找留言失败！", Pages.REMARK_JSP);
                return;
            default:
        }
    }
}
