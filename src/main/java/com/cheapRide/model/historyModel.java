package com.cheapRide.model;
/**
 * 
 * @author Agnes 
 *
 */
public class historyModel {
private String date;
private String provider;
private String pickup;
private String destination;
private String fee;

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
}
