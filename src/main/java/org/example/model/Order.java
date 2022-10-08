package org.example.model;

public class Order {
    private int price;
    private int size;
    private OrderType type;

    public Order(int price, int size, OrderType type) {
        this.price = price;
        this.size = size;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public OrderType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (price != order.price) return false;
        if (size != order.size) return false;
        return type == order.type;
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + size;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
