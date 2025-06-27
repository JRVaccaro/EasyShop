package org.yearup.data;

import org.yearup.models.Order;

public interface OrderDao {
    Order create(Order order);

    //new method to get user's order
    Order getOrderByUserId(int userId);
}
