package org.example.command;

import org.example.model.Order;
import org.example.model.OrderBook;
import org.example.model.OrderType;
import org.example.service.impl.OrderOperationManagerImpl;
import org.example.service.impl.OrderUpdateManagerImpl;
import org.example.service.impl.QueryManagerImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final OrderUpdateManagerImpl orderUpdateManager;
    private final QueryManagerImpl queryManager;
    private final OrderOperationManagerImpl orderOperationManager;
    private final List<Order> orderList;

    public CommandManager(List<String> commands) {
        orderUpdateManager = new OrderUpdateManagerImpl();
        queryManager = new QueryManagerImpl();
        orderOperationManager = new OrderOperationManagerImpl();
        OrderBook orderBook = new OrderBook();
        orderList = new ArrayList<>(orderBook.getOrderList());
        redirect(commands);
    }

    private void redirect(List<String> commands) {
        String[] command;
        for (String s : commands) {
            command = s.split(",");
            switch (command[0]) {
                case "u":
                    makeUpdate(command);
                    break;
                case "q":
                    makeQuery(command);
                    break;
                case "o":
                    makeOrder(command);
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
        }
        makeOutput(orderList);
    }

    private void makeUpdate(String[] command) {
        int price = Integer.parseInt(command[1]);
        int size = Integer.parseInt(command[2]);
        OrderType type = size == 0 ? OrderType.SPREAD : OrderType.valueOf(command[3].toUpperCase());
        orderUpdateManager.update(orderList, new Order(price, size, type));
    }

    private void makeQuery(String[] command) {
        switch (command[1]) {
            case "best_bid":
                queryManager.findBestBid(orderList);
                break;
            case "best_ask":
                queryManager.findBestAsk(orderList);
                break;
            case "size":
                queryManager.findCustomBest(orderList, Integer.parseInt(command[2]));
                break;
        }
    }

    private void makeOrder(String[] command) {
        switch (command[1]) {
            case "buy":
                orderOperationManager.buy(orderList, Integer.parseInt(command[2]));
                break;
            case "sell":
                orderOperationManager.sell(orderList, Integer.parseInt(command[2]));
                break;
        }
    }

    public void makeOutput(List<Order> orderList) {
        StringBuilder sb = new StringBuilder();

        for (Order order : orderList) {
            if (order.getSize() != 0 || !order.getType().name().equals("SPREAD")) {
                sb.append(order.getPrice())
                        .append(",")
                        .append(order.getSize())
                        .append(System.getProperty("line.separator"));

            }
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
