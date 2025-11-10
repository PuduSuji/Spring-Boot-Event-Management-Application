package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Repository.RegistrationRepository;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.exception.IdNotFoundException;

@Repository
public class RegistrationDao {
	@Autowired
	private RegistrationRepository registrationRepository;
	//REGISTRATION
			//Create Registartion
			public Registration createRegistration(Registration registration) {
				return registrationRepository.save(registration);
			}
			//Get All Registration 
			public List<Registration> getAllRegistrations(){
				return registrationRepository.findAll();
			}
			//Get Registration By Id
			public Optional<Registration> getRegistrationById(Integer id) {
				return registrationRepository.findById(id);
			}
			 //Cancel Registration
			public void cancelRegistration(Integer id) {
				Optional<Registration> opt = registrationRepository.findById(id);
				if(opt.isPresent()) {
				     registrationRepository.delete(opt.get());
				}else {
				throw new IdNotFoundException("Registration Id is not available in DB");
				}
			}
			//get Registration By Event
			public List<Registration> getRegistrationByEvent(Integer eventId){
				return registrationRepository.getRegistrationsByEventId(eventId);
			}
			//get Registration By Attendee
			public List<Registration> getRegistrationByAttendee(Attendee attendee){
				return registrationRepository.findByAttendee(attendee);
			}
}
