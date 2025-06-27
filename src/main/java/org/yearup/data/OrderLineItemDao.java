package org.yearup.data;

import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;

public interface OrderLineItemDao {

    //Creates new order line in for database
    OrderLineItem create(OrderLineItem orderLineItem);
}
