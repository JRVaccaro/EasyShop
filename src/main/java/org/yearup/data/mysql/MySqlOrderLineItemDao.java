package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;

import javax.sql.DataSource;

@Component
public class MySqlOrderLineItemDao extends MySqlDaoBase implements OrderLineItemDao {

    //Constructor to pass DataSource to base class
    public MySqlOrderLineItemDao(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public OrderLineItem create(OrderLineItem orderLineItem){
        String sql = "INSERT INTO order_line_items (order_id, product_id, sales_price, quantity, discount) " +
             "VALUES (?, ?, ?, ?, ?";
    }
}
