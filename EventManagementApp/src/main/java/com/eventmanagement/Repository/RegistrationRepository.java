package com.eventmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	@Query("SELECT r FROM Registration r WHERE r.event.id = ?1")
	List<Registration> getRegistrationsByEventId(Integer eventId);
	
	List<Registration> findByAttendee(Attendee attendee);
}
