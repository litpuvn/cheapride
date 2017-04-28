package com.cheapRide.model;
/**
 * 
 * @author Agnes 
 *
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "history")
public class HistoryModel {
@Id
private String Id;
private String date;
private String provider;
private String pickup;
private String destination;
private String fee;
private String username;

public String getDate(){
	return date;
}
public void setDate(String date){
		this.date = date;
}
public String getProvider(){
	return provider;
}
public void setProvider(String provider){
	this.provider = provider;
}
public String getPickup(){
	return pickup;
}
public void setPickup(String pickup){
	this.pickup = pickup;
}
public String getDestination(){
	return destination;
}
public void setDestination(String destination){
	this.destination = destination;
}
public String getFee(){
	return fee;
}
public void setFee(String fee){
	this.fee = fee;
}
public String getUsername(){
	return username;
}
public void setUsername(String username){
		this.username = username;
}
public HistoryModel(String username, String date,String pickup,String destination, String fee, String provider) {
	this.username = username;
	this.date = date;
	this.pickup = pickup;
	this.destination = destination;
	this.fee = fee;
	this.provider = provider;
}
public HistoryModel(){
	
}
@Override
public String toString() {
	return "History [id ="+Id+ " username=" + username + ",date=" + date + ",pickup=" + pickup + ",destination=" + destination + ",fee=" + fee + ", provider=" + provider + "]";
}

}
