package com.cheapRide.controller;

import com.cheapRide.model.LoginResponse;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by pshayegh on 3/8/2017.
 */
@RestController
public class LoginController {
    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (loginService.loginUsingUsernameAndPassword(user.getUsername(), user.getPassword()) == "found") {
                loginResponse.setMessage("You are authorized to access");
                loginResponse.setToken(getRandomToken());
                return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
    }

    private String getRandomToken() {
        UUID tmepUUID = UUID.randomUUID();
        String tempValidId = tmepUUID.toString();
        return tempValidId;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (loginService.createNewUsernameAndPassword(user.getUsername(), user.getPassword()) == "done") {
                loginResponse.setMessage("You are authorized to access");
                loginResponse.setToken(getRandomToken());
                return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> delete(@RequestBody User user) {
        LoginResponse loginResponse = new LoginResponse();
        try {
            if (loginService.removeAccount(user.getUsername(), user.getPassword())== "done") {
                loginResponse.setMessage("You are authorized to access");
                return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
        }

    }

}
