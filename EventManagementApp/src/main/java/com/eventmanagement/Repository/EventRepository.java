package com.eventmanagement.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{
	@Query("SELECT e FROM Event e WHERE e.organizer.id = :organizerId")
	Optional<List<Event>> findEventByOrganizerId(Integer organizerId);
	
	@Query("SELECT r.attendee FROM Event e JOIN e.resgistrations r WHERE e.id = :eventId")
	List<Attendee> findAttendeeByEventId(Integer eventId);

}
