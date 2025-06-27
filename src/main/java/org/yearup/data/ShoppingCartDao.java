package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    //Gets the shopping cart and it's items from logged in user by userId
    ShoppingCart getByUserId(int userId);

//Add products to cart
ShoppingCart addToCart (int userId, int productId);

//Update quantity in cart
ShoppingCart updateCart (int userId, int productId, int quantity);

//Remove items from cart
ShoppingCart deleteInCart(int userId);

//Clear all items from cart
ShoppingCart clearCart(int userId);
}