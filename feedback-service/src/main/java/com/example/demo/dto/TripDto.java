package com.example.demo.dto;

import java.time.LocalDate;

public class TripDto {
int id;
String title;
String routedetails;
int vehicleid;
int driverid;
LocalDate duedate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getRoutedetails() {
	return routedetails;
}
public void setRoutedetails(String routedetails) {
	this.routedetails = routedetails;
}
public int getVehicleid() {
	return vehicleid;
}
public void setVehicleid(int vehicleid) {
	this.vehicleid = vehicleid;
}
public int getDriverid() {
	return driverid;
}
public void setDriverid(int driverid) {
	this.driverid = driverid;
}
public LocalDate getDuedate() {
	return duedate;
}
public void setDuedate(LocalDate duedate) {
	this.duedate = duedate;
}


}
