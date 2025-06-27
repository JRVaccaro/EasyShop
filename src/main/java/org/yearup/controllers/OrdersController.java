package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.OrderDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Order;
import org.yearup.models.User;
import org.yearup.data.UserDao;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@CrossOrigin

public class OrdersController {
    private OrderDao orderDao;
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;

    public OrdersController(OrderDao orderDao, ShoppingCartDao shoppingCartDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
    }

    @PostMapping
    public Order createOrder(Order order, Principal principal){

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        //Set userId on order
        order.setUserId(user.getId());

        //Create the order
        Order createdOrder = orderDao.create(order);

        //Clear the user's shopping cart after order is created
        shoppingCartDao.clearCart(user.getId());

        return createdOrder;

    }
}
