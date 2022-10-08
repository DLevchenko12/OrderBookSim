package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    private final List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "orderList=" + orderList +
                '}';
    }
}
