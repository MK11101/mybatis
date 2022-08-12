package com.MK.Dao;

import com.MK.domain.Order;

import java.util.List;

public interface OrderMapper {
    public List<Order> findOrderAndUser();

}
