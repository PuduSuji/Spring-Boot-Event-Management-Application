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
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.service.OrganizerService;

@RestController
public class OrganizerController {
  @Autowired
  private OrganizerService organizerService;
//ORGANIZER
	@PostMapping("/organizer")
	public ResponseEntity<ResponseStructure<Organizer>> saveVenue(@RequestBody Organizer organizer) {
		return organizerService.addOrganizer(organizer);
	}

	@GetMapping("/organizer")
	public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer() {
		return organizerService.getAllOrganizer();
	}

	@GetMapping("/organizer/{id}")
	public ResponseEntity<ResponseStructure<Optional<Organizer>>> getOrganizerById(@PathVariable Integer id) {

		return organizerService.getOrganizerById(id);
	}

	@PutMapping("/organizer")
	public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(@RequestBody Organizer organizer) {
		return organizerService.updateOrganizer(organizer);
	}

	@DeleteMapping("/organizer/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteOrganizer(@PathVariable Integer id) {
		return organizerService.deleteOrganizer(id);
	}

	@GetMapping("/organizer/page/sort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizerByPaginationAndSorting(
			@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
		return organizerService.getOrganizereByPaginationAndSorting(pageNumber, pageNumber, field);
	}
}
