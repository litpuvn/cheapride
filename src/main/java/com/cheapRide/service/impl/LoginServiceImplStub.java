package com.cheapRide.service.impl;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;
import com.cheapRide.service.exception.DbException;
import com.cheapRide.service.exception.UserNameAlreadyExist;
import com.cheapRide.service.exception.UsernameOrPasswordWrongException;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;

/**
 * Created by pshayegh on 3/17/2017.
 */
public class LoginServiceImplStub implements LoginService {

    @Override
    public String loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException {
        return null;
    }

    @Override
    public String createNewUsernameAndPassword( String username, String password) throws  UnknownHostException {
        return null;
    }

    @Override
    public String removeAccount(String username, String password) throws  UnknownHostException {
        return null;
    }

	@Override
	public String getRandomToken() {
		// TODO Auto-generated method stub
		return null;
	}
}
