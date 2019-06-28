package com.programming.exercise.synthesis.controller;

import com.programming.exercise.synthesis.entity.User;
import com.programming.exercise.synthesis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Author: Dheeraj
 *
 * User controller API
 */

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @GetMapping(path = "/user/{userId}")
    public  @ResponseBody
    User getUser(@PathVariable Integer userId){
        LOGGER.info(" id is"+userId);
        Optional<User> user= userService.getUser(userId);
        return  user.get();
    }


    @PostMapping(path = "/user")
    public  @ResponseBody
    ResponseEntity<Object> makeUser(@Valid @RequestBody  User user){

        //following logs will be masked for security purpose

        LOGGER.info("name="+user.getName());
        LOGGER.info("email address="+user.getEmailAddress());
        userService.makeUser(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(user.getUserId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
