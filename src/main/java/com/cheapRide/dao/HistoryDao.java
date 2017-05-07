package com.cheapRide.dao;

import java.util.ArrayList;
import java.util.Date;

import com.cheapRide.model.HistoryModel;

/**
 * 
 * @author Agnes
 *
 */
public interface HistoryDao {
	public HistoryModel addHistory(String username,String date, String pickup,String destination,String fee, String provider);
	public ArrayList<HistoryModel> getHistoryByUsername(String username, int pageNumber, int size);
	public ArrayList<HistoryModel> getHistoryByProvider(String username,String provider, int pageNumber, int size);
<<<<<<< HEAD
	public ArrayList<HistoryModel> getHistoryByDate(String username,Date from, Date to, int pageNumber, int size);
=======
	public ArrayList<HistoryModel> getHistoryByDate(String username,String from, String to, int pageNumber, int size);
>>>>>>> 8bd28f8ee19a561d5439025f552a8b024540c2bd
}
