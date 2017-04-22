package com.cheapRide.dao;

import com.cheapRide.model.HistoryModel;

/**
 * 
 * @author Agnes
 *
 */
public interface HistoryDao {
	public void addHistory(String username, String date,String pickup,String destination, String fee, String provider);
	public HistoryModel getHistoryByUsername(String username);
	public HistoryModel getHistoryByProvider(String username,String provider);
	public HistoryModel getHistoryByDate(String username,String fromDate, String toDate);
}
