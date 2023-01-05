package com.lee.www.service;

import com.lee.www.service.constant.Status;
import com.lee.www.vo.PagesVo;

/**
 * @author ming
 * @date 2023-01-03 21:30
 * @description 用于向控制层返回数据和状态
 */
public class Result {
    /**
     * 服务状态枚举常量
     */
    private Status status;
    /**
     * 返回的数据
     */
    private PagesVo data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PagesVo getData() {
        return data;
    }

    public void setData(PagesVo data) {
        this.data = data;
    }

    public Result(Status status, PagesVo data) {
        this.status = status;
        this.data = data;
    }

    public Result(Status status) {
        this.status = status;
    }

    public Result(PagesVo data) {
        this.data = data;
    }

    public Result() {
    }
}
