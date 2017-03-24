package com.cheapRide.controller;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheapRide.model.LoginResponse;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;

/**
 * Created by pshayegh on 3/8/2017.
 */
@RestController
public class LoginController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
    	logger.debug("Start => LoginController => login  for user "+ user.getUsername());
    	ResponseEntity<LoginResponse>  loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            if ("found".equalsIgnoreCase(loginService.loginUsingUsernameAndPassword(user.getUsername(), user.getPassword()))) {
                loginResponse.setMessage("You are authorized to access");
                loginResponse.setToken(loginService.getRandomToken());
                loginResponseEntity=  ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => login  for user "+ user.getUsername());
        }
        logger.debug("End => LoginController => login  for user "+ user.getUsername());
        return loginResponseEntity;
    }

 

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
    	logger.debug("Start => LoginController => register  for user "+ user.getUsername());
    	ResponseEntity<LoginResponse>  loginResponseEntity;
    	LoginResponse loginResponse = new LoginResponse();
        try {
        	String serviceReturnMsg = loginService.createNewUsernameAndPassword(user.getUsername(), user.getPassword());
            if ("done".equalsIgnoreCase(serviceReturnMsg)) {
                loginResponse.setMessage("User created");
                loginResponse.setToken(loginService.getRandomToken());
                loginResponseEntity = ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
            } else {
                loginResponse.setMessage(serviceReturnMsg);
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => register  for user "+ user.getUsername());
        }
        logger.debug("End => LoginController => register  for user "+ user.getUsername());
        return loginResponseEntity;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> delete(@RequestBody User user) {
    	logger.debug("Start => LoginController => delete  for user "+ user.getUsername());
    	ResponseEntity<LoginResponse>  loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            if ("done".equalsIgnoreCase(loginService.removeAccount(user.getUsername(), user.getPassword())) ){
                loginResponse.setMessage("You are authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => delete  for user "+ user.getUsername());
        }
        
        logger.debug("End => LoginController => delete  for user "+ user.getUsername());
        return loginResponseEntity;

    }

}
