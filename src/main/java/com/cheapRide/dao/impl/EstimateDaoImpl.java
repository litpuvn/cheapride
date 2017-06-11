package com.cheapRide.dao.impl;

import com.cheapRide.dao.EstimateDao;
import com.cheapRide.model.PopularPlaceInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by pshayegh on 6/11/2017.
 */
@Repository
public class EstimateDaoImpl implements EstimateDao {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(EstimateDaoImpl.class);
    @Autowired
    private MongoTemplate mongoOperation;
    @Override
    public String storeEstimateTimeAndCost(PopularPlaceInfo popularPlaceInfo) {
        logger.debug("Start => EstimateDaoImpl => registerNewUser  for name "
                + popularPlaceInfo.getName());
        String returnString;
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setToken("INVALID");
//        user.setDate(new Date());
        // save
        mongoOperation.save(popularPlaceInfo);
        returnString = "done";
        logger.debug("End => EstimateDaoImpl => registerNewUser  for name "
                + popularPlaceInfo.getName());
        return returnString;
    }

    @Override
    public PopularPlaceInfo restorePopularPlaceInfo(String name) {
        logger.debug("Start => LoginDaoImpl => getUserByUserAndPass  for  "
                + name);
        PopularPlaceInfo info = null;
        Query searchUserQuery = new Query(Criteria.where("name").is(
                name));

        // find the saved user again.
        try {
            info = mongoOperation.findOne(searchUserQuery, PopularPlaceInfo.class);
        } catch (Exception e) {
            logger.error("ERROR => LoginDaoImpl => getUserByUserAndPass  for user "
                    + name);
        }

        logger.debug("End => LoginDaoImpl => getUserByUserAndPass  for user "
                + name);
        return info;    }
}
