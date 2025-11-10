package com.eventmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.entity.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer>{
  
	Optional<Attendee> findByContact(Long contact);
}
