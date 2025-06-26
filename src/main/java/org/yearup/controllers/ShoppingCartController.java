package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;
@RestController
@PreAuthorize("isAuthenticated()") //logged in user option. Also all methods in this class will have this
@CrossOrigin
@RequestMapping("cart")
public class ShoppingCartController
{
    // a shopping cart requires
    @Autowired
    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }


    @GetMapping("")//Makes method for logged in users only. Handles GET requests
    public ShoppingCart getCart(Principal principal)
    {
        try {
            // get the currently logged in username from the principal object
            String userName = principal.getName();

            // Use username to find the user details in database
            User user = userDao.getByUserName(userName);

            //If user is not found, return a 404 error
            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

            //Get user Id from user object
            int userId = user.getId();

            // use the shoppingcartDao to get all items in the cart and return the cart
            return shoppingCartDao.getByUserId(userId);

        }
        catch(Exception e)
        {
            //If any other errors occur, return a 500 error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }


    @PostMapping ("/products/{productId}")//Post method to add a product to cart
    public void addToUserCart(@PathVariable int productId, Principal principal){
        try{
            // get the currently logged in username from the principal object
            String userName = principal.getName();
            // Use username to find the user details in database
            User user = userDao.getByUserName(userName);

            //If user is not found, return a 404 error
            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

            //Get user Id from user object
            int userId = user.getId();

            //
            shoppingCartDao.addToCart(userId, productId);
        }
        catch(Exception e)
        {
            //If any other errors occur, return a 500 error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }



    @PutMapping("/products/{productId}")
    public void updateCartItem(@PathVariable int productId, @RequestBody ShoppingCartItem cartItem, Principal principal){

        try {
            // get the currently logged in username from the principal object
            String userName = principal.getName();
            // Use username to find the user details in database
            User user = userDao.getByUserName(userName);

            //If user is not found, return a 404 error
            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

            //Get user Id from user object
            int userId = user.getId();

            //Get quantity from the request body
            int quantity = cartItem.getQuantity();

            //Update the cart with new quantity
            shoppingCartDao.updateCart(userId, productId, quantity);
        }
              catch(Exception e)
            {
                //If any other errors occur, return a 500 error
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
            }
        }



    // add a DELETE method to clear all products from the current users cart
    // https://localhost:8080/cart
@DeleteMapping("")
public void clearCart(Principal principal){
    try {
        // get the currently logged in username from the principal object
        String userName = principal.getName();
        // Use username to find the user details in database
        User user = userDao.getByUserName(userName);

        //If user is not found, return a 404 error
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

        //Get user Id from user object
        int userId = user.getId();

        //Delete all items in cart for user
        shoppingCartDao.deleteInCart(userId);
    }
              catch(Exception e)
        {
            //If any other errors occur, return a 500 error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }


}

