package com.cheapRide.service;
import java.awt.print.Pageable;
/**
 * @author Agnes
 */
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import com.cheapRide.model.HistoryModel;

public interface HistoryService {
	public String checkAddHistory(String username,Date date,String pickup, String destination, String fee, String provider) ;
	public ArrayList<HistoryModel> checkForUsername(String username, int pageNumber,int size);
	public ArrayList<HistoryModel> checkForProvider(String username, String provider, int pageNumber,int size);
	public ArrayList<HistoryModel> checkDate(String username, Date from,Date to, int pageNumber,int size);
}
