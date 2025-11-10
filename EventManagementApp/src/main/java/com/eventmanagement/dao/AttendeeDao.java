package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Repository.AttendeeRepository;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Repository
public class AttendeeDao {
	@Autowired
	private AttendeeRepository attendeeRepository;
	//ATTENDEE
		//Register Attendee
		public Attendee registerAttendee(Attendee attendee) {
			return attendeeRepository.save(attendee);
		}
		//Get All Attendee
		public List<Attendee> getAllAttendee(){
			return attendeeRepository.findAll();
		}
		//Get Attendee By Id
		public Optional<Attendee> getAttendeeById(Integer id) {
			return attendeeRepository.findById(id);
		}
		//Update Attendee
		public Attendee updateAttendee(Attendee attendee) {
			Optional<Attendee> opt =attendeeRepository.findById(attendee.getId());
			if(opt.isPresent())
			    return attendeeRepository.save(attendee);
			else
			throw new IdNotFoundException("Id is not available in DB");
		}
	    //Delete Attendee
		public void deleteAttendee(Integer id) {
			Optional<Attendee> opt = attendeeRepository.findById(id);
			if(opt.isPresent()) {
			     attendeeRepository.delete(opt.get());
			}else {
			throw new IdNotFoundException("Attendee Id is not available in DB");
			}
		}
		//get attendee By Contact
		public Optional<Attendee> getAttendeeByContact(Long contact){
			return attendeeRepository.findByContact(contact);
		}
		//Pagination and Sorting
			public Page<Attendee> getAttendeeByPaginationAndSorting(int pageNumber,int pageSize,String field){
				Page<Attendee> pages= attendeeRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).descending()));
				if(!pages.isEmpty())
					return pages;
				else
				throw new NoRecordAvailableException("No Attendee is available");

			}
}
