package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

public interface FlightService {
	FlightModel getFlightDetail(Long id);
	FlightModel getFlightDetailNumber(String flightNumber);
	void addFlight(FlightModel flight);
	void deleteFlight(Long id);
	void updateFlight(FlightModel newFlight, String id);
}
