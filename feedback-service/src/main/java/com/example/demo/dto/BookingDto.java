package com.example.demo.dto;

import java.time.LocalDate;

public class BookingDto {
int id;
int customerId;
int vehicleId;
LocalDate date;
int durationdays;
String status;
Integer driverId;
boolean driverRequired;

public Integer getDriverId() {
	return driverId;
}
public void setDriverId(Integer driverId) {
	this.driverId = driverId;
}
public boolean isDriverRequired() {
	return driverRequired;
}
public void setDriverRequired(boolean driverRequired) {
	this.driverRequired = driverRequired;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public int getVehicleId() {
	return vehicleId;
}
public void setVehicleId(int vehicleId) {
	this.vehicleId = vehicleId;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public int getDurationdays() {
	return durationdays;
}
public void setDurationdays(int durationdays) {
	this.durationdays = durationdays;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
