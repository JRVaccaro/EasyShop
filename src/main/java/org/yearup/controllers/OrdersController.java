package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.OrderDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Order;
import org.yearup.models.User;
import org.yearup.data.UserDao;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@PreAuthorize("isAuthenticated()")

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
                                //Trying to see if extracting query parameters from url will work?
    public Order createOrder(@RequestParam String address,
                             @RequestParam String city,
                             @RequestParam String state,
                             @RequestParam String zip,
                             @RequestParam BigDecimal shippingAmount, Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
            }

            Order order = new Order();
            order.setUserId(user.getId());
            order.setDate(LocalDateTime.now());
            order.setAddress(address);
            order.setCity(city);
            order.setState(state);
            order.setZip(zip);
            order.setShippingAmount(shippingAmount);

            //Save order
            Order createdOrder = orderDao.create(order);

            //Clear cart
            shoppingCartDao.clearCart(user.getId());


            return createdOrder;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while creating the order");
        }
    }

    @GetMapping("/current")
    public Order getCurrentOrder(Principal principal) {
        try {
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }


            // Retrieve the current open order for the user
            Order order = orderDao.getOrderByUserId(user.getId());

            // If no order is found, return 404
            if (order == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order found for this user");
            }

            return order;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve order");
        }
    }
}