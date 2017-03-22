package com.cheapRide.dao;

import com.cheapRide.model.User;

import java.net.UnknownHostException;

/**
 * Created by pshayegh on 3/22/2017.
 */
public interface LoginDao {
    User getUserByUserAndPass(String username, String password) throws UnknownHostException;
    String registerNewUser(String name,String username, String password);
    String deleteUser(String username, String password);
}
