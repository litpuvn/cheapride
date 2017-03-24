package com.cheapRide.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pshayegh on 3/8/2017.
 */
@Service
public interface LoginService {
    String loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;

    String createNewUsernameAndPassword( String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;

    String removeAccount(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;
    
    String getRandomToken();
}
