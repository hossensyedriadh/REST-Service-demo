package live.syedriadh.Demo.REST.Service.controller;

import live.syedriadh.Demo.REST.Service.entity.Order;
import live.syedriadh.Demo.REST.Service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/orders", headers = {"Accept=application/json,application/xml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/by-order-id")
    @ResponseBody
    public Optional<Order> getOrderById(@RequestParam("order-id") int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/by-customer-id")
    @ResponseBody
    public List<Order> getOrdersByCustomerId(@RequestParam("customer-id") int customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/by-product-id")
    @ResponseBody
    public List<Order> getOrdersByProductId(@RequestParam("product-id") int productId) {
        return orderService.getOrdersByProductId(productId);
    }

    @GetMapping("/by-price-range")
    @ResponseBody
    public List<Order> getOrdersPricedWithin(@RequestParam("min") double min, @RequestParam("max") double max) {
        return orderService.getOrdersPricedWithin(min, max);
    }

    @GetMapping("/by-unpaid")
    @ResponseBody
    public List<Order> getAllUnpaidOrders() {
        return orderService.getAllUnpaidOrders();
    }

    @GetMapping("/by-paid")
    @ResponseBody
    public List<Order> getAllPaidOrders() {
        return orderService.getAllPaidOrders();
    }

    @GetMapping("/by-placed-on")
    @ResponseBody
    public List<Order> getOrdersPlacedOn(@RequestParam("date") Date date) {
        return orderService.getOrdersPlacedOn(date);
    }

    @GetMapping("/by-placed-month")
    @ResponseBody
    public List<Order> getOrdersPlacedInMonth(@RequestParam("month") int month) {
        return orderService.getOrdersPlacedInMonth(month);
    }

    @GetMapping("/by-placed-year")
    @ResponseBody
    public List<Order> getOrdersPlacedInYear(@RequestParam("year") int year) {
        return orderService.getOrdersPlacedInYear(year);
    }

    @PostMapping("/new")
    @ResponseBody
    public Order addNewOrder(@RequestBody Order order) {
        return orderService.addNewOrder(order);
    }

    @PatchMapping("/update")
    @ResponseBody
    public Optional<Order> updateOrder(@RequestParam("id") int id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteOrder(@RequestParam("id") int id) {
        orderService.deleteOrder(id);
    }
}
