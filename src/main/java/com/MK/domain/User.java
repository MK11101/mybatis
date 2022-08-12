package com.MK.domain;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;

    public List<User> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<User> orderList) {
        this.orderList = orderList;
    }

    private String password;
    private Date birthday;
    private List<User> orderList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", orderList=" + orderList +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
