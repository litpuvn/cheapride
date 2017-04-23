package com.cheapRide.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cheapRide.model.LoginResponse;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;

/**
 * Created by pshayegh on 3/8/2017.
 */
@Controller
public class LoginController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        logger.debug("Start => LoginController => login  for user " + user.getUsername());
        ResponseEntity<LoginResponse> loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            User foundUser = loginService.loginUsingUsernameAndPassword(user.getUsername(), user.getPassword());
            if (foundUser != null) {
                loginResponse.setMessage("You are authorized to access");
                loginResponse.setToken(foundUser.getToken());
                loginResponseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => login  for user " + user.getUsername());
        }
        logger.debug("End => LoginController => login  for user " + user.getUsername());
        return loginResponseEntity;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    
    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
        logger.debug("Start => LoginController => register  for user " + user.getUsername());
        ResponseEntity<LoginResponse> loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            String serviceReturnMsg = loginService.createNewUsernameAndPassword(user.getUsername(), user.getPassword());
            if ("done".equalsIgnoreCase(serviceReturnMsg)) {
                loginResponse.setMessage("User created");
                loginResponse.setToken("");
                loginResponseEntity = ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
            } else {
                loginResponse.setMessage(serviceReturnMsg);
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => register  for user " + user.getUsername());
        }
        logger.debug("End => LoginController => register  for user " + user.getUsername());
        return loginResponseEntity;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> delete(@RequestBody User user) {
        logger.debug("Start => LoginController => delete  for user " + user.getUsername());
        ResponseEntity<LoginResponse> loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            if ("done".equalsIgnoreCase(loginService.removeAccount(user.getUsername(), user.getPassword()))) {
                loginResponse.setMessage("You are authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not authorized to access");
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not authorized to access");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => delete  for user " + user.getUsername());
        }

        logger.debug("End => LoginController => delete  for user " + user.getUsername());
        return loginResponseEntity;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> logout(@RequestBody User user) {
        logger.debug("Start => LoginController => logout  for user " + user.getToken());
        ResponseEntity<LoginResponse> loginResponseEntity;
        LoginResponse loginResponse = new LoginResponse();
        try {
            if ("logoutSuccessful".equalsIgnoreCase(loginService.invalidateTokenBylogout(user.getToken()))) {
                loginResponse.setMessage("You are loged out to access");
                loginResponse.setToken("INVALID");
                loginResponseEntity = ResponseEntity.status(HttpStatus.OK).body(loginResponse);
            } else {
                loginResponse.setMessage("You are not loged out");
                loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            }
        } catch (Exception e) {
            loginResponse.setMessage("You are not loged out");
            loginResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
            logger.error("Error => LoginController => logout  for user " + user.getToken());
        }
        logger.debug("End => LoginController => logout  for user " + user.getToken());
        return loginResponseEntity;
    }

}
