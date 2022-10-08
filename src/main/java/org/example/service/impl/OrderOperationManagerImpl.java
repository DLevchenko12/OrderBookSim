package org.example.service.impl;

import org.example.model.Order;
import org.example.service.OrderOperationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class OrderOperationManagerImpl implements OrderOperationManager {

    @Override
    public List<Order> buy(List<Order> orderList, int size) {
        Predicate<Order> predicate = order -> order.getType().name().equalsIgnoreCase("ask");

        int findMin = orderList.stream()
                .filter(predicate)
                .mapToInt(Order::getPrice)
                .min()
                .orElseThrow(NoSuchElementException::new);

        return getOrderList(orderList, size, predicate, findMin);
    }

    @Override
    public List<Order> sell(List<Order> orderList, int size) {
        Predicate<Order> predicate = order -> order.getType().name().equalsIgnoreCase("bid");

        int findMax = orderList.stream()
                .filter(predicate)
                .mapToInt(Order::getPrice)
                .max()
                .orElseThrow(NoSuchElementException::new);

        return getOrderList(orderList, size, predicate, findMax);
    }

    private List<Order> getOrderList(List<Order> orderList, int size, Predicate<Order> predicate, int find) {
        Predicate<Order> orderPredicate = order -> predicate.test(order) && order.getPrice() == find;
        orderList.removeIf(order -> orderPredicate.test(order) && order.getSize() <= size);
        for (Order order : orderList) {
            if (orderPredicate.test(order) && order.getSize() > size) {
                order.setSize(order.getSize() - size);

            }
        }
        return orderList;
    }
}

