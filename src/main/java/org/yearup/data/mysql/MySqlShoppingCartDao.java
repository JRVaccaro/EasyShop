package org.yearup.data.mysql;


import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    //Constructor injection for database connection
    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {

        //Query to join shopping_cart and products tables for given userId, along with quantity
        String sql = "SELECT * FROM shopping_cart JOIN products ON shopping_cart.product_id = products.product_id " +
                "WHERE shopping_cart.user_id = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //Set user Id parameter for query
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            //Create new shopping cart object to hold results
            ShoppingCart cart = new ShoppingCart();

            //Loop through results to build cart
            while (resultSet.next()) {
                //Change current row into a ShoppingCartItem object
                ShoppingCartItem item = mapRowToCartItem(resultSet);

                // For each row in the result set, create a ShoppingCartItem and add it to the ShoppingCart
                cart.add(item);

            }
            return cart;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
        //Helper method to build a ShoppingCartItem from a result row
        protected static ShoppingCartItem mapRowToCartItem (ResultSet row) throws SQLException {
        //Extract product details from the current row
            int productId = row.getInt("product_id");
            String name = row.getString("name");
            BigDecimal price = row.getBigDecimal("price");
            int categoryId = row.getInt("category_id");
            String description = row.getString("description");
            String color = row.getString("color");
            int stock = row.getInt("stock");
            boolean isFeatured = row.getBoolean("featured");
            String imageUrl = row.getString("image_url");

            //Extract quantity of the product in the shopping cart
            int quantity = row.getInt("quantity");

            //Create a product object with extracted details
            Product product = new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(quantity);

            //Return the fully built shopping cart item
            return item;
        }
    }