package com.cheapRide.dao;


/**
 * 
 * @author Agnes
 *
 */
public interface HistoryDao {
	public void addHistory(String username, String date,String pickup,String destination, String fee, String provider);
	public void getHistoryByUsername(String username);
}
