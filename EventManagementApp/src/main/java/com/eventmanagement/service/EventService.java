package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Repository.OrganizerRepository;
import com.eventmanagement.Repository.VenueRepository;
import com.eventmanagement.dao.EventDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.exception.EventNotFoundException;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
	// Event
	// Create Event
	@Autowired
	private VenueRepository venueRepository;
	@Autowired
	private OrganizerRepository organizerRepository;
	Event event = new Event();

	public ResponseEntity<ResponseStructure<Event>> createEvent(Event event) {
		Integer venueId = event.getVenue().getId();
		Integer oraganizerId = event.getOrganizer().getId();
		Optional<Venue> opt = venueRepository.findById(venueId);
		Optional<Organizer> opt2 = organizerRepository.findById(oraganizerId);
		if (opt.isPresent())
			event.setVenue(opt.get());
		else
			throw new IdNotFoundException("Venue Id is Not Available in DB");

		if (opt2.isPresent())
			event.setOrganizer(opt2.get());
		else
			throw new IdNotFoundException("Organizer Id is Not Available in DB");

		ResponseStructure<Event> response = new ResponseStructure<Event>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Event is Successfully Created");
		response.setData(eventDao.createEvent(event));
		return new ResponseEntity<ResponseStructure<Event>>(response, HttpStatus.CREATED);
	}

//get all Events
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
		List<Event> event = eventDao.getAllEvent();
		ResponseStructure<List<Event>> response = new ResponseStructure<List<Event>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("The Event Details is Retrieved");
		response.setData(event);
		return new ResponseEntity<ResponseStructure<List<Event>>>(response, HttpStatus.OK);
	}

	// Get Event By Id
	public ResponseEntity<ResponseStructure<Optional<Event>>> getEventById(Integer id) {
		Optional<Event> opt = eventDao.getEventById(id);
		if (opt.isPresent()) {
			ResponseStructure<Optional<Event>> response = new ResponseStructure<Optional<Event>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("event retrived by id :" + id);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<Event>>>(response, HttpStatus.OK);
		} else
			throw new EventNotFoundException("No Event is found in DB");
	}

	// Update Event
	public ResponseEntity<ResponseStructure<Event>> updateEvent(Event event) {
		ResponseStructure<Event> response = new ResponseStructure<Event>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Event is updated Sucessfully");
		response.setData(eventDao.updateEvent(event));
		return new ResponseEntity<ResponseStructure<Event>>(response, HttpStatus.OK);
	}

	// Delete Event
	public ResponseEntity<ResponseStructure<String>> deleteEvent(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		eventDao.deleteEvent(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Evenet is Deleted");
		response.setData("Event id Deleted");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}

	// Get Event By Organizer Id
	public ResponseEntity<ResponseStructure<Optional<List<Event>>>> getEventByOrganizerId(Integer id) {
		Optional<List<Event>> opt = eventDao.getEventByOrganizerId(id);
		if (opt.isPresent()) {
			ResponseStructure<Optional<List<Event>>> response = new ResponseStructure<Optional<List<Event>>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Sucessfull");
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<List<Event>>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("No record found with the oragnizer id : " + id);
	}

	// Get attendee by event id
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(Integer id) {
		List<Attendee> attendee = eventDao.getAttendeeByEventId(id);
		if (attendee.size() > 0) {
			ResponseStructure<List<Attendee>> response = new ResponseStructure<List<Attendee>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Attendee is retrived by event id");
			response.setData(attendee);
			return new ResponseEntity<ResponseStructure<List<Attendee>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("NO Attende Record foun in the data base");
	}

	// Pagination and Sorting
	public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(int pageNumber, int pageSize,
			String field) {
		ResponseStructure<Page<Event>> response = new ResponseStructure<Page<Event>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Event sorted based on pagination");
		response.setData(eventDao.getEventByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Event>>>(response, HttpStatus.OK);
	}
}
