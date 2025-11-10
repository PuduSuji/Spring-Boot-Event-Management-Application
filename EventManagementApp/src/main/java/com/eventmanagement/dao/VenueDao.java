package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Repository.AttendeeRepository;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.Repository.OrganizerRepository;
import com.eventmanagement.Repository.RegistrationRepository;
import com.eventmanagement.Repository.VenueRepository;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Repository
public class VenueDao {
	
	@Autowired
	private VenueRepository venueRepository;

	//Venue 
	//Add a venu into DB
	public Venue addVenue(Venue venue) {
		return venueRepository.save(venue);
	}
	//Get all the venues in DB
	public List<Venue> getAllVenue(){
		return venueRepository.findAll();
	}
	//getting venue by id
	public Optional<Venue> getVenueById(int id){
		return venueRepository.findById(id);
	}
	//Update the venue
	public Venue updateVenue(Venue venue) {
		Optional<Venue> opt =venueRepository.findById(venue.getId());
		if(opt.isPresent())
		    return venueRepository.save(venue);
		else
		throw new IdNotFoundException("Id is not available in DB");
	}
	//delete a venue
	public void deleteVenue(Integer id) {
		Optional<Venue> opt = venueRepository.findById(id);
		if(opt.isPresent())
		     venueRepository.delete(opt.get());
		else
		throw new IdNotFoundException("Id is not available in DB");
	}
	//get Event by Venue ID
	public List<Event> getEventsByVenueId(Integer venueId){
		return venueRepository.getEventsByVenueId(venueId);
	}
	//get Venue By Location
	public List<Venue> getVenueByLocation(String location){
		return venueRepository.findByLocation(location);	
	}
	//Get Venue by pagination and sorting
	public Page<Venue> getVenueByPaginationAndSorting(int pageNumber,int pageSize,String field){
		Page<Venue> pages= venueRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).descending()));
		if(!pages.isEmpty())
			return pages;
		else
		throw new NoRecordAvailableException("No Venue is available");

	}
	
	
	
		
}