package org.yearup.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.User;
import org.yearup.security.JwtAccessDeniedHandler;
import org.yearup.security.JwtAuthenticationEntryPoint;
import org.yearup.security.UserModelDetailsService;
import org.yearup.security.jwt.TokenProvider;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest
{
    @Autowired
    //Makes a fake, simulated HTTP request to the controller
    private MockMvc mockMvc;

    @MockBean //Mocks the ShoppingCartDao dependency so that no real DB interaction happens, which is needed or else it will call the Shopping Cart Dao
    private ShoppingCartDao shoppingCartDao;

    @MockBean // Mocks the UserDao so we can decide what user gets returned when getByUserName is called
    private UserDao userDao;

    @MockBean //Mocks the ProductDao, in case the controller needs it so it doesn't crash
    private ProductDao productDao;

    @MockBean//Mocks the TokenProvider so Spring Security doesn't fail. This is being used to check login tokens
    private TokenProvider tokenProvider;

    // This is part of WebSecurityConfig and helps handle what happens when a user isn't logged in or the token is missing.
    // Adding a fake one here so the test runs without errors.
    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // This is used in WebSecurityConfig to decide what happens when a user is logged in but doesn't have permission (like the wrong role).
// Adding a fake one here so the test runs without errors
    @MockBean
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // This is used in WebSecurityConfig to load user info during login (like username and roles).
//Adding a fake one here so Spring Security doesn’t cause errors in the test.
    @MockBean
    private UserModelDetailsService userModelDetailsService;


    // Simulates a logged-in user with username "gary" and role "USER"
     @Test
    @WithMockUser(username = "tater", roles = {"USER"})
    void clearCart_ShouldReturn200() throws Exception
    {
        // Creating a fake user object to simulate the userDao behavior
        User user = new User();
        user.setId(1);
        user.setUsername("tater");


        //Telling Mockito to return the fake user when getByUserName "gary" is called
        Mockito.when(userDao.getByUserName("tater")).thenReturn(user);

        // Act & Assert, use the DELETE in cart and get a 200 OK status
        mockMvc.perform(delete("/cart"))
                .andExpect(status().isOk());
    }
}
