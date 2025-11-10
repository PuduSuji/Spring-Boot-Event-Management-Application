package com.eventmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer>{
    
	List<Venue> findByLocation(String location);
	
	@Query("SELECT e FROM Event e WHERE e.venue.id = :venueId")
	List<Event> getEventsByVenueId(Integer venueId);
}
