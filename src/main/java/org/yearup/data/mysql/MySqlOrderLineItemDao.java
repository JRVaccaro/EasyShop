package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlOrderLineItemDao extends MySqlDaoBase implements OrderLineItemDao {

    //Constructor to pass DataSource to base class
    public MySqlOrderLineItemDao(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public OrderLineItem create(OrderLineItem orderLineItem){
        //Statement to insert a new order line item
        String sql = "INSERT INTO order_line_items (order_id, product_id, sales_price, quantity, discount) " +
             "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection()) {
            // Prepare statement with option to retrieve generated key
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, orderLineItem.getOrderId());
            statement.setInt(2, orderLineItem.getProductId());
            statement.setBigDecimal(3, orderLineItem.getSalesPrice());
            statement.setInt(4, orderLineItem.getQuantity());
            statement.setBigDecimal(5, orderLineItem.getDiscount());

            int rowsAffected = statement.executeUpdate();

            //If insert succeeded, get the generated ID
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    //Set generated ID to orderLineItem object
                    orderLineItem.setOrderLineItemId(newId);
                    return orderLineItem;
                }
            }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        return null;
}
}