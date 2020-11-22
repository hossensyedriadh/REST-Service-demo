package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Customer;
import live.syedriadh.Demo.REST.Service.entity.Order;
import live.syedriadh.Demo.REST.Service.entity.Product;
import live.syedriadh.Demo.REST.Service.repository.CustomerJpaRepository;
import live.syedriadh.Demo.REST.Service.repository.OrderJpaRepository;
import live.syedriadh.Demo.REST.Service.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderJpaRepository orderJpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    @Autowired
    public OrderServiceImpl(OrderJpaRepository orderJpaRepository, CustomerJpaRepository customerJpaRepository, ProductJpaRepository productJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getId)).collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderJpaRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing((order) -> order.getCustomer().getId())).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getCustomer().getId() == customerId) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public List<Order> getOrdersByProductId(int productId) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing((order) -> order.getProduct().getId())).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getProduct().getId() == productId) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public List<Order> getOrdersPricedWithin(double min, double max) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getId)).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (min < max) {
                if (order.getTotalPayable() >= min && order.getTotalPayable() <= max) {
                    matchedOrders.add(order);
                }
            } else if (min == max) {
                if (order.getTotalPayable() == max) {
                    matchedOrders.add(order);
                }
            } else if (min > max) {
                if (order.getTotalPayable() >= max && order.getTotalPayable() <= min) {
                    matchedOrders.add(order);
                }
            }
        }

        return matchedOrders;
    }

    @Override
    public List<Order> getAllOrdersByPaid(Boolean paid) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getId)).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getPaid() == paid) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @SuppressWarnings("all")
    @Override
    public List<Order> getOrdersPlacedOn(Date date) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getOrderPlacedOn)).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getOrderPlacedOn().getDayOfMonth() == date.getDay()
                    && order.getOrderPlacedOn().getMonthValue() == date.getMonth() &&
                    order.getOrderPlacedOn().getYear() == date.getYear()) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public List<Order> getOrdersPlacedInMonth(int month) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getOrderPlacedOn)).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getOrderPlacedOn().getMonthValue() == month) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public List<Order> getOrdersPlacedInYear(int year) {
        List<Order> orderList = orderJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Order::getOrderPlacedOn)).collect(Collectors.toList());

        List<Order> matchedOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getOrderPlacedOn().getYear() == year) {
                matchedOrders.add(order);
            }
        }

        return matchedOrders;
    }

    @Override
    public Order addNewOrder(Order order) {
        long epoch = Long.parseLong(String.valueOf(System.currentTimeMillis() / 1000));
        Instant instant = Instant.ofEpochSecond(epoch);
        Timestamp timestamp = Timestamp.from(instant);

        if (customerJpaRepository.existsById(order.getCustomer().getId()) &&
                productJpaRepository.existsById(order.getProduct().getId())) {

            Customer matchedCustomer = customerJpaRepository.getOne(order.getCustomer().getId());
            Product matchedProduct = productJpaRepository.getOne(order.getProduct().getId());

            if (order.getQuantity() <= matchedProduct.getStock() && order.getQuantity() > 0) {
                order.setOrderPlacedOn(timestamp.toLocalDateTime());
                order.setTotalPayable((double) order.getQuantity() * matchedProduct.getPricePerUnit());

                int stock = matchedProduct.getStock();
                matchedProduct.setStock(stock - order.getQuantity());
                productJpaRepository.saveAndFlush(matchedProduct);

                order.setProduct(matchedProduct);
                order.setCustomer(matchedCustomer);
            }
        }

        return orderJpaRepository.saveAndFlush(order);
    }

    @Override
    public Optional<Order> updateOrder(int id, Order updatedOrder) {
        Order matchedOrder = new Order();

        if (orderJpaRepository.existsById(id)) {
            matchedOrder = orderJpaRepository.getOne(id);

            if (updatedOrder.getQuantity() != null && updatedOrder.getQuantity() > 0) {
                int previousQuantity = matchedOrder.getQuantity();
                int updatedQuantity = updatedOrder.getQuantity();
                int quantityChanged = updatedQuantity - previousQuantity;

                matchedOrder.setQuantity(updatedOrder.getQuantity());
                matchedOrder.setTotalPayable(matchedOrder.getQuantity() * matchedOrder.getProduct().getPricePerUnit());

                Product product = productJpaRepository.getOne(matchedOrder.getProduct().getId());

                product.setStock(product.getStock() - quantityChanged);
                productJpaRepository.saveAndFlush(product);
            }

            if (updatedOrder.getPaid() != null) {
                matchedOrder.setPaid(updatedOrder.getPaid());
            }

            orderJpaRepository.saveAndFlush(matchedOrder);
        }

        return Optional.of(matchedOrder);
    }

    @Override
    public void deleteOrder(int id) {
        if (orderJpaRepository.existsById(id)) {
            orderJpaRepository.deleteById(id);
        }
    }
}
