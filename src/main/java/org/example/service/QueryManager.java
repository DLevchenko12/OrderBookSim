package org.example.service;

import org.example.model.Order;

import java.util.List;

public interface QueryManager {
    Order findBestBid(List<Order> orderList);

    Order findBestAsk(List<Order> orderList);

    String findCustomBest(List<Order> orderList, int price);

}
