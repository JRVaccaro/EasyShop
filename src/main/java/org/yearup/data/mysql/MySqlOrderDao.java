package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao {
    public MySqlOrderDao(DataSource dataSource) {
        super(dataSource);
    }
    @Override
    public Order create(Order order){
        String sql = "INSERT INTO orders (user_id, date, address, city, state, zip, shipping_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = getConnection()){
            // Prepare statement with option to retrieve generated keys (order_id)
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getUserId());
            // Convert LocalDateTime to SQL Timestamp
            statement.setTimestamp(2, Timestamp.valueOf(order.getDate()));

            statement.setString(3, order.getAddress());
            statement.setString(4, order.getCity());
            statement.setString(5, order.getState());
            statement.setString(6, order.getZip());
            statement.setBigDecimal(7, order.getShippingAmount());

            int rowsAffected = statement.executeUpdate();

            //If insert succeeded, get the generated order ID
            if (rowsAffected > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()){
                    int newOrderId = generatedKeys.getInt(1);
                    // Set the generated ID back to the Order object
                    order.setOrderId(newOrderId);
                    return order;
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
    }
        return null;
}
    }
