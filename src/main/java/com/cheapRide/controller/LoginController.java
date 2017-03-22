package com.cheapRide.controller;

import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pshayegh on 3/8/2017.
 */
@RestController
public class LoginController {
    @Autowired
    public LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam(value = "user") String username, @RequestParam(value = "password") String password) {
//        try {
//            return loginService.loginUsingUsernameAndPassword(username, password);
//        } catch (UnknownHostException e) {
//            return "failedConnectionToDb";
//        }
//    }
    public String login(@RequestBody User user) {
        try {
            return loginService.loginUsingUsernameAndPassword(user.getUsername(), user.getPassword());
        } catch (UnknownHostException e) {
            return "failedConnectionToDb";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        try {
            return loginService.createNewUsernameAndPassword(user.getName(), user.getUsername(), user.getPassword());
        } catch (UnknownHostException e) {
            return "failedConnectionToDb";
        } catch (UnsupportedEncodingException e) {
            return "failedHashingCode";
        } catch (NoSuchAlgorithmException e) {
            return "failedHashingCode";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody User user) {
        try {
            return loginService.removeAccount(user.getUsername(), user.getPassword());
        } catch (UnknownHostException e) {
            return "failedConnectionToDb";
        }

    }
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody User user) {
        return "555";
    }
    
}
