package com.eventmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.service.AttendeeService;

@RestController
public class AttendeeController {
  @Autowired
  private AttendeeService attendeeService;
//Attendee
	@PostMapping("/attendee")
	public ResponseEntity<ResponseStructure<Attendee>> createEvent(@RequestBody Attendee attendee) {
		return attendeeService.registerAttendee(attendee);
	}

	@GetMapping("/attendee")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee() {
		return attendeeService.getAllAttendee();
	}

	@GetMapping("/attendee/{id}")
	public ResponseEntity<ResponseStructure<Optional<Attendee>>> getAttendeeById(@PathVariable Integer id) {
		return attendeeService.getAttendeeById(id);
	}

	@PutMapping("/attendee")
	public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(@RequestBody Attendee attendee) {
		return attendeeService.updateAttendee(attendee);
	}

	@DeleteMapping("/attendee/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteAttendee(@PathVariable Integer id) {
		return attendeeService.deleteAttendee(id);
	}

	@GetMapping("/attendee/contact/{contact}")
	public ResponseEntity<ResponseStructure<Optional<Attendee>>> getAttendeeByContact(@PathVariable Long contact) {
		return attendeeService.getAttendeeByContact(contact);
	}

	@GetMapping("/attendee/page/sort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(
			@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
		return attendeeService.getAttendeeByPaginationAndSorting(pageNumber, pageNumber, field);
	}

}
