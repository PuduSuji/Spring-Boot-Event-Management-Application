package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.Repository.AttendeeRepository;
import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.dao.RegistrationDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Service
public class RegistrationService {
	@Autowired
	private RegistrationDao registrationDao;
	// REGISTRATION
	// Create Registartion
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private AttendeeRepository attendeeRepository;
	Registration registration = new Registration();
	private Attendee attendee;

	public ResponseEntity<ResponseStructure<Registration>> createRegistration(Registration registration) {
		Integer eventId = registration.getEvent().getId();
		Integer attendeeId = registration.getAttendee().getId();

		Optional<Event> opt = eventRepository.findById(eventId);
		Optional<Attendee> opt2 = attendeeRepository.findById(attendeeId);
		if (opt.isPresent())
			registration.setEvent(opt.get());
		else
			throw new IdNotFoundException("Event Id is Not Available in DB");

		if (opt2.isPresent())
			registration.setAttendee(opt2.get());
		else
			throw new IdNotFoundException("Attendee Id is Not Available in DB");

		ResponseStructure<Registration> response = new ResponseStructure<Registration>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Registartion is Successfully Completed");
		response.setData(registrationDao.createRegistration(registration));
		return new ResponseEntity<ResponseStructure<Registration>>(response, HttpStatus.CREATED);
	}

//Get all Registration
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistrations() {
		List<Registration> registration = registrationDao.getAllRegistrations();
		ResponseStructure<List<Registration>> response = new ResponseStructure<List<Registration>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("The Registration Details is Retrieved");
		response.setData(registration);
		return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);
	}

	// Get Registration By Id
	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationById(Integer id) {
		Optional<Registration> opt = registrationDao.getRegistrationById(id);
		if (opt.isPresent()) {
			ResponseStructure<Optional<Registration>> response = new ResponseStructure<Optional<Registration>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Registration is retrived by id :" + id);
			response.setData(opt);
			return new ResponseEntity<ResponseStructure<Optional<Registration>>>(response, HttpStatus.OK);
		} else
			throw new IdNotFoundException("No Registration is Done in DB");
	}

	// Cancel Registration
	public ResponseEntity<ResponseStructure<String>> cancelRegistration(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		registrationDao.cancelRegistration(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Registration is Cancel");
		response.setData("Registration id Cancel");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}

	// Get Registration by Event
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByEventId(Integer eventId) {
		List<Registration> registration = registrationDao.getRegistrationByEvent(eventId);
		if (!registration.isEmpty()) {
			ResponseStructure<List<Registration>> response = new ResponseStructure<List<Registration>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("registration is retrived by event id");
			response.setData(registration);
			return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("NO Registration  found in the data base");
		}
	}

	// Get Registration By Attendee
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(Integer attendeeId) {
		Optional<Attendee> opt = attendeeRepository.findById(attendeeId);
		if (opt.isPresent()) {
			ResponseStructure<List<Registration>> response = new ResponseStructure<List<Registration>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("registration is retrived by event id");
			response.setData(registrationDao.getRegistrationByAttendee(opt.get()));
			return new ResponseEntity<ResponseStructure<List<Registration>>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Attendee id is Not Available in DB");
		}
	}

}
