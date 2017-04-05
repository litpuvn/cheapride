package com.cheapRide.dao.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.model.User;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;

/**
 * Created by pshayegh on 3/22/2017.
 */
public class LoginDaoImpl implements LoginDao {

	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(LoginDaoImpl.class);

	@Autowired
	private MongoTemplate mongoOperation;

	@Override
	public User getUserByUserAndPass(String username, String password) {

		logger.debug("Start => LoginDaoImpl => getUserByUserAndPass  for user "
				+ username);
		User savedUser = null;
		Query searchUserQuery = new Query(Criteria.where("username").is(
				username).and("password").is(password));

		// find the saved user again.
		try {
			savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		} catch (Exception e) {
			logger.error("ERROR => LoginDaoImpl => getUserByUserAndPass  for user "
					+ username);
		}

		logger.debug("End => LoginDaoImpl => getUserByUserAndPass  for user "
				+ username);
		return savedUser;
	}

	@Override
	public User getUserByUserAndPass(String username) {

		logger.debug("Start => LoginDaoImpl => getUserByUserAndPass  for user "
				+ username);
		User savedUser = null;
		Query searchUserQuery = new Query(Criteria.where("username").is(
				username));

		// find the saved user again.
		try {
			savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		} catch (Exception e) {
			logger.error("ERROR => LoginDaoImpl => getUserByUserAndPass  for user "
					+ username);
		}

		logger.debug("End => LoginDaoImpl => getUserByUserAndPass  for user "
				+ username);
		return savedUser;
	}

	@Override
	public String refreshTokenAndDate(User user) {
		logger.debug("Start => LoginDaoImpl => update token and date for user "
				+ user.getUsername());
		String returnString="";
		Query searchUserQuery = new Query(Criteria.where("username").is(
				user.getUsername()));
		mongoOperation.updateFirst(searchUserQuery, Update.update("LoginDate", user.getDate()),User.class);
		mongoOperation.updateFirst(searchUserQuery, Update.update("Token", user.getToken()),User.class);
		logger.debug("End => LoginDaoImpl => registerNewUser  for user "
				+ user.getUsername());
		return returnString;
	}

	@Override
	public User getUserByToken(String token) {
		logger.debug("Start => LoginDaoImpl => get User by token for user "
				+ token);
		logger.debug("Start => LoginDaoImpl => getUserByToken for user "
				+ token);
		User savedUser = null;
		Query searchUserQuery=null;
		 searchUserQuery = new Query(Criteria.where("token").is(
				token));

		// find the saved user again.
		try {
			savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		} catch (Exception e) {
			logger.error("ERROR => LoginDaoImpl => getUserByToken  for user "
					+ token);
		}

		logger.debug("End => LoginDaoImpl => getUserByUserAndPass  for user "
				+ token);
		return savedUser;
	}

	@Override
	public String registerNewUser(String username, String password) {
		logger.debug("Start => LoginDaoImpl => registerNewUser  for user "
				+ username);
		String returnString;
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setToken("INVALID");
			user.setDate(new Date());
			// save
			mongoOperation.save(user);
			returnString = "done";
		logger.debug("End => LoginDaoImpl => registerNewUser  for user "
				+ username);
		return returnString;
	}

	@Override
	public String deleteUser(String username, String password) {
		logger.debug("Start => LoginDaoImpl => deleteUser  for user "
				+ username);
		Query searchUserQuery = new Query(Criteria.where("username").is(
				username));
		mongoOperation.remove(searchUserQuery, User.class);
		logger.debug("End => LoginDaoImpl => deleteUser  for user " + username);
		return "done";

	}
}
