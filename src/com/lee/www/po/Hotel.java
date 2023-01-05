package com.lee.www.po;

import com.lee.www.po.abs.BaseEntity;

import java.math.BigDecimal;

/**
 * @author ming
 * @date 2023-01-02 20:57
 * @description 酒店实体类
 */
public class Hotel extends BaseEntity {
    private String number;
    private String name;
    private String site;
    private String type;
    private BigDecimal area;
    private BigDecimal star;
    private BigDecimal score;
    private String introduction;
    private String photo;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getStar() {
        return star;
    }

    public void setStar(BigDecimal star) {
        this.star = star;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
