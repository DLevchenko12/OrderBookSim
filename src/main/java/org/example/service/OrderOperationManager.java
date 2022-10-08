package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface OrderOperationManager {
    List<Order> buy(List<Order> orderList, int size);

    List<Order> sell(List<Order> orderList, int size);
}
