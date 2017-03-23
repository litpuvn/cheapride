package com.cheapRide.service.impl;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public String loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(loginDao.getUserByUserAndPass(username, Security.SHA1(password))!=null)
            return "found";
        else
            return "UserNotFound";

    }

    @Override
    public String createNewUsernameAndPassword(String username, String password) throws  UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (loginDao.getUserByUserAndPass(username,  Security.SHA1(password)) != null)
            return "alreadyRegistered";
        else {
            loginDao.registerNewUser(username, Security.SHA1(password));
            return "done";
        }

    }

    @Override
    public String removeAccount(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(loginDao.getUserByUserAndPass(username, Security.SHA1(password))==null)
            return "UserNotFound";
        else{
            loginDao.deleteUser(username,password);
            return "done";
        }
    }


}
