package org.yearup.controllers;

import org.springframework.web.bind.annotation.*;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.OrderLineItem;

@RestController
@RequestMapping("/order-line-items")
@CrossOrigin
public class OrderLineItemsController {
    private OrderLineItemDao orderLineItemDao;

    public OrderLineItemsController(OrderLineItemDao orderLineItemDao) {
        this.orderLineItemDao = orderLineItemDao;
    }
    // POST request to create a new order line item
    @PostMapping
    public OrderLineItem create(@RequestBody OrderLineItem orderLineItem){
        return orderLineItemDao.create(orderLineItem);
    }
}
