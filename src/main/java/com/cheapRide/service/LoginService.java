package com.cheapRide.service;

import com.cheapRide.model.User;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;
import org.springframework.stereotype.Service;

/**
 * Created by pshayegh on 3/8/2017.
 */
@Service
public interface LoginService {
    User loginUsingUsernameAndPassword(String username, String password) throws DbException,UsernameOrPasswordWrongException;
    String createNewUsernameAndPassword(String firstName, String lastName, String username, String password) throws DbException,UserNameAlreadyExist;
    String removeAccount(String username, String password) throws DbException,UsernameOrPasswordWrongException;
}
