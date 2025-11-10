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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.service.VenueService;

@RestController
@RequestMapping("/eventmanagement")
public class VenueController {
	@Autowired
	private VenueService venueService;

	@PostMapping("/venue")
	public ResponseEntity<ResponseStructure<Venue>> saveVenue(@RequestBody Venue venue) {
		return venueService.addVenue(venue);
	}

	@GetMapping("/venue")
	public ResponseEntity<ResponseStructure<List<Venue>>> getAllVenue() {
		return venueService.getAllVenue();
	}

	@GetMapping("/venue/{id}")
	public ResponseEntity<ResponseStructure<Optional<Venue>>> getVenueById(@PathVariable Integer id) {

		return venueService.getVenueById(id);
	}

	@PutMapping("/venue")
	public ResponseEntity<ResponseStructure<Venue>> updateVenue(@RequestBody Venue venue) {
		return venueService.updateVenue(venue);
	}

	@DeleteMapping("/venue/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteVenue(@PathVariable Integer id) {
		return venueService.deleteVenue(id);
	}

	@GetMapping("/venue/event/{venueId}")
	public ResponseEntity<ResponseStructure<List<Event>>> getEventsByVenueId(@PathVariable Integer venueId) {
		return venueService.getEventByVenueId(venueId);
	}

	@GetMapping("/venue/location/{location}")
	public ResponseEntity<ResponseStructure<List<Venue>>> getVenueByLocation(@PathVariable String location) {
		return venueService.getVenueByLocation(location);
	}

	@GetMapping("/venue/page/sort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Venue>>> getVenueByPaginationAndSorting(@PathVariable int pageNumber,
			@PathVariable int pageSize, @PathVariable String field) {
		return venueService.getVenueByPaginationAndSorting(pageNumber, pageNumber, field);
	}

}
