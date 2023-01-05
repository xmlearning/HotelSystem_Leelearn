package com.lee.www.controller.constant;

/**
 * @author ming
 * @date 2023-01-02 11:05
 */
public enum Pages {
    REGIST_JSP,

    INDEX_JSP,

    ERROR_JSP,

    ROOM_JSP,

    LOGIN_JSP,

    USER_JSP,

    ORDER_JSP,

    SUCCESS_JSP,

    REMARK_JSP,

    PICTRUES_JSP;


    /*
    转为jsp页面的那种格式
     */
    @Override
    public String toString(){
        return super.toString().toLowerCase().replace("_",".");
    }
}
