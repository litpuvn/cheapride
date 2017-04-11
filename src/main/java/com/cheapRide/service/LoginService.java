package com.cheapRide.service;

import com.cheapRide.model.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by pshayegh on 3/8/2017.
 */
@Service
public interface LoginService {
    User loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;

    String createNewUsernameAndPassword( String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;

    String removeAccount(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException;
    
    String getRandomToken();

    String invalidateTokenBylogout(String token);
}
