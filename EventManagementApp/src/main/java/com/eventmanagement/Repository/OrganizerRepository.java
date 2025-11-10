package com.eventmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer>{

}
