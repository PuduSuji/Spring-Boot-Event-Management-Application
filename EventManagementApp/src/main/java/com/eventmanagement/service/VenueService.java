package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Repository.AttendeeRepository;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.Repository.OrganizerRepository;
import com.eventmanagement.Repository.VenueRepository;
import com.eventmanagement.dao.VenueDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.EventNotFoundException;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VenueService {
   @Autowired
	private VenueDao venueDao;
    //Venue
   //add venues
   public ResponseEntity<ResponseStructure<Venue>> addVenue(Venue venue){
		ResponseStructure<Venue> response=new ResponseStructure<Venue>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Venue is Created");
		response.setData(venueDao.addVenue(venue));
		return new ResponseEntity<ResponseStructure<Venue>>(response,HttpStatus.CREATED);
	}
   //get all venues
   public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue(){
		List<Venue> venue = venueDao.getAllVenue() ;
		ResponseStructure<List<Venue>> response = new ResponseStructure<List<Venue>>();
		
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("The Venue Details is Retrieved");
			response.setData(venue);
			
			return new ResponseEntity<ResponseStructure<List<Venue>>>(response, HttpStatus.OK);
	}
   //get venue by id
   public  ResponseEntity<ResponseStructure<Optional<Venue>>> getVenueById(int id){
		Optional<Venue> opt = venueDao.getVenueById(id);
		ResponseStructure<Optional<Venue>> response = new ResponseStructure<Optional<Venue>>();
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Venue get By id");
			response.setData(opt);
			return new  ResponseEntity<ResponseStructure<Optional<Venue>>>(response,HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id is not available in DB");
		}
	}
   //Update Venue
   public ResponseEntity<ResponseStructure<Venue>> updateVenue(Venue venue){
		ResponseStructure<Venue> response = new ResponseStructure<Venue>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(" Venue details is Updated");
			response.setData(venueDao.updateVenue(venue));
			return new ResponseEntity<ResponseStructure<Venue>>(response,HttpStatus.OK);
	}
   //delete Venue
   public  ResponseEntity< ResponseStructure<String>> deleteVenue(Integer id){
		ResponseStructure<String> response=new ResponseStructure<String>();
		venueDao.deleteVenue(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Venue is Deleted");
			response.setData("Venue is Deleted");
			return new  ResponseEntity< ResponseStructure<String>>(response,HttpStatus.OK);
	}
 //get Event by Venue ID
   public ResponseEntity<ResponseStructure<List<Event>>> getEventByVenueId(Integer venueId){
		List<Event> event=venueDao.getEventsByVenueId(venueId);
		if(!event.isEmpty()) {
			ResponseStructure<List<Event>> response=new ResponseStructure<List<Event>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Sucessfull");
			response.setData(event);
			return new ResponseEntity<ResponseStructure<List<Event>>>(response, HttpStatus.OK);
		}else
		throw new NoRecordAvailableException("No record found with the Venue id : "+venueId);
	}
   ///get Venue By Location
     public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(String location){
		List<Venue> venue=venueDao.getVenueByLocation(location);
		ResponseStructure<List<Venue>> response=new ResponseStructure<List<Venue>>();
		if(!venue.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Venue is Getted based in location");
			response.setData(venue);
			return new ResponseEntity<ResponseStructure<List<Venue>>>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No Venue is Present");
		}
	}
   //Pagination and Sorting
   		public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(int pageNumber,int pageSize,String field){
   			ResponseStructure<Page<Venue>> response = new ResponseStructure<Page<Venue>>();
   			response.setStatusCode(HttpStatus.OK.value());
   			response.setMessage("Venue sorted based onn pagination");
   			response.setData(venueDao.getVenueByPaginationAndSorting(pageNumber, pageSize, field));
   			return new ResponseEntity<ResponseStructure<Page<Venue>>>(response,HttpStatus.OK);
   		}
   		
   		
    
   
}
