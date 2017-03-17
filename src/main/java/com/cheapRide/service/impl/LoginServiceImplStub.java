package com.cheapRide.service.impl;

import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;

/**
 * Created by pshayegh on 3/17/2017.
 */
public class LoginServiceImplStub implements LoginService{
    @Override
    public User loginUsingUsernameAndPassword(String username, String password) throws DbException, UsernameOrPasswordWrongException {
        User user=new User("parva","333");
        return user;

    }

    @Override
    public String createNewUsernameAndPassword(String firstName, String lastName, String username, String password) throws DbException, UserNameAlreadyExist {
        return "done";
    }

    @Override
    public String removeAccount(String username, String password) throws DbException, UsernameOrPasswordWrongException {
        return "deleted";
    }
}
