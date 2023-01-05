package com.lee.www.po;

import com.lee.www.po.abs.BaseEntity;

/**
 * @author ming
 * @date 2023-01-02 21:07
 * @description 图片
 */
public class Picture extends BaseEntity {
    private String authorId;
    private String picture;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
