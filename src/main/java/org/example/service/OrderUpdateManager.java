package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface OrderUpdateManager {

    List<Order> update(List<Order> orderList, Order order);
}
