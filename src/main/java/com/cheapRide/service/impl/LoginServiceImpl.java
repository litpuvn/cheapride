package com.cheapRide.service.impl;

import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;

import java.util.UUID;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public User loginUsingUsernameAndPassword(String username, String password) throws DbException,UsernameOrPasswordWrongException {
        //todo: query to db to check if user and pass does exist.
//        String name=get from db;
        //todo: return name from db and then remove default value;
        String name="default value";
        UUID tmepUUID = UUID.randomUUID();
        String tempValidId=tmepUUID.toString();
        //todo: store tempValidId in db
        User user=new User(name,tempValidId);
        return user;
    }

    @Override
    public String createNewUsernameAndPassword(String firstName, String lastName, String username, String password) throws DbException,UserNameAlreadyExist {
        //todo:check if user name is already exist throw exception
        //todo: create new record for user or throw db exception
        return "done";
    }

    @Override
    public String removeAccount(String username, String password) throws DbException,UsernameOrPasswordWrongException {
        //todo:check if user name does exist or throw UsernameOrPasswordWrongException
        //todo: remove record for user or throw db exception
        return "deleted";
    }
}
