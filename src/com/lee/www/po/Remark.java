package com.lee.www.po;

import com.lee.www.po.abs.BaseEntity;

/**
 * @author ming
 * @date 2023-01-02 21:08
 * @description 评论的实体类
 */
public class Remark extends BaseEntity {
    private String userName;
    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
