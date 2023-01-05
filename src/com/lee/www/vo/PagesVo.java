package com.lee.www.vo;

import com.lee.www.po.*;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 21:31
 * @description 负责向前端传输首页页面数据
 */
public class PagesVo {

    private LinkedList<Room> rooms;
    private LinkedList<Hotel> hotels;
    private LinkedList<User> users;
    private LinkedList<OrderRoom> orderRooms;
    private LinkedList<Picture> pictures;
    private LinkedList<Remark> remarks;
    private String message;
    private int maxPage;
    private int count;

    public LinkedList<Remark> getRemarks() {
        return remarks;
    }

    public void setRemarks(LinkedList<Remark> remarks) {
        this.remarks = remarks;
    }

    public LinkedList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(LinkedList<Room> rooms) {
        this.rooms = rooms;
    }

    public LinkedList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(LinkedList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(LinkedList<Picture> pictures) {
        this.pictures = pictures;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public LinkedList<OrderRoom> getOrderRooms() {
        return orderRooms;
    }

    public void setOrderRooms(LinkedList<OrderRoom> orderRooms) {
        this.orderRooms = orderRooms;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
