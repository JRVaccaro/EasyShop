package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    //Gets the shopping cart and it's items from logged in user by userId
    ShoppingCart getByUserId(int userId);

//Add products to cart
void addToCart (int userId, int productId);

//Update quantity in cart
void updateCart (int userId, int productId, int quantity);

//Remove items from cart
void deleteInCart(int userId);
}