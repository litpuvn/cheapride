package com.cheapRide.service;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import com.cheapRide.model.HistoryModel;

public interface HistoryService {
	public HistoryModel checkAddHistory(String username,String date,String pickup, String destination, String fee, String provider) ;
	public ArrayList<HistoryModel> checkForUsername(String username);
	public ArrayList<HistoryModel> checkForProvider(String username, String provider);
	public ArrayList<HistoryModel> checkDate(String username, String fromDate,String toDate);
}
