package com.cheapRide.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.model.User;
import com.cheapRide.service.LoginService;

/**
 * Created by pshayegh on 3/8/2017.
 */
@Service
public class LoginServiceImpl implements LoginService {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginDao loginDao;

    @Override
    public User loginUsingUsernameAndPassword(String username, String password) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	logger.debug("Start => LoginServiceImpl => loginUsingUsernameAndPassword  for user "+ username);
//    	String returnString;
    	User foundUser=loginDao.getUserByUserAndPass(username, Security.SHA1(password));
    	if(foundUser!=null) {
			foundUser.setDate(new Date());
			foundUser.setToken(getRandomToken());
			loginDao.refreshTokenAndDate(foundUser);
		}
    	logger.debug("end => LoginServiceImpl => loginUsingUsernameAndPassword  for user "+ username);
    	return foundUser;
    }

    @Override
    public String createNewUsernameAndPassword(String username, String password) throws  UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	logger.debug("Start => LoginServiceImpl => createNewUsernameAndPassword  for user "+ username);
    	String returnString;
    	if (loginDao.getUserByUserAndPass(username) != null){

    		returnString = "alreadyRegistered";}
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
		tempValidId = tempValidId.replace("-", "");
        logger.debug("Start => LoginServiceImpl => getRandomToken  for tokrn "+ tmepUUID);
        return tempValidId;
    }

	@Override
	public String invalidateTokenBylogout(String token) {
		logger.debug("Start => Logout => invalidateTokenBylogout  for user "+ token);
		String returnString;
		if(loginDao.getUserByToken(token)!=null){
			User user=loginDao.getUserByToken(token);
			user.setDate(new Date());
			user.setToken("INVALID");
			loginDao.refreshTokenAndDate(user);
			returnString = "logoutSuccessful";}
		else
			returnString = "logoutFailed";
		logger.debug("end => LoginServiceImpl => invalidateTokenBylogout  for user "+ token);
		return returnString;
	}


}
