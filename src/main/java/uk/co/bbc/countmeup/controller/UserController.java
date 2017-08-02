package uk.co.bbc.countmeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bbc.countmeup.entity.User;
import uk.co.bbc.countmeup.service.UserService;

/**
 * Created by Chris on 01-Aug-17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("In registerUser method of UserController");
        try {
            User createdUser = userService.registerUser(user.getUserName());
            return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
        }

    }
}
