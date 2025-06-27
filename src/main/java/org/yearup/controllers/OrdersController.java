package org.yearup.controllers;

import org.springframework.web.bind.annotation.*;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;

@RestController
@RequestMapping("/orders")
@CrossOrigin

public class OrdersController {
    private OrderDao orderDao;

    public OrdersController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderDao.create(order);
    }
}
