package com.cheapRide.dao.impl;

import com.cheapRide.dao.LoginDao;
import com.cheapRide.dao.SpringMongoConfig;
import com.cheapRide.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.net.UnknownHostException;

/**
 * Created by pshayegh on 3/22/2017.
 */
public class LoginDaoImpl implements LoginDao {

    @Override
    public User getUserByUserAndPass(String username, String password) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

        Query searchUserQuery = new Query(Criteria.where("username").is(username));

        // find the saved user again.
        User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
        System.out.println("2. find - savedUser : " + savedUser);
        return savedUser;

    }

    @Override
    public String registerNewUser(String name,String username, String password) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        User user = new User(name, username,password);
        // save
        mongoOperation.save(user);

        // now user object got the created id.
        System.out.println("1. user : " + user);
        return "done";
    }

    @Override
    public String deleteUser(String username, String password) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Query searchUserQuery = new Query(Criteria.where("username").is(username));
        mongoOperation.remove(searchUserQuery, User.class);
        return "done";

    }
}
