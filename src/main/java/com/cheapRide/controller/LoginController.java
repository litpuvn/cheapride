package com.cheapRide.controller;

import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pshayegh on 3/8/2017.
 */
@RestController
public class LoginController {
    @Autowired
    public LoginService loginService;


    @RequestMapping("/login")
    public User login(@RequestParam(value="user") String username, @RequestParam(value = "password") String password) {
        User user= null;
        try {
            user = loginService.loginUsingUsernameAndPassword(username,password);
        } catch (DbException e) {
            e.printStackTrace();
        } catch (UsernameOrPasswordWrongException e) {
            e.printStackTrace();
        }
        return user;
    }
    @RequestMapping("/register")
    public String register(@RequestParam(value="firstName") String firstName,@RequestParam(value="lastName") String lastName,@RequestParam(value="user") String username, @RequestParam(value = "password") String password) {
        try {
            return loginService.createNewUsernameAndPassword(firstName,lastName,username,password);
        } catch (DbException e) {
            return "400";
        } catch (UserNameAlreadyExist userNameAlreadyExist) {
           return "401";
        }
    }
    @RequestMapping("/register")
    public String delete(@RequestParam(value="user") String username, @RequestParam(value = "password") String password) {
        try {
            return loginService.removeAccount(username,password);
        } catch (DbException e) {
            return "400";
        } catch (UsernameOrPasswordWrongException e) {
            return "401";
        }
    }


}
