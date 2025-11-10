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
import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;

@RestController
public class EventController {
 @Autowired
 private EventService eventService;
//Event
	@PostMapping("/event")
	public ResponseEntity<ResponseStructure<Event>> createEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

	@GetMapping("/event")
	public ResponseEntity<ResponseStructure<List<Event>>> getAllEvent() {
		return eventService.getAllEvent();
	}

	@GetMapping("/event/{id}")
	public ResponseEntity<ResponseStructure<Optional<Event>>> getEventById(@PathVariable Integer id) {
		return eventService.getEventById(id);
	}

	@PutMapping("/event")
	public ResponseEntity<ResponseStructure<Event>> updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}

	@DeleteMapping("/event/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEvent(@PathVariable Integer id) {
		return eventService.deleteEvent(id);
	}

	@GetMapping("/org/event/{id}")
	public ResponseEntity<ResponseStructure<Optional<List<Event>>>> getEventByOrganizerId(@PathVariable Integer id) {
		return eventService.getEventByOrganizerId(id);
	}

	@GetMapping("/attendee/event/{id}")
	public ResponseEntity<ResponseStructure<List<Attendee>>> getAttendeeByEventId(@PathVariable Integer id) {
		return eventService.getAttendeeByEventId(id);
	}

	@GetMapping("/event/page/sort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Event>>> getEventByPaginationAndSorting(@PathVariable int pageNumber,
			@PathVariable int pageSize, @PathVariable String field) {
		return eventService.getEventByPaginationAndSorting(pageNumber, pageNumber, field);
	}

}
