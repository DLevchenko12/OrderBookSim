package org.example.service.impl;

import org.example.model.Order;
import org.example.service.QueryManager;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class QueryManagerImpl implements QueryManager {

    @Override
    public Order findBestBid(List<Order> orderList) {
        Predicate<Order> predicate = order -> order.getType().name().equalsIgnoreCase("bid");
        int bestBid = orderList.stream()
                .filter(predicate)
                .max(Comparator.comparing(Order::getPrice))
                .get()
                .getPrice();

        Order result = null;
        for (Order current : orderList) {
            if (predicate.test(current) && current.getPrice() == bestBid) {
                result = current;
            }
        }
        return result;
    }

    @Override
    public Order findBestAsk(List<Order> orderList) {
        Predicate<Order> predicate = order -> order.getType().name().equalsIgnoreCase("ask");
        int bestAsk = orderList.stream()
                .filter(predicate)
                .min(Comparator.comparing(Order::getPrice))
                .get()
                .getPrice();

        Order result = null;
        for (Order current : orderList) {
            if (predicate.test(current) && current.getPrice() == bestAsk) {
                result = current;
            }
        }
        return result;
    }

    @Override
    public String findCustomBest(List<Order> orderList, int price) {

        int sizeSum = orderList.stream()
                .filter(order -> order.getPrice() == price)
                .mapToInt(Order::getSize)
                .sum();

        return "Size: " + sizeSum + " at price: " + price + " (bid/ask/spread)";
    }
}
