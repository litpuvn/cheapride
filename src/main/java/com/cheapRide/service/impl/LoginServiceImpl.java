package com.cheapRide.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.service.LoginService;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class LoginServiceImpl implements LoginService {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginDao loginDao;

    @Override
    public String loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	logger.debug("Start => LoginServiceImpl => loginUsingUsernameAndPassword  for user "+ username);
    	String returnString;
    	if(loginDao.getUserByUserAndPass(username, Security.SHA1(password))!=null)
    		returnString = "found";
        else
        	returnString = "UserNotFound";
    	logger.debug("end => LoginServiceImpl => loginUsingUsernameAndPassword  for user "+ username);
    	return returnString;
    }

    @Override
    public String createNewUsernameAndPassword(String username, String password) throws  UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	logger.debug("Start => LoginServiceImpl => createNewUsernameAndPassword  for user "+ username);
    	String returnString;
    	if (loginDao.getUserByUserAndPass(username) != null)
    		returnString = "alreadyRegistered";
        else {
            loginDao.registerNewUser(username, Security.SHA1(password));
            returnString = "done";
        }
    	logger.debug("End => LoginServiceImpl => createNewUsernameAndPassword  for user "+ username);
    	return returnString;
    	

    }

    @Override
    public String removeAccount(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	logger.debug("Start => LoginServiceImpl => removeAccount  for user "+ username);
    	String returnString;
    	if(loginDao.getUserByUserAndPass(username, Security.SHA1(password))==null)
    		returnString = "UserNotFound";
        else{
            loginDao.deleteUser(username,password);
            returnString = "done";
        }
    	logger.debug("End => LoginServiceImpl => removeAccount  for user "+ username);
    	return returnString;
    }

	@Override
	public String getRandomToken() {
		logger.debug("Start => LoginServiceImpl => getRandomToken  for token ");
        UUID tmepUUID = UUID.randomUUID();
        String tempValidId = tmepUUID.toString();
        logger.debug("Start => LoginServiceImpl => getRandomToken  for tokrn "+ tmepUUID);
        return tempValidId;
    }


}
