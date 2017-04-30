package com.cheapRide.service;
import java.awt.print.Pageable;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import com.cheapRide.model.HistoryModel;

public interface HistoryService {
	public String checkAddHistory(String username,String date,String pickup, String destination, String fee, String provider) ;
	public String checkForUsername(String username);
	public String checkForProvider(String username, String provider);
	public String checkDate(String username, String fromDate,String toDate);
}
