package com.lee.www.po;

import com.lee.www.annotation.TableName;
import com.lee.www.po.abs.BaseEntity;

import java.math.BigDecimal;

/**
 * @author ming
 * @date 2023-01-02 21:11
 * @desription 对应数据库中的用户表，用来存储用户信息
 */
@TableName(value = "t_user")
public class User extends BaseEntity implements Cloneable {
    private String name;
    private String password;
    private String phoneNumber;
    private String idNumber;
    private String type;
    private String nickName;
    private String photo;
    private BigDecimal balance;
    private String payPwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    @Override
    public User clone(){
        try {
            return (User) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            throw new RuntimeException("无法克隆User对象",e);
        }
    }

}
