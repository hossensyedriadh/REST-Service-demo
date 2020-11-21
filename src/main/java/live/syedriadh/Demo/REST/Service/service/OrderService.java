package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(int id);
    List<Order> getOrdersByCustomerId(int customerId);
    List<Order> getOrdersByProductId(int productId);
    List<Order> getOrdersPricedWithin(double min, double max);
    List<Order> getAllUnpaidOrders();
    List<Order> getAllPaidOrders();
    List<Order> getOrdersPlacedOn(Date date);
    List<Order> getOrdersPlacedInMonth(int month);
    List<Order> getOrdersPlacedInYear(int year);
    Order addNewOrder(Order order);
    Optional<Order> updateOrder(int id, Order updatedOrder);
    void deleteOrder(int id);
}
