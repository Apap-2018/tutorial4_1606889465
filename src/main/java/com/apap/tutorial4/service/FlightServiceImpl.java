package com.apap.tutorial4.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public void deleteFlight(Long id) {
		FlightModel flight =flightDb.findById(id).get();
		flightDb.delete(flight);
	}
	
	@Override
	public FlightModel getFlightDetail(Long id) {
		return flightDb.findById(id).get();
	}
	
	public FlightModel getFlightDetailNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
	
	
	
	@Override
	public void updateFlight(FlightModel newFlight,String id){
		FlightModel oldFlight= this.getFlightDetail(Long.parseLong(id));
		
		oldFlight.setFlightNumber(newFlight.getFlightNumber());
		oldFlight.setOrigin(newFlight.getOrigin());
		oldFlight.setDestination(newFlight.getDestination());
		oldFlight.setTime(newFlight.getTime());
		
	}
	
}
