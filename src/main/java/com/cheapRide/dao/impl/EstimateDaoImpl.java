package com.cheapRide.dao.impl;

import com.cheapRide.dao.EstimateDao;
import com.cheapRide.model.PopularPlaceInfo;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
        mongoOperation.save(popularPlaceInfo);
        logger.debug("End => EstimateDaoImpl => registerNewUser  for name "
                + popularPlaceInfo.getName());
        return "done";
    }

    @Override
    public List<PopularPlaceInfo> restorePopularPlaceInfo(Date date) {

        //Criteria.where("sto").regex("dd:dd:ddd")
        Query searchUserQuery = new Query(Criteria.where("storeDate").lte(
                date));

        searchUserQuery.with(new Sort(new Sort.Order(Sort.Direction.DESC, "storeDate")));
        searchUserQuery.limit(8);

        List<PopularPlaceInfo> popularPlaceInfos = mongoOperation.find(searchUserQuery, PopularPlaceInfo.class);
        return popularPlaceInfos;

    }

    @Override
    public String dropTable() {
        mongoOperation.dropCollection(PopularPlaceInfo.class);
        return "done";
    }
}
