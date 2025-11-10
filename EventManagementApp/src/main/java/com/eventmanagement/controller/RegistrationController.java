package com.eventmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.service.RegistrationService;

@RestController
public class RegistrationController {
   @Autowired
   private RegistrationService registrationService;
// Registration
	@PostMapping("/registration")
	public ResponseEntity<ResponseStructure<Registration>> createEvent(@RequestBody Registration registration) {
		return registrationService.createRegistration(registration);
	}

	@GetMapping("/registration")
	public ResponseEntity<ResponseStructure<List<Registration>>> getAllRegistrations() {
		return registrationService.getAllRegistrations();
	}

	@GetMapping("/registration/{id}")
	public ResponseEntity<ResponseStructure<Optional<Registration>>> getRegistrationById(@PathVariable Integer id) {
		return registrationService.getRegistrationById(id);
	}

	@DeleteMapping("/registration/{id}")
	public ResponseEntity<ResponseStructure<String>> cancelRegistration(@PathVariable Integer id) {
		return registrationService.cancelRegistration(id);
	}

	@GetMapping("/registration/event/{eventId}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistartionByEventId(
			@PathVariable Integer eventId) {
		return registrationService.getRegistrationByEventId(eventId);
	}

	@GetMapping("/registration/attendee/{attendeeId}")
	public ResponseEntity<ResponseStructure<List<Registration>>> getRegistrationByAttendee(
			@PathVariable Integer attendeeId) {
		return registrationService.getRegistrationByAttendee(attendeeId);
	}

}
