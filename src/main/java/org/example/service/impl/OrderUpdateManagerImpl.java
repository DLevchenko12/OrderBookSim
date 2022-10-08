package org.example.service.impl;

import org.example.model.Order;
import org.example.service.OrderUpdateManager;

import java.util.List;

public class OrderUpdateManagerImpl implements OrderUpdateManager {

    @Override
    public List<Order> update(List<Order> orderList, final Order order) {
        if (orderList.isEmpty() || !orderList.contains(order)) {
            orderList.add(order);
        } else {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).equals(order)) {
                    orderList.set(i, order);
                }
            }
        }
        return orderList;
    }

}
