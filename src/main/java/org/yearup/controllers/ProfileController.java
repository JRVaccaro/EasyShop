package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.models.Profile;
import org.yearup.data.UserDao;
import org.yearup.models.User;

import java.security.Principal;


@RestController
@RequestMapping("/profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()") //logged in user option. Also all methods in this class will have this
public class ProfileController
{
    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(ProfileDao profileDao, UserDao userDao){
        this.profileDao = profileDao;
        this.userDao = userDao;
    }
    @GetMapping("")
    public Profile getProfile(Principal principal){
        try {
            // get the currently logged in username from the principal object
            String userName = principal.getName();

            // Use username to find the user details in database
            User user = userDao.getByUserName(userName);

            //If user is not found, return a 404 error
            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");

            return profileDao.getByUserId(user.getId());
        }
        catch(Exception e)
        {
            //If any other errors occur, return a 500 error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

}
