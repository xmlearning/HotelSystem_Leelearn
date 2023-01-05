package com.lee.www.po.abs;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author ming
 * @date 2023-01-02 20:53
 */
public abstract class Product extends BaseEntity {
    private String number;
    private String name;
    private String type;
    private BigDecimal price;
    private String level;
    private BigDecimal score;
    private BigInteger remarkNum;
    private String hotelId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigInteger getRemarkNum() {
        return remarkNum;
    }

    public void setRemarkNum(BigInteger remarkNum) {
        this.remarkNum = remarkNum;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
