package com.apap.tutorial4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method= RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber")String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot =pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
		
	}
	
	@RequestMapping(value="/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value="/flight/delete/{id}", method=RequestMethod.GET)
	private String deleteFlight(@PathVariable(value="id")String id, Model model){	
		
		flightService.deleteFlight(Long.parseLong(id));
		return "delete";
	}
	
	@RequestMapping(value="/flight/update/{id}", method=RequestMethod.GET)
	private String updateFlight(@PathVariable(value="id")String id,Model model){
		FlightModel archive = flightService.getFlightDetail(Long.parseLong(id));
		System.out.println(archive.getFlightNumber());
		model.addAttribute("flight", archive);
		return "updateFlight";
		
	}
	

	@RequestMapping(value="/flight/update/{id}/succes", method=RequestMethod.POST)
	private String updateFlight(@PathVariable(value="id") String id,@ModelAttribute FlightModel newFlight){
		flightService.updateFlight(newFlight, id);
		return "update";
		
	}
	
	@RequestMapping("/flight/view/{id}")
	public String viewId(@PathVariable(value="id") String id, Model model) {
		FlightModel archiveFlight = flightService.getFlightDetail(Long.parseLong(id));
		PilotModel archivePilot = archiveFlight.getPilot();
		
		model.addAttribute("flight",archiveFlight);
		model.addAttribute("pilot",archivePilot);
		return "view-flight";
	}
	
	@RequestMapping("/flight/view")
	public String viewNumber(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel archiveFlight = flightService.getFlightDetailNumber(flightNumber);
		PilotModel archivePilot = archiveFlight.getPilot();
		
		model.addAttribute("flight",archiveFlight);
		model.addAttribute("pilot",archivePilot);
		return "view-flight";
	}
	
	
}


